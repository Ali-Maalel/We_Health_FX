/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
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
public class CategorieService implements IService<Categorie>{
     Connection cnx;

    public CategorieService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public Categorie ajouter(Categorie t) throws SQLException {
        String req = "insert into categorie(nom) values('" + t.getNom() + "')";
        Statement st = cnx.createStatement();
        int affectedRows=st.executeUpdate(req,st.RETURN_GENERATED_KEYS);
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
    public void modifier(Categorie t) throws SQLException {
        String req = "update Categorie set nom = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setInt(2, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Categorie t) throws SQLException {
         String req = "delete from Categorie where id = "+t.getId();
         //String req1 = "delete from categorie where id = ?";
         Statement st = cnx.createStatement();
         /*PreparedStatement ps = cnx.prepareStatement(req1);
         ps.setInt(1,t.getId());*/
         st.executeUpdate(req);
       
    }

     @Override
    public List<Categorie> recuperer() throws SQLException {
        ArticleService artsr = new ArticleService ();
        List<Categorie> categories = new ArrayList<>();
        String req = "select * from Categorie";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Categorie p = new Categorie();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            //p.setArticles(artsr.recupererByCategorie(p));
            categories.add(p);
        }
        return categories;
    }
    
    public List<Categorie>recupererById (Categorie t)throws SQLException{
        ArticleService artsr = new ArticleService ();
        List<Categorie> categories = new ArrayList<>();
        String req = " select * from categorie where id = "+t.getId();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Categorie p = new Categorie();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            //p.setArticles(artsr.recupererByCategorie(p));

            categories.add(p);
        }
        return categories;
        
        
    }
    
    
            
}
