package com.farm.javaFiles.Screens;

import com.farm.javaFiles.functions.AppBar;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class HomePage {
    int imageIndex = 0;

    Stage stage ;

  //  private List<ImageView> images ;

  final String[] images = {
    "assets/images/screensImage/backk.jpg" ,
    "assets/images/screensImage/backk2.jpg" ,
    "assets/images/screensImage/backk4.jpg",
    "assets/images/screensImage/backk6.jpg",
    "assets/images/screensImage/backk5.png",
    
};
    private int currentIndex = 0;

    
    public HomePage( Stage stage ){
        this.stage = stage ;
    }

    



    public void homeScreen(){

       

        StackPane root = new StackPane();
        root.setPrefSize(1470, 900);


        AppBar appbarObj  = new AppBar(stage);
        BorderPane appBar = appbarObj.getToolBar(null);

        ImageView animalHusbandryImage  = takeImage(new  Image("assets/images/screensImage/animalHusbands.jpeg"));
        Label animalHusbandry = getLabel("Animal Husbandry");
        VBox animalHusbandryVBox = getVBox(animalHusbandryImage, animalHusbandry);
        Button animalHusbandryButton = getButton(animalHusbandryVBox);

        animalHusbandryButton.setOnAction(e->{

           AnimalHusbandry obj = new AnimalHusbandry(stage );
           obj.getanimalHusbandary();
            
        });
        

        ImageView workerImage  = takeImage(new  Image("assets/images/screensImage/labor.jpg")); 
        Label workerLabel = getLabel("Labor");

        VBox workerVbox = getVBox(workerImage, workerLabel);
        Button workerButton = getButton(workerVbox);
        workerButton.setOnAction(e->{
          Labor obj = new Labor(stage);
          obj.getLaborScreen();
        });


        ImageView farmImplementsImage  = takeImage(new  Image("assets/images/screensImage/equipment2.jpeg"));
        Label farmImplements = getLabel("Farm Implements");
        VBox farmImplementsVBox = getVBox(farmImplementsImage, farmImplements);
        Button farmImplementButton  = getButton(farmImplementsVBox);

        farmImplementButton.setOnAction(e->{

           FarmImplements obj = new FarmImplements(stage);
           obj.getfarmImplements();
        });


      

        

        ImageView whetherImage  = takeImage(new  Image("assets/images/screensImage/unnamed.jpg"));
        Label whether = getLabel("Whether Forcast");
        VBox whetherVBox = getVBox(whetherImage, whether);
        Button whetherButton = getButton(whetherVBox);

        whetherButton.setOnAction(e->{
            WeatherScreen obj = new WeatherScreen(stage);
            obj.getWeatherScreen();
        });

        HBox hb2 = new HBox(150,whetherButton);
        hb2.setPadding(new Insets(0,0,0,100));
        
        HBox hb1 = new HBox(50,animalHusbandryButton,workerButton,farmImplementButton,whetherButton);
        hb1.setPadding(new Insets(470,0,0,224));


       
       
        

        

        ImageView backgroundImageView = new ImageView(new Image(images[imageIndex]));
       
        backgroundImageView.setFitHeight(500);
        backgroundImageView.setFitWidth(1270);

        //  Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {

        //     FadeTransition  fadeOut = new FadeTransition(Duration.seconds(3) , backgroundImageView);
        //     fadeOut.setFromValue(1);
        //     fadeOut.setToValue(0.1);
           
        //     fadeOut.setOnFinished(e->{

        //         imageIndex = (imageIndex + 1) % images.length;
        //         backgroundImageView.setImage(new Image(images[imageIndex]));

        //         FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), backgroundImageView);
        //         fadeIn.setFromValue(0);
        //         fadeIn.setToValue(1);
        //         fadeIn.play();
             

        //     });

        //     fadeOut.play();


           
        // }));

        // timeline.setCycleCount(Timeline.INDEFINITE);
        // timeline.play();

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), backgroundImageView);
        fadeOut.setFromValue(0.7);
        fadeOut.setToValue(0.1);

        // Create the fade-in transition
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), backgroundImageView);
        fadeIn.setFromValue(0.1);
        fadeIn.setToValue(0.7);

        // Create a pause transition between fades
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        // Chain the transitions
        fadeOut.setOnFinished(e -> {
            // Change the image after fade-out
            imageIndex = (imageIndex + 1) % images.length;
            backgroundImageView.setImage(new Image(images[imageIndex]));

            // Start the fade-in transition
            fadeIn.play();
        });

        fadeIn.setOnFinished(e -> {
            // Pause before starting the fade-out again
            pause.play();
        });

        pause.setOnFinished(e -> {
            // Start the fade-out transition again
            fadeOut.play();
        });

        // Start the initial fade-out
        fadeOut.play();

        VBox backVBox=new VBox(backgroundImageView);
        backVBox.setPadding(new Insets(30, 0, 0,100));
        

    
        VBox finalVBox = new VBox(50,appBar);
        // finalVBox.setLayoutX(450);
        finalVBox.setPadding(new Insets(0,0,0,50));



        // images = loadImages();
        // root.getChildren().addAll(images);
        // root.setPadding(new Insets(-350,0,0,0));

        Label slogan = new Label("जय \n  जवान \n\n   जय \n   किसान");
        slogan.setFont(Font.font("Arial", FontWeight.BOLD, 50));
    
        slogan.setPadding(new Insets(-100,0,0,-680));








        StackPane st = new StackPane(backVBox,hb1);
        


         ScrollPane scrollPane = new ScrollPane(st);
        scrollPane.setPadding(new Insets(10));
        
        // Optional: Customize scroll pane properties
        scrollPane.setFitToWidth(true); // Ensure content width fits the ScrollPane width
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar if not needed
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show vertical scrollbar as needed

        

      
        


       
        VBox vb = new VBox(finalVBox,st);

       

       
        Group gp = new Group(vb);
      
        



        Scene scene = new Scene(gp);


        stage.setScene(scene);
        stage.setTitle("LoginPage");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();
      //  showNextImage();

      //  fetchData();
    }

    public List<ImageView> loadImages() {
        List<ImageView> imageViews = new ArrayList<>();
        File imageDir = new File("/Users/vivekkarche/SceneCreator/hellokrushiapp_javafx/farmingproject/src/main/resources/assets/images/farmImplements"); // Directory containing your images
        File[] imageFiles = imageDir.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".jpeg"));
        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                Image image = new Image(imageFile.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(1000);
                imageView.setFitHeight(500);
                imageView.setTranslateX(1470); // start off-screen to the right
                imageViews.add(imageView);
                imageView.setPreserveRatio(true);

            }
        }
        return imageViews;
    }

    //  private void showNextImage() {
    //     ImageView imageView = images.get(currentIndex);
    //     TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.7), imageView);
    //     slideIn.setFromX(1000);
    //     slideIn.setToX(0);

    //     PauseTransition pause = new PauseTransition(Duration.seconds(5));

    //     TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.1), imageView);
    //     slideOut.setFromX(-1200);
    //     slideOut.setToX(-1470);

    //     slideOut.setOnFinished(event -> {
    //         currentIndex = (currentIndex + 1) % images.size();
    //         showNextImage();
    //     });

    //     SequentialTransition sequence = new SequentialTransition(slideIn, pause, slideOut);
    //     sequence.play();
    // }


    Label getLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb.setStyle("-fx-background-color: #299617; -fx-text-fill:white;-fx-background-radius:0 0 15 15");
        lb.setMaxWidth(200);
        lb.setPrefHeight(40);
        lb.setAlignment(Pos.CENTER);

        return lb ;
    }

    public ImageView takeImage(Image image){
        ImageView img = new ImageView(image);
        img.setFitWidth(200);
        img.setFitHeight(200);
        img.setStyle("-fx-background-color: white; -fx-border-color: transparent ");
      
        return img ;
    }


    VBox getVBox(ImageView iv , Label lb){
        VBox vb = new VBox(iv, lb);
        vb.setAlignment(Pos.CENTER);
        return vb ;
    }


    Button getButton( VBox vb){
        Button bt = new Button();
        bt.setGraphic(vb);
        bt.setStyle("-fx-background-color: transparent; -fx-border-color: transparent ; -fx-background-radius: 15");



        bt.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            bt.setTextFill(Color.BLACK);
            bt.setScaleX(1.1);
            bt.setScaleY(1.1);
        });

        bt.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            bt.setTextFill(Color.BLACK);
            bt.setScaleX(1.0);
            bt.setScaleY(1.0);
        });


    
        // bt.setPadding(new Insets(20));
       // bt.setStyle("-fx-background-color:GREEN");
        

        return bt ;
    }
    
    
}
