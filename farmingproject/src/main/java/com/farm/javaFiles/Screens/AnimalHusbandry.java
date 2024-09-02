package com.farm.javaFiles.Screens;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.farm.javaFiles.functions.AppBar;
import com.farm.javaFiles.functions.SearchAppBar;
import com.farm.javaFiles.loginPage.AgricultureUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AnimalHusbandry {

   

    private GridPane gp;
    private ScrollPane scrollPane;
    private Stage stage;
    private VBox finalVBox;

    public AnimalHusbandry(Stage stage) {
        this.stage = stage;
        initializeGridPane();
        initializeScrollPane();
        initializeAppBar();
    }

    private void initializeGridPane() {
        gp = new GridPane();
        gp.setHgap(80);
        gp.setVgap(60);
        gp.setPadding(new Insets(50, 80, 50, 80));
        gp.setStyle("-fx-background-color:LIGHTGRAY");
    }

    private void initializeScrollPane() {
        scrollPane = new ScrollPane(gp);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private void initializeAppBar() {
        AppBar appbarObj = new AppBar(stage, null,this);
        BorderPane appBar = appbarObj.getToolBar("animal");
        finalVBox = new VBox(50, appBar);
    }

    

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public VBox getFinalVBox() {
        return finalVBox;
    }

    public void getanimalHusbandary() {
        System.out.println("In AnimalHusbandry");
       getAnimalData(AgricultureUI.animalHusbandryData); 
    }
   

    private void getAnimalData(List<Map<String, String>> data) {
        int columns = 3;
        int numItems = data.size();
        int row = 0;
        int col = 0;

        for (int i = 0; i < numItems; i++) {
            VBox animalImageView = takeImage(new Image(data.get(i).get("AnimalHusbandryImage")));
            Label nameLabel = getNameLabel(data.get(i).get("AnimalName"));
            nameLabel.setWrapText(true);
            Label categoryLabel = getLocation("Category : " + data.get(i).get("Category"));
            ImageView locationImageView = new ImageView(new Image("assets/images/farmImplements/file.png"));
            locationImageView.setFitWidth(20);
            locationImageView.setFitHeight(20);
            Label locationLabel = getLocation(data.get(i).get("City") + "," + data.get(i).get("Locality"));
            HBox hb = new HBox(2, locationImageView, locationLabel);
            Label rupeekalvadLabel = getPriceLabel("₹" + data.get(i).get("price") );
            Label contactLabel = getContactLabel("Description:\n" + data.get(i).get("Description"));

            VBox animalVBox = getVBox(animalImageView, nameLabel, categoryLabel, hb, rupeekalvadLabel, contactLabel);

            int index = i;
            animalVBox.setOnMouseClicked(e -> {
                AnimalInnerScreen animalInnerScreen = new AnimalInnerScreen(stage, data);
                clearGridPane();
                animalInnerScreen.showDetails(index);
            });

            gp.add(animalVBox, col, row);
            col++;
            if (col == columns) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(gp);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox vb = new VBox(finalVBox, scrollPane);
      
        Scene scene = new Scene(vb);
        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);
        stage.setTitle("Animal Husbandry");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }
   
    public void clearGridPane() {
        if (gp != null) {
            gp.getChildren().clear();
        } else {
            System.out.println("GridPane (gp) is null. Initialize it first.");
        }
    }

    public void updateGridPaneByCategory(String category) {
        clearGridPane();
        List<Map<String, String>> filteredData = AgricultureUI.animalHusbandryData.stream()
                .filter(item -> item.get("Category").equalsIgnoreCase(category))
                .collect(Collectors.toList());
        getAnimalData(filteredData);
    }

    


     Label getNameLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        // lb.setMaxWidth(270);
        // lb.setPrefHeight(40);
        // lb.setPadding(new Insets(0,0,0,45));
        lb.setAlignment(Pos.CENTER_LEFT);


        return lb ;
    }

   Label  getLocation(String name){

        Label lb = new Label(name);
        lb.setFont(new Font(15));
        lb.setAlignment(Pos.CENTER_LEFT);
        return lb ;
    }
    

    Label getPriceLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb.setStyle("-fx-text-fill: green");
        // lb.setMaxWidth(270);
        // lb.setPrefHeight(40);
        // lb.setPadding(new Insets(0,0,0,90));
        lb.setAlignment(Pos.CENTER_LEFT);
        return lb ;
    }

    Label getContactLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Calibri", FontWeight.EXTRA_LIGHT, 14));
        // lb.setMaxWidth(270);
        // lb.setPrefHeight(40);
        // lb.setPadding(new Insets(0,0,0,75));
        lb.setAlignment(Pos.CENTER_LEFT);
      //  lb.setStyle("-fx-background-color: green; -fx-padding: 10;-fx-background-radius: 10 ;-fx-border-radius: 10;-FX-TEXT-FILL:WHITE");
        lb.setWrapText(true);
        

        return lb ;
    }



    public static VBox takeImage(Image image){
        ImageView img = new ImageView(image);
        img.setFitWidth(220);
        img.setFitHeight(200);
        img.setStyle("-fx-background-color: white; -fx-border-color: transparent");

        Rectangle clip = new Rectangle(210, 190);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        img.setClip(clip);
        VBox vb = new VBox(img);
        vb.setAlignment(Pos.CENTER);

      
        return vb ;
    }


    public static VBox getVBox(VBox iv,Label lb,Label lb4,HBox hb,Label lb1 , Label lb2 ){
        VBox vb = new VBox(iv, lb,lb4,hb,lb1 ,lb2);
        vb.setSpacing(10);
        // vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
        vb.setPadding(new Insets(25));
        vb.setPrefWidth(370);
        vb.setAlignment(Pos.CENTER_LEFT);



        vb.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
             vb.setScaleX(1.08);
             vb.setScaleY(1.08);
             vb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
        });

        vb.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            vb.setScaleX(1.0);
            vb.setScaleY(1.0);
            vb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");

        });

        return vb ;
    }

    
}
