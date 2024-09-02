package com.farm.javaFiles.functions;


import com.farm.javaFiles.Screens.AnimalHusbandry;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.sampled.Clip;


import com.farm.firebase_connection.FirebaseService;

import com.farm.javaFiles.Screens.FarmImplements;
import com.farm.javaFiles.Screens.HomePage;
import com.farm.javaFiles.Screens.Labor;
import com.farm.javaFiles.Screens.personalAccount;
import com.farm.javaFiles.loginPage.AgricultureUI;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;



// ya class madhun appbar return hotoy

public class AppBar {
    Stage stage ;
    boolean isAppear=false ;

    public  AppBar(Stage stage){
        this.stage = stage ;
    }

    

    private FarmImplements farmImplements; // Add member variable to store FarmImplements reference
    private AnimalHusbandry animalHusbandry;
    private Set<String> availableCategories;
    private Set<String> availableCategories1;
    

    public AppBar(Stage stage, FarmImplements farmImplements,AnimalHusbandry animalHusbandry) { // Modify constructor
        this.stage = stage;
        this.farmImplements = farmImplements; // Initialize the member variable
        this.animalHusbandry = animalHusbandry;
        this.availableCategories1 = AgricultureUI.farmImplementsData.stream()
            .map(item -> item.get("Category").trim().toLowerCase())
            .collect(Collectors.toSet());
        this.availableCategories = AgricultureUI.animalHusbandryData.stream()
            .map(item -> item.get("Category").trim().toLowerCase())
            .collect(Collectors.toSet());
        // System.out.println("Available Categories: " + availableCategories);
    }
   

    // public AppBar(Stage stage2, AnimalHusbandry animalHusbandry) {
    //     this.stage = stage;
    //     this.animalHusbandry = animalHusbandry;
    // }

    String whichAppbar ;


   public BorderPane getToolBar(String whichAppbar){

        this.whichAppbar = whichAppbar ;

        System.out.println(this);
        ToolBar appBar = new ToolBar();


        Image krushi = new Image("assets/images/screensImage/helloKrushi.png");
        ImageView krushImageView  = new ImageView(krushi);
        krushImageView.setFitHeight(60);
        krushImageView.setFitWidth(120);


        TextField searchTextField = new TextField() ;
          searchTextField.setPrefWidth(300);
          searchTextField.setAlignment(Pos.CENTER);

          //searchTextField.setPrefHeight(35);
          searchTextField.setPromptText("Search Here");
          searchTextField.setPadding(new Insets(10,0,0,0));
          //searchTextField.setStyle(" -fx-alignment: center");
          searchTextField.setVisible(false);


          Label searchTapButton = new Label("Go");
          searchTapButton.setFont(new Font("Arial", 15));
          searchTapButton.setTextFill(Color.GREEN);
          searchTapButton.setStyle("-fx-font-weight:BOLD");
          searchTapButton.setVisible(false);
          searchTapButton.setPadding(new Insets(10,0,0,0));
        //   searchTapButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {
        //         String category = searchTextField.getText();
        //         if (!category.isEmpty()) {
        //             FarmImplements farmImplements = new FarmImplements(stage);
        //             farmImplements.updateGridPaneByCategory(category);
        //         }
        //     }
        //   });

        //HBox hb = new HBox(10,searchTextField,searchTapButton);
        //hb.setPadding(new Insets(15,0,0,0));


        Label post = getL("Upload CV");
      


        Image profilImage = new Image("assets/images/loginpage/profile.png");
        ImageView profileImageView = new ImageView(profilImage);
        profileImageView.setFitHeight(40);
        profileImageView.setFitWidth(40);

        Label homeLabel = getL("Home");
        Label aboutLabel = getL("About");
        Label contactLabel = getL("Contact");
        Label newsLabel = getL("News");
        Label profileLabel = getL("Profile");


        Label searchLabel = getL("Search");
        Label myJobsLabel=getL("My Jobs");
       Label farmPostAddLabel = getL("Become a Seller");
       Label cartLabel=getL("Cart");
       Label animalPostAddLabel = getL("Post Ad");
       Label notAvailabelLabel=getL("Not Available");

       profileLabel.setOnMouseClicked(e->{
        personalAccount obj = new personalAccount(stage);
        obj.getPersonalPage();
       });

        HBox navigationHBox = new HBox(60);
        navigationHBox.setAlignment(Pos.CENTER);
        navigationHBox.getChildren().addAll(homeLabel,aboutLabel,contactLabel,newsLabel,profileLabel);

        if(whichAppbar == "farm"){
            searchTapButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String category = searchTextField.getText();
                    
                        if (!category.isEmpty()) {
                            if (categoryExists1(category)) {
                                farmImplements.updateGridPaneByCategory(category);
                            } else {
                                showAlert("Not Found", "Category does not exist");
                            }
                        }
                }
              });
    
            HBox hb = new HBox(10,searchTextField,searchTapButton);
            hb.setPadding(new Insets(15,0,0,0));
            navigationHBox.getChildren().clear();
            navigationHBox.getChildren().addAll(hb,homeLabel,searchLabel,farmPostAddLabel,cartLabel); 
        }
        searchLabel.setOnMouseClicked(e->{
            isAppear = !isAppear;
            searchTextField.setVisible(isAppear);
            searchTapButton.setVisible(isAppear);

        });


        if(whichAppbar == "animal"){
            searchTapButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String category = searchTextField.getText();
                    
                        if (!category.isEmpty()) {
                            if (categoryExists(category)) {
                                AnimalHusbandry animalHusbandry = new AnimalHusbandry(stage);
                                animalHusbandry.updateGridPaneByCategory(category);
                            } else {
                                showAlert("Not Found", "Category does not exist");
                            }
                        }
                }
              });
    
            HBox hb = new HBox(10,searchTextField,searchTapButton);
            hb.setPadding(new Insets(15,0,0,0));
            navigationHBox.getChildren().clear();
            navigationHBox.getChildren().addAll(hb,homeLabel,searchLabel,farmPostAddLabel,cartLabel); 
        }

      

    
        if(whichAppbar=="labor"){

            navigationHBox.getChildren().clear();
            navigationHBox.getChildren().addAll(homeLabel,aboutLabel,myJobsLabel,post , profileLabel);
          
        }


        post.setOnMouseClicked(e->{

            if(Labor.farmerTy.trim().equals("शेतकरी")){
               FarmerForm obj = new FarmerForm(stage);
               obj.getFarmerRegisterForm();
            } else{
               LaborForm obj = new LaborForm(stage);
               obj.getWorkerRegisterForm();
            }

           
             
        });



        farmPostAddLabel.setOnMouseClicked(e->{
            if(whichAppbar.equals("animal")){
                AnimalAdForm obj = new AnimalAdForm(stage);
                obj.animalHusbandryRegister();
            }else{
                FarmAdFormExample obj1 = new FarmAdFormExample(stage);
                obj1.farmingImplementsRegister(stage);

            }
          
        });

        animalPostAddLabel.setOnMouseClicked(e->{
            AnimalAdForm obj1 = new AnimalAdForm(stage);
            obj1.animalHusbandryRegister();
            
        });   
       

        homeLabel.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                HomePage obj = new HomePage(stage);
                obj.homeScreen();
            }
            
        });
        
   


        //   ImageView personImageView = getImage(new Image("assets/images/screensImage/person.png"));
        //   personImageView.setFitHeight(35);
        //   personImageView.setFitWidth(40);
        //   Button persButton = getButton(personImageView);

        //   persButton.setOnAction( new EventHandler<ActionEvent>(){

        //     @Override
        //     public void handle(ActionEvent event) {

        //         System.out.println("hello in person");
                
        //     }

        //   });


        //   ImageView savedImageView = getImage(new Image("assets/images/screensImage/saved.png"));
        //   Button savedButton = getButton(savedImageView);

        //   ImageView notificationImageView = getImage(new Image("assets/images/screensImage/notification.png"));
        //   Button notificationButton = getButton(notificationImageView);

        //   HBox threeIconHBox = new HBox(10 ,persButton , savedButton , notificationButton);


        HBox mainHBox = new HBox(500, krushImageView , navigationHBox);

        if(whichAppbar == "farm"){
            mainHBox.setSpacing(250);
        }
        
        if(whichAppbar == "animal"){
            mainHBox.setSpacing(250);
        }

        if(whichAppbar == "labor"){
            mainHBox.setSpacing(600);
        }

        appBar.getItems().addAll(mainHBox);
        appBar.setStyle("-fx-background-color: transparent;");

       
      

        




        BorderPane root = new BorderPane();
        root.setTop(appBar);
        root.setPadding(new Insets(15, 0, 0, 50));
        // root.setStyle("-fx-background-color:lightgray");

           

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
     Label getL(String name){
        Label lb = new Label(name);
        lb.setTextFill(Color.BLACK);
        lb.setFont(Font.font("Arial", 22));

        lb.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            lb.setTextFill(Color.BLACK);
             lb.setScaleX(1.2);
             lb.setScaleY(1.2);
        });

        lb.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            lb.setTextFill(Color.BLACK);
            lb.setScaleX(1.0);
            lb.setScaleY(1.0);
        });
        return lb ;

    }   


private boolean categoryExists(String category) {
    return availableCategories.contains(category);
}

private boolean categoryExists1(String category) {
    return availableCategories1.contains(category);
}


// Method to show an alert
private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    

}
