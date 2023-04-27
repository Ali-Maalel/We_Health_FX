/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Article;
import entities.Comment;
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
public class CommentService implements IService<Comment> {
    
Connection cnx;

    public CommentService() {
        cnx = MYDB.getInstance().getCnx();
    }

    @Override
    public Comment ajouter(Comment t) throws SQLException {
       String req = "insert into comment(article_id,user_id,contenu,created_at) values('" + t.getArticle().getId()+"','"+ t.getUser().getId()+"','"+ t.getContenu()+ "','"+ t.getCreated_at()+"')";
        Statement st = cnx.createStatement();
        int affectedRows=st.executeUpdate(req,st.RETURN_GENERATED_KEYS);
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = st.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                t.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        return t;
    }
    

    @Override
    public void modifier(Comment t) throws SQLException {
        String req = "update Comment set article_id = ? , user_id = ? , contenu = ? , created_at = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getArticle().getId());
        ps.setInt(2, t.getUser().getId());
        ps.setString(3, t.getContenu());
        ps.setDate(4, t.getCreated_at());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Comment t) throws SQLException {
        String req = "delete from Comment where id = "+t.getId();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Comment> recuperer() throws SQLException {
        ArticleService artsr =new ArticleService();
        List<Comment> comments = new ArrayList<>();
        String req = "select * from Comment";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Comment p = new Comment();
            p.setId(rs.getInt("id"));
            p.setArticle(artsr.recupererById(
                    new Article(
                            rs.getInt("article_id"))
            ).get(0));
           
            p.setContenu(rs.getString("contenu"));
            p.setCreated_at(rs.getDate("created_at"));
            
            comments.add(p);
        }
        return comments;
    }
    public List<Comment> recupererById(Comment t) throws SQLException {
        ArticleService artsr =new ArticleService();
        UserService usersr = new UserService();
        List<Comment> comments = new ArrayList<>();
        String req = "select * from Comment where id = "+t.getId();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Comment p = new Comment();
            p.setId(rs.getInt("id"));
            p.setArticle(artsr.recupererById(
                    new Article(
                            rs.getInt("article_id"))
            ).get(0));
            p.setUser(usersr.recupererById(
                    
                            rs.getInt("user_id")
            ).get(0));
           
            p.setContenu(rs.getString("contenu"));
            p.setCreated_at(rs.getDate("created_at"));
            
            comments.add(p);
        }
        return comments;
    }
     public List<Comment> recupererByArticle(Article t) throws SQLException {
        ArticleService artsr =new ArticleService();
        UserService usersr = new UserService();
        List<Comment> comments = new ArrayList<>();
        String req = "select * from Comment where article_id = "+t.getId();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Comment p = new Comment();
            p.setId(rs.getInt("id"));
            p.setArticle(artsr.recupererById(
                    new Article(
                            rs.getInt("article_id"))
            ).get(0));
            p.setUser(usersr.recupererById(
                    
                            rs.getInt("user_id")
            ).get(0));
           
            p.setContenu(rs.getString("contenu"));
            p.setCreated_at(rs.getDate("created_at"));
            
            comments.add(p);
        }
        return comments;
    }
    
}
