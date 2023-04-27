/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXSlider;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.EvenementService;
import utils.MyListener;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class EventFrontController implements Initializable {

    EvenementService es = new EvenementService();
    public static List<Evenement> eventList = new ArrayList<>();
    public static List<Evenement> eventList1 = new ArrayList<>();
    public static List<Evenement> eventList2 = new ArrayList<>();
    public static List<Evenement> event = new ArrayList<>();
        private MyListener myListener;

    @FXML
    private HBox EventLayout;
    @FXML
    private JFXSlider priceSlider;
    @FXML
    private TextField searchBar;
    @FXML
    private HBox event2Layout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        priceSlider.setValue(0);
        try {
            eventList.clear();
            event.clear();

           eventList.addAll(es.recuperer());
           Collections.reverse(eventList);

           event.addAll(getData());
            Collections.reverse(event);
            
            Date date = new Date(System.currentTimeMillis());
            for (Evenement evenement : eventList) {
                if(evenement.getDate_debut().before(date)) {
                    eventList2.add(evenement);
                } else {
                    eventList1.add(evenement);
                }
            }

            for (int i = 0; i < eventList1.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox cardBox = fxmlLoader.load();

                EventCardController eventcardcontroller = fxmlLoader.getController();
                eventcardcontroller.setData(eventList1.get(i),myListener);
                
                EventLayout.getChildren().add(cardBox);

            }
            
            for (int i = 0; i < eventList2.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("NoEventCard.fxml"));
                VBox cardBox = fxmlLoader.load();

                NoEventCardController noeventcardcontroller = fxmlLoader.getController();
                noeventcardcontroller.setData(eventList2.get(i),myListener);
                
                event2Layout.getChildren().add(cardBox);

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
    
    List<Evenement> filtredPrixServices = new ArrayList<>();
    private int eventSlider = 0;

    @FXML
    private void filterByPrice(MouseEvent event) {
        filtredPrixServices = new ArrayList<>();
        eventSlider = (int) priceSlider.getValue();

        if (searchBar.getText().equals("") && priceSlider.getValue() <= 0) {
            filtredPrixServices = eventList1;
        }

        if (searchBar.getText().equals("") && priceSlider.getValue() > 0) {
            for (Evenement e : eventList1) {

                if (e.getPrix() >= eventSlider) {
                    filtredPrixServices.add(e);
                }
            }
        }
        if (!searchBar.getText().equals("") && priceSlider.getValue() > 0) {
            for (Evenement e : filteredSearch) {

                if (e.getPrix()>= eventSlider) {
                    filtredPrixServices.add(e);
                }
            }
        }

        if (!searchBar.getText().equals("") && priceSlider.getValue() <= 0) {

            for (Evenement e : filteredSearch) {

                if (e.getTitre().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    filtredPrixServices.add(e);
                }
            }

        }
        
        try {
            EventLayout.getChildren().clear();
            for (int i = 0; i < filtredPrixServices.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox cardBox = fxmlLoader.load();

                EventCardController eventcardcontroller = fxmlLoader.getController();
                eventcardcontroller.setData(filtredPrixServices.get(i),myListener);
                
                EventLayout.getChildren().add(cardBox);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private List<Evenement> evenements = new ArrayList<>();
    private List<Evenement> filteredSearch = new ArrayList<>();

    @FXML
    private void onClickSearch(ActionEvent event) {
        filteredSearch = new ArrayList<>();

        if (searchBar.getText().equals("") && priceSlider.getValue() <= 0) {
            filteredSearch = eventList1;

        }
        if (searchBar.getText().equals("") && priceSlider.getValue() > 0) {
            filteredSearch = filtredPrixServices;

        }

        if (!searchBar.getText().equals("") && priceSlider.getValue() <= 0) {

            for (Evenement e : eventList1) {

                if (e.getTitre().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    filteredSearch.add(e);
                }
            }

        }

        if (!searchBar.getText().equals("") && priceSlider.getValue() > 0) {

            for (Evenement p : filtredPrixServices) {

                if (p.getTitre().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    filteredSearch.add(p);
                }
            }

        }
        
        EventLayout.getChildren().clear();

        try {
            for (int i = 0; i < filteredSearch.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox cardBox = fxmlLoader.load();

                EventCardController eventcardcontroller = fxmlLoader.getController();
                eventcardcontroller.setData(filteredSearch.get(i),myListener);
                
                EventLayout.getChildren().add(cardBox);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
