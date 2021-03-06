/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Conexion.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Model.Producto;



public class ProductoDAO {
    
 public void create(Producto p) {
        
        Connection con =  Connect.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO productos (nombre,codigo,precio,cantidad,fecha_vencimiento)VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCodigo());
            stmt.setString(3, p.getPrecio());
            stmt.setInt(4, p.getCantidad());
            stmt.setString(5, p.getFecha_vencimiento());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Guardado con exito!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Connect.closeConnection(con, stmt);
        }

    }

    public List<Producto> read() {
        Connection con = Connect.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Producto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM productos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Producto produto = new Producto();

                produto.setId(rs.getInt("id"));
                produto.setNombre(rs.getString("nombre"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setPrecio(rs.getString("precio"));
                produto.setCantidad(rs.getInt("cantidad"));
                produto.setFecha_vencimiento(rs.getString("fecha_vencimiento"));

                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    
    public List<Producto> readForDesc(String desc) {

        Connection con = Connect.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Producto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM productos WHERE nombre LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Producto produto = new Producto();

                produto.setId(rs.getInt("id"));
                produto.setNombre(rs.getString("nombre"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setPrecio(rs.getString("precio"));
                produto.setCantidad(rs.getInt("cantidad"));
                produto.setFecha_vencimiento(rs.getString("fecha_vencimiento"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.closeConnection(con, stmt, rs);
        }

        return produtos;

    }

    public void update(Producto p) {

        Connection con = Connect.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE productos SET nombre = ? ,codigo = ? ,precio = ? ,cantidad = ?,fecha_vencimiento=? WHERE id = ?");
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCodigo());
            stmt.setString(3, p.getPrecio());
            stmt.setInt(4, p.getCantidad());
            stmt.setString(5, p.getFecha_vencimiento());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Actualizado con exito!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex);
        } finally {
            Connect.closeConnection(con, stmt);
        }

    }
    public void delete(Producto p) {

        Connection con = Connect.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM productos WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Borrado con exito!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar: " + ex);
        } finally {
            Connect.closeConnection(con, stmt);
        }

    }

    public Producto readById(int dd) {
        Connection con = Connect.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = new Producto();

        try {
            stmt = con.prepareStatement("SELECT * FROM productos WHERE id=?");
            stmt.setInt(1, dd);

            rs = stmt.executeQuery();
            rs.next();

            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setCodigo(rs.getString("codigo"));
            producto.setPrecio(rs.getString("precio"));
            producto.setCantidad(rs.getInt("cantidad"));
            producto.setFecha_vencimiento(rs.getString("fecha_vencimiento"));

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.closeConnection(con, stmt, rs);
        }

        return producto;
    }
}
