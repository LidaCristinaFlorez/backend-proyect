package com.proyect.ishop.convertic.repository;

import com.proyect.ishop.convertic.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductoDao extends JpaRepository<Producto, Long> {

    /*@Query("select p from Producto p where p.descripcion like ?1")
    public List<Producto> findByNombre(String termino);*/

    public List<Producto> findProductoByDescripcionContainingIgnoreCase(String termino);

    @Query("select p from Producto p where (p.descripcion like %?1% or p.color.descripcion like %?1% or p.marca.descripcion like %?1%) and p.categoria.descripcion=?2")
    public List<Producto> EncontrarPorCategoria(String termino, String descripcion);

    @Query("select p from Producto p where p.categoria.descripcion =?1")
    public List<Producto> findProductoByCategoriaDescripcion(String descripcion);

    @Query("from Producto p where p.categoria.descripcion =: descripcion")
    public List<Producto> getByCategoria(String descripcion);

    public List<Producto> findTop4ByOrderByContBusqueda();



}
