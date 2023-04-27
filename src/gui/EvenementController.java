/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategorieEvenement;
import entities.Evenement;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CategorieEventService;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class EvenementController implements Initializable {

    @FXML
    private TableColumn<Evenement, String> coltitre;
    @FXML
    private TableColumn<Evenement, String> coldesc;
    @FXML
    private TableColumn<Evenement, Date> coldatedebut;
    @FXML
    private TableColumn<Evenement, Date> coldatefin;
    @FXML
    private TableColumn<Evenement, Integer> colmax;
    @FXML
    private TableColumn<Evenement, Integer> colprix;
    @FXML
    private TableColumn<Evenement, Integer> collien;
    @FXML
    private TextField tftitre;
    @FXML
    private Button btnimport;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnvider;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private TextField tfmax;
    @FXML
    private TextField tfliencons;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfprix;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Label labelphoto;
    @FXML
    private Label ctrltitre;
    @FXML
    private Label ctrldesc;

    private List<Evenement> evenements = new ArrayList<Evenement>();
    EvenementService es = new EvenementService();
    private List<CategorieEvenement> Eventcategories = new ArrayList<CategorieEvenement>();

    @FXML
    private Label ctrllien;
    @FXML
    private TableView<Evenement> tblevent;
    @FXML
    private Label lblidevent;
    @FXML
    private Label lblimgevent;
    @FXML
    private Label lblDateevent;
    @FXML
    private ComboBox<CategorieEvenement> combocat;
    @FXML
    private TableView<CategorieEvenement> tblcategorie;
    @FXML
    private TableColumn<Evenement, CategorieEvenement> colnomcat;
    @FXML
    private TextField tfnomcat;
    @FXML
    private Button btnajoutcat;
    @FXML
    private Label ctrlnom;
    CategorieEventService CS = new CategorieEventService();
    @FXML
    private Label lblidcat;
    @FXML
    private TableColumn<Evenement, CategorieEvenement> collcat;
    @FXML
    private PieChart eventsChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<CategorieEvenement> categorieObs = FXCollections.observableArrayList(CS.recuperer());
            combocat.setItems(categorieObs);

            Refresh();
            displayEventsChart();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    private void displayEventsChart() {

        pieChartData.clear();
        eventsChart.getData().clear();
        //            int categoriesN = categories.size();
        for (CategorieEvenement c : Eventcategories) {
            int catN = 0;
            for (Evenement e : evenements) {
                if (c.getNom().equals(e.getCategorie().getNom())) {
                    catN++;
                }
            }

            PieChart.Data data = new PieChart.Data(c.getNom(), catN);
            pieChartData.add(data);

        }
        eventsChart.setLabelsVisible(true);
        eventsChart.setLegendVisible(true);
        eventsChart.setLabelLineLength(10);
        eventsChart.getData().addAll(pieChartData);
    }

    private void Refresh() {
        try {
            this.evenements.clear();
            this.Eventcategories.clear();

            this.evenements = this.es.recuperer();
            this.Eventcategories = this.CS.recuperer();

            ObservableList<Evenement> articlesObs = FXCollections.observableArrayList(evenements);
            coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            coldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            colmax.setCellValueFactory(new PropertyValueFactory<>("max"));
            colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            collien.setCellValueFactory(new PropertyValueFactory<>("consultationurl"));
            tblevent.setItems(articlesObs);
            ObservableList<CategorieEvenement> categoriesObs = FXCollections.observableArrayList(Eventcategories);
            colnomcat.setCellValueFactory(new PropertyValueFactory<>("nom"));
            collcat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            tblcategorie.setItems(categoriesObs);

        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(new Stage());
        labelphoto.setText(file.toString());
        String image = labelphoto.getText();
        String newimageFile = image.replace("C:\\Users\\sarra\\OneDrive\\Documents\\NetBeansProjects\\wehealthapplication\\src\\images\\", "");
        labelphoto.setText(newimageFile);
    }

    private void reset() {
        ctrldesc.setText("");
        ctrltitre.setText("");
        lblidevent.setText("");
        tfdesc.setText("");
        tfliencons.setText("");
        tfmax.setText("");
        tfprix.setText("");
        tftitre.setText("");
        labelphoto.setText("");
        datedebut.setValue(null);
        datefin.setValue(null);
    }

    private void resetCat() {
        tfnomcat.setText("");
    }

    @FXML
    private void AjouterArticle(ActionEvent event) {
        Boolean isValid = true;
        if (tftitre.getText().isEmpty()) {
            ctrltitre.setText("champ vide");
            isValid = false;
        } else {
            ctrltitre.setText("");
        }
        if (tfliencons.getText().isEmpty()) {
            ctrllien.setText("champ vide");
            isValid = false;
        } else {
            ctrllien.setText("");
        }
        if (tfdesc.getText().isEmpty()) {
            ctrldesc.setText("champ vide");
            isValid = false;
        } else {
            ctrldesc.setText("");
        }
        if (datefin.getValue() != null && datedebut.getValue() != null) {

            if (datefin.getValue().compareTo(datedebut.getValue()) < 0) {
                lblDateevent.setText("date debut > date fin");
                isValid = false;
            } else {
                lblDateevent.setText("");
            }
        } else {
            isValid = false;
        }
        if (isValid) {
            try {
                Evenement p = new Evenement();
                p.setTitre(tftitre.getText());
                p.setDescription(tfdesc.getText());
                p.setConsultationurl(tfliencons.getText());
                p.setDate_debut(new Date(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth()));
                p.setDate_fin(new Date(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth()));
                p.setImage(labelphoto.getText());
                p.setPrix(Integer.parseInt(tfprix.getText()));
                p.setMax(Integer.parseInt(tfmax.getText()));
                p.setReservations(0);
                CategorieEvenement cat = combocat.getValue();
                p.setCategorie(cat);
                es.ajouter(p);
                reset();
                Refresh();
            displayEventsChart();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void selectevent(MouseEvent event) {
        Evenement evenement = tblevent.getSelectionModel().getSelectedItem();
        tfdesc.setText(evenement.getDescription());
        tfliencons.setText(evenement.getConsultationurl());
        tfmax.setText(String.valueOf(evenement.getMax()));
        tftitre.setText(evenement.getTitre());
        tfprix.setText(String.valueOf(evenement.getPrix()));
        datedebut.setValue(LocalDate.of(evenement.getDate_debut().getYear() + 1900, evenement.getDate_debut().getMonth() + 1, evenement.getDate_debut().getDate()));
        datefin.setValue(LocalDate.of(evenement.getDate_fin().getYear() + 1900, evenement.getDate_fin().getMonth() + 1, evenement.getDate_fin().getDate()));
        lblidevent.setText(String.valueOf(evenement.getId()));
        labelphoto.setText(evenement.getImage());
    }

    @FXML
    private void ViderChampsevent(ActionEvent event) {
        reset();
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) {
        if (!(lblidevent.getText().isEmpty() && lblidevent.getText() == "")) {

            try {
                Evenement p = new Evenement();
                p.setId(Integer.parseInt(lblidevent.getText()));
                p.setTitre(tftitre.getText());
                p.setDescription(tfdesc.getText());
                p.setConsultationurl(tfliencons.getText());
                p.setDate_debut(new Date(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth()));
                p.setDate_fin(new Date(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth()));
                p.setImage(labelphoto.getText());
                es.supprimer(p);
                reset();
                Refresh();
            displayEventsChart();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void ModifierEvent(ActionEvent event) {
        if (!(lblidevent.getText().isEmpty() && lblidevent.getText() == "")) {
            Boolean isValid = true;
            if (tftitre.getText().isEmpty()) {
                ctrltitre.setText("champ vide");
                isValid = false;
            } else {
                ctrltitre.setText("");
            }
            if (tfliencons.getText().isEmpty()) {
                ctrllien.setText("champ vide");
                isValid = false;
            } else {
                ctrllien.setText("");
            }
            if (tfdesc.getText().isEmpty()) {
                ctrldesc.setText("champ vide");
                isValid = false;
            } else {
                ctrldesc.setText("");
            }
            if (isValid) {
                try {
                    Evenement p = new Evenement();
                    p.setId(Integer.parseInt(lblidevent.getText()));
                    p.setTitre(tftitre.getText());
                    p.setDescription(tfdesc.getText());
                    p.setConsultationurl(tfliencons.getText());
                    p.setDate_debut(new Date(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth()));
                    p.setDate_fin(new Date(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth()));
                    p.setImage(labelphoto.getText());
                    p.setPrix(Integer.parseInt(tfprix.getText()));
                    p.setMax(Integer.parseInt(tfmax.getText()));
                    CategorieEvenement cat = combocat.getValue();
                    p.setCategorie(cat);
                    es.modifier(p);

                    Refresh();
            displayEventsChart();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        if (tfnomcat.getText().isEmpty()) {
            ctrlnom.setText("champ vide");
        } else {
            ctrlnom.setText("");
        }
        String nom = tfnomcat.getText();
        if (!nom.isEmpty()) {
            try {
                CategorieEvenement ce = new CategorieEvenement();
                ce.setNom(tfnomcat.getText());
                CS.ajouter(ce);
                resetCat();
                Refresh();
            displayEventsChart();

            } catch (SQLException ex) {
                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("fill the name please");
        }
    }

    @FXML
    private void ViderChampCat(ActionEvent event) {
        resetCat();
    }

    @FXML
    private void SupprimerCat(ActionEvent event) {
        if (!(lblidcat.getText().isEmpty() && lblidcat.getText() == "")) {

            try {
                CategorieEvenement ce = new CategorieEvenement();
                ce.setId(Integer.parseInt(lblidcat.getText()));
                ce.setNom(tfnomcat.getText());
                CS.supprimer(ce);
                resetCat();
                Refresh();
            displayEventsChart();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void ModifierCat(ActionEvent event) {
        if (!(lblidcat.getText().isEmpty() && lblidcat.getText() == "")) {
            Boolean isValid = true;
            if (tfnomcat.getText().isEmpty()) {
                ctrlnom.setText("champ vide");
                isValid = false;
            } else {
                ctrlnom.setText("");
            }

            if (isValid) {
                try {
                    CategorieEvenement p = new CategorieEvenement();
                    p.setId(Integer.parseInt(lblidcat.getText()));
                    p.setNom(tfnomcat.getText());
                    CS.modifier(p);

                    Refresh();
            displayEventsChart();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void selectCat(MouseEvent event) {

        CategorieEvenement categories = tblcategorie.getSelectionModel().getSelectedItem();
        tfnomcat.setText(categories.getNom());
        lblidcat.setText(String.valueOf(categories.getId()));
    }
}
