package com.proyect.ishop.convertic.service;

import com.proyect.ishop.convertic.domain.model.Cliente;
import com.proyect.ishop.convertic.domain.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();
    public List<Producto> findProductoByNombre(String termino);

    //public List<Producto> getByIdCategoria(Long id_categoria);

    public List<Producto> encontrarTermino(String termino, String descripcion);

    public List<Producto> buscarProductoPorGenero(String descripcion);

    public List<Producto> buscarGenero(String descripcion);

    void contMasBuscados(Producto producto) throws Exception;

    List<Producto> getMasBuscados() throws Exception;

    //void contMasBuscados (Producto producto) throws Exception;
    //List<Producto> getMasBuscados()throws Exception;

}
