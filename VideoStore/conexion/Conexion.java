/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoStore.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sun.applet.Main;

/**
 *
 * @author GogoMix
 */
public class Conexion {
    private static String database = "proyecto_final";
    private static String login = "root";
    private static String password ="1234";
    private static String url = "jdbc:mysql://localhost/"+database;
    public static Connection connection = null;
    public static Statement stm;   
    public static ResultSet rs;
    public static PreparedStatement ps;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");            
            connection = DriverManager.getConnection(url, login, password);
            
            if(connection != null)
                System.out.println("Base de Datos " +database +" lista.");
        }
       catch(SQLException e){
            System.out.println(e);
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return connection;
    } 
    public static void main(String[] args){
        Conexion con = new Conexion();
        con.getConnection();
    }
}
