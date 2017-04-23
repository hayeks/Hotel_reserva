/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edwin
 */
public class DB {
    
    public static Connection conexion = null;

  // "jdbc:postgresql://localhost/northwind"
   public DB(String db,String user,String pass)   {
        String url = "jdbc:postgresql://localhost:5432/"+db;
        
        try { 
            Class.forName("org.postgresql.Driver");
            conexion=DriverManager.getConnection(url,user,pass);
        } catch (SQLException e){
            //throw new SQLException(exc);
            System.err.println("** Error de Base de datos **\n"+e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
   public Connection getConection(){
       return conexion;
   }
    
    public ResultSet execSelect(String sql) throws SQLException
    {   
         try 
         {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = comando.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
           // Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException(ex);
            //System.err.println("** Error de Base de datos en el select **\n"+ex.getMessage());
            //return null;
        }
    }
        
    public void executeQuery(String query) throws SQLException
       {
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
             throw new SQLException(ex);
           // Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
           // System.err.println("** Error de Base de datos **\n"+ex.getMessage());
        }
    }

    public void CerrarConexion() {
        try {

            conexion.close();
            conexion = null;
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  String comilla(String st)
    {
       return "'"+st+"'"; 
    }
  
            
    
    
}
