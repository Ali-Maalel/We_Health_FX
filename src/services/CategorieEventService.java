/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategorieEvenement;
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
public class CategorieEventService implements IServices<CategorieEvenement>{
Connection cnx;

    public CategorieEventService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(CategorieEvenement t) throws SQLException {
 String req = "INSERT INTO categorie_event (nom) VALUES(?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.executeUpdate();
    }

    @Override
    public void modifier(CategorieEvenement t) throws SQLException {
 String req = "update categorie_event set nom = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setInt(2, t.getId());
        ps.executeUpdate();    }

    @Override
    public boolean supprimer(CategorieEvenement t) throws SQLException {
  boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from categorie_event where id = ? ");
            req.setInt(1, t.getId());

            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    @Override
    public List<CategorieEvenement> recuperer() throws SQLException {   
        EvenementService artsr = new EvenementService ();
        List<CategorieEvenement> categories = new ArrayList<>();
        String req = "select * from categorie_event";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
              CategorieEvenement p = new CategorieEvenement();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            //p.setArticles(artsr.recupererByCategorie(p));
            categories.add(p);
        }
        return categories;
    }
    public List<CategorieEvenement> recupererById (CategorieEvenement t)throws SQLException{
        EvenementService artsr = new EvenementService ();
        List<CategorieEvenement> categories = new ArrayList<>();
        String req = " select * from categorie_event where id = "+t.getId();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            CategorieEvenement p = new CategorieEvenement();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            //p.setArticles(artsr.recupererByCategorie(p));

            categories.add(p);
        }
        return categories;
        
        
    }
 
    


    
   
}
