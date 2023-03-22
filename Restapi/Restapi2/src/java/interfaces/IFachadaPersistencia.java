/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tonyd
 */
public interface IFachadaPersistencia {
    public Producto getProductoById(int id);
    public List<Producto> getProductos();
    public void deleteProducto(int id) throws SQLException;
    public Producto actualizarProducto(Producto p);
    public Producto addProduct(Producto p);
}
