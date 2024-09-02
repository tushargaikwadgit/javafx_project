package com.farm.javaFiles.loginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.farm.controller.LoginController;
import com.farm.firebase_connection.DataService;
import com.farm.firebase_connection.DisplayDataScene;
import com.farm.firebase_connection.FirebaseService;
import com.farm.firebase_connection.FirebaseService1;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.farm.javaFiles.Screens.HomePage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmailLoginPage {

   // int flag1 =0 ;

     TextField emailField ;
    PasswordField passwordField ;
    Image backgroundImage ;
    ImageView backgroundImageView ;
    Button  continueBotton;
    Image arrowImage;
    ImageView arrowImageView ;
    Button arrow;
    VBox startPageVBox;
    StackPane startPageStackPane;
    FirebaseService1 firebaseService1;

    Stage stage ;

    public  EmailLoginPage(Stage stage){
      
        this.stage = stage ;
    }


    public static String  userEmail = "";
    public static String  userProfile = "";
    public static String  userName = "";
    public static String  farmerType = "";
    public static boolean isPosted = false ;

    public static  List<Map<String , String>> jobApplied;



      public void fetchLoginUserDetail(String email){
     
      try {
         
          DocumentSnapshot document = DataService.getUserCrediential(email);
          userName    =  document.getString("name");
          farmerType  =  document.getString("farmerType");
          userProfile = document.getString("profileImage");
          userEmail   =  document.getString("email");
        
          System.out.println(farmerType);
          System.out.println(userEmail);
         
      } catch (Exception p) {
         
        System.out.println(p);
      }
  }

  public void fetchUserJobAppliedData(String email){
    DisplayDataScene obj = new DisplayDataScene(stage);
    try {
        obj.getJobAppliedList(email);
        jobApplied = DisplayDataScene.getJobApplied();
        isPosted = true ;
        System.out.println(jobApplied);
    } catch (Exception p) {
       System.out.println("Job Post Not Available");
       isPosted = false;
    }
}



   public  VBox getLoginField(HomePage obj1){


        emailField = new TextField() ;
        emailField.setPrefWidth(350);
        emailField.setPromptText("Enter your email...");
        emailField.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 3 0 ;-fx-text-fill: white");

        passwordField = new PasswordField();
       passwordField.setPrefWidth(350);
        passwordField.setPromptText("Enter Password");
        passwordField.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 4 0 ;-fx-text-fill: white");
        

        backgroundImage = new Image("assets/images/loginpage/backgroundImage.jpeg");
        backgroundImageView = new ImageView(backgroundImage);
       //   backgroundImageView.setFitHeight(920);
       //   backgroundImageView.setFitWidth(600);
       //backgroundImageView.setLayoutX(0);

     

        firebaseService1 = new FirebaseService1(this,emailField,passwordField);



        continueBotton= new Button("Continue");
        continueBotton.setStyle("-fx-text-fill: white");
        continueBotton.setStyle("-fx-background-color: white ;-fx-text-fill:black ;-fx-background-radius:10");
        continueBotton.setMaxWidth(350);



      continueBotton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {

              String emailString = emailField.getText();
              fetchLoginUserDetail(emailString);
              fetchUserJobAppliedData(emailString);
               firebaseService1.continuemethod(obj1);
              
            }
            
        });


        arrowImage = new Image("assets/images/screensImage/goback.png");
        arrowImageView = new ImageView(arrowImage);
        arrowImageView.setFitHeight(20);
        arrowImageView.setFitWidth(20);

        arrow=new Button();
        arrow.setGraphic(arrowImageView);
        arrow.setOnAction(new  EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
             
             
                // StartPage obj = new StartPage(stage);
                // obj.start();
  
            }
            
        });



          startPageVBox = new VBox(30);
          startPageVBox.getChildren().addAll( emailField , passwordField , continueBotton);
       
        
        VBox vBox = new VBox(30,emailField , passwordField , continueBotton);

   
        return vBox ;

    }
    
}
