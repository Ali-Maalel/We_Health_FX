/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class TestController implements Initializable {

    @FXML
    private Label lblTitre;

    @FXML
    private Label lblContenu;

    private Article a;
    @FXML
    private TextArea txtAreaCommentaires;
    @FXML
    private TextField txtFieldNom;
    @FXML
    private TextArea txtAreaNouveauCommentaire;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Button btnLike;
    @FXML
    private Button btnDislike;
    @FXML
    private Button btnPDF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(Article a){
        this.a =a ;
        lblTitre.setText(this.a.getTitre());
        lblContenu.setText(this.a.getContenu());
    }

    @FXML
    private void handleEnvoyerButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleLikeButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleDislikeButtonAction(ActionEvent event) {
    }

}
