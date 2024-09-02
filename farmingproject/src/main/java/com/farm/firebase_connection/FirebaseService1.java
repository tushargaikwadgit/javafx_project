package com.farm.firebase_connection;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.farm.javaFiles.Screens.HomePage;
import com.farm.javaFiles.loginPage.EmailLoginPage;


import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class FirebaseService1 {
     Stage stage;

     boolean isLogin = false ;
    
    

    TextField emailField;
    EmailLoginPage emailLoginPage;
    
    PasswordField passwordField;

     public FirebaseService1(EmailLoginPage emailLoginPage ,TextField emailField,PasswordField passwordField){

        this.emailLoginPage = emailLoginPage;
        

        this.emailField =emailField;
     
        this.passwordField=passwordField;

    }

     public void continuemethod(HomePage obj1){

        String email = emailField.getText();
        String password = passwordField.getText();

        try{

            String apiKey ="AIzaSyAawKv-Wrc9e6j1lXPDnDzDeUJiJdKOFp8";

            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="+apiKey);
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/jason; charset=UTF-8");
            conn.setDoOutput(true);

           JSONObject josonRequest= new JSONObject(); 
            josonRequest.put("email", email);
            josonRequest.put("password", password);
            josonRequest.put("returnSecureToken", true);

            try(OutputStream os = conn.getOutputStream()){
                byte[] input = josonRequest.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

                // to Check response
                if (conn.getResponseCode() == 200) {

                   obj1.homeScreen();

                    System.out.println("successul login");
                   showAlert(true);
               
                   
                } else {
                    showAlert("Invalid Login", "Invalid credentials!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(false);
            }
        }

      void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    void showAlert(boolean b){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("login successfully");
        alert.setHeaderText(null);
        alert.setContentText("To continue please click on ok ");
        alert.showAndWait();
        
        System.out.println("hello welcome||");
              
        
    
    }


    void getHome(){
        if(isLogin==true){
            HomePage obj = new HomePage(stage);
            obj.homeScreen();
        }else{
            System.out.println("error");
        }
    }



}

