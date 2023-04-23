/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.EvenementService;
import utils.MyListener;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class EventCardController implements Initializable {

    @FXML
    private VBox eventbox;
    @FXML
    private ImageView eventimg;
    @FXML
    private Label eventtitle;
    @FXML
    private Label eventdate;
    @FXML
    private Label eventprice;
    @FXML
    private Label eventdetails;
    @FXML
    private Button btnparticiper;

    private List<Evenement> evenements = new ArrayList<Evenement>();
    EvenementService es = new EvenementService();
    private MyListener myListener;
    private Evenement e;
    @FXML
    private VBox boxinfos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void click(MouseEvent event) {
        myListener.onClickListener(e);
    }

    public void setData(Evenement e, MyListener myListener) {
        this.e = e;
        this.myListener = myListener;
        eventtitle.setText(e.getTitre());
        eventdate.setText(" Le" + e.getDate_debut());
        Image myImage = new Image(getClass().getResourceAsStream("/images/" + e.getImage() + ""));
        eventimg.setImage(myImage);
        /*Image image = new Image(getClass().getResourceAsStream(e.getImage() + ""));
        eventimg.setImage(image);*/
    }

    @FXML
    private void participer(ActionEvent event) {
    }
}
