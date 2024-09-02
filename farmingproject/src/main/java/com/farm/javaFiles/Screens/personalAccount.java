
package com.farm.javaFiles.Screens;

import java.util.Map;

import javax.swing.plaf.metal.MetalBorders.ToolBarBorder;

import com.farm.firebase_connection.DataService;
import com.farm.javaFiles.functions.AppBar;
import com.farm.javaFiles.functions.ImagePickerExample;
import com.farm.javaFiles.loginPage.AgricultureUI;
import com.farm.javaFiles.loginPage.EmailLoginPage;
import com.farm.javaFiles.loginPage.Register;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class personalAccount{

    Stage stage ;

    GridPane gp = new GridPane();
    GridPane gp2 = new GridPane();
    
    public personalAccount(Stage stage){
        this.stage = stage ;
    }
    


    public void getGrid(){

        if(EmailLoginPage.isPosted==true){
            System.out.println(EmailLoginPage.jobApplied.size());
            for (int i = 0; i < EmailLoginPage.jobApplied.size(); i++) {
                Map<String, String> data = EmailLoginPage.jobApplied.get(i);
                HBox laborCard = createFarmerCard(data);
                gp.add(laborCard, 0, i);
            }

        }else{

            ImageView postJob = new ImageView("assets/images/loginpage/jobPost.png");
            postJob.setFitWidth(200);
            postJob.setFitHeight(200);
            // postJob.setLayoutX(400);
            Label emptyLabel = new Label("There are currently no job posted.");
            emptyLabel.setFont(new Font(25));
            // emptyLabel.setPadding(new Insets(100,50,0,50));

            VBox vb = new VBox(postJob , emptyLabel);
            vb.setPadding(new Insets(100,50,0,100));
            gp.add(vb, 0, 0);

        }
      
    }

    public void getGrid2(){

        // if(EmailLoginPage.isPosted==true){
        //     System.out.println(EmailLoginPage.jobApplied.size());
        //     for (int i = 0; i < EmailLoginPage.jobApplied.size(); i++) {
        //         Map<String, String> data = EmailLoginPage.jobApplied.get(i);
        //         HBox laborCard = createFarmerCard(data);
        //         gp.add(laborCard, 0, i);
        //     }

        // }else{

            ImageView postJob = new ImageView("assets/images/loginpage/notification.png");
            postJob.setFitWidth(200);
            postJob.setFitHeight(200);
            // postJob.setLayoutX(400);
            Label endLabel = new Label("\"You have no new notifications.");
            endLabel.setFont(new Font(25));
            // emptyLabel.setPadding(new Insets(100,50,0,50));

            VBox vb = new VBox(postJob , endLabel);
            vb.setPadding(new Insets(100,50,0,100));
            gp2.add(vb, 0, 0);

      //  }
      
    }



    public HBox createFarmerCard(Map<String, String> data){
      
        ImageView imgImageView = takeImage(new Image(data.get("laborImage")));
        StackPane stackPane = new StackPane(imgImageView);
        stackPane.setMaxSize(100, 100);
         Border border = new Border(new BorderStroke(
             Color.GREEN, // Border color
             BorderStrokeStyle.SOLID, // Border style 
             new CornerRadii(80), // Corner radii, match the clip's corner radii
             new BorderWidths(5) // Border widths
     ));
         stackPane.setBorder(border);
         Label name = getNameLabel(data.get("Name"));
         Image ig = new Image("assets/images/labor/location.png");
         ImageView iv = new ImageView(ig);
         iv.setFitWidth(20);
         iv.setFitHeight(20);
         Label location = getLocationLabel("Location :"+data.get("City"));
         HBox locationHBox = new HBox(2,iv,location);
         Label price = getPriceLabel("2000/day");
         Label job_title = getDescriptionLabel("शीर्षक: "+"farmer" );
         Label job_type = getDescriptionLabel("प्रकार :"+"Full-time" );

         ToggleButton togglebtn = new ToggleButton(data.get("status"));
         togglebtn.setPrefWidth(150);
         
          Label toglLabel =  getDescriptionLabel("Status: ");
       //   Label  status= getDescriptionLabel("Status : Available Now ");
          togglebtn.setOnAction(event -> {
             if (togglebtn.isSelected()) {
                if(data.get("status").equals("available now")){
                    togglebtn.setStyle("-fx-background-color: RED ;-fx-text-fill: WHITE");
                    DataService.updateLaborStatus("status","currently not available ");
                    togglebtn.setText("Not Available");

                }else{
                    togglebtn.setStyle("-fx-background-color: green ;-fx-text-fill: WHITE");
                    DataService.updateLaborStatus("status","available now");
                    togglebtn.setText(" Available Now");


                }
              
             } else {
               
                    DataService.updateLaborStatus("status","not available now");
                    togglebtn.setText("Currently not Available ");
                    togglebtn.setStyle("-fx-background-color: black ;-fx-text-fill: WHITE");

                }            
         });
         HBox  hb1= new HBox(5,toglLabel , togglebtn);
         hb1.setStyle("-fx-alignment: center");
         VBox vb  = new VBox(15,name,price,locationHBox ,job_title , job_type, hb1 );
          vb.setPrefWidth(450);
          vb.setAlignment(Pos.CENTER_LEFT);
         HBox hb = new HBox(10,stackPane,vb);
         hb.setPrefWidth(550);
         hb.setPrefHeight(250);
         hb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
         hb.setPadding(new Insets(21));
         hb.setAlignment(Pos.CENTER);
      
         return hb;
         

          
    }

    public void getPersonalPage(){

        gp.setHgap(30);
        gp.setVgap(10);
        getGrid();
        getGrid2();
        

        // Circle person = new Circle(50 , Color.WHITESMOKE);
        // person.setStrokeWidth(2);
        // imageView.setImage( new Image("assets/images/loginpage/camera.png"));
        // imageView.setFitWidth(60);
        // imageView.setFitHeight(60);
    
        

            ImageView imageView = new ImageView();
            Image image = new Image(EmailLoginPage.userProfile);
            imageView.setImage(image);
            double radius = 50 ;
            imageView.setFitHeight(radius*2);
            imageView.setFitWidth(radius*2);
            Circle clip = new Circle(radius , radius , radius);
            imageView.setClip(clip);





       AppBar obj = new AppBar(stage , null ,null);
       BorderPane appB =  obj.getToolBar(null);
        // Create a VBox for the main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20, 50, 20, 50));
        // Title
        Label title = new Label("Your Account");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        // Create a GridPane for the buttons
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        // Buttons
        Button yourOrders = createButton("Your Orders", "assets/images/loginpage/cart.png");
        yourOrders.setStyle("-fx-background-radius: 15");
        Button loginSecurity = createButton("Applied Jobs ", "assets/images/loginpage/jobPost.png");
        loginSecurity.setStyle("-fx-background-radius: 15");
        Button yourAddresses = createButton("Address", "assets/images/loginpage/adress.png");
        yourAddresses.setStyle("-fx-background-radius: 15");
        Button contactUs = createButton("Contact Us", "assets/images/loginpage/contactUss.png");
        contactUs.setStyle("-fx-background-radius: 15");
        Button logOut = createButton("Logout", "assets/images/loginpage/logiout.png");
        logOut.setStyle("-fx-background-radius: 15");

        // Add buttons to the grid
        gridPane.add(yourOrders, 0, 0);
        gridPane.add(loginSecurity, 0, 1);
        gridPane.add(yourAddresses, 0, 2);
        gridPane.add(contactUs, 0, 3);
        gridPane.add(logOut, 0, 4);

        Label user = new Label("hello ,"+EmailLoginPage.userName);
        user.setFont(new Font(20));

        // Add title and grid to the main layout
        mainLayout.getChildren().addAll( title, imageView, user , gridPane);



        // 2nd slot
        VBox middleLayout = new VBox(20);
        middleLayout.setPadding(new Insets(20, 20, 20, 20));

        // Title
        Label middleTitle = new Label("Your Post ");
        middleTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        middleLayout.getChildren().addAll(middleTitle , gp);
      
      
       
        VBox endVBox = new VBox(20);
        endVBox.setPadding(new Insets(20, 20, 20, 20));

        Label endTitle = new Label("Notifications");
        endTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        endVBox.getChildren().addAll(endTitle , gp2);

       

        Line line1 = new Line(150, 0, 150, 700);
        line1.setStroke(Color.BLACK);

        Line line2 = new Line(450, 0, 450, 700);
        line1.setStroke(Color.BLACK);


         
       

        // Add title and grid to the main layout
      


       
        HBox hb = new HBox(mainLayout ,line1, middleLayout ,line2 , endVBox);
        VBox vb = new VBox(appB , hb);
        // Create the scene and add it to the stage
        VBox mainV = new VBox(vb);
        Group startPageGroup  = new Group(mainV);
        Scene startScene = new Scene(startPageGroup);
        stage.setScene(startScene);
        stage.setTitle("LoginPage");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }

 
    Button createButton(String title, String imageLink) {
        Button button = new Button();
       // button.setMinSize(250, 100);
        button.setPrefWidth(250);
        button.setPrefHeight(75);
        button.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: green; -fx-font-size: 14px;");
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 22px;");
        VBox vbox = new VBox(titleLabel);
        VBox icon = new VBox();
        ImageView iconImageView = new ImageView(new Image(imageLink));
        iconImageView.setFitHeight(50);
        iconImageView.setFitWidth(50);
        icon.getChildren().add(iconImageView);
        icon.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        HBox finalHBox = new HBox(15, icon , vbox);
        button.setGraphic(finalHBox);
        return button;
    }

  
 


      


     static Label getNameLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Arial", FontWeight.THIN, 25));
        return lb ;
    }


    static Label getLocationLabel(String name){
        Label lb = new Label(name);
        lb.setFont(new Font(22));
        lb.setAlignment(Pos.CENTER);
        return lb ;
    }

    

    static Label getPriceLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Arial",  20));
        lb.setAlignment(Pos.CENTER);
        return lb ;
    }

    static Label getDescriptionLabel(String name){
        Label lb = new Label(name);
        lb.setFont(new Font(18));
        lb.setAlignment(Pos.CENTER);
        lb.setWrapText(true);
        return lb ;
    }



    public static ImageView takeImage(Image image){
        ImageView img = new ImageView(image);
        img.setFitWidth(100);
        img.setFitHeight(100);
        img.setStyle("-fx-background-color: white; -fx-border-color: transparent;-fx-background-radius: 50 ;-fx-border-radius: 50");
        Circle clip = new Circle(50);
        clip.setCenterX(50); // Center X coordinate
        clip.setCenterY(50); 
        img.setClip(clip);
        return img ;
    }



    public static HBox getHBox(ImageView iv , Label lb,Label lb1 , Label lb2 ){
        HBox hb = new HBox(iv, lb ,lb1 ,lb2);
        hb.setSpacing(10);
        // vb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-background-color:white;-fx-border-color: black;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
        hb.setPadding(new Insets(25));
        hb.setPrefWidth(210);
        hb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
        hb.setPadding(new Insets(20));
        return hb ;


    }


    public static ImageView takeIconImage(Image image){
        ImageView img = new ImageView(image);
        img.setFitWidth(30);
        img.setFitHeight(30);
        return img ;
    }

    static Label getL(String name){
        Label lb = new Label(name);
        lb.setTextFill(Color.GREEN);
        lb.setFont(new Font(20));
        return lb ;
    }


}


