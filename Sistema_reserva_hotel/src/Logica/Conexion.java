/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Deliberth
 */
public class Conexion {

    static Connection contacto = null;
//     static Connection conect;

    public static Connection getConexion() {
//        try {
//            String connectionUrl = "jdbc:sqlserver://;database=Reserva_Hotel;integratedSecurity=true;";
//            conect = DriverManager.getConnection(connectionUrl);
//            System.out.println("Conectado.");
//        } catch (SQLException ex) {
//            System.out.println("Error.");
//        }

        //String url = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=Reserva_Hotel";
        String url = "jdbc:postgresql://localhost:5432/hoteleria";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion. Revisar el driver" + e.getMessage(), "Error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        try {
            contacto = DriverManager.getConnection(url, "postgres", "12345");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de conexion", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
        return contacto;
    }

    public static ResultSet Consulta(String consulta) {
        Connection con = getConexion();
        Statement declara;
        try {
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);

            return respuesta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de Conexion", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
