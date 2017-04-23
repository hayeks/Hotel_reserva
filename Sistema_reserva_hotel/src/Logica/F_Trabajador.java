/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import Datos.C_Persona;
import Datos.C_Trabajador;
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
public class F_Trabajador {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
    private String ssql2 = "";

    public Integer total_registro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulo = {"ID", "Nombre", "Apaterno", "Amaterno", "Doc", "Númeor de documento", "Dirección", "Teléfono", "Email", "Sueldo","Acceso","Login","Password","Estado"};

        String[] registro = new String[14];

        total_registro = 0;
        modelo = new DefaultTableModel(null, titulo);
        ssql = "select p.id_persona, p.nombre, p.apaterno, p.amaterno, p.tipo_documento,p.numero_documento, p.direccion,"
                + "p.telefono, p.email, t.sueldo,t.acceso,t.login,t.pass,t.estado from persona p inner join trabajador t on p.id_persona = t.id_persona "
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
                registro[9] = res.getString("sueldo");
                registro[10] = res.getString("acceso");
                registro[11] = res.getString("login");
                registro[12] = res.getString("pass");
                registro[13] = res.getString("estado");

                total_registro += 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(C_Trabajador dts) {
        ssql = "insert into persona (nombre,apaterno,amaterno,tipo_documento,numero_documento,direccion,telefono,email) values (?,?,?,?,?,?,?,?)";
        ssql2 = "insert into trabajador (id_persona,sueldo,acceso,login,pass,estado) values ((select id_persona from persona order by id_persona desc limit 1),?,?,?,?,?)";

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
                
            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());

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
            Logger.getLogger(F_Trabajador.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean editar(C_Trabajador dts) {
        ssql = "update persona set nombre=?,apaterno=?,amaterno=?,tipo_documento=?,numero_documento=?,direccion=?,telefono=?,email=? where id_persona=?";

        ssql2 = "update trabajador set sueldo=?,acceso=?,login=?,pass=?,estado=? where id_persona=?";

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

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getId_persona());

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

    public boolean eliminar(C_Trabajador dts) {
        ssql = "delete from trabajador where id_persona=?";
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
    
    public DefaultTableModel login(String login, String pass) {
        DefaultTableModel modelo;

        String[] titulo = {"ID", "Nombre", "Apaterno","Amaterno","Acceso","Login","Clave","Estado"};

        String[] registro = new String[8];

        total_registro = 0;
        modelo = new DefaultTableModel(null, titulo);
        ssql = "select p.id_persona, p.nombre, p.apaterno, p.amaterno, t.acceso,t.login,t.pass,t.estado "
                +"from persona p inner join trabajador t on p.id_persona = t.id_persona "
                + "where t.login='" + login + "' and t.pass= '"+ pass +"' and t.estado='Activado'  ";
//and t.estado=A 
        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery(ssql);
            System.out.println(ssql);
            while (res.next()) {
                registro[0] = res.getString("id_persona");
                registro[1] = res.getString("nombre");
                registro[2] = res.getString("apaterno");
                registro[3] = res.getString("amaterno");
                registro[4] = res.getString("acceso");
                registro[5] = res.getString("login");
                registro[6] = res.getString("pass");
                registro[7] = res.getString("estado");
                C_Persona pers = new C_Persona();
                
                pers.setId_persona(Integer.parseInt(res.getString("id_persona")));
                pers.setNombre(res.getString("nombre"));
                pers.setApe_paterno(res.getString("apaterno"));
                C_Persona.setPersona(pers);
                total_registro += 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (SQLException e) {
            Logger.getLogger(F_Trabajador.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
