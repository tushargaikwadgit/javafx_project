package com.farm.javaFiles.functions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import com.farm.firebase_connection.DataService;
import com.farm.javaFiles.Screens.HomePage;
import com.farm.javaFiles.Screens.Labor;
import com.farm.javaFiles.loginPage.AgricultureUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;





public class FarmerForm {
    String imageLink  ;
    Stage stage ;
    
    public FarmerForm(Stage stage){
        this.stage = stage ;
    }

    
    public void getFarmerRegisterForm() {
       


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
        Label professionLabel = new Label("नोकरीचे शीर्षक*");
        //professionLabel.setStyle("-fx-text-fill: green;");
        ComboBox<String> professionField = new ComboBox<>();
        professionField.getItems().addAll("पेरणी" , "आंतर मशागत" ,"पाणीव्यवस्थापन", "कीटकनाशकांची फवारणी" , "पिकाची काढणी व मळणी","पशू पोषण","अनुभवी शेतीबागाचे व्यवस्थापन  " );
        
        professionField.setMaxWidth(300);

        professionField.setPromptText("E.g. नांगरणी ");
        //professionField.setStyle("  -fx-border-color: black;-fx-border-color: transparent transparent black transparent;-fx-border-width: 0 0 3 0 ;");
        

        // Hiring Pattern and Hiring Fee
        HBox hiringBox = new HBox(10);
        hiringBox.setSpacing(10);

        Label hiringPatternLabel = new Label("Job Type*");
        //hiringPatternLabel.setStyle("-fx-text-fill: green;");
        ComboBox<String> hiringPatternCombo = new ComboBox<>();
        hiringPatternCombo.getItems().addAll("पूर्णवेळ (Full-time)" , "अर्धवेळ (Part-time)"," तात्पुरते (Temporary)");
        hiringPatternCombo.setPromptText(" तात्पुरते (Temporary)");
        

        Label hiringFeeLabel = new Label("पगार / वेतन*");
        //hiringFeeLabel.setStyle("-fx-text-fill: green;");
        ComboBox<String> hiringFeeField = new ComboBox<>();
         hiringFeeField.getItems().addAll(
               
                "रुपये १०० प्रति दिवस",
                "रुपये २०० प्रति दिवस",
                "रुपये ३०० प्रति दिवस",
                "रुपये ४०० प्रति दिवस",
                "रुपये ५०० प्रति दिवस",
                "रुपये ६०० प्रति दिवस",
                "रुपये ७०० प्रति दिवस",
                "रुपये ८०० प्रति दिवस",
                "रुपये ९०० प्रति दिवस",
                "रुपये १००० प्रति दिवस"

);
        hiringFeeField.setPromptText("रुपये ३०० प्रति दिवस");

      

        hiringBox.getChildren().addAll(hiringPatternLabel, hiringPatternCombo, hiringFeeLabel, hiringFeeField);

        // City/Town and Locality
        VBox locationBox = new VBox(10);
        locationBox.setPrefWidth(200);
        // locationBox.setSpacing(10);

        Label cityLabel = new Label("नोकरी सुरू होण्याची तारीख (Start Date)* ");
        //cityLabel.setStyle("-fx-text-fill: green;");
        TextField dateField = new TextField();
        dateField.setPromptText("Eg. DD/MM/YYYY");
        dateField.setMaxWidth(300);
        

        Label localityLabel = new Label("कामाचे स्थान *");
        //localityLabel.setStyle("-fx-text-fill: green;");
        TextField localityField = new TextField();
        localityField.setMaxWidth(300);

        Label mobileNumberLabel = new Label("Mobile Number*");
        //mobileNumberLabel.setStyle("-fx-text-fill: green;");
        TextField mobileNumberField = new TextField();
        mobileNumberField.setMaxWidth(300);
       
        //descriptionLabel.setStyle("-fx-text-fill: green;");
       
      //  descriptionArea.setWrapText(true);
      

        locationBox.getChildren().addAll(cityLabel, dateField, localityLabel, localityField , mobileNumberLabel , mobileNumberField);

        // Description
        
    
        Image ig = new Image("assets/images/laborRegisterForm.png");
        ImageView sideBoxImageView = new ImageView(ig);
        sideBoxImageView.setFitHeight(450);
        sideBoxImageView.setFitWidth(400);
        //sideBoxImageView.setPreserveRatio(true);


        // Image Upload
        Label imageUploadLabel = new Label("We suggest you to upload your own image to gain client's credibility. The first image is the AD profile picture.*");
        //imageUploadLabel.setStyle("-fx-text-fill: green;");
       
     
        Button pickimage = new Button("Upload Photo");
        pickimage.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        pickimage.setOnAction(e->{
            ImagePickerExample obj = new ImagePickerExample(stage);
            obj.getImage();
            imageLink = obj.imageLink ;
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
            data.put("job-title",professionField.getValue());
            data.put("job-type",hiringPatternCombo.getValue());
            data.put("Salary",hiringFeeField.getValue());
            data.put("startDate",dateField.getText());
            data.put("Location",localityField.getText());
            data.put("MobileNumber",mobileNumberField.getText());
            data.put("laborImage",imageLink);
             
            try{

                AgricultureUI.farmerData.add(data);
                
                DataService.addData("farmer",nameField.getText(),data);
            
                nameField.clear();
               
                localityField.clear();
                mobileNumberField.clear();
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
        HomePage obj = new HomePage(stage);
        obj.homeScreen();
        });

        VBox vb = new VBox(5,postAdButton,agreeLabel,btn);
        // vb.setPrefWidth(1470);
        vb.setAlignment(Pos.CENTER);

        // Add all elements to main VBox
        mainVBox.getChildren().addAll(nameLabel,nameField,professionLabel, professionField, hiringBox, locationBox, imageUploadLabel,pickimage, vb);
        mainVBox.setPrefHeight(600);
        mainVBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10");

      

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

     

