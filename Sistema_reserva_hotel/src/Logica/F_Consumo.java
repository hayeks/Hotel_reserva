
package Logica;


import Datos.C_Consumo;
import static Logica.Conexion.getConexion;
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
public class F_Consumo {
    
   private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConexion();
    private String ssql = "";
   public Integer totalregistros;
   public Double totalconsumo;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","ID_Reserva","ID_Producto","Producto","Cantidad","Precio Venta","Estado"};
       
       String [] registro =new String [7];
       
       totalregistros=0;
       totalconsumo=0.0;
       modelo = new DefaultTableModel(null,titulos);
       
       ssql="select c.id_consumo,c.id_reserva,c.id_producto,p.nombre,c.cantidad,c.precio_venta "
               + ",c.estado from consumo c inner join producto p on c.id_producto=p.id_producto"
               + " where c.id_reserva ="+ buscar + " order by c.id_consumo desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(ssql);
           
           while(rs.next()){
               registro [0]=rs.getString("id_consumo");
               registro [1]=rs.getString("id_reserva");
               registro [2]=rs.getString("id_producto");
               registro [3]=rs.getString("nombre");
               registro [4]=rs.getString("cantidad");
               registro [5]=rs.getString("precio_venta");
               registro [6]=rs.getString("estado");
               
               totalregistros=totalregistros+1;
               totalconsumo=totalconsumo + (rs.getDouble("cantidad") * rs.getDouble("precio_venta") );
               
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           Logger.getLogger(F_Consumo.class.getName()).log(Level.SEVERE, null, e);
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (C_Consumo dts){
       ssql="insert into consumo (id_reserva,id_producto,cantidad,precio_venta,estado)" +
               "values (?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(ssql);
           pst.setInt(1, dts.getIdreserva());
           pst.setInt(2, dts.getIdproducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecio_venta());
           pst.setString(5, dts.getEstado());
           
           
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
   
   public boolean editar (C_Consumo dts){
       ssql="update consumo set id_reserva=?,id_producto=?,cantidad=?,precio_venta=?,estado=?"+
               " where id_consumo=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(ssql);
           pst.setInt(1, dts.getIdreserva());
           pst.setInt(2, dts.getIdproducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecio_venta());
           pst.setString(5, dts.getEstado());
           
           pst.setInt(6, dts.getIdconsumo());
           
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
  
   public boolean eliminar (C_Consumo dts){
       ssql="delete from consumo where id_consumo=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(ssql);
           
           pst.setInt(1, dts.getIdconsumo());
           
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
