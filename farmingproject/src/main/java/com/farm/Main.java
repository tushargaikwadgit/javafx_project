

package com.farm;



import com.farm.javaFiles.loginPage.AgricultureUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
  

    @Override
    public void start(Stage primaryStage) throws Exception {

      AgricultureUI obj = new AgricultureUI(primaryStage);
      obj.startScreen();

   
     }
    
    public static void main(String[] args) {
        System.out.println("Hello world!");
      launch(args);
        
      }
    

}
