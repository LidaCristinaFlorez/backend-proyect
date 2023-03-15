package com.proyect.ishop.convertic.service;

import com.proyect.ishop.convertic.domain.model.Cliente;
import com.proyect.ishop.convertic.domain.model.Producto;
import com.proyect.ishop.convertic.domain.model.TipoDocumento;
import com.proyect.ishop.convertic.repository.IClienteDao;
import com.proyect.ishop.convertic.repository.IProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteDao clienteDao;



    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumento> findAllTipoDocumento() {
        return clienteDao.findAllTipoDocumento();
    }


    @Override
    @Transactional(readOnly = true)
    public Boolean login(String correoElectronico, String contrasena) throws Exception {
        try{
        Cliente cliente = clienteDao.findByCorreoElectronicoAndContrasena(correoElectronico, contrasena);
        return cliente != null;
    }catch (Exception e){
        throw new Exception(e.getMessage());
        }
    }





}
