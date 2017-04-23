/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.C_Habitacion;
import Datos.C_Producto;
import Datos.C_Reserva;
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
public class F_Reserva {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
    public Integer total_registro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulo = {"ID", "ID_Habitacion", "Numero", "ID_Cliente","Cliente", "ID_Trabajador","Trabajador", "Tipo Reserva", "Fecha Reserva", "Fecha Ingreso", "Fecha Salida", "Costo", "Estado"};

        String[] registro = new String[13];

        total_registro = 0;
        modelo = new DefaultTableModel();
        for(int i = 0; i<titulo.length;i++)
            modelo.addColumn(titulo[i]);
        
        ssql = "select r.id_reserva,r.id_habitacion,h.numero,r.id_cliente,"+
               "(select nombre from persona where id_persona=r.id_cliente)as ncliente,"+
               "(select apaterno from persona where id_persona=r.id_cliente)as clienteap,"+
               "r.id_trabajador,(select nombre from persona where id_persona=r.id_trabajador)as trabajadorn,"+
               "(select apaterno from persona where id_persona=r.id_trabajador)as trabajadorap,"+
               "r.tipo_reserva,r.fecha_reserva,r.fec"
               + "ha_ingreso,r.fecha_salida,"+
               "r.costo_alojamiento,r.estado from reserva r inner join habitacion h on r.id_habitacion=h.id_habitacion where r.fecha_reserva like '%"+ buscar + "%' order by id_reserva desc";
        System.out.println("Query ---> "+ssql);
        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery(ssql);

            while (res.next()) {
                registro[0] = res.getString("id_reserva");
                registro[1] = res.getString("id_habitacion");
                registro[2] = res.getString("numero");
                registro[3] = res.getString("id_cliente");
                registro[4] = res.getString("ncliente") + " " + res.getString("clienteap") ;
                registro[5] = res.getString("id_trabajador");
                registro[6] = res.getString("trabajadorn") + " " + res.getString("trabajadorap");
                registro[7] = res.getString("tipo_reserva");
                registro[8] = res.getString("fecha_reserva");
                registro[9] = res.getString("fecha_ingreso");
                registro[10] = res.getString("fecha_salida");
                registro[11] = res.getString("costo_alojamiento");
                registro[12] = res.getString("estado");

                total_registro += 1;
                modelo.addRow(registro);
            }
            return modelo;

        } catch (SQLException e) {
            Logger.getLogger(F_Reserva.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }

    public boolean insertar(C_Reserva dts) {
        ssql = "insert into reserva (id_habitacion, id_cliente, id_trabajador,tipo_reserva,fecha_reserva,fecha_ingreso,fecha_salida,costo_alojamiento,estado) values (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setInt(1, dts.getId_habitacion());
            pst.setInt(2, dts.getId_cliente());
            pst.setInt(3, dts.getId_trabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setString(5, dts.getFecha_reserva().toString());
            pst.setString(6, dts.getFecha_ingreso().toString());
            pst.setString(7, dts.getFecha_salida().toString());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
             Logger.getLogger(F_Reserva.class.getName()).log(Level.SEVERE, null, e);   
            JOptionPane.showConfirmDialog(null, e);
            
            
            return false;
        }
    }

    public boolean editar(C_Reserva dts) {
        ssql = "update reserva set id_habitacion=?,id_cliente=?,id_trabajador=?,tipo_reserva=?,fecha_reserva=?,fecha_ingreso=?,fecha_salida=?,costo_alojamiento=?,estado=?"
                + " where id_reserva=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setInt(1, dts.getId_habitacion());
            pst.setInt(2, dts.getId_cliente());
            pst.setInt(3, dts.getId_trabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getFecha_reserva());
            pst.setDate(6, dts.getFecha_ingreso());
            pst.setDate(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());

            pst.setInt(10, dts.getId_reserva());

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

    public boolean eliminar(C_Reserva dts) {
        ssql = "delete from reserva where id_reserva=?";

        try {
            PreparedStatement pst = cn.prepareStatement(ssql);
            pst.setInt(1, dts.getId_reserva());

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
    
    public boolean pagar (C_Reserva dts){
       ssql="update reserva set estado='Pagada'"+
               " where id_reserva=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(ssql);
             
           
           pst.setInt(1, dts.getId_reserva());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
}
