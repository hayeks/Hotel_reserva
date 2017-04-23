/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author JuanLuis
 */
public class Utilities {
    
    private static Connection cnn = null;
    
    public static DB getConection(){
        DB dbase = new DB("Proyecto2","postgres","JuanLH@21");
        cnn = dbase.getConection();
        return dbase;
    }

    public static Connection getCnn() {
        return cnn;
    }
    
    public static Dimension getScreenSize(){
       return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    /*public static coordinates getCenterLocation(javax.swing.JDialog frm){
        // Determine the new location of the window
        int w = frm.getSize().width;
        int h = frm.getSize().height;
        int x = (getScreenSize().width-w)/2;
        int y = (getScreenSize().height-h)/2;
        
        // Move the window
        return new coordinates(x,y);
        
    }*/
    
    public static Date getCurrentDate(){
        DB dbase = Utilities.getConection();
        Date date=null;
        String sql = "select * from current_date ";
        try{
            ResultSet rs = dbase.execSelect(sql);
            rs.next();
            date = rs.getDate(1);
            return date;
        }
        catch(SQLException e){
            System.out.println("Error de la base de datos -> "+e.getMessage());
            return date;
        }
        
        
    }
    
    
    
    public static int getMaxId(String tabla){
        DB dbase = Utilities.getConection();
        String sql = "select max(id) from "+tabla+";";
        try{
            ResultSet rs = dbase.execSelect(sql);
            rs.next();
            int id = rs.getInt(1)+1;
            rs.close();
                    
            return id;
            
        }
        catch(SQLException e){
            System.out.println("Bdd error"+e);
        }
        return -1;
    }
   
    
    public static void println(String line){
        System.out.println(line);
    }

    
    
    
}
