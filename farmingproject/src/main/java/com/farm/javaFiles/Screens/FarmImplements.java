package com.farm.javaFiles.Screens;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.farm.firebase_connection.DisplayDataScene;
import com.farm.javaFiles.functions.AppBar;
import com.farm.javaFiles.loginPage.AgricultureUI;

// public class FarmImplements {

//    public  GridPane gp;
//    // public List<Map<String, String>> farmImplementsData;
//   public Stage stage;

//     public FarmImplements(Stage stage) {
//         this.stage = stage;
//         initializeGridPane();
//         //initializeScrollPane();
       
//     }

//     private void initializeGridPane() {
//         gp = new GridPane();
//         gp.setHgap(80);
//         gp.setVgap(60);
//         gp.setPadding(new Insets(50, 80, 50, 80));
//         gp.setStyle("-fx-background-color:LIGHTGRAY");
//     }

    

//     public void getfarmImplements(){

//         System.out.println("In farmImplemenets ");
//       //  System.out.println(farmImplementsData);


//         // SearchAppBar searchObj = new SearchAppBar(stage);
//         // BorderPane farmBorderPane =   searchObj.getToolBar();


//         AppBar appbarObj  = new AppBar(stage);
//         BorderPane appBar = appbarObj.getToolBar("farm");
        

//         VBox finalVBox = new VBox(50, appBar);
//         finalVBox.setPadding(new Insets(0, 0, 0, 0));

//         AnimalHusbandry obj = new AnimalHusbandry(stage);

//         // gp = new GridPane();
//         // gp.setHgap(80);
//         // gp.setVgap(60);
//         // gp.setPadding(new Insets(50, 80, 50, 80));
//         // gp.setStyle("-fx-background-color:LIGHTGRAY");

//         int columns = 3;
//         int numItems = AgricultureUI.farmImplementsData.size();

//         for (int i = 0; i < numItems; i++) {
//             int row = i / columns;
//             int col = i % columns;

//             VBox kalvadamendhaImageView = obj.takeImage(new Image(AgricultureUI.farmImplementsData.get(i).get("FarmImplementImage")));
//             Label kalvadLabel = obj.getNameLabel(AgricultureUI.farmImplementsData.get(i).get("ProductName"));
//             kalvadLabel.setWrapText(true);
//             Label categoryLabel = obj.getLocation("Category : "+AgricultureUI.farmImplementsData.get(i).get("Category"));
//             ImageView locationImageView = new ImageView(new Image("assets/images/farmImplements/file.png"));
//             locationImageView.setFitWidth(20);
//             locationImageView.setFitHeight(20);
//             Label locationLabel = obj.getLocation(AgricultureUI.farmImplementsData.get(i).get("City") + "," +AgricultureUI.farmImplementsData.get(i).get("Locality"));
//             HBox hb = new HBox(2, locationImageView, locationLabel);
//             Label rupeekalvadLabel = obj.getPriceLabel("₹" + AgricultureUI.farmImplementsData.get(i).get("RentalFees") + "/" + AgricultureUI.farmImplementsData.get(i).get("RentalDuration"));
//             Label contactLabel = obj.getContactLabel("Description:\n" + AgricultureUI.farmImplementsData.get(i).get("Description"));

//             VBox animalVBox = obj.getVBox(kalvadamendhaImageView, kalvadLabel, categoryLabel,hb, rupeekalvadLabel, contactLabel);

//             // Add event handler for VBox click
//             int index = i;
//             animalVBox.setOnMouseClicked(e -> {
               
//                 InnerScreen innerScreen = new InnerScreen(stage, AgricultureUI.farmImplementsData);
//                 clearGridPane();
//                 innerScreen.showDetails(index);

                
//             });

//             gp.add(animalVBox, col, row);
//         }

//         ScrollPane scrollPane = new ScrollPane(gp);
//         scrollPane.setPadding(new Insets(10));
//         scrollPane.setFitToWidth(true);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

//         VBox vb = new VBox(finalVBox, scrollPane);
//         Scene scene = new Scene(vb);
//         scene.setFill(Color.LIGHTGRAY);
//         stage.setScene(scene);
//         stage.setTitle("Farm Implements");
//         stage.setHeight(900);
//         stage.setWidth(1470);
//         stage.setX(0);
//         stage.setY(0);
//         stage.show();
//     }

//     public void clearGridPane() {
//         if (gp != null) {
//             gp.getChildren().clear();
//         } else {
//             System.out.println("GridPane (gp) is null. Initialize it first.");
//         }
//     }
    
//     public void updateGridPaneByCategory(String category) {
//         AnimalHusbandry obj = new AnimalHusbandry(stage);
//         gp.getChildren().clear();

//         int columns = 3;
//         int numItems = AgricultureUI.farmImplementsData.size();
//         int row = 0;
//         int col = 0;

//         for (int i = 0; i < numItems; i++) {
//             if (AgricultureUI.farmImplementsData.get(i).get("Category").equalsIgnoreCase(category)) {
//                 VBox kalvadamendhaImageView = obj.takeImage(new Image(AgricultureUI.farmImplementsData.get(i).get("FarmImplementImage")));
//                 Label kalvadLabel = obj.getNameLabel(AgricultureUI.farmImplementsData.get(i).get("ProductName"));
//                 kalvadLabel.setWrapText(true);
//                 Label categoryLabel = obj.getLocation("Category : "+AgricultureUI.farmImplementsData.get(i).get("Category"));
//                 ImageView locationImageView = new ImageView(new Image("assets/images/farmImplements/file.png"));
//                 locationImageView.setFitWidth(20);
//                 locationImageView.setFitHeight(20);
//                 Label locationLabel = obj.getLocation(AgricultureUI.farmImplementsData.get(i).get("City") + "," +AgricultureUI.farmImplementsData.get(i).get("Locality"));
//                 HBox hb = new HBox(2, locationImageView, locationLabel);
//                 Label rupeekalvadLabel = obj.getPriceLabel("₹" + AgricultureUI.farmImplementsData.get(i).get("RentalFees") + "/" + AgricultureUI.farmImplementsData.get(i).get("RentalDuration"));
//                 Label contactLabel = obj.getContactLabel("Description:\n" + AgricultureUI.farmImplementsData.get(i).get("Description"));

//                 VBox animalVBox = obj.getVBox(kalvadamendhaImageView, kalvadLabel, categoryLabel,hb, rupeekalvadLabel, contactLabel);

//                 int index = i;
//                 animalVBox.setOnMouseClicked(e -> {
//                     InnerScreen innerScreen = new InnerScreen(stage, AgricultureUI.farmImplementsData);
//                     clearGridPane();
//                     innerScreen.showDetails(index);
//                 });

//                 gp.add(animalVBox, col, row);
//                 col++;
//                 if (col == columns) {
//                     col = 0;
//                     row++;
//                 }
//             }
//         }
//     }

// }



public class FarmImplements {
    private GridPane gp;
    private ScrollPane scrollPane;
    private Stage stage;
    private VBox finalVBox;

    public FarmImplements(Stage stage) {
        this.stage = stage;
        initializeGridPane();
        initializeScrollPane();
        initializeAppBar();
    }

    private void initializeGridPane() {
        gp = new GridPane();
        gp.setHgap(80);
        gp.setVgap(60);
        gp.setPadding(new Insets(50, 80, 50, 80));
        gp.setStyle("-fx-background-color:LIGHTGRAY");
    }

    private void initializeScrollPane() {
        scrollPane = new ScrollPane(gp);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private void initializeAppBar() {
        AppBar appbarObj = new AppBar(stage, this,null);
        BorderPane appBar = appbarObj.getToolBar("farm");
        finalVBox = new VBox(50, appBar);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public VBox getFinalVBox() {
        return finalVBox;
    }

    public void getfarmImplements() {
        System.out.println("In farmImplements");
        populateGridPane(AgricultureUI.farmImplementsData); // Call helper method to populate GridPane
    }

    private void populateGridPane(List<Map<String, String>> data) {
        AnimalHusbandry obj = new AnimalHusbandry(stage);
        int columns = 3;
        int numItems = data.size();
        int row = 0;
        int col = 0;

        for (int i = 0; i < numItems; i++) {
            VBox kalvadamendhaImageView = obj.takeImage(new Image(data.get(i).get("FarmImplementImage")));
            Label kalvadLabel = obj.getNameLabel(data.get(i).get("ProductName"));
            kalvadLabel.setWrapText(true);
            Label categoryLabel = obj.getLocation("Category : " + data.get(i).get("Category"));
            ImageView locationImageView = new ImageView(new Image("assets/images/farmImplements/file.png"));
            locationImageView.setFitWidth(20);
            locationImageView.setFitHeight(20);
            Label locationLabel = obj.getLocation(data.get(i).get("City") + "," + data.get(i).get("Locality"));
            HBox hb = new HBox(2, locationImageView, locationLabel);
            Label rupeekalvadLabel = obj.getPriceLabel("₹" + data.get(i).get("RentalFees") + "/" + data.get(i).get("RentalDuration"));
            Label contactLabel = obj.getContactLabel("Description:\n" + data.get(i).get("Description"));

            VBox animalVBox = obj.getVBox(kalvadamendhaImageView, kalvadLabel, categoryLabel, hb, rupeekalvadLabel, contactLabel);

            int index = i;
            animalVBox.setOnMouseClicked(e -> {
                InnerScreen innerScreen = new InnerScreen(stage, data);
                clearGridPane();
                innerScreen.showDetails(index);
            });

            gp.add(animalVBox, col, row);
            col++;
            if (col == columns) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(gp);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox vb = new VBox(finalVBox, scrollPane);
        Scene scene = new Scene(vb);
        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);
        stage.setTitle("Farm Implements");
        stage.setHeight(900);
        stage.setWidth(1470);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }

    public void clearGridPane() {
        if (gp != null) {
            gp.getChildren().clear();
        } else {
            System.out.println("GridPane (gp) is null. Initialize it first.");
        }
    }

    public void updateGridPaneByCategory(String category) {
        // if(categoryExists(category)){
        //     System.out.println("Not Availabe!!");
        //         showAlert("not exits", "Not found");
        // }       
       
        clearGridPane();
        List<Map<String, String>> filteredData = AgricultureUI.farmImplementsData.stream()
                .filter(item -> item.get("Category").equalsIgnoreCase(category))
                .collect(Collectors.toList());
        populateGridPane(filteredData);
    }

//      private boolean categoryExists(String category) {
//      List <String> availableCategories = AgricultureUI.farmImplementsData.stream()
//                 .map(item -> item.get("Category"))
//                 .distinct()
//                 .collect(Collectors.toList());
//     return availableCategories.contains(category);
// }

// // Method to show an alert
// private void showAlert(String title, String message) {
//     Alert alert = new Alert(AlertType.INFORMATION);
//     alert.setTitle(title);
//     alert.setHeaderText(null);
//     alert.setContentText(message);
//     alert.showAndWait();
// }

}
