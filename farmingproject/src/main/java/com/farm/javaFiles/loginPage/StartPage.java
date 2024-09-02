package com.farm.javaFiles.loginPage;

import com.farm.javaFiles.Screens.HomePage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartPage {


    VBox startPageVBox  ;
    Image frontImage ;
    Image backgroundImage ;
    ImageView frontImageView ;
    ImageView backgroundImageView ;
    Button signUpButton ;
    Button loginButton ; 
    Button continueBotton ;
    TextField mobile ;
    PasswordField password ;
    Button arrow;
    StackPane startPageStackPane; 
    Label wrongDetaiLabel;
    Image arrowImage;
    ImageView arrowImageView ;
    Pane arrowtopleft;
    Scene startScene;

    
    Stage stage ;

    public StartPage(Stage stage){
        this.stage = stage ;
    }

    public  Scene start(){
        


        
       
          
        backgroundImage = new Image("assets/images/loginpage/backgroundImage.jpeg");
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(920);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setLayoutX(0);
       

        frontImage = new Image("assets/images/loginpage/frontImage.png");
        frontImageView = new ImageView(frontImage);
        frontImageView.setFitHeight(800);
        frontImageView.setFitWidth(300);
        frontImageView.setPreserveRatio(true);
   

        loginButton = new Button("Login");
        loginButton.setStyle("-fx-text-fill: white");
        loginButton.setStyle("-fx-background-Color:green;-fx-text-fill:white;-fx-background-radius:15");
        loginButton.setMaxWidth(200);

        
        signUpButton = new Button("Sign up");
        signUpButton.setStyle("-fx-text-fill: white");
        signUpButton.setStyle("-fx-background-Color:green;-fx-text-fill:white;-fx-background-radius:15");
        signUpButton.setMaxWidth(200);

       

        

        startPageVBox = new VBox(30);
        startPageVBox.getChildren().addAll(frontImageView,loginButton,signUpButton);
        startPageVBox.setAlignment(Pos.CENTER);
        startPageVBox.setLayoutX(350);
        
        


        startPageStackPane = new StackPane(backgroundImageView,startPageVBox);
        startPageStackPane.setLayoutX(450);
        startPageStackPane.setAlignment(Pos.CENTER);


        

       

      
        


        Group startPageGroup  = new Group(startPageStackPane);
        Scene startScene = new Scene(startPageGroup);
       
        stage.setScene(startScene);
        stage.setTitle("LoginPage");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();


        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

          //  EmailLoginPage obj = new EmailLoginPage(stage);
          //  obj.login( new HomePage(stage));
              
             
                    
            }
            
        });

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               Register obj = new Register(stage);
               obj.register();
            }
        });
        return startScene;
    }
   
        
    }
    
