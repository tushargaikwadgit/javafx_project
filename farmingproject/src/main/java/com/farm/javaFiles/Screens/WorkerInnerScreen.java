package com.farm.javaFiles.Screens;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

import com.farm.javaFiles.functions.AppBar;


public class WorkerInnerScreen {

    public ImageView sideImageView;
    public Label nameLabel;
    public Label locationLabel;
    public Label priceLabel;
    public Label descriptionLabel;
    public VBox mainVBox;
    public Stage stage;
    public Label contactLabel;
    public Label myNameLabel;
    // String str;
    
    private List<Map<String, String>> data;

    public WorkerInnerScreen(Stage stage, List<Map<String, String>> data) {
        this.stage = stage;
        this.data = data;
    }

    public void showDetails(int index) {
        AppBar appbarObj = new AppBar(stage);
      
        BorderPane appBar = appbarObj.getToolBar("labor");
        VBox appbarVBox = new VBox(50, appBar);
        appbarVBox.setAlignment(Pos.TOP_LEFT);

        // Initialize components
        sideImageView = new ImageView();
        sideImageView.setFitHeight(450);
        sideImageView.setFitWidth(500);
        VBox sideImageVBox = new VBox(sideImageView);

        nameLabel = new Label();
        nameLabel.setFont(Font.font("Arial", FontWeight.THIN, 25));

        Image locationImage = new Image("assets/images/farmImplements/file.png");
        ImageView locationImageView = new ImageView(locationImage);
        locationImageView.setFitHeight(25);
        locationImageView.setFitWidth(25);
        locationLabel = new Label();
        locationLabel.setFont(Font.font("Arial", FontWeight.THIN, 15));
        HBox locationHBox = new HBox(locationImageView, locationLabel);

        priceLabel = new Label();
        priceLabel.setFont(Font.font("Arial", FontWeight.THIN, 22));

        Line line1 = getLine();
        Line line2 = getLine();
        Line line3 = getLine();

        Label desLabel = new Label("Description");
        desLabel.setFont(Font.font("Arial", FontWeight.THIN, 16));

        descriptionLabel = new Label();
        descriptionLabel.setFont(Font.font("Arial", FontWeight.THIN, 15));
        descriptionLabel.setWrapText(true);

        contactLabel = new Label();
        contactLabel.setFont(Font.font("Arial", FontWeight.THIN, 15));

        Label mobNumberLabel = new Label("Contact :");
        mobNumberLabel.setFont(Font.font("Arial", FontWeight.THIN, 16));
        

        myNameLabel = new Label();
        myNameLabel.setFont(Font.font("Arial", FontWeight.THIN, 15));
        

        HBox mobNumberHBox = new HBox(6, mobNumberLabel ,contactLabel);
        HBox nameHBox = new HBox(6, myNameLabel);
        
        

        Label lb1 = new Label(" ");

        VBox descriptionVBox = new VBox(desLabel, descriptionLabel, lb1);
        descriptionVBox.setMaxWidth(500); 

        Label ratingLabel = new Label("Ratings and Reviews");
        ratingLabel.setFont(Font.font("Arial", FontWeight.THIN, 16));

        Image starImage = new Image("assets/images/farmImplements/star.png");
        ImageView starImageView = new ImageView(starImage);
        starImageView.setFitHeight(15);
        starImageView.setFitWidth(15);

        Label lb = new Label("Be the first one to Rate");
        lb.setTextFill(Color.ORANGE);

        HBox rateHBox = new HBox(6, starImageView, lb);

        VBox rateVBox = new VBox(5, ratingLabel, rateHBox);

        Button contactButton = new Button("View Contact");
        contactButton.setStyle("-fx-background-color:Green; -fx-background-radius: 15; -fx-text-fill:White");

        VBox vb=new VBox(5,nameHBox,mobNumberHBox);
        vb.setVisible(false);

        contactButton.setOnAction(e->{

            vb.setVisible(!vb.isVisible());
         });

        
        Button gobackButton = new Button("<-");
        gobackButton.setStyle("-fx-background-color:Green; -fx-background-radius: 15; -fx-text-fill:White");
        gobackButton.setPrefWidth(40);
        gobackButton.setOnAction(e->{

            FarmImplements obj1 = new FarmImplements(stage);
            obj1.getfarmImplements();
             
         });

        mainVBox = new VBox(15);
        mainVBox.getChildren().addAll(nameLabel, locationHBox, priceLabel, line1, descriptionVBox, line2, rateVBox, line3, contactButton,vb);

        Pane p = new Pane();
        p.getChildren().addAll(appbarVBox,gobackButton, sideImageVBox, mainVBox);

        gobackButton.setLayoutX(65);
        gobackButton.setLayoutY(100);

        sideImageVBox.setLayoutX(140);
        sideImageVBox.setLayoutY(170);

        mainVBox.setLayoutX(700);
        mainVBox.setLayoutY(200);

        Group gp = new Group(p);
        Scene scene = new Scene(gp);
        stage.setScene(scene);
        stage.setTitle("Farm Implement Details");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();

        // Initialize the details view with the first item
        updateDetailsView(index);
    }

    private void updateDetailsView(int index) {
        if (data == null || data.size() <= index) {
            System.out.println("Invalid index or farmImplementsData is null");
            return;
        }

        Map<String, String> data1 = data.get(index);

        if (sideImageView != null) {
            sideImageView.setImage(new Image(data1.get("laborImage")));
        } else {
            System.out.println("sideImageView is null");
        }

        if (nameLabel != null) {
            nameLabel.setText(data1.get("Profession"));
        } else {
            System.out.println("nameLabel is null");
        }

        if (locationLabel != null) {
            locationLabel.setText(data1.get("City") + ", " + data1.get("Locality"));
        } else {
            System.out.println("locationLabel is null");
        }

        if (priceLabel != null) {
            priceLabel.setText("₹" + data1.get("HiringFee"));
        } else {
            System.out.println("priceLabel is null");
        }

        if (descriptionLabel != null) {
            descriptionLabel.setText(data1.get("Description"));
        } else {
            System.out.println("descriptionLabel is null");
        }

        if (contactLabel != null){
            contactLabel.setText(data1.get("MobileNumber"));
        }else{
            System.out.println("Contact is null");
        }

        if (myNameLabel != null){
            myNameLabel.setText(data1.get("Name"));
        }else{
            System.out.println("Name is null");
        }

        
    }

    private Line getLine() {
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(490);
        line.setEndY(0);
        line.setStroke(Color.LIGHTGRAY);
        return line;
    }
}
