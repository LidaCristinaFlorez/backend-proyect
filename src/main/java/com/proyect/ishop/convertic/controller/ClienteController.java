package com.proyect.ishop.convertic.controller;

import com.proyect.ishop.convertic.domain.model.Cliente;
import com.proyect.ishop.convertic.domain.model.TipoDocumento;
import com.proyect.ishop.convertic.service.ExceptionCliente;
import com.proyect.ishop.convertic.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index(){
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
            Cliente cliente = null;
            Map<String, Object> response = new HashMap<>();

        try{
             cliente = clienteService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error en la consulta de base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
         if(cliente ==null){
            response.put("mensaje", "El cliente no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?>  create(@Valid @RequestBody Cliente cliente, BindingResult result){
        Cliente clientenuevo = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){

            List<String> errors = result.getFieldErrors()
                            .stream()
                                    .map(err-> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                                            .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try{
            clientenuevo = clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al insertar cliente en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje","El cliente ha sido creado con éxito!");
        response.put("cliente", clientenuevo);
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @GetMapping("/clientes/tipoDocumento")
    public List<TipoDocumento> listarTipoDocumento(){
        return clienteService.findAllTipoDocumento();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente cliente) {

        try {
            Boolean login = clienteService.login(cliente.getCorreoElectronico(), cliente.getContrasena());
            return new ResponseEntity<>(login, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);



        }


    }





}
