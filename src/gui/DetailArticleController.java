/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Article;
import entities.Comment;
import entities.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.ArticleService;
import services.CommentService;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class DetailArticleController implements Initializable {

    private static int idA;
    private static int i = 0;
    private static Comment commentaire = new Comment();
    @FXML
    private Button exit;
    @FXML
    private Button returnP;
    @FXML
    private AnchorPane AA;
    @FXML
    private VBox VV;
    @FXML
    private BorderPane PP;
    @FXML
    private Button titleeP;
    @FXML
    private ImageView PicP;
    @FXML
    private Label dateP;
    @FXML
    private Label descP;
    @FXML
    private Button pdf;
    @FXML
    private Button clear;
    @FXML
    private Button val1;
    @FXML
    private Button val2;
    @FXML
    private Button val3;
    @FXML
    private Button val4;
    @FXML
    private Button val5;
    @FXML
    private Label rateP;
    @FXML
    private VBox ContainerVBox;
    @FXML
    private ScrollPane scrollCom;
    @FXML
    private VBox VBoxCom;
    @FXML
    private TextArea tfCom;
    @FXML
    private Button ajoutCom;
    @FXML
    private Button AnnulerCom;
    private  Article article;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfCom.setPromptText("Add your comment..");
        DetailArticleController.i = 0;
        try {
            ShowComments();
        } catch (SQLException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(i);
    }

    public  Article getArticle() {
        return article;
    }

    public  void setArticle(Article article) {
        this.article = article;
        System.out.println("setArticle"+ this.article.toString());
    }

    public static int getIdA() {
        return idA;
    }

    public static void setIdA(int idA) {
        DetailArticleController.idA = idA;
    }

    public void setArticleView(Article article) {
        this.article=article;
        System.out.println("setArticleView"+ this.article.toString());
        titleeP.setText(this.article.getTitre());
        descP.setText(this.article.getContenu());
        dateP.setText("" + this.article.getCreated_at());
        if (this.article.getNbrRate() == 0) {
            rateP.setText(String.valueOf((int) this.article.getNbrRate()) + "/5");
        } else {
            DecimalFormat df = new DecimalFormat("#.0");
            rateP.setText(String.valueOf(df.format(this.article.getNbrRate())) + "/5");
        }

        System.out.println(this.article.getNum_media().getNom_fichier());
        FileInputStream input;
        try {
            input = new FileInputStream(this.article.getNum_media().getNom_fichier());
            Image image = new Image(input);

            PicP.setImage(image);
            PicP.setPreserveRatio(true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleQuitter(ActionEvent event) {
        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

    @FXML
    private void retourBlog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowArticleFront.fxml"));
            Parent root = loader.load();
            returnP.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generateePDF(ActionEvent event) {
        try {
            generatePDF(this.article);
            System.out.println("pdf générée");
        } catch (Exception ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clearValue(ActionEvent event) {
    }

    @FXML
    private void val1(ActionEvent event) {
        System.out.println("here");
        try {
            ArticleService ps = new ArticleService();
            Article post = ps.recupererById(new Article(idA)).get(0);
            int nbr = post.getRate();
            double moy = post.getNbrRate();
            double summ = nbr * moy;
            
            post.setRate(nbr + 1);
            
            post.setNbrRate((summ + 1) / post.getRate());
            System.out.println(post.toString());
            ps.modifier(post);
            DecimalFormat df = new DecimalFormat("#.0");
            rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");
        } catch (SQLException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void val2(ActionEvent event) {
        try {
            ArticleService ps = new ArticleService();
            Article post = ps.recupererById(new Article(idA)).get(0);
            int nbr = post.getRate();
            double moy = post.getNbrRate();
            double summ = nbr * moy;
            post.setRate(nbr + 1);
            System.out.println(post.toString());
            post.setNbrRate((summ + 2) / post.getRate());
            ps.modifier(post);
            DecimalFormat df = new DecimalFormat("#.0");
            rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");
        } catch (SQLException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void val3(ActionEvent event)  {

        try {
            ArticleService ps = new ArticleService();
            Article post = ps.recupererById(new Article(idA)).get(0);
            int nbr = post.getRate();
            double moy = post.getNbrRate();
            double summ = nbr * moy;
            post.setRate(nbr + 1);
            
            post.setNbrRate((summ + 3) / post.getRate());
            DecimalFormat df = new DecimalFormat("#.0");
            rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");
        } catch (SQLException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void val4(ActionEvent event) {
        try {
            ArticleService ps = new ArticleService();
            Article post = ps.recupererById(new Article(idA)).get(0);
            System.out.println(idA);
            int nbr = post.getRate();
            double moy = post.getNbrRate();
            double summ = nbr * moy;
            post.setRate(nbr + 1);
            
            post.setNbrRate((summ + 4) / post.getRate());
            ps.modifier(post);
            DecimalFormat df = new DecimalFormat("#.0");
            rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");
        } catch (SQLException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void val5(ActionEvent event) {
        try {
            ArticleService ps = new ArticleService();
            Article post = ps.recupererById(new Article(idA)).get(0);
            int nbr = post.getRate();
            double moy = post.getNbrRate();
            double summ = nbr * moy;
            post.setRate(nbr + 1);
            
            post.setNbrRate((summ + 5) / post.getRate());
            ps.modifier(post);
            DecimalFormat df = new DecimalFormat("#.0");
            rateP.setText(String.valueOf(df.format(post.getNbrRate())) + "/5");
        } catch (SQLException ex) {
            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void newComment(ActionEvent event) {
        ArticleService utilisateurService = new ArticleService();
        CommentService cs = new CommentService();

        if (i == 0) {
            if (verifs()) {
                try {
                    long millis = System.currentTimeMillis();
                    Comment p = new Comment(article, new User(1), filterWords(tfCom.getText()), new Date(millis));
                    cs.ajouter(p);
                    
                    tfCom.setPromptText("Add your comment..");
                    tfCom.clear();
                    System.out.println(DetailArticleController.i);
                    try {
                        VBoxCom.getChildren().clear();
                        ShowComments();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            if (verifs()) {
                try {
                    DetailArticleController.commentaire.setContenu(filterWords(tfCom.getText()));
                    cs.modifier(DetailArticleController.commentaire);
                    tfCom.setPromptText("Add your comment..");
                    tfCom.clear();
                    
                    DetailArticleController.i = 0;
                    System.out.println(DetailArticleController.i);
                    try {
                        VBoxCom.getChildren().clear();
                        ShowComments();
                    } catch (SQLException ex) {
                        Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @FXML
    private void annulerC(ActionEvent event) {
    }

    private boolean verifs() {
        if (tfCom.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champ commentaire est obligatoire !");
            return false;
        }
        return true;
    }

    public static String filterWords(String text) {
        String[] filterWords = {"ahla", "word2", "word3"};
        String[] data = text.split("\\s+");
        String str = "";
        for (String s : data) {
            boolean g = false;
            for (String lib : filterWords) {
                if (s.equals(lib)) {
                    String t = "";
                    DetailArticleController.sendMail();
                    for (int i = 0; i < s.length(); i++) {
                        t += "*";
                    }
                    str += t + " ";
                    g = true;
                    break;
                }
            }
            if (!g) {
                str += s + " ";
            }
        }
        return str.trim();
    }

    private void ShowComments() throws SQLException {
        ArticleService ps = new ArticleService();
        List<Comment> comments = ps.showComments(new Article(idA));
        System.out.println(comments);
        Collections.reverse(comments);

        if (comments.size() == 0) {
            Pane pane = new Pane();
            pane.setLayoutX(10.0);
            pane.setLayoutY(30.0);
            pane.setPrefHeight(208.0);
            pane.setPrefWidth(458.0);
            pane.setStyle("-fx-border-color: #ffffff;");

            Label label2 = new Label("No comments found for this post");
            label2.setAlignment(Pos.CENTER);
            label2.setLayoutY(10.0);
            label2.setPrefHeight(93.0);
            label2.setPrefWidth(458.0);
            label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 0 0 0 0;");
            label2.setFont(Font.font("Calibri Italic", 30));
            label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));

            pane.getChildren().addAll(label2);
            VBoxCom.getChildren().add(pane);
            scrollCom.setContent(VBoxCom);
        } else {
            for (int i = 0; i < comments.size(); i++) {
                Comment com = comments.get(i);
                System.out.println(com.toString());
                Pane pane = new Pane();
                pane.setLayoutX(10.0);
                pane.setLayoutY(30.0);
                pane.setPrefHeight(208.0);
                pane.setPrefWidth(458.0);
                pane.setStyle("-fx-border-color: #DCDCDC;");

                Label label1 = new Label("Comment owner");
                label1.setLayoutX(76.0);
                label1.setLayoutY(12.0);
                label1.setPrefHeight(44.0);
                label1.setPrefWidth(163.0);
                label1.setFont(Font.font("Calibri Bold Italic", 20));

                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../images/comment_2.png")));
                imageView.setFitHeight(53.0);
                imageView.setFitWidth(78.0);
                imageView.setLayoutX(14.0);
                imageView.setLayoutY(9.0);
                imageView.setPreserveRatio(true);

                Label label2 = new Label(com.getContenu());
                label2.setAlignment(Pos.TOP_LEFT);
                label2.setLayoutY(69.0);
                label2.setPrefHeight(93.0);
                label2.setPrefWidth(458.0);
                label2.setStyle("-fx-border-color: #DCDCDC; -fx-border-width: 1 0 0 0;");
                label2.setFont(Font.font("Calibri Italic", 20));
                label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));

                Label label3 = new Label("" + com.getCreated_at());
                label3.setLayoutX(14.0);
                label3.setLayoutY(163.0);
                label3.setPrefHeight(23.0);
                label3.setPrefWidth(186.0);
                label3.setFont(Font.font("Calibri Bold Italic", 18));
                label3.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));
                Button SuppC = new Button();
                SuppC.setLayoutX(408.0);
                SuppC.setLayoutY(147.0);
                SuppC.setMnemonicParsing(false);
                SuppC.getStyleClass().add("buttonNext");
                ImageView imageSupp = new ImageView(new Image(getClass().getResourceAsStream("../images/icons8-delete-trash-50.png")));
                imageSupp.setFitHeight(24.0);
                imageSupp.setFitWidth(26.0);
                imageSupp.setPickOnBounds(true);
                imageSupp.setPreserveRatio(true);
                SuppC.setGraphic(imageSupp);
                SuppC.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Comment");
                    alert.setHeaderText("Are you sure you want to delete this Comment?");
                    alert.setContentText("This action is required!");

                    // Show the confirmation dialog and wait for the user's response
                    Optional<ButtonType> result = alert.showAndWait();

                    // If the user clicks "OK", delete the fournisseur
                    if (result.get() == ButtonType.OK) {
                        CommentService fs = new CommentService();
                        try {
                            fs.supprimer(com);
                            DetailArticleController.i = 0;
                            tfCom.setPromptText("Add your comment..");
                            tfCom.setText(null);
                            System.out.println(DetailArticleController.i);
                            VBoxCom.getChildren().clear();
                            ShowComments();
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailArticleController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        // Close the dialog and do nothing
                        alert.close();
                    }
                });

                Button ModC = new Button();
                ModC.setLayoutX(380.0);
                ModC.setLayoutY(147.0);
                ModC.setMnemonicParsing(false);
                ModC.getStyleClass().add("buttonNext");
                ImageView imageMod = new ImageView(new Image(getClass().getResourceAsStream("../images/a.gif")));
                imageMod.setFitHeight(24.0);
                imageMod.setFitWidth(26.0);
                imageMod.setPickOnBounds(true);
                imageMod.setPreserveRatio(true);
                ModC.setGraphic(imageMod);
                ModC.setOnAction(event -> {
                    tfCom.setText(com.getContenu());
                    tfCom.setPromptText("Modify " + com.getContenu() + "..");
                    DetailArticleController.i = 1;
                    int a = DetailArticleController.i;
                    System.out.println(a);
                    DetailArticleController.commentaire = com;
                    System.out.println(DetailArticleController.commentaire);
                });
                pane.getChildren().addAll(label1, imageView, label2, label3, SuppC, ModC);

                VBoxCom.getChildren().add(pane);
//vBox.setPadding(new Insets(20.0));

            }
            scrollCom.setContent(VBoxCom);
        }
    }

    public static void sendMail() {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "arco.sc0156@gmail.com";
        String password = "hghseksuroiqviag";

        // Set the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a new email session using the specified properties
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message msg = new MimeMessage(session);

            // Set the "From" address for the email
            // msg.setFrom(new InternetAddress("ahmed.benabid2503@gmail.com"));
            // Add the "To" address for the email (including the recipient's name)
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yasmine.ghouili@esprit.tn"));

            // Set the subject and body text for the email
            msg.setSubject("Avertissement!");
            msg.setText("Votre commentaire contient des mots innapropriés, la deuxième avertissement votre compte dera bloqué");
            // Create an alert to notify the user that the email was sent successfully

            // Send the email
            Transport.send(msg);

            // Print a message to the console to indicate that the email was sent successfully
        } catch (AddressException e) {
            // Create an alert to notify the user that there was an error with the email address
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    public static void generatePDF(Article c) throws Exception {
        Document document = new Document();
        String fileName = "Article" + c.getId() + ".pdf";

        // Ouvrir une fenêtre de choix de fichier pour sélectionner l'emplacement où enregistrer le fichier PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pdf pour l'article " + c.getId());
        fileChooser.setInitialFileName(fileName);
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Enregistrer le fichier PDF à l'emplacement sélectionné
            PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
            document.open();

            // Ajouter les informations du ticket
            com.itextpdf.text.Font fontTitre = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 24, com.itextpdf.text.Font.BOLD);
            Paragraph titre = new Paragraph(c.getTitre(), fontTitre);
            titre.setAlignment(Element.ALIGN_CENTER);
            titre.setSpacingAfter(20f);
            document.add(titre);

//     Image image = new Image(c.getImage());
//      image.setAlignment(Element.ALIGN_CENTER);
//       image.scaleAbsolute(400, 200); // ajuster la taille de l'image en points
//        document.add((Element) image);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14); // créer une police avec une taille de 12 points

//        Paragraph info = new Paragraph();
//        info.setAlignment(Element.ALIGN_CENTER);
//        info.setSpacingBefore(20f);
//        info.setSpacingAfter(10f);
//        document.add(info);
            Paragraph info = new Paragraph("Description d'article: " + c.getContenu(), font);
            info.setSpacingAfter(5f);
            document.add(info);

            Paragraph ref = new Paragraph("note: " + c.getNbrRate() + "/5", font);
            ref.setSpacingAfter(5f);
            document.add(ref);

            Paragraph date = new Paragraph("Date de publication: " + c.getCreated_at(), font);
            date.setSpacingAfter(5f);
            document.add(date);

            document.close();

            // Ouvrir le fichier PDF une fois qu'il est enregistré
            Desktop.getDesktop().open(selectedFile);
        }
    }

}
