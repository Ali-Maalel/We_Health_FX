/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author sarra
 */
public class CategorieEvenement {
    private int id;
    private String nom;
    private List<Evenement> evenements;

    @Override
    public String toString() {
        return  nom ;
    }

    public CategorieEvenement() {
    }

    public CategorieEvenement(int id) {
        this.id = id;
    }

    public CategorieEvenement(String nom, List<Evenement> evenements) {
        this.nom = nom;
        this.evenements = evenements;
    }

    public CategorieEvenement(String nom) {
        this.nom = nom;
    }

    public CategorieEvenement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public CategorieEvenement(int id, String nom, List<Evenement> evenements) {
        this.id = id;
        this.nom = nom;
        this.evenements = evenements;
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

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }
    
    
}
