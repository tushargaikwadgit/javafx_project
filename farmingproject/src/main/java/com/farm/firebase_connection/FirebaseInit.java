// package com.farm.firebase_connection;

// import java.io.FileInputStream;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;
// import java.util.ArrayList;
// import java.util.List;

// import com.google.api.core.ApiFuture;
// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.cloud.firestore.CollectionReference;
// import com.google.cloud.firestore.DocumentReference;
// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.Firestore;
// import com.google.cloud.firestore.QueryDocumentSnapshot;
// import com.google.cloud.firestore.QuerySnapshot;
// import com.google.cloud.firestore.SetOptions;
// import com.google.cloud.firestore.WriteResult;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import com.google.firebase.cloud.FirestoreClient;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

// public class FirebaseInit {

//     private static Firestore db;
//     public static void initializeFirebase() throws IOException {
//         FileInputStream serviceAccount = new FileInputStream("src/main/resources/fx-auth-fb.jason");

//         FirebaseOptions options = new FirebaseOptions.Builder()
//                 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                 .setDatabaseUrl("https://javafx-project1-83368-default-rtdb.asia-southeast1.firebasedatabase.appp")
//                 .build();

//         FirebaseApp.initializeApp(options);
//         db=FirestoreClient.getFirestore();

//     }

//     public void createRec() throws InterruptedException,ExecutionException{

//         Map<String,Object> teamData=new HashMap<>();
//         teamData.put("team","India");
//         teamData.put("captain", "Rohit Sharma");
//         teamData.put("manOfMatch", "Virat Kohli");
       

//         ApiFuture<WriteResult> future=db.collection("T20WTC").document("winner").set(teamData);
//         System.out.println("Update Time"+ future.get().getUpdateTime());

//     }

//     // public void createRecordWithImageUrl(String imageUrl) throws InterruptedException, ExecutionException {
//     //     Map<String, Object> teamData = new HashMap<>();
//     //     teamData.put("team", "India");
//     //     teamData.put("captain", "Rohit Sharma");
//     //     teamData.put("manOfMatch", "Virat Kohli");
//     //     teamData.put("imageUrl", imageUrl); 

//     //     ApiFuture<WriteResult> future = db.collection("T20WTC").document("winner").set(teamData);
//     //     System.out.println("Update Time: " + future.get().getUpdateTime());
//     // }

//     public static Map<String, Object> readRec()throws InterruptedException,ExecutionException{
    
//         DocumentReference docRef=db.collection("T20WTC").document("winner");
//         ApiFuture<DocumentSnapshot> snapShot=docRef.get();
//         DocumentSnapshot docSnap=snapShot.get();
        

//         if(docSnap.exists()){
//             // System.out.println(docSnap.get("team"));
//             // System.out.println(docSnap.get("captain"));
//             // System.out.println(docSnap.get("manOfMatch"));
//             return docSnap.getData();
//         }else{
//             System.out.println("Document Not Found");
//             return null;
//         }        
//     } 

//     public static List<Map<String, Object>> readMultipleDocuments(String collectionName) throws InterruptedException, ExecutionException {
//         CollectionReference collectionRef = db.collection(collectionName);
//         ApiFuture<QuerySnapshot> querySnapshot = collectionRef.get();

//         List<Map<String, Object>> documents = new ArrayList<>();
//         for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
//             documents.add(document.getData());
//         }
//         return documents;
//     }

//     public void updateRec(){
//         Map<String,Object> updateData=new HashMap<>();
//         updateData.put("manOfSeries","J.Bumrah");

//         ApiFuture<WriteResult> future=db.collection("T20WTC").document("winner").set(updateData,SetOptions.merge());
//     }
// }


// // import java.io.FileInputStream;
// // import java.io.IOException;

// // import com.google.auth.oauth2.GoogleCredentials;
// // import com.google.cloud.firestore.Firestore;
// // import com.google.cloud.storage.Storage;
// // import com.google.cloud.storage.StorageOptions;
// // import com.google.firebase.FirebaseApp;
// // import com.google.firebase.FirebaseOptions;
// // import com.google.firebase.cloud.FirestoreClient;

// // public class FirebaseInit {
// //     private static Firestore db;
// //     private static Storage storage;

// //     public static void initializeFirebase() throws IOException {
// //         FileInputStream serviceAccount = new FileInputStream("src\\main\\resources\\fx-project.json");

// //         // FirebaseOptions options = new FirebaseOptions.builder()
// //         //         .setCredentials(GoogleCredentials.fromStream(serviceAccount))
// //         //         .setDatabaseUrl("https://fx-project-4d753-default-rtdb.asia-southeast1.firebasedatabase.app")
// //         //         .build();

// //         FirebaseOptions options = FirebaseOptions.builder()
// //                 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
// //                 .setDatabaseUrl("https://fx-project-4d753-default-rtdb.asia-southeast1.firebasedatabase.app")
// //                 .build();
            

// //         FirebaseApp.initializeApp(options);
// //         db = FirestoreClient.getFirestore();
// //         storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();
// //     }

// //     public static Firestore getFirestore() {
// //         return db;
// //     }

// //     public static Storage getStorage() {
// //         return storage;
// //     }
// // }