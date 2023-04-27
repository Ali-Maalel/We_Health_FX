/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Article;
import entities.Categorie;
import entities.Comment;
import entities.Num_media;
import entities.User;
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
 * @author yasmi
 */
public class ArticleService implements IService<Article> {

    Connection cnx;

    public ArticleService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public Article ajouter(Article t) throws SQLException {
        String req = "insert into Article (Titre,featured_text,Contenu,created_at,updated_at,categorie_id,featured_image_id,image,rate,moy_rate) values('" + t.getTitre() + "','" + t.getFeatured_text() + "','" + t.getContenu() + "','" + t.getCreated_at() + "','" + t.getUpdated_at() + "','" + t.getCategorie().getId() + "','" + t.getNum_media().getId() + "','" + t.getNum_media().getNom_fichier().replace("\\", "\\\\")+"',0,0)";
        Statement st = cnx.createStatement();
        int affectedRows = st.executeUpdate(req, st.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating article failed, no rows affected.");
        }

        try (ResultSet generatedKeys = st.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                t.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        return t;
    }

    @Override
    public void modifier(Article t) throws SQLException {
        Num_mediaService num_mediaService =new Num_mediaService();
        String req = "update article set Titre = ? , featured_text = ? , Contenu = ? , created_at = ? , updated_at = ? , categorie_id = ? , featured_image_id = ? , rate = ?  , moy_rate = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        num_mediaService.modifier(t.getNum_media());
        ps.setString(1, t.getTitre());
        ps.setString(2, t.getFeatured_text());
        ps.setString(3, t.getContenu());
        ps.setDate(4, t.getCreated_at());
        ps.setDate(5, t.getUpdated_at());
        ps.setInt(6, t.getCategorie().getId());
        ps.setInt(7, t.getNum_media().getId());
        ps.setInt(8, t.getRate());
        ps.setDouble(9, t.getNbrRate());
        ps.setInt(10, t.getId());
        
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Article t) throws SQLException {
        String req = "delete from Article where id = " + t.getId();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Article> recuperer() throws SQLException {
        CategorieService catsr = new CategorieService();
        Num_mediaService medsr = new Num_mediaService();
        List<Article> articles = new ArrayList<>();
        String req = "select * from Article";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Article p = new Article();
            p.setId(rs.getInt("id"));
            p.setTitre(rs.getString("Titre"));
            p.setFeatured_text(rs.getString("featured_text"));
            p.setContenu(rs.getString("Contenu"));
            p.setCreated_at(rs.getDate("created_at"));
            p.setUpdated_at(rs.getDate("updated_at"));
            p.setCategorie(catsr.recupererById(
                    new Categorie(
                            rs.getInt("categorie_id"))
            ).get(0));
            p.setNum_media(medsr.recupererById(
                    rs.getInt("featured_image_id")
            ).get(0));
            p.setLikes(this.recupererlikes(p.getId()));
            p.setRate(rs.getInt("rate"));
            p.setNbrRate(rs.getDouble("moy_rate"));
            articles.add(p);
        }
        return articles;

    }

    public List<Article> recupererById(Article t) throws SQLException {
        CategorieService catsr = new CategorieService();
        Num_mediaService medsr = new Num_mediaService();
        List<Article> articles = new ArrayList<>();
        String req = "select * from Article where id = " + t.getId();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Article p = new Article();
            p.setId(rs.getInt("id"));
            p.setTitre(rs.getString("Titre"));
            p.setFeatured_text(rs.getString("featured_text"));
            p.setContenu(rs.getString("Contenu"));
            p.setCreated_at(rs.getDate("created_at"));
            p.setUpdated_at(rs.getDate("updated_at"));
            p.setCategorie(catsr.recupererById(
                    new Categorie(
                            rs.getInt("categorie_id"))
            ).get(0));
            p.setNum_media(medsr.recupererById(
                    rs.getInt("featured_image_id")
            ).get(0));
            p.setLikes(this.recupererlikes(p.getId()));
            p.setRate(rs.getInt("rate"));
            p.setNbrRate(rs.getDouble("moy_rate"));
            articles.add(p);
        }
        return articles;
    }

    public List<Article> recupererByCategorie(Categorie t) throws SQLException {
        CategorieService catsr = new CategorieService();
        Num_mediaService medsr = new Num_mediaService();
        List<Article> articles = new ArrayList<>();
        String req = "SELECT * FROM Article where categorie_id = ? ";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, t.getId());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Article p = new Article();
            p.setId(rs.getInt("id"));
            p.setTitre(rs.getString("Titre"));
            p.setFeatured_text(rs.getString("featured_text"));
            p.setContenu(rs.getString("Contenu"));
            p.setCreated_at(rs.getDate("created_at"));
            p.setUpdated_at(rs.getDate("updated_at"));
            p.setCategorie(t);
            p.setNum_media(medsr.recupererById(
                    rs.getInt("featured_image_id")
            ).get(0));
            p.setLikes(this.recupererlikes(p.getId()));
            p.setRate(rs.getInt("rate"));
            p.setNbrRate(rs.getDouble("moy_rate"));
            articles.add(p);
        }
        return articles;

    }

    public List<User> recupererlikes(int t) throws SQLException {
        List<User> users = new ArrayList<>();
        UserService service = new UserService();
        String req = "SELECT * FROM Article_like where article_id = ? ";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, t);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(t);
             User user = service.recupererById(id).get(0);
            users.add(user);
        }
        return users;
    }
    public List<Comment> showComments(Article t) throws SQLException {
        List<Comment> Comments = new ArrayList<>();
        ArticleService serviceart = new ArticleService();
        UserService service = new UserService();
        String req = "SELECT * FROM Comment where article_id = ? ";
        PreparedStatement st = cnx.prepareStatement(req);
        System.out.println(t);
        st.setInt(1, t.getId());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println("showComments : "+rs.getInt("user_id"));
            int id = rs.getInt("user_id");
            
            User user = service.recupererById(id).get(0);
            System.out.println("showComments : "+user.toString());
            Comment c = new Comment(rs.getInt("id"), t, user, rs.getString("contenu"), rs.getDate("created_at"));
            Comments.add(c);
        }
        return Comments;
    }
}
