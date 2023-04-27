/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategorieEvenement;
import entities.User;
import entities.Evenement;
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
public class EvenementService implements IServices<Evenement> {

    Connection cnx;

    public EvenementService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Evenement t) throws SQLException {
        String req = "INSERT INTO evennement (titre,description,consultationurl,date_debut,date_fin,all_day,reservations,max,image,prix,categorie_evenement_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getTitre());
        ps.setString(2, t.getDescription());
        ps.setString(3, t.getConsultationurl());
        ps.setDate(4, t.getDate_debut());
        ps.setDate(5, t.getDate_fin());
        ps.setBoolean(6, t.isAll_day());
        ps.setInt(7, t.getReservations());
        ps.setInt(8, t.getMax());
        ps.setString(9, t.getImage());
        ps.setInt(10, t.getPrix());
        ps.setInt(11, t.getCategorie().getId());

        ps.executeUpdate();

    }

    @Override
    public void modifier(Evenement t) throws SQLException {
        String req = "update evennement set titre = ?, description = ?, consultationurl = ?, date_debut = ?, date_fin = ?, all_day=?,max=?,image=?,prix=?,categorie_evenement_id=?,reservations=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getTitre());
        ps.setString(2, t.getDescription());
        ps.setString(3, t.getConsultationurl());
        ps.setDate(4, t.getDate_debut());
        ps.setDate(5, t.getDate_fin());
        ps.setBoolean(6, t.isAll_day());
        ps.setInt(7, t.getMax());
        ps.setString(8, t.getImage());
        ps.setInt(9, t.getPrix());
        ps.setInt(10, t.getCategorie().getId());
        ps.setInt(11, t.getReservations());
        ps.setInt(12, t.getId());
        ps.executeUpdate();

    }

    @Override
    public boolean supprimer(Evenement t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from evennement where id = ? ");
            req.setInt(1, t.getId());

            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    @Override
    public List<Evenement> recuperer() throws SQLException {
        List<Evenement> events = new ArrayList<>();
        CategorieEventService catsr = new CategorieEventService();
        String s = "select * from evennement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Evenement r = new Evenement();
            r.setId(rs.getInt("id"));
            r.setTitre(rs.getString("titre"));
            r.setDescription(rs.getString("description"));
            r.setConsultationurl(rs.getString("consultationurl"));
            r.setDate_debut(rs.getDate("date_debut"));
            r.setDate_fin(rs.getDate("date_fin"));
            r.setAll_day(rs.getBoolean("all_day"));
            r.setReservations(rs.getInt("reservations"));
            r.setMax(rs.getInt("max"));
            r.setImage(rs.getString("image"));
            r.setPrix(rs.getInt("prix"));
            r.setCategorie(catsr.recupererById(
                    new CategorieEvenement(rs.getInt("categorie_evenement_id"))
            ).get(0));

            events.add(r);

        }
        return events;
    }

    public List<Evenement> recupererById(int id) throws SQLException {
        List<Evenement> events = new ArrayList<>();
        String req = "select * from personne where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Evenement e = new Evenement();
            e.setId(rs.getInt("id"));
            e.setTitre(rs.getString("titre"));
            e.setDescription(rs.getString("description"));
            e.setDate_debut(rs.getDate("date_debut"));
            e.setDate_fin(rs.getDate("date_fin"));
            e.setAll_day(rs.getBoolean("all_day"));
            e.setReservations(rs.getInt("reservations"));
            e.setMax(rs.getInt("max"));
            e.setImage(rs.getString("image"));
            e.setPrix(rs.getInt("prix"));

            events.add(e);
        }
        return events;
    }

    public List<User> recupererByIduser(int id) throws SQLException {
        List<User> participants = new ArrayList<User>();
        String req = "SELECT * FROM `evennement_user where evennement_id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        UserService us = new UserService();
        while (rs.next()) {
            int user_id = rs.getInt("user_id");
            User u = us.recupererById(user_id).get(0);
            participants.add(u);
        }
        return participants;
    }
     public List<Evenement> recupererByCategorie(CategorieEvenement t) throws SQLException {
        CategorieEventService catsr = new CategorieEventService();
        List<Evenement> articles = new ArrayList<>();
        String req = "SELECT * FROM evennement where categorie_evenement_id = ? ";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, t.getId());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Evenement e = new Evenement();
             e.setId(rs.getInt("id"));
            e.setTitre(rs.getString("titre"));
            e.setDescription(rs.getString("description"));
            e.setDate_debut(rs.getDate("date_debut"));
            e.setDate_fin(rs.getDate("date_fin"));
            e.setAll_day(rs.getBoolean("all_day"));
            e.setReservations(rs.getInt("reservations"));
            e.setMax(rs.getInt("max"));
            e.setImage(rs.getString("image"));
            e.setPrix(rs.getInt("prix"));
            e.setCategorie(t);
            articles.add(e);
        }
        return articles;

    }
}
