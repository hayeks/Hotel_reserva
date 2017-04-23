/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.C_Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Deliberth
 */
public class F_Cliente {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
    private String ssql2 = "";

    public Integer total_registro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulo = {"ID", "Nombre", "Apaterno", "Amaterno", "Doc", "Númeor de documento", "Dirección", "Teléfono", "Email", "Cóodigo"};

        String[] registro = new String[10];

        total_registro = 0;
        modelo = new DefaultTableModel(null, titulo);
        ssql = "select p.id_persona, p.nombre, p.apaterno, p.amaterno, p.tipo_documento,p.numero_documento, p.direccion, "
                + "p.telefono, p.email, c.codigo_cliente from persona p inner join cliente c on p.id_persona = c.id_persona "
                + "where numero_documento like '%" + buscar + "'order by id_persona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery(ssql);

            while (res.next()) {
                registro[0] = res.getString("id_persona");
                registro[1] = res.getString("nombre");
                registro[2] = res.getString("apaterno");
                registro[3] = res.getString("amaterno");
                registro[4] = res.getString("tipo_documento");
                registro[5] = res.getString("numero_documento");
                registro[6] = res.getString("direccion");
                registro[7] = res.getString("telefono");
                registro[8] = res.getString("email");
                registro[9] = res.getString("codigo_cliente");

                total_registro += 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(C_Cliente dts) {
        ssql = "insert into persona (nombre,apaterno,amaterno,tipo_documento,numero_documento,direccion,telefono,email) values (?,?,?,?,?,?,?,?)";
        ssql2 = "insert into cliente (id_persona,codigo_cliente) values ((select id_persona from persona order by id_persona desc limit 1),?)";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);

            PreparedStatement pst2 = cn.prepareStatement(ssql2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApe_paterno());
            pst.setString(3, dts.getApe_materno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setString(1, dts.getCodigo_cliente());

            int n = pst.executeUpdate();

            if (n != 0) {

                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(F_Cliente.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(C_Cliente dts) {
        ssql = "update persona set nombre=?,apaterno=?,amaterno=?,tipo_documento=?,numero_documento=?,direccion=?,telefono=?,email=? where id_persona=?";

        ssql2 = "update cliente set codigo_cliente=? where id_persona=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);

            PreparedStatement pst2 = cn.prepareStatement(ssql2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApe_paterno());
            pst.setString(3, dts.getApe_materno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getId_persona());

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getId_persona());

            int n = pst.executeUpdate();

            if (n != 0) {

                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {

            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(C_Cliente dts) {
        ssql = "delete from cliente where id_persona=?";
        ssql2 = "delete from persona where id_persona=?";

       try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            PreparedStatement pst2 = cn.prepareStatement(ssql2);

            pst.setInt(1, dts.getId_persona());
            pst2.setInt(1, dts.getId_persona());

            int n = pst.executeUpdate();

            if (n != 0) {

                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {

            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
