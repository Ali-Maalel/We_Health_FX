/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Num_media;
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
 * @author yasmi
 */
public class Num_mediaService implements IService<Num_media> {
     Connection cnx;

    public Num_mediaService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public Num_media ajouter(Num_media t) throws SQLException {
        String req = "insert into Num_media(nom,nom_fichier) values('" + t.getNom().replace("\\", "\\\\") + "','" + t.getNom_fichier().replace("\\", "\\\\") + "')";
        Statement st = cnx.createStatement();
        int affectedRows = st.executeUpdate(req,st.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = st.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                t.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        return t;
    }


    @Override
    public void modifier(Num_media t) throws SQLException {
        String req = "update Num_media set nom = ? , nom_fichier = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getNom_fichier());
        ps.setInt(3, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Num_media t) throws SQLException {
       String req = "delete from Num_media where id = "+t.getId();
         //String req1 = "delete from categorie where id = ?";
         Statement st = cnx.createStatement();
         /*PreparedStatement ps = cnx.prepareStatement(req1);
         ps.setInt(1,t.getId());*/
         st.executeUpdate(req);
    }

    @Override
    public List<Num_media> recuperer() throws SQLException {
        List<Num_media> medias = new ArrayList<>();
        String req = "select * from num_media";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Num_media p = new Num_media();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setNom(rs.getString("nom_fichier"));
          
           medias.add(p);
        }
        return medias;
    }
     public List<Num_media> recupererById(int t) throws SQLException {
        List<Num_media> medias = new ArrayList<>();
        String req = " select * from num_media where id = "+t;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Num_media p = new Num_media();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setNom_fichier(rs.getString("nom_fichier"));
          
           medias.add(p);
        }
        return medias;
    }
    
    
}
