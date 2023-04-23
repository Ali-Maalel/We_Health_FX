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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.EvenementService;
import utils.MyListener;

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
    EvenementService es = new EvenementService();
    public static List<Evenement> eventList = new ArrayList<>();
    public static List<Evenement> event = new ArrayList<>();
        private MyListener myListener;

    @FXML
    private HBox EventLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

           eventList.addAll(es.recuperer());
           Collections.reverse(eventList);

           event.addAll(getData());
            Collections.reverse(event);

            for (int i = 0; i < eventList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox cardBox = fxmlLoader.load();

                EventCardController eventcardcontroller = fxmlLoader.getController();
                eventcardcontroller.setData(eventList.get(i),myListener);
                
                EventLayout.getChildren().add(cardBox);

            }
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Evenement> getData() {
        List<Evenement> events = new ArrayList<>();
        Evenement event;

        for (Evenement e : eventList) {
            event = new Evenement();
            event.setTitre(e.getTitre());
            event.setPrix(e.getPrix());
            event.setImage(e.getImage());

            events.add(event);
        }

        return events;

    }
}
