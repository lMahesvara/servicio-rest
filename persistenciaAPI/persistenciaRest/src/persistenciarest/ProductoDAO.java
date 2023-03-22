/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistenciarest;

import entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tonyd
 */
public class ProductoDAO {
    private String url = "jdbc:mysql://localhost:3306/productos?useSSL=false&allowPublicKeyRetrieval=true";     
    private String usuario = "root";
    private String contraseña = "1234";
    private Connection conexion;
   
    // Constructor que establece la conexión a la base de datos
    public ProductoDAO() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion = DriverManager.getConnection(this.url, this.usuario,this.contraseña);
    }

    // Método para cerrar la conexión a la base de datos
    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
    

    // Método para crear un nuevo producto en la base de datos
    public Producto crearProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO producto (nombre, descripcion, precio) VALUES (?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecio());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            producto.setId(rs.getInt(1));
        }
        ps.close();
        rs.close();
        return producto;
    }

    // Método para obtener un producto de la base de datos por su ID
    public Producto obtenerProducto(int id) throws SQLException {
        String query = "SELECT * FROM producto WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Producto producto = null;
        if (rs.next()) {
            producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
        }
        rs.close();
        ps.close();
        return producto;
    }

    // Método para actualizar un producto en la base de datos
    public void actualizarProducto(Producto producto) throws SQLException {
        String query = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ? WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecio());
        ps.setInt(4, producto.getId());
        ps.executeUpdate();
        ps.close();
    }

    // Método para eliminar un producto de la base de datos por su ID
    public void eliminarProducto(int id) throws SQLException {
        String query = "DELETE FROM producto WHERE id = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
    
    public List<Producto> obtenerProductos() throws SQLException {
    String query = "SELECT * FROM producto";
    Statement stmt = conexion.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    List<Producto> productos = new ArrayList<>();
    while (rs.next()) {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        productos.add(producto);
    }
    rs.close();
    stmt.close();
    return productos;
}
}
