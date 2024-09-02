package com.farm.javaFiles.loginPage;

import java.util.HashMap;
import java.util.Map;

import com.farm.firebase_connection.FirebaseService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.farm.javaFiles.Screens.HomePage;
import com.farm.javaFiles.functions.ImagePickerExample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import javafx.stage.Stage;

public class Register {

    
   
     VBox startPageVBox  ;
    Image frontImage ;
      Image backgroundImage ;
    ImageView frontImageView ;
    ImageView backgroundImageView ;
    Button signUpButton ;
    Button loginButton ; 
    Button continueBotton ;
    TextField emailField ;
    PasswordField passwordField;
    FirebaseService firebaseService;

    Stage stage ;
    public static String imageLink ;

    public Register(Stage stage){

        this.stage = stage ;

    }

    public  VBox register(){





        // backgroundImage = new Image("assets/images/loginpage/backgroundImage.jpeg");
        // backgroundImageView = new ImageView(backgroundImage);
        // backgroundImageView.setFitHeight(920);
        // backgroundImageView.setFitWidth(600);
        // backgroundImageView.setLayoutX(0);

        // Image hellokrushi = new Image("assets/images/loginpage/hellokrushi.png");
        // ImageView helloKruImageView = new ImageView(hellokrushi);
        // helloKruImageView.setFitHeight(130);
        // helloKruImageView.setFitWidth(180);


        Label regiLabel = new Label("Do Register !");
        regiLabel.setFont(new Font("Arial", 20));
        regiLabel.setTextFill(Color.GREEN);
        regiLabel.setStyle("-fx-font-weight:BOLD");
        regiLabel.setLayoutX(100);


       
        

        
      //  Circle border = new Circle(radius , radius , radius+5);
      ///  border.setFill(Color.TRANSPARENT);
      //  border.setStroke(Color.BLACK);

        Circle person = new Circle(50 , Color.WHITESMOKE);
        person.setStrokeWidth(2);
        ImageView imageView = new ImageView();
        imageView.setImage( new Image("assets/images/loginpage/camera.png"));
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
    
        

        person.setOnMouseClicked(event -> {
            ImagePickerExample imgPickerobj = new ImagePickerExample(stage);
            this.imageLink =  imgPickerobj.getImage();
            Image image = new Image(imgPickerobj.imageLink);
            imageView.setImage(image);
            double radius = 50 ;
             imageView.setFitHeight(radius*2);
             imageView.setFitWidth(radius*2);
             Circle clip = new Circle(radius , radius , radius);
            imageView.setClip(clip);

        });


        StackPane persoStackPane = new StackPane();
        persoStackPane.getChildren().addAll(person  , imageView);

        Image personImage = new Image("assets/images/loginpage/picon.png");
        ImageView personImageView = new ImageView(personImage);
        personImageView.setFitHeight(25);
        personImageView.setFitWidth(25);


        TextField name = new TextField();
        name.setPromptText("Enter your name");
        name.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 3 0 ;-fx-text-fill: white");
        name.setPrefWidth(250);

        HBox nameBox = new HBox(10,personImageView , name);
         

        Image ageImage = new Image("assets/images/loginpage/ages.png");
        ImageView ageImageView = new ImageView(ageImage);
        ageImageView.setFitHeight(25);
        ageImageView.setFitWidth(25);
        


        TextField contact = new TextField();
        contact.setPromptText("Enter your age");
        contact.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 3 0 ;-fx-text-fill: white");

        contact.setPrefWidth(250);

        HBox ageBox = new HBox(10,ageImageView , contact);

        Image emailImage = new Image("assets/images/loginpage/email.png");
        ImageView emailImageView = new ImageView(emailImage);
        emailImageView.setFitHeight(27);
        emailImageView.setFitWidth(27);
        emailImageView.setPreserveRatio(true);

        emailField = new TextField() ;
        emailField.setPrefWidth(250);
        emailField.setPromptText("Enter your email...");
        emailField.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 3 0 ;-fx-text-fill: white");


        HBox emailBox = new HBox(10,emailImageView , emailField);

        passwordField = new PasswordField();
        passwordField.setPrefWidth(250);
        passwordField.setPromptText("Enter Password");
        
        passwordField.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 3 0 ;-fx-text-fill: white");


        Image passwordImage = new Image("assets/images/loginpage/password.png");
        ImageView passwordImageView = new ImageView(passwordImage);
        passwordImageView.setFitHeight(30);
        passwordImageView.setFitWidth(30);


        HBox passwordBox = new HBox(10,passwordImageView , passwordField);





        // RadioButton maleRadioButton = new RadioButton("Male");
        // maleRadioButton.setStyle("-fx-text-fill: white");
        // RadioButton femaleRadioButton = new RadioButton("Female");
        // femaleRadioButton.setStyle("-fx-text-fill: white");
        // RadioButton otherRadioButton = new RadioButton("Other");
        // otherRadioButton.setStyle("-fx-text-fill: white");

        // ToggleGroup genderToggleGroup = new ToggleGroup();
        // maleRadioButton.setToggleGroup(genderToggleGroup);
        // femaleRadioButton.setToggleGroup(genderToggleGroup);
        // otherRadioButton.setToggleGroup(genderToggleGroup);

        //HBox genderBox = new HBox(25,maleRadioButton, femaleRadioButton, otherRadioButton);



        Label chooseType = getL("Choose type");

        ComboBox<String> farmerTypecomboBox = new ComboBox<>();
        farmerTypecomboBox.getItems().addAll("शेतकरी","रोजगारकर्ता " );
        farmerTypecomboBox.setPromptText("तुम्ही कोण आहात ते निवडा..?");
        farmerTypecomboBox.getEditor().setStyle("-fx-prompt-text-fill: white");

       // farmerTypecomboBox.setStyle(" -fx-background-color: transparent ;-fx-border-color: transparent transparent white transparent;-fx-border-width: 0 0 3 0 ;-fx-prompt-text-fill: gray");
        farmerTypecomboBox.setPrefWidth(280);
        VBox farmerTypeVBox=new VBox(5,chooseType , farmerTypecomboBox);
        



        Label chooseDis = getL("Choose District");

        String[] districts = {
            "पुणे", "सातारा", "सोलापूर", "अहमदनगर"
        };
         
        ComboBox<String> disTypecomboBox = new ComboBox<>();
         disTypecomboBox.getItems().addAll(districts);
        disTypecomboBox.setPromptText("कृपया जिल्हा निवडा .!");
        disTypecomboBox.setPrefWidth(280);
        VBox disTypeVBox=new VBox(5,chooseDis ,disTypecomboBox);
        Label chooseDiv = getL("Choose Taluka");

        Map<String, String[]> talukasMap = new HashMap<>();
        talukasMap.put("पुणे", new String[]{"पुणे शहर", "पिंपरी-चिंचवड", "खेड", "मावळ", "शिरूर"});
        talukasMap.put("सातारा", new String[]{"सातारा", "कोरेगाव", "वाई", "फलटण", "कराड"});
        talukasMap.put("सोलापूर", new String[]{"सोलापूर शहर", "पंढरपूर", "माळशिरस", "बार्शी", "मंगळवेढा"});
        talukasMap.put("अहमदनगर", new String[]{"अहमदनगर", "पारनेर", "राहाता", "राहुरी", "शेवगाव"});
         
        ComboBox<String> divTypecomboBox = new ComboBox<>();
        
        disTypecomboBox.setOnAction(event -> {
            String selectedDistrict = disTypecomboBox.getValue();
            System.out.println(selectedDistrict);
            if (selectedDistrict != null) {
               divTypecomboBox.getItems().clear();
                divTypecomboBox.getItems().addAll(talukasMap.get(selectedDistrict));
            }
            else{
                System.out.println("heloo");
            }
        });
        divTypecomboBox.setPromptText("कृपया तालुका निवडा .!");
        divTypecomboBox.setPrefWidth(280);
        VBox divTypeVBox=new VBox(5,chooseDiv ,divTypecomboBox);
        


        
        

        Button regButton = new Button("Register Now... ");
        regButton.setStyle("-fx-text-fill: white");
        regButton.setStyle("-fx-background-color: white ;-fx-text-fill:black ;-fx-background-radius:10");
        regButton.setTextFill(Color.WHITE);
        regButton.setPrefWidth(280);









        regButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FirebaseService  obj = new FirebaseService(emailField,passwordField , name , contact ,farmerTypecomboBox ,divTypecomboBox,disTypecomboBox , imageLink) ;
                obj.registerMethod();
               
            }
            
        });
        
        VBox regVBox = new VBox(10, persoStackPane,nameBox ,ageBox ,emailBox,passwordBox, farmerTypeVBox,disTypeVBox , divTypeVBox ) ; 
        regVBox.setPrefWidth(320);

         return new VBox(20,regVBox , regButton) ;
        

         
        // Button arrow=new Button("Go Back");
        // arrow.setStyle("-fx-background-color:GREEN ; -fx-background-radius:15");
        // arrow.setTextFill(Color.WHITE);
        // arrow.setPrefWidth(70);
        // arrow.setLayoutX(580);
        // arrow.setLayoutY(90);
        // arrow.setOnAction(new EventHandler<ActionEvent>() {

        //     @Override
        //     public void handle(ActionEvent event) {
             
             
        //         StartPage obj = new StartPage(stage);
        //         obj.start();
        
                
        //     }
            
        // });\

        

        //  VBox upperVBox=new VBox( 25,helloKruImageView,regiLabel,regVBox,regButton);
        //  upperVBox.setAlignment(Pos.CENTER);
        //  upperVBox.setLayoutY(70);
        //  upperVBox.setLayoutX(630);
        // // StackPane registerStackPane = new StackPane();
        // //  // registerStackPane.setLayoutX(450);
        // // registerStackPane.setAlignment(Pos.CENTER);
        //  return  new Group(arrow,upperVBox);
        // Scene startScene = new Scene(startPageGroup);
        // stage.setScene(startScene);
        // stage.setTitle("LoginPage");
        // stage.setHeight(900);
        // stage.setWidth(1470);
        // stage.setX(0);
        // stage.setY(0);
        // stage.show();
       
    }

    Label getL(String name){
        Label lb = new Label(name);
        lb.setTextFill(Color.WHITE);
        lb.setFont(Font.font("Arial", 10));
        return lb ;
    }
    
    
}
