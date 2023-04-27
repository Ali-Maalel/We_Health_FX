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
public class Evenement {
    private int id;
    private String titre;
    private String description;
    private String consultationurl;
    private Date date_debut;
    private Date date_fin;
    private boolean all_day;
    private int reservations;
    private int max;
    private String image;
    private int prix;
    private List<User> participants;
    private CategorieEvenement categorie;
    
    public Evenement(String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix, List<User> participants, CategorieEvenement categorie, String catName) {
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
        this.participants = participants;
        this.categorie = categorie;
    }
    

    public Evenement(int id, String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix, List<User> participants, CategorieEvenement categorie, String catName) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
        this.participants = participants;
        this.categorie = categorie;
    }
    
    
    
    public Evenement() {
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix, CategorieEvenement categorie) {
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Evenement(int id, String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix, CategorieEvenement categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Evenement(int id, String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix, List<User> participants, CategorieEvenement categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
        this.participants = participants;
        this.categorie = categorie;
    }

    public CategorieEvenement getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieEvenement categorie) {
        this.categorie = categorie;
    }

    public Evenement(int id, String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
        
    }

    public Evenement(String titre, String description, String consultationurl, Date date_debut, Date date_fin, boolean all_day, int reservations, int max, String image, int prix) {
        this.titre = titre;
        this.description = description;
        this.consultationurl = consultationurl;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.all_day = all_day;
        this.reservations = reservations;
        this.max = max;
        this.image = image;
        this.prix = prix;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConsultationurl() {
        return consultationurl;
    }

    public void setConsultationurl(String consultationurl) {
        this.consultationurl = consultationurl;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public boolean isAll_day() {
        return all_day;
    }

    public void setAll_day(boolean all_day) {
        this.all_day = all_day;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }


    
    
  
    
}
