/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import entities.Facture;
import entities.Participants;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EvenementService;
import services.FactureService;
import services.ParticipantsService;
import services.UserService;
import utils.MyListener;

/**
 *
 * @author sarra
 */
public class DetailsController {

    private MyListener myListener;
    private Evenement e;
    private Participants p;
    private Facture f;
    @FXML
    private ImageView lblImage;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblNbPlaceRes;
    @FXML
    private Button cancelBtn;

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setData(Evenement e, MyListener myListener) {
        this.e = e;
        this.myListener = myListener;
        lblTitle.setText("Evenement: " + e.getTitre());
        lblDate.setText(" Le" + e.getDate_debut());
        lblPrice.setText("Prix: " + e.getPrix() + "TND");
        lblNbPlaceRes.setText("Nombre de places disponibles: " + e.getMax());
        lblDesc.setText("Description: " + e.getDescription());
        Image myImage = new Image(getClass().getResourceAsStream("/images/" + e.getImage() + ""));
        lblImage.setImage(myImage);
        /*Image image = new Image(getClass().getResourceAsStream(e.getImage() + ""));
        eventimg.setImage(image);*/
    }

    @FXML
    private void reserverEvent(ActionEvent event) throws SQLException {
        try {

            ParticipantsService parS = new ParticipantsService();
            UserService userS = new UserService();
            User user = new User();
            user = userS.recupererById(1).get(0);
            Participants p = new Participants(e, user);

            FactureService facS = new FactureService();
            Random random = new Random();
            int randomNumber = random.nextInt(8999) + 1000;
            Date date = new Date(System.currentTimeMillis());
            Facture f = new Facture(date, e.getPrix(), randomNumber, user);

            this.p = p;
            this.f = f;
            URL fxURL = getClass().getResource("Facture.fxml");
            FXMLLoader loader = new FXMLLoader(fxURL);
            Parent root = loader.load();
            FactureController dc = loader.getController();
            dc.setData(e, this.f, this.p, myListener);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //parS.ajouter(p);
    }

}
