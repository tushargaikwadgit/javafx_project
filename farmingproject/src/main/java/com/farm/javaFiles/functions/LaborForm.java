package com.farm.javaFiles.functions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.farm.firebase_connection.DataService;
import com.farm.javaFiles.Screens.Labor;
import com.farm.javaFiles.loginPage.AgricultureUI;
import com.farm.javaFiles.loginPage.EmailLoginPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;




public class LaborForm {
    String imageLink  ;
    Stage stage ;


    public LaborForm(Stage stage){
        this.stage = stage ;
    }

    
    public void getWorkerRegisterForm() {
        stage.setTitle("Ad Form Example");



        // Image iv = new Image("assets/images/imagesbckk.jpeg");
        // ImageView imagebackView = new ImageView(iv);
        // imagebackView.setFitHeight(900);
        // imagebackView.setFitWidth(1470);
        // Main VBox
        VBox mainVBox = new VBox(10);
        mainVBox.setPadding(new Insets(20));


        Label nameLabel = new Label("Full Name");
        //nameLabel.setStyle("-fx-text-fill: green;");
        TextField nameField = new TextField();
        nameField.setMaxWidth(300);
       

        // Profession
        Label professionLabel = new Label("Profession*");
        //professionLabel.setStyle("-fx-text-fill: green;");
        TextField professionField = new TextField();
        professionField.setMaxWidth(300);

        professionField.setPromptText("E.g. Painter or Interior Designer");
        //professionField.setStyle("  -fx-border-color: black;-fx-border-color: transparent transparent black transparent;-fx-border-width: 0 0 3 0 ;");
        

        // Hiring Pattern and Hiring Fee
        HBox hiringBox = new HBox(10);
        hiringBox.setSpacing(10);

        Label hiringPatternLabel = new Label("Hiring Pattern*");
        //hiringPatternLabel.setStyle("-fx-text-fill: green;");
        ComboBox<String> hiringPatternCombo = new ComboBox<>();
        hiringPatternCombo.getItems().addAll("Per Day");

        Label hiringFeeLabel = new Label("Hiring Fee*");
        //hiringFeeLabel.setStyle("-fx-text-fill: green;");
        TextField hiringFeeField = new TextField();
        hiringFeeField.setPromptText("Input price");

        CheckBox fixedChargesCheckBox = new CheckBox("Fixed charges");
        //fixedChargesCheckBox.setStyle("-fx-text-fill: green");

        hiringBox.getChildren().addAll(hiringPatternLabel, hiringPatternCombo, hiringFeeLabel, hiringFeeField, fixedChargesCheckBox);

        // City/Town and Locality
        VBox locationBox = new VBox(10);
        locationBox.setPrefWidth(200);
        // locationBox.setSpacing(10);

        Label cityLabel = new Label("City/Town*");
        //cityLabel.setStyle("-fx-text-fill: green;");
        TextField cityField = new TextField();
        cityField.setMaxWidth(300);
        

        Label localityLabel = new Label("Locality*");
        //localityLabel.setStyle("-fx-text-fill: green;");
        TextField localityField = new TextField();
        localityField.setMaxWidth(300);

        Label mobileNumberLabel = new Label("Mobile Number*");
        //mobileNumberLabel.setStyle("-fx-text-fill: green;");
        TextField mobileNumberField = new TextField();
        mobileNumberField.setMaxWidth(300);

        locationBox.getChildren().addAll(cityLabel, cityField, localityLabel, localityField);

        // Description
        Label descriptionLabel = new Label("Description");
        //descriptionLabel.setStyle("-fx-text-fill: green;");
        TextArea descriptionArea = new TextArea();
    
        descriptionArea.setPromptText("Write in detail about your unique skills to reach more clients.");
        descriptionArea.setWrapText(true);
        descriptionArea.setMaxWidth(300);
        descriptionArea.setMaxHeight(300);


        // Image Upload
        Label imageUploadLabel = new Label("We suggest you to upload your own image to gain client's credibility. The first image is the AD profile picture.*");
        //imageUploadLabel.setStyle("-fx-text-fill: green;");
        HBox imageBox = new HBox(10);
        imageBox.setSpacing(10);
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(0);
            imageView.setFitHeight(0);
            imageView.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-background-color: lightgrey;");
            imageBox.getChildren().add(imageView);
        }

        Image ig = new Image("assets/images/laborRegisterForm.png");
        ImageView sideBoxImageView = new ImageView(ig);
        sideBoxImageView.setFitHeight(450);
        sideBoxImageView.setFitWidth(400);

        Button pickimage = new Button("Upload Photo");
        pickimage.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        pickimage.setOnAction(e->{
            ImagePickerExample obj = new ImagePickerExample(stage);
            
            this.imageLink = obj.getImage(); ;
            sideBoxImageView.setImage(new Image(imageLink));
        });

        // Ad Title
        Label adTitleLabel = new Label("Your Ad Title (Autogenerated)");
        adTitleLabel.setStyle("-fx-text-fill: green;");

        // Post Ad Button
        Button postAdButton = new Button("Register");
        postAdButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        postAdButton.setOnAction(e->{
            Map<String,String> data=new HashMap<>();
            data.put("Name",nameField.getText());
            data.put("Profession",professionField.getText());
            data.put("HiringFee",hiringFeeField.getText());
            data.put("FixedCharges",fixedChargesCheckBox.getText());
            data.put("City",cityField.getText());
            data.put("Locality",localityField.getText());
            data.put("MobileNumber","9322884250");
            data.put("Description",descriptionArea.getText());
            data.put("laborImage",imageLink);
            data.put("status","available now");
             
            try{

                try {
                    AgricultureUI.laborData.add(data);
                    EmailLoginPage.jobApplied.add(data);

                    
                }catch(Exception q){
                    System.out.println("not added");
                }
              
                
                DataService.addData("Labor",EmailLoginPage.userEmail,data);
                DataService.addData(EmailLoginPage.userEmail,EmailLoginPage.userEmail,data);
                
                nameField.clear();
                professionField.clear();
                hiringFeeField.clear();
                cityField.clear();
                localityField.clear();
                mobileNumberField.clear();
                descriptionArea.clear();
                showAlert("Success", "Data added successfully");




            }catch(ExecutionException | InterruptedException ex){
                ex.printStackTrace();
                showAlert("Error", "Failed to add data");
            }  
            
        });

        // T&C
        Hyperlink termsLink = new Hyperlink("T&C");
        Label agreeLabel = new Label("By proceeding, you agree to Hello Krushi ");
        //agreeLabel.setStyle("-fx-text-fill: green;");
        HBox termsBox = new HBox(termsLink, agreeLabel);
        termsBox.setSpacing(5);

        Button btn = new Button("go back");
        btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btn.setOnAction(e->{
            Labor obj = new Labor(stage);
            obj.getLaborScreen();
          

        });

        VBox vb = new VBox(5,postAdButton,agreeLabel,btn);
        // vb.setPrefWidth(1470);
        vb.setAlignment(Pos.CENTER);

        // Add all elements to main VBox
        mainVBox.getChildren().addAll(nameLabel,nameField,professionLabel, professionField, hiringBox, locationBox, descriptionLabel, descriptionArea, imageUploadLabel, imageBox,pickimage, vb);
        mainVBox.setPrefHeight(600);
        mainVBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10");

        
        //sideBoxImageView.setPreserveRatio(true);

        VBox sideImageVBox = new VBox(sideBoxImageView);
        sideImageVBox.setPrefHeight(600);
        sideImageVBox.setPrefWidth(500);
        // sideImageVBox.setStyle("-FX-BACKGROUND-COLOR:Green");
        sideImageVBox.setAlignment(Pos.CENTER);
        
        mainVBox.setPrefHeight(700);

        Pane p=new Pane();
        //p.setStyle("-fx-background-color:YELLOWGREEN");
        p.getChildren().addAll(sideImageVBox,mainVBox);
       
        
        sideImageVBox.setLayoutX(30);
        sideImageVBox.setLayoutY(100);

        mainVBox.setLayoutX(550);
        mainVBox.setLayoutY(50);

        
        Scene scene = new Scene(p, 1470, 900);
        stage.setScene(scene);
        stage.show();
    }

    
    void showAlert(String title, String message) {
        // Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
}

     

