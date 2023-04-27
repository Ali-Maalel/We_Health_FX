/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.List;

public class Article {

    private int id;
    private String Titre;
    private String Contenu;
    private String featured_text;
    private Date created_at;
    private Date updated_at;
    private Categorie categorie;
    private Num_media num_media;
    private List<User> likes;
    private List<Comment> comments;

    private int rate;
    private double nbrRate;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getNbrRate() {
        return nbrRate;
    }

    public void setNbrRate(double nbrRate) {
        this.nbrRate = nbrRate;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", Titre=" + Titre + ", Contenu=" + Contenu + ", featured_text=" + featured_text + ", created_at=" + created_at + ", updated_at=" + updated_at + ", categorie=" + categorie + ", num_media=" + num_media + ", likes=" + likes + ", comments=" + comments + ", rate=" + rate + ", nbrRate=" + nbrRate + '}';
    }



    public Article() {
    }

    public Article(String Titre, String Contenu, String featured_text, Date created_at, Date updated_at, Categorie categorie, Num_media num_media) {
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.featured_text = featured_text;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.categorie = categorie;
        this.num_media = num_media;

    }

    public Article(int id, String Titre, String Contenu, String featured_text, Date created_at, Date updated_at, Categorie categorie, Num_media num_media, List<User> likes, List<Comment> comments) {
        this.id = id;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.featured_text = featured_text;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.categorie = categorie;
        this.num_media = num_media;
        this.likes = likes;
        this.comments = comments;
    }

    public Article(int id, String Titre, String Contenu, String featured_text, Date created_at, Date updated_at, Categorie categorie, Num_media num_media) {
        this.id = id;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.featured_text = featured_text;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.categorie = categorie;
        this.num_media = num_media;
    }
    
    public Article(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public String getFeatured_text() {
        return featured_text;
    }

    public void setFeatured_text(String featured_text) {
        this.featured_text = featured_text;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Num_media getNum_media() {
        return num_media;
    }

    public void setNum_media(Num_media num_media) {
        this.num_media = num_media;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
