/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Producto;
import interfaces.IFachadaPersistencia;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistenciarest.ProductoDAO;

/**
 *
 * @author tonyd
 */
public class FachadaPersistencia implements IFachadaPersistencia {

    ProductoDAO persistencia;

    public FachadaPersistencia() throws SQLException {
        persistencia = new ProductoDAO();
    }

    @Override
    public Producto getProductoById(int id) {
        try {
            return persistencia.obtenerProducto(id);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return null;
    }

    @Override
    public List<Producto> getProductos() {
        try {
            return persistencia.obtenerProductos();
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return null;
    }

    @Override
    public void deleteProducto(int id) throws SQLException{
        try {
            persistencia.eliminarProducto(id);
        } catch (SQLException ex) {
            throw new SQLException();
        }
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        try {
            persistencia.actualizarProducto(producto);
            return producto;
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return null;
    }

    @Override
    public Producto addProduct(Producto p) {
        try {
            return persistencia.crearProducto(p);
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return null;
    }

}
