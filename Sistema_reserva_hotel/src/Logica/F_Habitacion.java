/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.C_Habitacion;
import Presentacion.JFr_Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Logger;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//import java.usisg.table.DefaulfTableModel;
/**
 *
 * @author Deliberth
 */
public class F_Habitacion {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
    public Integer total_registro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulo = {"ID", "Numero", "Piso", "Descripcion", "Caracteristicas", "Precio", "Tipo_Habitacion", "Estado"};

        String[] registro = new String[8];

        total_registro = 0;
        modelo = new DefaultTableModel(null, titulo);
        ssql = "select * from habitacion where piso like '%" + buscar + "%' order by id_habitacion";

        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery(ssql);

            while (res.next()) {
                registro[0] = res.getString("id_habitacion");
                registro[1] = res.getString("numero");
                registro[2] = res.getString("piso");
                registro[3] = res.getString("descripcion");
                registro[4] = res.getString("caracteristica");
                registro[5] = res.getString("precio_diario");
                registro[6] = res.getString("tipo_habitacion");
                registro[7] = res.getString("estado");

                total_registro += 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            Logger.getLogger(F_Habitacion.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

    public boolean insertar(C_Habitacion dts) {
        ssql = "insert into habitacion (numero,piso,descripcion,caracteristica,precio_diario,tipo_habitacion,estado) values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);

            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristica());
            pst.setDouble(5, dts.getPresio_diario());
            pst.setString(6, dts.getTipo_habitacion());
            pst.setString(7, dts.getEstado());

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

    public boolean editar(C_Habitacion dts) {
        ssql = "update habitacion set numero=?,piso=?,descripcion=?,caracteristica=?,precio_diario=?,tipo_habitacion=?,estado=? where id_habitacion=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristica());
            pst.setDouble(5, dts.getPresio_diario());
            pst.setString(6, dts.getTipo_habitacion());
            pst.setString(7, dts.getEstado());
            pst.setInt(8, dts.getId_habitacion());

            System.out.println("QUery ----->  " + pst.toString());
            //int n = pst.executeUpdate();
            pst.executeUpdate();

            /*if(n != 0){
                return true;
            }else{
                return false;
            }*/
            return true;
        } catch (SQLException e) {
            //JOptionPane.showConfirmDialog(null, e);
            Logger.getLogger(F_Habitacion.class.getName()).log(Level.SEVERE, null, e);
            return false;

        }
    }

    public boolean eliminar(C_Habitacion dts) {
        ssql = "delete from habitacion where id_habitacion=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setInt(1, dts.getId_habitacion());

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

    public boolean desocupar(C_Habitacion dts) {
        ssql = "update habitacion set estado='Disponible'"
                + " where id_habitacion=?";
        //alt + 39

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);

            pst.setInt(1, dts.getId_habitacion());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean ocupar(C_Habitacion dts) {
        ssql = "update habitacion set estado='Ocupado'"
                + " where id_habitacion=?";
        //alt + 39

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);

            pst.setInt(1, dts.getId_habitacion());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public DefaultTableModel mostrarvista(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Estado", "Tipo habitación"};

        String[] registro = new String[8];

        total_registro = 0;
        modelo = new DefaultTableModel(null, titulos);

        ssql = "select * from habitacion where piso like '%" + buscar + "%' and estado='Disponible' order by id_habitacion";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(ssql);

            while (rs.next()) {
                registro[0] = rs.getString("id_habitacion");
                registro[1] = rs.getString("numero");
                registro[2] = rs.getString("piso");
                registro[3] = rs.getString("descripcion");
                registro[4] = rs.getString("caracteristica");
                registro[5] = rs.getString("precio_diario");
                registro[6] = rs.getString("estado");
                registro[7] = rs.getString("tipo_habitacion");

                total_registro = total_registro + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
