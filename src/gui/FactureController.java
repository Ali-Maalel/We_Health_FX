/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Evenement;
import entities.Facture;
import entities.Participants;
import entities.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.EvenementService;
import services.FactureService;
import services.ParticipantsService;
import services.UserService;
import utils.MYDB;
import utils.MyListener;
import utils.javaMailUtil;

/**
 *
 * @author sarra
 */
public class FactureController {

    private MyListener myListener;
    private Evenement e;
    private Participants p;
    private Facture f;
    @FXML
    private Label lblNum;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblNomEvent;
    @FXML
    private Label lblPrice;
    @FXML
    private Button cancelBtn;

    public void setData(Evenement e, Facture f, Participants p, MyListener myListener) {
        this.e = e;
        this.f = f;
        this.p = p;
        this.myListener = myListener;
        lblNom.setText("Nom: " + p.getUser().getNom());
        lblPrenom.setText("Prenom: " + p.getUser().getPrenom());
        lblEmail.setText("Email: " + p.getUser().getEmail());
        lblPrice.setText("Prix: " + p.getEvent().getPrix() + " TND");
        lblNomEvent.setText("Evennement: " + p.getEvent().getTitre());
        lblDate.setText("Date: " + f.getCreated_at());
        lblNum.setText("Facture N° " + f.getNum());
    }

    @FXML
    private void confirmRes(ActionEvent event) throws SQLException, Exception {
        ParticipantsService parS = new ParticipantsService();
        FactureService facS = new FactureService();
        EvenementService evtS = new EvenementService();

        int max = e.getMax() - 1;
        int res = e.getReservations() + 1;
        System.out.println(max + " " + res);
        e.setMax(max);
        e.setReservations(res);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to proceed?");
        alert.setContentText("Click 'Yes' to proceed, or 'No' to cancel.");

        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            evtS.modifier(e);
        parS.ajouter(p);
        facS.ajouter(f);
        javaMailUtil.sendMail(f.getUser().getEmail(), e.getTitre());
        } else {
            alert.close();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void printFac(ActionEvent event) throws SQLException, FileNotFoundException, Exception {
        exportTable();
    }

    public void exportTable() throws SQLException, FileNotFoundException {
        try {
            FactureService facS = new FactureService();
            List<Facture> factures = facS.getAll(f.getId());

            Document my_pdf_report = new Document();
            int min = 10000;
            int max = 99999;
            int random_id = (int) Math.floor(Math.random() * (max - min + 1) + min);
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users/sarra/OneDrive/Desktop/projetFX/We_Health_FX/src/uploads/factures/facture" + random_id + ".pdf"));

            my_pdf_report.open();
            my_pdf_report.add(new Paragraph("                                      Factures report", FontFactory.getFont("Arial", 20)));
            my_pdf_report.add(new Paragraph("  "));
            my_pdf_report.add(new Paragraph("  "));

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(4);

            //create a cell object
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("Num"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Date"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("User"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Prix"));
            my_report_table.addCell(table_cell);

            for (Facture facture : factures) {
                String num = facture.getNum() + "";
                table_cell = new PdfPCell(new Phrase(num));
                my_report_table.addCell(table_cell);
                String created_at = facture.getCreated_at() + "";
                table_cell = new PdfPCell(new Phrase(created_at));
                my_report_table.addCell(table_cell);

                UserService userS = new UserService();
                User user = facture.getUser();
                String userName = user.getNom() + " " + user.getPrenom();
                table_cell = new PdfPCell(new Phrase(userName));
                my_report_table.addCell(table_cell);

                String price = facture.getPrix() + "";
                table_cell = new PdfPCell(new Phrase(price));
                my_report_table.addCell(table_cell);

                System.out.println(facture);
            }

            Notifications notifications = Notifications.create();
            notifications.text("Facture downloaded successfully");
            notifications.title("Notification");
            notifications.hideAfter(Duration.seconds(4));
            notifications.darkStyle();
            notifications.show();
            my_pdf_report.addTitle("Factures report");
            my_pdf_report.add(my_report_table);

            my_pdf_report.close();

        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
