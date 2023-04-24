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
public class Categorie {
     private int id;
     private String nom;
     private List<Article> articles;
    public Categorie() {
    }

    @Override
    public String toString() {
        return  nom;
    }
public Categorie(int id) {
        this.id = id;
    }
    public Categorie(int id, String nom, List<Article> articles) {
        this.id = id;
        this.nom = nom;
        this.articles = articles;
    }

    public Categorie(String nom, List<Article> articles) {
        this.nom = nom;
        this.articles = articles;
    }
    public Categorie(String nom) {
        this.nom = nom;

    }

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
     
    
}
