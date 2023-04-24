/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import entities.Categorie;
import entities.Num_media;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ArticleService;
import services.CategorieService;
import services.Num_mediaService;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class AdminPageController implements Initializable {

    @FXML
    private TableColumn<Article, String> coltitre;
    @FXML
    private TableColumn<Article, String> colavant;
    @FXML
    private TableColumn<Article, String> colcontenu;
    @FXML
    private TableColumn<Article, Categorie> colcategorie;
    @FXML
    private TableColumn<Article, Date> coldate;
    @FXML
    private TableColumn<Categorie, String> colnom;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfavant;
    @FXML
    private Button btnimport;
    @FXML
    private TextArea tfContenu;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnvider;
    @FXML
    private ComboBox<Categorie> cbcat;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btnajout1;
    @FXML
    private Button btnvider1;
    @FXML
    private Button btnsupp1;
    @FXML
    private Button btnmodif1;
    @FXML
    private Label lblpath;
    @FXML
    private TableView<Article> tblarticle;
    @FXML
    private TableView<Categorie> tblcategorie;
    @FXML
    private Label lblid;
    @FXML
    private Label lblimg;
    @FXML
    private Label ctrltitre;
    @FXML
    private Label ctrlavant;
    @FXML
    private Label ctrlnom;
    @FXML
    private Button btnretour;

    private void reset() {
        ctrltitre.setText("");
        ctrlavant.setText("");
        ctrlnom.setText("");

    }

    private final ArticleService articleService = new ArticleService();
    private final CategorieService categorieService = new CategorieService();
    private final Num_mediaService num_mediaService = new Num_mediaService();
    private List<Article> articles = new ArrayList<Article>();
    private List<Categorie> categories = new ArrayList<Categorie>();
    @FXML
    private Label lblidcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Refresh();
    }

    private void Refresh() {
        try {
            this.articles.clear();
            this.categories.clear();

            this.articles = this.articleService.recuperer();
            this.categories = this.categorieService.recuperer();

            ObservableList<Article> articlesObs = FXCollections.observableArrayList(articles);
            coltitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            colavant.setCellValueFactory(new PropertyValueFactory<>("featured_text"));
            colcontenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
            colcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("created_at"));

            tblarticle.setItems(articlesObs);

            ObservableList<Categorie> categoriesObs = FXCollections.observableArrayList(categories);
            colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));

            tblcategorie.setItems(categoriesObs);

            cbcat.setItems(categoriesObs);

        } catch (SQLException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(new Stage());
        lblpath.setText(file.toString());
        System.out.println(file.toString());
    }

    @FXML
    private void AjouterArticle(ActionEvent event) {
        if (tftitre.getText().isEmpty()) {
            ctrltitre.setText("champ vide");
        }
        else{ctrltitre.setText("");}
        if (tfavant.getText().isEmpty()) {
            ctrlavant.setText("champ vide");
        }
        else{ctrlavant.setText("");}
        

        try {
            String titre = tftitre.getText();
            String avant = tfavant.getText();
            String contenu = tfContenu.getText();
            long millis = System.currentTimeMillis();
            Date created = new Date(millis);
            String imageUrl = lblpath.getText();
            Categorie cat = cbcat.getValue();
            Num_media num = new Num_media(imageUrl, imageUrl);
            num = num_mediaService.ajouter(num);
            Article article = new Article(titre, contenu, avant, created, created, cat, num);
            article = articleService.ajouter(article);
            Refresh();
        } catch (SQLException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerArticle(ActionEvent event) {
        if (!(lblid.getText().isEmpty() && lblid.getText() == "")) {
            try {
                Article article = new Article(Integer.parseInt(lblid.getText()));
                articleService.supprimer(article);
                vider();
                Refresh();
            } catch (SQLException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void ModifierArticle(ActionEvent event) {
        if (!(lblid.getText().isEmpty() && lblid.getText() == "")) {
            try {
                String titre = tftitre.getText();
                String avant = tfavant.getText();
                String contenu = tfContenu.getText();
                long millis = System.currentTimeMillis();
                Date created = new Date(millis);
                String imageUrl = lblpath.getText();
                Categorie cat = cbcat.getValue();
                Num_media num = new Num_media(Integer.parseInt(lblimg.getText()), imageUrl, imageUrl);
                Article article = new Article(Integer.parseInt(lblid.getText()), titre, contenu, avant, created, created, cat, num);
                articleService.modifier(article);
                Refresh();
            } catch (SQLException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        if (tfnom.getText().isEmpty()) {
            ctrlnom.setText("champ vide");
        }
        else{ctrlnom.setText("");}
        String nom = tfnom.getText();
        if (!nom.isEmpty()) {
            try {
                categorieService.ajouter(new Categorie(nom));
                Refresh();
            } catch (SQLException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("fill the name please");
        }
    }

    @FXML
    private void ViderChampscat(ActionEvent event) {
        viderCat();
    }

    @FXML
    private void SupprimerCategorie(ActionEvent event) {
        if (!(lblidcat.getText().isEmpty() && lblidcat.getText() == "")) {
            try {
                Categorie categorie = new Categorie(Integer.parseInt(lblidcat.getText()));
                categorieService.supprimer(categorie);
                viderCat();
                Refresh();
            } catch (SQLException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ModifierCategorie(ActionEvent event) {
        if (!(lblidcat.getText().isEmpty() && lblidcat.getText() == "")) {
            try {
                String nom = tfnom.getText();
                categorieService.modifier(new Categorie(Integer.parseInt(lblidcat.getText()), nom));
                Refresh();
            } catch (SQLException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ViderChampsarticle(ActionEvent event) {
        vider();
    }

    @FXML
    private void selectArticle(MouseEvent event) {
        Article article = tblarticle.getSelectionModel().getSelectedItem();
        tfContenu.setText(article.getContenu());
        tfavant.setText(article.getFeatured_text());
        tftitre.setText(article.getTitre());
        lblid.setText("" + article.getId());
        cbcat.setValue(article.getCategorie());
        lblpath.setText(article.getNum_media().toString());
        lblimg.setText(String.valueOf(article.getNum_media().getId()));
        Refresh();
    }

    private void vider() {
        tfContenu.setText("");
        tfavant.setText("");
        tftitre.setText("");
        lblid.setText("");
        cbcat.setValue(new Categorie());
        lblpath.setText("");
        lblimg.setText("");
    }

    private void viderCat() {
        tfnom.setText("");
        lblidcat.setText("");
    }

    @FXML
    private void selectCategorie(MouseEvent event) {
        Categorie categorie = tblcategorie.getSelectionModel().getSelectedItem();
        tfnom.setText(categorie.getNom());
        lblidcat.setText(String.valueOf(categorie.getId()));
    }

    @FXML
    private void GoHomePage(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            btnretour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
