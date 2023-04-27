/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author sarra
 */
public class Facture {
    
    private int id;
    private Date created_at;
    private int prix;
    private int num;
    private User user;
    private List<Evenement> evenements;
    public Facture() {
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", created_at=" + created_at + ", prix=" + prix + ", num=" + num + ", user=" + user + ", evenements=" + evenements + '}';
    }

    public Facture(int id, Date created_at, int prix, int num, User user_id) {
        this.id = id;
        this.created_at = created_at;
        this.prix = prix;
        this.num = num;
        this.user = user_id;
    }
    
    public Facture(Date created_at, int prix, int num, User user_id) {
        this.created_at = created_at;
        this.prix = prix;
        this.num = num;
        this.user = user_id;
    }

    public int getId() {
        return id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public int getPrix() {
        return prix;
    }

    public int getNum() {
        return num;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    
    
    
}
