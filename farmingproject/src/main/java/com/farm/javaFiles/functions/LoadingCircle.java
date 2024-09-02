package com.farm.javaFiles.functions;

import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoadingCircle {
    
    Stage stage ;

    public  LoadingCircle(Stage stage){
       
        this.stage = stage ;
    }


    public void getLoadingCircle(){

        ProgressIndicator progressIndicator = new ProgressIndicator();
         StackPane root = new StackPane(progressIndicator);
         Scene startScene = new Scene(root);
         stage.setScene(startScene);
         stage.setTitle("LoginPage");
         stage.setHeight(900);
         stage.setWidth(1470);
         stage.setX(0);
         stage.setY(0);
         stage.show();

    }

    
}
