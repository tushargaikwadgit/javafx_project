package com.farm.firebase_connection;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.farm.javaFiles.loginPage.EmailLoginPage;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class DataService {
 static private Firestore db;
   static private  boolean isInitialize = false ;

    static{
        try{
            if(!isInitialize){
                initializeFirebase();
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void initializeFirebase() throws IOException {


        FileInputStream serviceAccount = new FileInputStream("hellokrushiapp_javafx-devolopement/farmingproject/src/main/resources/javafx-project1-83368-firebase-adminsdk-rh85d-019d4fb45c.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        db=FirestoreClient.getFirestore();
        isInitialize = true ;

    }

    public static void addData(String collection,String document,Map<String,String> data) throws InterruptedException, ExecutionException{
        DocumentReference docRef=db.collection(collection).document(document);
        ApiFuture<WriteResult> result=docRef.set(data);
        result.get();
    }


    public static List<Map<String, Object>> readMultipleDocuments(String collection) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> documentsList = new ArrayList<>();

        try {
            CollectionReference collectionReference = db.collection(collection);
            ApiFuture<QuerySnapshot> future = collectionReference.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                documentsList.add(document.getData());
               // System.out.println("documentList"+documentsList);
            }
        } catch (Exception e) {
            System.out.println("in catch");
        
            // e.printStackTrace();
            // throw e;
        }
        System.out.println(documentsList.get(0));

        return documentsList;
    }

     public static DocumentSnapshot document ;
    
     public static  DocumentSnapshot getUserCrediential(String userEmail){
        Firestore db = FirestoreClient.getFirestore();
        try {
            ApiFuture<DocumentSnapshot> collectionReference = db.collection("users").document(userEmail).get();
             document = collectionReference.get();
            if(document.exists()){
                System.out.println(document.getData());
            }else{
                System.out.println("no such elmenet");
            }
        } catch (Exception e) {
            System.out.println("in catch");
        }
      
        return document ;
    

    }

    public static void updateLaborStatus(String fieldToUpdate , String updateMsg){
            String documentId = EmailLoginPage.userEmail ;
         
            // Create a map with the field and its new value
            Map<String, Object> updates = new HashMap<>();
            updates.put(fieldToUpdate, updateMsg); // Replace "updatedValue" with your new value

            // Create a reference to the document and update the field
            DocumentReference docRef = db.collection("Labor").document(documentId);
            ApiFuture<WriteResult> future = docRef.set(updates, SetOptions.merge());
            DocumentReference docRef1 = db.collection(EmailLoginPage.userEmail).document(documentId);
            ApiFuture<WriteResult> future1 = docRef1.set(updates, SetOptions.merge());
     
     
    }
    


    
    
}
