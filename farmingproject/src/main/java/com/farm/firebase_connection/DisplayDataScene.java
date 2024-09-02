package com.farm.firebase_connection;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class DisplayDataScene {


    Stage stage ;
    public DisplayDataScene(Stage stage){
        this.stage = stage ;
    }



    public static List<Map<String, String>> laborData = new ArrayList<>();
    public static List<Map<String, String>> farmerData = new ArrayList<>();
    public static List<Map<String, String>> farmImplementsData = new ArrayList<>();
    public static List<Map<String, String>>  jobAppliedData = new ArrayList<>();






    public void displayData( ) throws Exception{
        List<Map<String, Object>> documents = DataService.readMultipleDocuments("Labor");
            if (documents != null && !documents.isEmpty()) {
                for (Map<String, Object> document : documents) {
                    Map<String, String> laborEntry = new HashMap<>();
                    addValueToEntry("Name", document, laborEntry);
                    addValueToEntry("Locality", document, laborEntry);
                    addValueToEntry("HiringFee", document, laborEntry);
                    addValueToEntry("Description", document, laborEntry);
                    addValueToEntry("laborImage", document, laborEntry);
                    addValueToEntry("City", document, laborEntry);
                    addValueToEntry("status", document, laborEntry);
                    laborData.add(laborEntry);
                }
            }
    }


    
   
    public void displayFarmerData( ) throws Exception{
        List<Map<String, Object>> documents = DataService.readMultipleDocuments("farmer");
        if (documents != null && !documents.isEmpty()) {
            for (Map<String, Object> document : documents) {
                Map<String, String> FarmerEntry = new HashMap<>();
                addValueToEntry("Name", document, FarmerEntry);
                addValueToEntry("Location", document, FarmerEntry);
                addValueToEntry("Salary", document, FarmerEntry);
                addValueToEntry("MobileNumber", document, FarmerEntry);
                addValueToEntry("laborImage", document, FarmerEntry);
                addValueToEntry("job-title", document, FarmerEntry);
                addValueToEntry("job-type", document, FarmerEntry);
                addValueToEntry("startDate", document, FarmerEntry);
                farmerData.add(FarmerEntry);
            }
        }
 }




    
    public void getFarmImplementsData( ) throws Exception{
        List<Map<String, Object>> documents = DataService.readMultipleDocuments("FarmImplements");
        if (documents != null && !documents.isEmpty()) {
            for (Map<String, Object> document : documents) {
                Map<String, String> farmImplementEntry = new HashMap<>();
                addValueToEntry("ProductName", document, farmImplementEntry);
                addValueToEntry("RentalDuration", document, farmImplementEntry);
                addValueToEntry("RentalFees", document, farmImplementEntry);
                addValueToEntry("FixedCharges", document, farmImplementEntry);
                addValueToEntry("City", document, farmImplementEntry);
                addValueToEntry("Locality", document, farmImplementEntry);
                addValueToEntry("Description", document, farmImplementEntry);
                addValueToEntry("FarmImplementImage", document, farmImplementEntry);
                addValueToEntry("MobileNumber", document, farmImplementEntry);
                addValueToEntry("Category", document, farmImplementEntry);
                addValueToEntry("Name", document, farmImplementEntry);
                farmImplementsData.add(farmImplementEntry);
            }
        }
    }
    public static List<Map<String, String>> animalHusbandryData = new ArrayList<>();

    
    public void getAnimalHusbandryData( ) throws Exception{

       
         List<Map<String, Object>> documents = DataService.readMultipleDocuments("AnimalHusbandry");
 
         if (documents != null && !documents.isEmpty()) {
             System.out.println("document is not empty");
             for (Map<String, Object> document : documents) {
                 Map<String, String> animalHusbandryEntry = new HashMap<>();
                 addValueToEntry("AnimalName", document, animalHusbandryEntry);
                 addValueToEntry("City", document, animalHusbandryEntry);
                 addValueToEntry("Locality", document, animalHusbandryEntry);
                 addValueToEntry("Description", document, animalHusbandryEntry);
                 addValueToEntry("AnimalHusbandryImage", document, animalHusbandryEntry);
                 addValueToEntry("MobileNumber", document, animalHusbandryEntry);
                 addValueToEntry("Category", document, animalHusbandryEntry);
                 addValueToEntry("price", document, animalHusbandryEntry);
                 animalHusbandryData.add(animalHusbandryEntry);
             }
         }
    }

    public void getJobAppliedList( String doc) throws Exception{
        List<Map<String, Object>> documents = DataService.readMultipleDocuments(doc);
            if (documents != null && !documents.isEmpty()) {
                for (Map<String, Object> document : documents) {
                    Map<String, String> laborEntry = new HashMap<>();
                    addValueToEntry("Name", document, laborEntry);
                    addValueToEntry("Locality", document, laborEntry);
                    addValueToEntry("HiringFee", document, laborEntry);
                    addValueToEntry("Description", document, laborEntry);
                    addValueToEntry("laborImage", document, laborEntry);
                    addValueToEntry("City", document, laborEntry);
                    addValueToEntry("status", document, laborEntry);
                    jobAppliedData.add(laborEntry);
                }
            }
    }

    private void addValueToEntry(String fieldName, Map<String, Object> document, Map<String, String> entry) {
        if (document.containsKey(fieldName)) {
            entry.put(fieldName, document.get(fieldName).toString());
        }
    }



    public static List<Map<String, String>> getLaborData() {
        return laborData;
    }


    public static List<Map<String, String>> getFarmImplementsList() {
        return farmImplementsData;
    }


    public static List<Map<String, String>> getAnimalHusbandryList() {
        return animalHusbandryData;
    }



    public static List<Map<String, String>> getFarmerData() {
        return farmerData;
    }


    public static List<Map<String, String>> getJobApplied() {
        return jobAppliedData ;
    }

   


}