package com.farm.javaFiles.Screens;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WeatherScreen {



    Stage stage;

    public WeatherScreen(Stage stage ){
        this.stage = stage;
    }


    
    Map<String , Object> getTemperature(String url) throws Exception{

        StringBuffer st = new StringBuffer();
        URL urlpath = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlpath.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new  InputStreamReader(conn.getInputStream()));
        String line ;
        while ((line = br.readLine())!= null) {
           st.append(line);
        }
        br.close();
        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.readValue(st.toString(), Map.class));
        return objectMapper.readValue(st.toString(), Map.class);

        }

    
    
       public void getWeatherScreen(){

        Image img = new Image("assets/images/weatherImages/BACKGROUND.jpeg");
        ImageView backgroundWeatherImageView = new ImageView(img);
        // backgroundImageView.setOpacity(0.4);
        backgroundWeatherImageView.setFitHeight(900);
        backgroundWeatherImageView.setFitWidth(1470);
        

        Label backButton = new Label("<");
        backButton.setFont(new Font("Arial", 25));
        backButton.setTextFill(Color.GREEN);
        backButton.setStyle("-fx-font-weight:BOLD");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);

        backButton.setOnMousePressed(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
               HomePage obj = new HomePage(stage);
               obj.homeScreen();
            }
            
        });

        Label weather = getLabel("Weather Template ");
        weather.setFont(new Font(40));

       TextField searchTextField = new TextField();
       searchTextField.setPromptText("search");
       searchTextField.setPrefWidth(400);
       searchTextField.setPrefHeight(15);
       searchTextField.setMaxHeight(20);


       Label tempLabel = getLabel("20°c");
       tempLabel.setFont(Font.font("arial",FontWeight.BOLD,40));
       tempLabel.setStyle("-fx-text-fill:#4169e1");

       Label cityLabel = getLabel("Pune");
       cityLabel.setFont(new Font(30));
       cityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
       cityLabel.setOpacity(0.7);


       Label search = getLabel("शोधा");
       search.setFont(new Font("Arial", 15));
        search.setTextFill(Color.GREEN);
        search.setStyle("-fx-font-weight:BOLD");


       
    

        

        HBox searchHBox = new HBox(10 , searchTextField , search);
        searchHBox.setLayoutX(350);
        searchHBox.setAlignment(Pos.CENTER);

         // get temprature when pressed on shodaa

         search.setOnMouseClicked(e->{
            try{

                String str = searchTextField.getText();
                String path = "https://api.openweathermap.org/data/2.5/weather?q="+str+"&appid=dd4e8c83fc096e452737d86514dba96f&units=metric";
                Map<String , Object>data =  getTemperature(path);
                @SuppressWarnings("unchecked")
                
                Map<String , Object> tempMap = (Map<String, Object>) data.get("main");
                Object da = tempMap.get("temp");
                tempLabel.setText(da.toString());
                cityLabel.setText(str);
                searchTextField.clear();
                
               
            }catch(Exception oo){
                System.out.println("error");
            }
           });


       Image imgColouImage = new Image("assets/images/weatherImages/weathericon.png");
       ImageView coloudicoImageView = new ImageView(imgColouImage);
       coloudicoImageView.setFitHeight(50);
       coloudicoImageView.setFitWidth(50);  

       


        HBox cloudTempHBox = new HBox(10,coloudicoImageView,tempLabel);
        cloudTempHBox.setLayoutX(350);
        cloudTempHBox.setAlignment(Pos.CENTER);

        VBox citycloudVBox = new VBox();
        citycloudVBox.getChildren().addAll(cityLabel,cloudTempHBox);
        citycloudVBox.setAlignment(Pos.CENTER);
        citycloudVBox.setLayoutX(350);
        

       Label timeLabel = getLabel("08:35");
       timeLabel.setFont(Font.font("arial",FontWeight.BOLD,30));

       Label ampmLabel = getLabel("AM");
       ampmLabel.setFont(Font.font("arial",FontWeight.THIN,15));
       ampmLabel.setOpacity(0.7);
       
       HBox timeHBox = new HBox(4,timeLabel,ampmLabel);
       timeHBox.setLayoutX(350);
       timeHBox.setAlignment(Pos.CENTER);
       

       Label dateLabel = getLabel("Thursday, 27 June 2024");
       dateLabel.setFont(Font.font("arial",FontWeight.THIN,15));
       dateLabel.setOpacity(0.7);
       

       VBox timedateVBox= new VBox(timeHBox,dateLabel);
       timedateVBox.setAlignment(Pos.CENTER);
       timedateVBox.setLayoutX(350);
       timedateVBox.setStyle("-fx-background-color:#ecf2ea;-fx-border-color:d0f0c0;-fx-border-width:2;-fx-border-radius: 10;-fx-background-radius: 10;");
       
      timedateVBox.setMaxWidth(300);
      timedateVBox.setPadding(new Insets(25));


        VBox weatherPageVBox = new VBox(40);
        weatherPageVBox.getChildren().addAll(weather,searchHBox,citycloudVBox,timedateVBox);
        weatherPageVBox.setAlignment(Pos.CENTER);
        weatherPageVBox.setLayoutX(350);
        

        StackPane weatherStackPane = new StackPane(backgroundWeatherImageView,weatherPageVBox);


        Pane p = new Pane(weatherStackPane,backButton);

        Group gp = new Group(p);
        Scene scene = new Scene(gp,200,200);
        stage.setScene(scene);
        stage.setTitle("LoginPage");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        
    }


    Label getLabel(String name ){

        Label lb = new Label(name);
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        return lb;
    }

    
}
