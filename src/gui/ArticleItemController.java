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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.ArticleService;
import utils.MyListener;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class ArticleItemController implements Initializable {

    @FXML
    private Label articletitre;
    @FXML
    private Label tmev;
    @FXML
    private Label articledate;
    @FXML
    private Label details;
    private Article a;
    @FXML
    private ImageView articleImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void setData(Article a) {
        this.a=a;
        articletitre.setText(a.getTitre());
        tmev.setText(a.getFeatured_text());
        articledate.setText(" Le" + a.getCreated_at());
        
        // Image myImage = new Image(getClass().getResourceAsStream( a.getNum_media().getNom_fichier()));
        // articleImg.setImage(myImage);
        FileInputStream input;
        try {
            input = new FileInputStream(a.getNum_media().getNom_fichier());
            Image image = new Image(input);
            articleImg.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArticleItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void details(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test.fxml"));
            
            AnchorPane details =fxmlLoader.load();
            TestController controller = fxmlLoader.getController();
            controller.setData(this.a);
            
            final Stage dialog = new Stage();
            Scene scene = new Scene(details);
            dialog.setScene(scene);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(ArticleItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
