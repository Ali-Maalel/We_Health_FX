package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import entities.Evenement; // Assurez-vous d'importer la classe Evenement depuis le package approprié
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.EvenementService;
// Assurez-vous d'importer la classe GestionEvenements depuis le package approprié

public class TestController implements Initializable {

    @FXML
    private FlowPane flowPane; // Utilisation de l'ID du FlowPane défini dans le fichier .fxml

    EvenementService es = new EvenementService();// Instance de la classe GestionEvenements

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Evenement> evenements = null;
        try {
            evenements = es.recuperer(); // Récupération des événements depuis la classe GestionEvenements
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Parcours de la liste des événements
        for (Evenement evenement : evenements) {
            // Création d'un VBox pour organiser les éléments de chaque événement verticalement
            VBox vBox = new VBox();
            vBox.setSpacing(5); // Espacement vertical entre les éléments

            // Création d'une ImageView pour afficher l'image de l'événement
               flowPane.setHgap(50);
               flowPane.setVgap(50);
            // Création de Labels pour afficher le titre, la description et le prix de l'événement
            Label labelTitre = new Label(evenement.getTitre());
            Label labelDescription = new Label(evenement.getDescription());
            Label labelPrix = new Label(String.valueOf(evenement.getPrix()));

            // Ajout des éléments dans le VBox
            vBox.getChildren().addAll(labelTitre, labelDescription, labelPrix);

            // Ajout du VBox dans le FlowPane
            flowPane.getChildren().add(vBox);
        }
    }
 }

  

