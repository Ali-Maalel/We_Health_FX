/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Participants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import utils.MYDB;

/**
 *
 * @author sarra
 */
public class ParticipantsService implements IServices<Participants> {
Connection cnx;

    public ParticipantsService() {
        cnx = MYDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Participants t) throws SQLException {
    String req = "INSERT INTO evennement_user (evennement_id,user_id) VALUES(?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getEvent().getId());
        ps.setInt(2, t.getUser().getId());
        ps.executeUpdate();
    }

    @Override
    public void modifier(Participants t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer(Participants t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
