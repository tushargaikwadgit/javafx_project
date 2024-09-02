package com.farm.javaFiles.functions;

import com.farm.javaFiles.Screens.HomePage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchAppBar {

   

    
   boolean isAppear=false ;
    Stage stage ;

    public  SearchAppBar(Stage stage){
        this.stage = stage ;
    }


   public   BorderPane getToolBar(){

        
         

          ToolBar appBar = new ToolBar();

         

          Label animalLabel = new Label("पशू खरेदी विक्री");
          animalLabel.setFont(new Font("Arial", 25));
          animalLabel.setTextFill(Color.GREEN);
          animalLabel.setStyle("-fx-font-weight:BOLD");
          animalLabel.setLayoutX(100);
          animalLabel.setPadding(new Insets(10,0,0,0));


          TextField searchTextField = new TextField() ;
          searchTextField.setPrefWidth(300);
          searchTextField.setPrefHeight(35);
          searchTextField.setPromptText("येथे सर्च करा ");
          searchTextField.setPadding(new Insets(10,0,0,0));
          searchTextField.setStyle(" -fx-alignment: center");
          searchTextField.setVisible(false);


          Label searchTapButton = new Label("शोधा");
          searchTapButton.setFont(new Font("Arial", 15));
          searchTapButton.setTextFill(Color.GREEN);
          searchTapButton.setStyle("-fx-font-weight:BOLD");
          searchTapButton.setVisible(false);
          searchTapButton.setPadding(new Insets(10,0,0,0));




          Label animalBackLabel = new Label(" < ");
          animalBackLabel.setFont(new Font("Arial", 25));
          animalBackLabel.setTextFill(Color.GREEN);
          animalBackLabel.setStyle("-fx-font-weight:BOLD");
          animalBackLabel.setLayoutX(100);
          animalBackLabel.setPadding(new Insets(10,0,0,0));

          animalBackLabel.setOnMouseClicked((MouseEvent event)->{

            HomePage obj = new HomePage(stage);
            obj.homeScreen();

          });





          Image krushi = new Image("assets/images/screensImage/helloKrushi.png");
          ImageView krushImageView  = new ImageView(krushi);
          krushImageView.setFitHeight(50);
          krushImageView.setFitWidth(100);
          


          ImageView searchImageView = getImage(new Image("assets/images/appbarImages/search.png"));
         
          Button searchButton = getButton(searchImageView);
          searchButton.setOnAction( new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
              isAppear = !isAppear;

           
            searchTextField.setVisible(isAppear);
            

            searchTapButton.setVisible(isAppear);

            
            }

          });


          ImageView pricefilterImageView = getImage(new Image("assets/images/appbarImages/priceFilter.png"));
          Button priceButton = getButton(pricefilterImageView);

          ImageView savedImageView = getImage(new Image("assets/images/screensImage/saved.png"));
          savedImageView.setFitHeight(25);
          Button savedButton = getButton(savedImageView);

          HBox threeIconHBox = new HBox(10  ,searchTextField,searchButton, priceButton,  savedButton );
          threeIconHBox.setPadding(new Insets(10,10,0,0));

          HBox twoIconHBox = new HBox(5, animalBackLabel , animalLabel);
          HBox oneIconHBox = new HBox(10 , searchTextField , searchTapButton);
          oneIconHBox.setPrefWidth(350);
          oneIconHBox.setPadding(new Insets(10,0,0,0));
        
          
          HBox mainHBox = new HBox(360, twoIconHBox,oneIconHBox, threeIconHBox);
          

          appBar.getItems().addAll(mainHBox);
          appBar.setStyle("-fx-background-color: transparent;");

           BorderPane root = new BorderPane();
           root.setTop(appBar);

           

           return root ;
          


    }
    ImageView getImage(Image img){
        ImageView image = new ImageView(img);
        image.setFitHeight(30);
        image.setFitWidth(30);
        return image ;
    }

    Button getButton(ImageView iv){
        Button bt = new Button();
        bt.setGraphic(iv);
        bt.setStyle("-fx-background-color: transparent; -fx-border-color: transparent");

        return bt ;
    }
}

    