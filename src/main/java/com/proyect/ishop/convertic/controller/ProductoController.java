package com.proyect.ishop.convertic.controller;


import com.proyect.ishop.convertic.domain.model.Cliente;
import com.proyect.ishop.convertic.domain.model.Producto;
import com.proyect.ishop.convertic.service.IClienteService;
import com.proyect.ishop.convertic.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;


    @GetMapping("/filtrar/{termino}")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> filtrarProductos(@PathVariable String termino){
        return productoService.findProductoByNombre(termino);
    }

   @GetMapping("/buscar/{descripcion}")
    public ResponseEntity<?> buscarCategoria(@PathVariable String descripcion) {


        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarProductoPorGenero(descripcion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ERROR.no se encontro productos}");
        }
    }

    @GetMapping("/traer/{descripcion}")
    public ResponseEntity<?> buscarGenero(@PathVariable String descripcion) {


        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarProductoPorGenero(descripcion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ERROR.no se encontro productos}");
        }
    }

    @GetMapping("/encontrar/{termino}/{descripcion}")
    public ResponseEntity<?> encontrarCategoria(@PathVariable String termino, @PathVariable String descripcion) {


        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.encontrarTermino(termino, descripcion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ERROR.no se encontro productos}");
        }
    }

    @GetMapping
    public ResponseEntity<?> ProductosMasBuscados() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.getMasBuscados());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Error. no se encuentran productos\"}");
        }
    }

    @GetMapping("/producto")
    public List<Producto> index(){
        return productoService.findAll();
    }

}
