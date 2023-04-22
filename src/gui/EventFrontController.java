/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class EventFrontController implements Initializable {

    @FXML
    private Button goposts;
    @FXML
    private Button goevent;
    @FXML
    private Button goarticle;
    @FXML
    private Button gomyevents;
    @FXML
    private FlowPane mesevents1;
    @FXML
    private VBox eventcontainer;
    @FXML
    private Button voirpls;
EvenementService es = new EvenementService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 // Remplacez par l'instance de votre service EventService
          // Appeler la méthode recuperer() pour récupérer les événements
              List<Evenement> evenements = null;
        try {
            evenements = es.recuperer(); // Récupération des événements depuis la classe GestionEvenements
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventFront.fxml"));
        VBox eventcontainer;
        try {
            eventcontainer = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        // Parcourir la liste des événements et créer des VBox dupliqués pour chaque événement
        for (Evenement event : evenements) {
            VBox duplicatedVBox = new VBox();
            //duplicatedVBox.getChildren().addAll(eventcontainer.getChildren());
            //duplicatedVBox.getChildren().addAll(boxevent.getChildren());

            // Créer des Labels pour afficher les informations de l'événement dans le VBox dupliqué
            Label lblTitre = new Label(event.getTitre());
    Label lblDescription = new Label(event.getDescription());
   

            // Ajouter les autres informations de l'événement à afficher dans les Labels

            // Ajouter les Labels dans le VBox dupliqué
            duplicatedVBox.getChildren().addAll(lblTitre, lblDescription);
            // Ajouter le VBox dupliqué dans le FlowPane
            mesevents1.getChildren().add(duplicatedVBox);
        }
    }
    }

   