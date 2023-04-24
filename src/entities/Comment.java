/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author yasmi
 */
public class Comment {
    private int id;
    private Article article;
    private User user;
    private String contenu;
    private Date created_at;

    public Comment() {
    }

    public Comment(int id, Article article, User user, String contenu, Date created_at) {
        this.id = id;
        this.article = article;
        this.user = user;
        this.contenu = contenu;
        this.created_at = created_at;
    }

    public Comment(Article article, User user, String contenu, Date created_at) {
        this.article = article;
        this.user = user;
        this.contenu = contenu;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    
}
