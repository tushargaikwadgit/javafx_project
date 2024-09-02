package com.farm.firebase_connection;



import com.farm.controller.LoginController;
import com.farm.javaFiles.loginPage.EmailLoginPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.util.HashMap;
import java.util.Map;


public class FirebaseService {

    Stage stage;
    
    

    TextField emailField;
    EmailLoginPage emailLoginPage;
    PasswordField passwordField;
    LoginController loginController;
   // Register register;
    TextField nameField ;
    TextField ageField ;
    ComboBox farmerTypeComboBox ;
    ComboBox divTypeComboBox ;
    ComboBox disTypeComboBox ;
    String imageLink ; 


    public FirebaseService(TextField emailField,PasswordField passwordField , TextField name , TextField age ,ComboBox farmerTypecomboBox , ComboBox divTypecomboBox , ComboBox disTypecomboBox , String imageLink){
        // this.emailLoginPage = emailLoginPage;
      //  this.register = register;
        this.emailField =emailField;
        this.passwordField=passwordField;
      
        this.nameField = name ;
        this.ageField = age ;
        this.farmerTypeComboBox = farmerTypecomboBox ; 
        this.divTypeComboBox = divTypecomboBox ;
        this.disTypeComboBox = disTypecomboBox ;
        this.imageLink = imageLink ;




    }

    public void registerMethod(){
       try {
           
            // Create user request
            String email =  emailField.getText();
            String password = passwordField.getText();
            String name =  nameField.getText() ;
            String age = ageField.getText();
            String farmerType = farmerTypeComboBox.getValue().toString();
            String division = divTypeComboBox.getValue().toString();
            String district = disTypeComboBox.getValue().toString();
            String imageLink = this.imageLink ;


            System.out.println(farmerType);
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created user: " + userRecord.getEmail());
            showAlert("Success", "User created successfully."+"\nUserId : "+userRecord.getUid()+"\nUserEmailId : "+userRecord.getEmail()+"\n Please Rember the Password!!");

            Map<String,String> data=new HashMap<>();
            data.put("email",email);
            data.put("password",password);
            data.put("name",name);
            data.put("age",age);
            data.put("farmerType",farmerType);
            data.put("division",division);
            data.put("district",district);
            data.put("profileImage",imageLink);
            try{
                 DataService.addData("users",email ,data); 
            }catch(Exception e){
                System.out.println("error aala");
            }

          } catch (FirebaseAuthException e) {
            showAlert("Error", "Failed to create user: " + e.getMessage());
            e.printStackTrace();
        }
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
