package com.proyect.ishop.convertic.service;

import com.proyect.ishop.convertic.domain.model.Cliente;
import com.proyect.ishop.convertic.domain.model.Producto;
import com.proyect.ishop.convertic.domain.model.TipoDocumento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    public List<Cliente> findAll();

    public Cliente findById(Long id);

    public Cliente save(Cliente cliente);

    public List<TipoDocumento> findAllTipoDocumento();

    //Optional<Object> findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);


    Boolean login(String correoElectronico, String contrasena) throws Exception;



}
