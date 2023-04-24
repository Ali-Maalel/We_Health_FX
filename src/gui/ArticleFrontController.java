/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.ArticleService;
import utils.MyListener;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class ArticleFrontController implements Initializable {

    private HBox articlelayout;
    ArticleService as = new ArticleService();
    public static List<Article> ArticleList = new ArrayList<>();
    public static List<Article> article = new ArrayList<>();
    private MyListener myListener;
    private GridPane gridarticle;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Article> articles = as.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < articles.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArticleItem.fxml"));
                AnchorPane griditem = fxmlLoader.load();

                ArticleItemController articleitemcontroller = fxmlLoader.getController();
                articleitemcontroller.setData(articles.get(i));
                
                grid.add(griditem, column, row);
                row++;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ArticleFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
        // TODO
    }

    public static List<Article> getData() {
        List<Article> articles = new ArrayList<>();
        Article article;

        for (Article a : ArticleList) {
            article = new Article();
            article.setTitre(a.getTitre());
            article.setFeatured_text(a.getFeatured_text());
            article.setCreated_at(a.getCreated_at());

            articles.add(article);
        }

        return articles;
    }
}
