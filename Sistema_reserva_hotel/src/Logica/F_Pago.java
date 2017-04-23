/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;


import Datos.C_Pago;
import Presentacion.JFr_Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CARLOS
 */
public class F_Pago {
    
   private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
   public Integer totalregistros;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Idreserva","Comprobante","Número","Igv","Total","Fecha Emisión","Fecha Pago"};
       
       String [] registro =new String [8];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       ssql="select * from pago where id_reserva="+ buscar + " order by id_pago desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(ssql);
           
           while(rs.next()){
               registro [0]=rs.getString("id_pago");
               registro [1]=rs.getString("id_reserva");
               registro [2]=rs.getString("tipo_comprobacion");
               registro [3]=rs.getString("num_comprobacion");
               registro [4]=rs.getString("igv");
               registro [5]=rs.getString("total_pago");
               registro [6]=rs.getString("fecha_emision");
               registro [7]=rs.getString("fecha_pago");
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           Logger.getLogger(F_Pago.class.getName()).log(Level.SEVERE, null, e);
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (C_Pago dts){
       ssql="insert into pago (id_reserva,tipo_comprobacion,num_comprobacion,igv,total_pago,fecha_emision,fecha_pago)" +
               "values (?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(ssql);
           pst.setInt(1, dts.getIdreserva());
           pst.setString(2, dts.getTipo_comprobante());
           pst.setString(3, dts.getNum_comprobante());
           pst.setDouble(4, dts.getIgv());
           pst.setDouble(5, dts.getTotal_pago());
           pst.setDate(6, dts.getFecha_emision());
           pst.setDate(7, dts.getFecha_pago());
           
           
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
   
   public boolean editar (C_Pago dts){
       ssql="update pago set id_reserva=?,tipo_comprobante=?,num_comprobante=?,igv=?,total_pago=?,fecha_emision=?,fecha_pago=?"+
               " where id_pago=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(ssql);
           pst.setInt(1, dts.getIdreserva());
           pst.setString(2, dts.getTipo_comprobante());
           pst.setString(3, dts.getNum_comprobante());
           pst.setDouble(4, dts.getIgv());
           pst.setDouble(5, dts.getTotal_pago());
           pst.setDate(6, dts.getFecha_emision());
           pst.setDate(7, dts.getFecha_pago());
           
           pst.setInt(8, dts.getIdpago());
           
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
  
   public boolean eliminar (C_Pago dts){
       ssql="delete from pago where id_pago=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(ssql);
           
           pst.setInt(1, dts.getIdpago());
           
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
