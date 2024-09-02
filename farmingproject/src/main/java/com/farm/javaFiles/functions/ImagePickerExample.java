package com.farm.javaFiles.functions;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;



public class ImagePickerExample  {
    private static final String bucketName="javafx-project1-83368.appspot.com";
     public String imageLink ;

    ImageView pickedImageView ;
    Stage stage  ;

     public ImagePickerExample(Stage stage){
      this.stage = stage ;
    }

    public String  getImage() {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg","*.jpeg"));

             File selectedFile=fileChooser.showOpenDialog(stage);
             if(selectedFile!=null){
                 imageLink= uploadImage(selectedFile.getPath(), selectedFile.getName());
                 if(imageLink!=null){
                   System.out.println(imageLink);
                 }
             }
             return imageLink ;
    
    }

       public static String uploadImage(String localFilePath,String uploadFileName){
        try{
            FileInputStream serviceAccount=new FileInputStream("hellokrushiapp_javafx-devolopement/farmingproject/src/main/resources/javafx-project1-83368-firebase-adminsdk-rh85d-019d4fb45c.json");
            Storage storage=(Storage) StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();

            Path path=Paths.get(localFilePath);
            byte[] bytes=Files.readAllBytes(path);

            BlobId blobId=BlobId.of(bucketName,uploadFileName);
            BlobInfo blobInfo=BlobInfo.newBuilder(blobId)
                .setContentType("image/jpeg")
                .setAcl(Arrays.asList(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER)))
                .build();

            storage.create(blobInfo,bytes);
            return "https://storage.googleapis.com/"+bucketName+"/"+uploadFileName;

        }catch(IOException e){
            e.printStackTrace();

            return null ;
            
        }
    }

}
