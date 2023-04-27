/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MYDB;

/**
 *
 * @author sarra
 */
public class UserService {

    Connection cnx;

    public UserService() {
        cnx = MYDB.getInstance().getCnx();
    }

    public List<User> recupererById(int id) throws SQLException {
        List users = new ArrayList<User>();
        System.out.println( "user:"+id);
        String req = "select * from User where id = "+id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setLogin(rs.getString("login"));
            u.setRoles(rs.getString("roles"));
            u.setPassword(rs.getString("password"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setTelephone(rs.getString("telephone"));
            u.setBlocked(rs.getInt("blocked"));
            u.setTypeuser(rs.getString("typeuser"));
            u.setEmail(rs.getString("email"));
            
          users.add(u);
        }
        return users;
    }
}
