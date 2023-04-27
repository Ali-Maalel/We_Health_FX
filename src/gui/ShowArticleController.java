/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import services.ArticleService;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class ShowArticleController implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private AnchorPane Empty;
    @FXML
    private BorderPane borderPost;
    @FXML
    private Button titleP;
    @FXML
    private Label descP;
    @FXML
    private Label commentP;
    @FXML
    private Label rateP;
    @FXML
    private ImageView imageP;
    @FXML
    private Button newtP;
    @FXML
    private Button PreviousP;
    @FXML
    private Label dateP;
    @FXML
    private Button ajoutPP;
    @FXML
    private Label articleNbr;

    private List<Article> articles;
    ArticleService ps = new ArticleService();
    public static int i = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.articles = ps.recuperer();
            Collections.reverse(this.articles);
        } catch (SQLException ex) {
            Logger.getLogger(ShowArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        showPost();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {

        i++;
        if (articles.size() == i) {
            i = 0;

            showPost();

        } else {
            showPost();

        }

    }));

    public void showPost() {
        System.out.println("here");
        if (articles.size() == 0) {
            Empty.getChildren().clear();
            Pane pane = new Pane();
            Label label2 = new Label("Pas d'article!");
            label2.setAlignment(Pos.CENTER);
            label2.setLayoutY(69.0);
            label2.setPrefHeight(93.0);
            label2.setPrefWidth(458.0);
            label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1 0 0 0;");
            label2.setFont(Font.font("Calibri Italic", 30));
            label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
            pane.getChildren().addAll(label2, ajoutPP);
            Empty.getChildren().add(pane);
        } else {

            try {
                Article article = articles.get(i);
                int NbrComments = ps.showComments(articles.get(i)).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                articleNbr.setText(i + 1 + "#");
                titleP.setText(article.getTitre());
                dateP.setText("" + article.getCreated_at());
                descP.setText(article.getContenu());
                FileInputStream input = new FileInputStream(article.getNum_media().getNom_fichier());
                Image image = new Image(input);

                imageP.setImage(image);
                if (article.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) article.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(article.getNbrRate())) + "/5");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ShowArticleController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShowArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void handleQuitter(ActionEvent event) {
        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

    @FXML
    private void detailP(ActionEvent event) {
        try {
            timeline.stop();
            List<Article> articles;
            try {
                articles = new ArticleService().recuperer();

                Collections.reverse(articles);
                Article article = articles.get(i);
                DetailArticleController b = new DetailArticleController();
                b.setIdA(article.getId());
                System.out.println(b.getIdA());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DetailArticle.fxml"));
                Parent root = loader.load();
                DetailArticleController controller = loader.getController();
                controller.setArticleView(article);
                controller.setArticle(article);

                Scene scene = new Scene(root);
                Stage stage = (Stage) titleP.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (SQLException ex) {
                Logger.getLogger(ShowArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void nextPost(ActionEvent event) {
        i++;

        try {

            if (this.articles.size() == i) {
                i = 0;

                showPost();
            } else {
                Article article = this.articles.get(i);
                int NbrComments = ps.showComments(this.articles.get(i)).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                articleNbr.setText(i + 1 + "#");
                titleP.setText(article.getTitre());
                dateP.setText("" + article.getCreated_at());
                Image image = new Image(article.getNum_media().getNom_fichier());

                imageP.setImage(image);
                descP.setText(article.getContenu());
                if (article.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) article.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(article.getNbrRate())) + "/5");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PreviousPost(ActionEvent event) {
        i--;

        try {

            if (i < 0) {

                i = this.articles.size() - 1;

                showPost();

            } else {
                Article article = this.articles.get(i);
                int NbrComments = ps.showComments(this.articles.get(i)).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                articleNbr.setText(i + 1 + "#");
                titleP.setText(article.getTitre());
                dateP.setText("" + article.getCreated_at());
                Image image = new Image(article.getNum_media().getNom_fichier());

                imageP.setImage(image);
                descP.setText(article.getContenu());
                if (article.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) article.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(article.getNbrRate())) + "/5");
                }
                System.out.println("" + article.getCreated_at());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjoutPost(ActionEvent event) {
    }

}
