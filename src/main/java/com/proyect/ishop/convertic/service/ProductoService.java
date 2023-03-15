package com.proyect.ishop.convertic.service;

import com.proyect.ishop.convertic.domain.model.Producto;
import com.proyect.ishop.convertic.repository.IProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoDao productoDao;


    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findProductoByNombre(String termino) {
        return productoDao.findProductoByDescripcionContainingIgnoreCase(termino);
    }

    @Override
    public List<Producto> encontrarTermino(String termino, String descripcion) {

        try {

            List<Producto> productoList = productoDao.EncontrarPorCategoria(termino, descripcion);

            for (Producto producto : productoList) {
                contMasBuscados(producto);

            }
            return productoList;
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public List<Producto> buscarProductoPorGenero(String descripcion) {
        return productoDao.findProductoByCategoriaDescripcion(descripcion);
    }

    @Override
    public List<Producto> buscarGenero(String descripcion) {
        return productoDao.getByCategoria(descripcion);
    }

    @Override
    public void contMasBuscados(Producto producto) throws Exception {
        producto.setContBusqueda(producto.getContBusqueda()+1);
        productoDao.save(producto);
    }

    @Override
    public List<Producto> getMasBuscados() throws Exception {
        return productoDao.findTop4ByOrderByContBusqueda();
    }









    /*@Override
    public List<Producto> getByCategoria(String descripcion) {
        return productoDao.getByCategoria(descripcion);
    }*/











    /*@Override
    @Transactional(readOnly = true)
    public List<Producto> getByIdCategoria(Long id_categoria) {
        return productoDao.getByIdCategoria(id_categoria);
    }*/


}
