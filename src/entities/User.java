/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yasmi
 */
public class User {
    private int id;
    private String login;
    private String roles;
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    private int blocked;
    private String typeuser;
    private String email;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", roles=" + roles + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", blocked=" + blocked + ", typeuser=" + typeuser + ", email=" + email + '}';
    }
    

    public User() {
    }

    public User(int id, String login, String roles, String password, String nom, String prenom, String telephone, int blocked, String typeuser, String email) {
        this.id = id;
        this.login = login;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.blocked = blocked;
        this.typeuser = typeuser;
        this.email = email;
        
    }

    public User(String login, String roles, String password, String nom, String prenom, String telephone, int blocked, String typeuser, String email) {
        this.login = login;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.blocked = blocked;
        this.typeuser = typeuser;
        this.email = email;
    }

    public User(int aInt) {
        this.id=aInt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
    
    
    
    
}