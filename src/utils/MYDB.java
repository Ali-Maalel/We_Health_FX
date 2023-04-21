/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Skand
 */
public class MYDB {

    private String url = "jdbc:mysql://localhost:3306/wehealth2";
    private String user = "root";
    private String password = "";
    
    private Connection cnx;
    
    private static MYDB instance;

    private MYDB() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static MYDB getInstance(){
        if(instance == null)
            instance = new MYDB();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
}
