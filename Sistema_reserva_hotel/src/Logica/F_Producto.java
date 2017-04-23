/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.C_Habitacion;
import Datos.C_Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Deliberth
 */
public class F_Producto {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
    public Integer total_registro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulo = {"ID", "Producto", "Descripcion", "Unidad de medida", "Precio de venta"};

        String[] registro = new String[5];

        total_registro = 0;
        modelo = new DefaultTableModel(null, titulo);
        ssql = "select * from producto where nombre like '%" + buscar + "%' order by id_producto desc";

        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery(ssql);

            while (res.next()) {
                registro[0] = res.getString("id_producto");
                registro[1] = res.getString("nombre");
                registro[2] = res.getString("descripcion");
                registro[3] = res.getString("unidad_medida");
                registro[4] = res.getString("precio_venta");

                total_registro += 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(C_Producto dts) {
        ssql = "insert into producto (nombre,descripcion,unidad_medida,precio_venta) values (?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {

            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(C_Producto dts) {
        ssql = "update producto set nombre=?,descripcion=?,unidad_medida=?,precio_venta=? where id_producto=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());

            pst.setInt(5, dts.getId_producto());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;

        }
    }

    public boolean eliminar(C_Producto dts) {
        ssql = "delete from producto where id_producto=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setInt(1, dts.getId_producto());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
