/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class MainContainerController implements Initializable {

    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("EventFront.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void switchEvents(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("EventFront.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(fxml);
    }

    @FXML
    private void switchRes(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("detailsEvent.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(fxml);
    }
    
}
