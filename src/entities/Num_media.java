/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author yasmi
 */
public class Num_media {
     private int id;
     private String nom;
     private String nom_fichier;
     private List<Article> articles;

    public Num_media() {
    }

    @Override
    public String toString() {
        return nom_fichier;
    }

    public Num_media(int id, String nom, String nom_fichier, List<Article> articles) {
        this.id = id;
        this.nom = nom;
        this.nom_fichier = nom_fichier;
        this.articles = articles;
    }

    public Num_media(String nom, String nom_fichier, List<Article> articles) {
        this.nom = nom;
        this.nom_fichier = nom_fichier;
        this.articles = articles;
    }
    public Num_media(String nom, String nom_fichier) {
        this.nom = nom;
        this.nom_fichier = nom_fichier;
    }

    public Num_media(int id, String nom, String nom_fichier) {
        this.id = id;
        this.nom = nom;
        this.nom_fichier = nom_fichier;
    }

    public Num_media(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom_fichier() {
        return nom_fichier;
    }

    public void setNom_fichier(String nom_fichier) {
        this.nom_fichier = nom_fichier;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
     
    
}
