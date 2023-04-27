/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;

import entities.User;
import entities.Facture;
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
public class FactureService implements IServices<Facture> {

    Connection cnx;

    public FactureService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Facture t) throws SQLException {
        String req = "INSERT INTO facture (user_id,created_at,price,num) VALUES(?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getUser().getId());
        ps.setDate(2, t.getCreated_at());
        ps.setInt(3, t.getPrix());
        ps.setInt(4, t.getNum());

        ps.executeUpdate();

    }

    @Override
    public void modifier(Facture t) throws SQLException {
        String req = "update facture set user_id = ?, created_at = ?, price = ?, num = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getUser().getId());
        ps.setDate(2, t.getCreated_at());
        ps.setDate(2, t.getCreated_at());
        ps.setInt(2, t.getNum());

        ps.executeUpdate();
    }

    @Override
    public boolean supprimer(Facture t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from facture where id = ? ");
            req.setInt(1, t.getId());

            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    }

    @Override
    public List<Facture> recuperer() throws SQLException {
        List<Facture> factures = new ArrayList<>();
        String s = "select * from facture";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Facture r = new Facture();
            r.setId(rs.getInt("id"));
            r.setCreated_at(rs.getDate("created_at"));
            r.setPrix(rs.getInt("price"));
            r.setNum(rs.getInt("num"));

            factures.add(r);

        }
        return factures;
    }

    public List<Facture> getAll(int id) throws SQLException {
        List<Facture> factures = new ArrayList<>();
        String s = "select * from facture";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Facture r = new Facture();
            r.setId(rs.getInt("id"));
            r.setCreated_at(rs.getDate("created_at"));
            r.setPrix(rs.getInt("price"));
            r.setNum(rs.getInt("num"));

            if (r.getId() == id) {
                factures.add(r);
            }

        }
        return factures;
    }

}
