package com.proyect.ishop.convertic.repository;

import com.proyect.ishop.convertic.domain.model.Cliente;

import com.proyect.ishop.convertic.domain.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    @Query("from TipoDocumento")
    public List<TipoDocumento> findAllTipoDocumento();


    Cliente findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
}
