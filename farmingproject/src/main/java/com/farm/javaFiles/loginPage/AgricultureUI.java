package com.farm.javaFiles.loginPage;



import com.farm.firebase_connection.DisplayDataScene;
import com.farm.javaFiles.Screens.HomePage;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;
import java.util.Map ;


public class AgricultureUI {

    Stage stage ;
    public  AgricultureUI( Stage stage){
        this.stage = stage ;
    }

    
   

    public static  List<Map<String , String>> laborData ;
    public static  List<Map<String , String>> farmImplementsData  ;
    public static  List<Map<String , String>> animalHusbandryData  ;
    public static  List<Map<String , String>> farmerData ;



    public boolean isLarge = true;


    public void fetchData(){  
        try{
            // gp.getChildren().clear();
            DisplayDataScene obj = new DisplayDataScene(stage);
            obj.displayData();
            laborData = DisplayDataScene.getLaborData();
            // getGrid();
            
        
        

        }catch(Exception p){
            System.out.println("error data not get");

        }


    }

    public void fetchFarmerData(){
        try{
            DisplayDataScene obj = new DisplayDataScene(stage);
            obj.displayFarmerData();
            farmerData = DisplayDataScene.getFarmerData();
           
        }catch(Exception e){
            System.out.println("hello");
        }
    }
    public void fetchFarmImplementData(){
        DisplayDataScene obj = new DisplayDataScene(stage);
        try {
            obj.getFarmImplementsData();
            farmImplementsData = DisplayDataScene.getFarmImplementsList();
        } catch (Exception p) {
            System.out.println(p);
        }
    }


    public void fetchAnimalHusbandryData(){
        DisplayDataScene obj = new DisplayDataScene(stage);
        try {
            obj.getAnimalHusbandryData();
            animalHusbandryData = DisplayDataScene.getAnimalHusbandryList();
        } catch (Exception p) {
            System.out.println(p);
        }
    }
   
    
    public void startScreen(){


          EmailLoginPage obj = new EmailLoginPage(stage);
          VBox vBox = obj.getLoginField(new HomePage(stage));
          vBox.setLayoutX(190);
          vBox.setLayoutY(300);
          vBox.setVisible(false);

         Register obj2  = new Register(stage);
           VBox registerVBox =  obj2.register();
           registerVBox.setLayoutX(190);
           registerVBox.setLayoutY(230);
         registerVBox.setVisible(false);

       
    
        Image backgroundImage = new Image("assets/images/AgricultureUI/background.jpeg");
        ImageView backgroundImageView  = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(900);
        backgroundImageView.setFitWidth(1480);
        
        Image topImage = new Image("assets/images/AgricultureUI/topbar.jpeg");
        ImageView topImageView  = new ImageView(topImage);
        topImageView.setFitHeight(120);
        topImageView.setFitWidth(1480);
        
        
        Image krushi = new Image("assets/images/AgricultureUI/hellokrushi.png");
        ImageView krushImageView  = new ImageView(krushi);
        krushImageView.setFitHeight(70);
        krushImageView.setFitWidth(150);

        Label homeLabel = getL("Home");
        Label aboutLabel = getL("About");
        Label pagesLabel = getL("Pages");
        Label projectLabel = getL("Project");
        Label newsLabel = getL("News");

        HBox navigationHBox = new HBox(45);
        navigationHBox.setAlignment(Pos.CENTER);
        navigationHBox.getChildren().addAll(homeLabel,aboutLabel,pagesLabel,projectLabel,newsLabel);

        Image callImage=new Image("assets/images/AgricultureUI/callIcon.png");
        ImageView callImageView  = new ImageView(callImage);
        callImageView.setFitHeight(20);
        callImageView.setFitWidth(20);

        // Label callLabel=new Label("Call Anytime");
        // callLabel.setStyle("-fx-text-fill: white");
        // callLabel.setFont(new Font(15));

        // Label mobNumber=new Label("0123456789");
        // mobNumber.setFont(new Font(15));
        // mobNumber.setStyle("-fx-text-fill: white");

        
    //     VBox contactBox=new VBox();
    //     contactBox.getChildren().addAll(callLabel,mobNumber);
    //    contactBox.setStyle("-fx-background-color:aqua");
    //     contactBox.setPrefHeight(10);
        

        Label callLabel = new Label("Call Anytime\n0123456789");
        callLabel.setStyle("-fx-text-fill: white");
        
        HBox callHBox=new HBox();
        callHBox.getChildren().addAll(callImageView,callLabel);
        callHBox.setStyle("-fx-background-color: #ffc107; -fx-background-radius:5; ");
        callHBox.setPrefHeight(40);
        callHBox.setPrefWidth(120);
        callHBox.setAlignment(Pos.CENTER);

        
        
        // HBox contactBox=new HBox(7);
        // contactBox.setAlignment(Pos.CENTER);
        // contactBox.getChildren().addAll(callImageView);
        // contactBox.setStyle("-fx-background-color: #ffc107; -fx-background-radius:5");

        HBox navHBox=new HBox(250);
        navHBox.getChildren().addAll(krushImageView,navigationHBox);
        navHBox.setPadding(new Insets(10,0,0,100));

        BorderPane topBar=new BorderPane();
        topBar.setTop(navHBox);
        topBar.setPadding(new Insets(20,0,0,0));

       

        Label mainLabel=new Label("WELCOME TO AGRICULTURE FARM");
        mainLabel.setFont(new Font(15));
        mainLabel.setStyle("-fx-text-fill:white; -fx-font-weight:BOLD");
        mainLabel.setTextFill(Color.WHITE);

        Label agriLabel=new Label("Agriculture\n& Eco Farming");
        agriLabel.setFont(Font.font("Georgia", 35));
        agriLabel.setStyle("-fx-text-fill:white");
        agriLabel.setTextFill(Color.WHITE);

        Label descriptionLabel=new Label("There are many of passeges of lorem lpsum,but the majori\nhas suffered with alteration in some form");
        descriptionLabel.setFont(new Font(15));
        descriptionLabel.setStyle("-fx-text-fill:white");
        descriptionLabel.setTextFill(Color.WHITE);


        VBox mainVBox=new VBox(15);
        mainVBox.getChildren().addAll(mainLabel,agriLabel,descriptionLabel);
        mainVBox.setPadding(new Insets(350,0,0,140));
        mainVBox.setVisible(true);

        Button loginBt = new Button("Login");
        loginBt.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
        loginBt.setFont(new Font("Georgia", 20));
        loginBt.setPrefWidth(120);
      


        loginBt.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
             loginBt.setScaleX(1.2);
             loginBt.setScaleY(1.2);
             loginBt.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
             loginBt.setFont(new Font("Georgia", 20));

        });

        loginBt.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            loginBt.setScaleX(1.0);
            loginBt.setScaleY(1.0);
            loginBt.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
            loginBt.setFont(new Font("Georgia", 20));

        });


        loginBt.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            loginBt.setScaleX(1.2);
            loginBt.setScaleY(1.2);

            // loginBt.setScaleX(1.0);
            //  loginBt.setScaleY(1.0);
             loginBt.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
             loginBt.setFont(new Font("Georgia", 20));


            loginBt.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                loginBt.setScaleX(1.2);
                loginBt.setScaleY(1.2);
                loginBt.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
                loginBt.setFont(new Font("Georgia", 20));
   
           });
   
           loginBt.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
               loginBt.setScaleX(1.0);
               loginBt.setScaleY(1.0);
               loginBt.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
               loginBt.setFont(new Font("Georgia", 20));
   
           });
   
    
        });





       
      

        Button signupButton = new Button("Signup");
        signupButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
        signupButton.setFont(new Font("Arial", 20));
        signupButton.setPrefWidth(120);

        signupButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            signupButton.setScaleX(1.2);
            signupButton.setScaleY(1.2);
            signupButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
            signupButton.setFont(new Font("Georgia", 20));

       });

       signupButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
           signupButton.setScaleX(1.0);
           signupButton.setScaleY(1.0);
           signupButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
           signupButton.setFont(new Font("Georgia", 20));

       });

    
    signupButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        signupButton.setScaleX(1.0);
        signupButton.setScaleY(1.0);
        signupButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
        signupButton.setFont(new Font("Georgia", 20));




        signupButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            signupButton.setScaleX(1.2);
            signupButton.setScaleY(1.2);
            signupButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
            signupButton.setFont(new Font("Georgia", 20));

       });

       signupButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
           signupButton.setScaleX(1.0);
           signupButton.setScaleY(1.0);
           signupButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-weight:BOLD");
           signupButton.setFont(new Font("Georgia", 20));

       });

    });

      

        HBox loginHBox=new HBox(45,loginBt,signupButton);
        loginHBox.setPadding(new Insets(575,0,0,140));


           
        // VBox loginFieldVBox =  obj.getLoginField( new HomePage(stage));

        loginBt.setOnAction(e->{

            // mainVBox.setVisible(false);
            // TranslateTransition info = new TranslateTransition(Duration.seconds(1), mainVBox);
            // info.setFromY(0); // Starting position
            // info.setToY(-900); 
            // info.play();
            // loginHBox.setVisible(false);
            vBox.setVisible(true);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), vBox);
            transition.setFromX(1470);
            transition.setToX(800);
            registerVBox.setVisible(false);// Ending position
          
            transition.play();

        });


        signupButton.setOnAction( e ->{
          //  TranslateTransition info = new TranslateTransition(Duration.seconds(1), mainVBox);
          //  info.setFromY(0); // Starting position
          //  info.setToY(-900); 
          //  info.play();
          // loginHBox.setVisible(false);
          vBox.setVisible(false);
              registerVBox.setVisible(true);
              TranslateTransition register = new TranslateTransition(Duration.seconds(1) , registerVBox);
              register.setFromX(1470);
              register.setToX(800);
              register.play();

        });

        
        
       
        Pane p=new Pane(callHBox);
        callHBox.setLayoutX(1200);
        callHBox.setLayoutY(50);

        StackPane agriculturStackPane=new StackPane();
        agriculturStackPane.getChildren().addAll(backgroundImageView,topImageView,topBar ,mainVBox , loginHBox);
        agriculturStackPane.setAlignment(topImageView, Pos.TOP_CENTER);
        

        Group startPageGroup  = new Group(agriculturStackPane ,p, vBox , registerVBox );
        Scene startScene = new Scene(startPageGroup);
        stage.setScene(startScene);
        stage.setTitle("LoginPage");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();
      // slide.play();




      Timeline tm1 = new Timeline(new KeyFrame(Duration.seconds(0.01),e->{
       fetchData();
       fetchAnimalHusbandryData();
       fetchFarmImplementData();
       fetchFarmerData();
       fetchAnimalHusbandryData();

      }));
      
      tm1.play();
      

       
    }

     Label getL(String name){
        Label lb = new Label(name);
        lb.setTextFill(Color.GREY);
        lb.setFont(Font.font("Calibri", 18));
        return lb ;
    }

}
