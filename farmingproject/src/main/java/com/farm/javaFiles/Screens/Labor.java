package com.farm.javaFiles.Screens;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.farm.firebase_connection.DisplayDataScene;
import com.farm.javaFiles.functions.LaborForm;
import com.farm.javaFiles.functions.AppBar;
import com.farm.javaFiles.loginPage.AgricultureUI;
import com.farm.javaFiles.loginPage.EmailLoginPage;
import com.google.cloud.storage.Acl.Group;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Labor {

    // static  List<Map<String , String>> laborData ;
   private Stage stage ;
  
   GridPane gp = new GridPane();
   VBox progressVbox  = new VBox();
   VBox hb = new VBox() ;
   VBox vb = new VBox() ;
 //  VBox bottom = new VBox();
   VBox finalVBox  = new VBox();
   Timeline timeline3 ;

    public static  String farmerTy = EmailLoginPage.farmerType ;

   ObservableList<CheckBox> cityCheckBoxes ;


    public Labor(Stage stage){
        this.stage = stage ;
        // this.laborData = laborData ;
    }


    ProgressIndicator progressIndicator ;
   



    ObservableList<String> cityNames = FXCollections.observableArrayList(
            "Mumbai", "Pune", "Nagpur", "Thane", "Nashik", "Kalyan-Dombivali", "Vasai-Virar",
            "Aurangabad", "Navi Mumbai", "Solapur", "Mira-Bhayandar", "Bhiwandi", "Amravati",
            "Nanded", "Kolhapur", "Sangli-Miraj & Kupwad", "Malegaon", "Jalgaon", "Akola",
            "Latur", "Dhule", "Ahmednagar", "Chandrapur", "Parbhani", "Ichalkaranji",
            "Jalna", "Ambarnath", "Bhusawal", "Panvel", "Badlapur", "Beed", "Gondia", "Satara",
            "Baramati", "Wardha", "Yavatmal", "Achalpur", "Osmanabad", "Nandurbar", "Hingoli",
            "Udgir", "Hinganghat", "Washim", "Lonavala", "Shirpur", "Pandharpur", "Malkapur",
            "Dahanu", "Uran", "Chalisgaon", "Mehkar", "Manmad", "Sillod", "Pusad", "Risod",
            "Yawal", "Sangole", "Sinnar", "Ozar", "Paithan", "Wai", "Uchgaon", "Vadgaon",
            "Shirdi", "Morshi", "Khamgaon", "Ghatanji", "Digras", "Gadchiroli", "Pathardi"
        );
   

   public void getGrid(){

        gp.getChildren().clear();


        System.out.println(farmerTy);
        
        if(farmerTy.trim().equals("शेतकरी")){
            for (int i = 0; i < AgricultureUI.laborData.size(); i++) {
                Map<String, String> data = AgricultureUI.laborData.get(i);
                HBox laborCard = createLaborCard(data);
                laborCard.setOnMouseClicked(e->{
                    WorkerInnerScreen obj=new WorkerInnerScreen(stage, AgricultureUI.laborData);
                    obj.showDetails(0);
                });
                gp.add(laborCard, 0, i);
            }
          
          
        }else{
          
            for (int i = 0; i < AgricultureUI.farmerData.size(); i++) {
                Map<String, String> data = AgricultureUI.farmerData.get(i);
                HBox laborCard = createFarmerCard(data);
                laborCard.setOnMouseClicked(e->{
                    FarmerInnerScreen obj=new FarmerInnerScreen(stage, AgricultureUI.farmerData);
                    obj.showDetails(0);
    
                });
                gp.add(laborCard, 0, i);
            }
            
        }
}


   public HBox createLaborCard(Map<String, String> data){

    ImageView imgImageView = takeImage(new Image(data.get("laborImage")));
    Label name = getNameLabel("Name :"+ data.get("Name"));
    Image ig = new Image("assets/images/labor/location.png");
    ImageView iv = new ImageView(ig);

    iv.setFitWidth(30);
    iv.setFitHeight(30);
    Label location = getLocationLabel("Location : "+data.get("City")+","+data.get("Locality"));
    HBox locationHBox = new HBox(2,iv,location);
    Label price = getPriceLabel("Fee :"+data.get("HiringFee")+" /Day" );
    ImageView heart = takeIconImage(new Image("assets/images/labor/HEART.png"));
    ImageView like= takeIconImage(new Image("assets/images/labor/like.png"));   
    HBox likeheartHBox = new HBox(10,heart,like);
    Label description = getDescriptionLabel("Description:"+(data.get("Description")));
    HBox priceDescHBox = new HBox(150,price,likeheartHBox);
    Label status1 = new Label("Status :");
    Label status2 = new Label(data.get("status"));
    status2.setFont(new Font(20));
    System.out.println(data.get("status"));
    if(data.get("status").trim().equals("available now")){

        status2.setStyle("-fx-text-fill: green; -fx-font-weight: bold");
    }else{
        status2.setStyle("-fx-text-fill: red ; -fx-font-weight: bold");
    }
    
    HBox status = new HBox(10 ,status1 , status2);
  
    VBox vb  = new VBox(10,name,locationHBox,priceDescHBox,status,description);
    vb.setPrefWidth(450);
    vb.setAlignment(Pos.CENTER_LEFT);
    HBox hb = new HBox(80,imgImageView,vb);
    hb.setPrefWidth(900);
    hb.setPrefHeight(250);
    hb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
    hb.setPadding(new Insets(21));
    hb.setAlignment(Pos.CENTER);
    hb.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
    hb.setScaleX(1.02);
    hb.setScaleY(1.02);
    });
    hb.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
        hb.setScaleX(1.0);
        hb.setScaleY(1.0);
    });
    return hb;

    }

    public HBox createFarmerCard(Map<String, String> data){
        ImageView imgImageView = takeImage(new Image(data.get("laborImage")));
           StackPane stackPane = new StackPane(imgImageView);
           stackPane.setMaxSize(160, 160);
            Border border = new Border(new BorderStroke(
                Color.GREEN, // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(80), // Corner radii, match the clip's corner radii
                new BorderWidths(5) // Border widths
        ));
            stackPane.setBorder(border);
            Label name = getNameLabel("Farmer name :"+ data.get("Name"));
            Image ig = new Image("assets/images/labor/location.png");
            ImageView iv = new ImageView(ig);
            iv.setFitWidth(30);
            iv.setFitHeight(30);
            Label location = getLocationLabel("Location :"+data.get("Location"));
            HBox locationHBox = new HBox(2,iv,location);
            Label price = getPriceLabel(data.get("Salary") );
            Label job_title = getDescriptionLabel("शीर्षक: "+data.get("job-title") );
            Label job_type = getDescriptionLabel("प्रकार :"+data.get("job-type") );
            HBox aboutJob = new HBox(100, job_title , job_type);

            ImageView heart = takeIconImage(new Image("assets/images/labor/HEART.png"));
            ImageView like= takeIconImage(new Image("assets/images/labor/like.png"));   
            HBox likeheartHBox = new HBox(10,heart,like);
            HBox likeFinalBox = new HBox(200 , price , likeheartHBox);
            Button apply = new Button("Apply Now");
            apply.setPrefWidth(230);
            apply.setStyle("-fx-background-color: green; -fx-text-fill: white;-fx-background-radius: 15");
            VBox vb  = new VBox(15,name,likeFinalBox,locationHBox ,aboutJob, apply);
            vb.setPrefWidth(450);
            vb.setAlignment(Pos.CENTER_LEFT);
            HBox hb = new HBox(80,stackPane,vb);
            hb.setPrefWidth(900);
            hb.setPrefHeight(250);
            hb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
            hb.setPadding(new Insets(21));
            hb.setAlignment(Pos.CENTER);
            hb.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            hb.setScaleX(1.02);
            hb.setScaleY(1.02);
            
            });
            hb.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                hb.setScaleX(1.0);
                hb.setScaleY(1.0);
            });
            return hb;
    }
    public void applyFilter(int index){
        int deletedNo = 0  ;
        System.out.println("hello");

        for(int i = 0 ; i< AgricultureUI.laborData.size() ; i++){
            if(!cityNames.get(index).equalsIgnoreCase(AgricultureUI.laborData.get(i).get("Locality"))){
                gp.getChildren().remove(i-deletedNo);
                deletedNo  = deletedNo+1 ;
            }
        }
    }

    // public void isTwoSelected(){

    //     int count = 0 ;
    //     for(CheckBox checkBox : cityCheckBoxes){
    //         if(checkBox.isSelected()){
    //             count++ ;
    //         }

    //     }

      
    // }


   public  void getLaborScreen(){

   
        gp.getChildren().clear();
        gp.setHgap(60);
        gp.setVgap(17);
        gp.setPadding(new Insets(30,70,30,80));
        gp.setStyle("-fx-background-color:LIGHTGRAY");
 
        AppBar appbarObj  = new AppBar(stage);
        BorderPane appBar = appbarObj.getToolBar("labor");
        

        VBox appbarVBox = new VBox(50,appBar);
        appbarVBox.setPadding(new Insets(10,0,0,0)); // Padding around the VBox
        appbarVBox.setAlignment(Pos.TOP_LEFT);
        
        // Create the VBox layout
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(15));
        vbox.setPrefSize(350, 800);

        // Hiring Pattern section
        Label hiringPatternLabel = getL("Hiring Pattern");
        CheckBox perHourCheckBox = new CheckBox("Per Hour");
        CheckBox perDayCheckBox = new CheckBox("Per Day");
        VBox hiringPatternBox = new VBox(5, hiringPatternLabel, perHourCheckBox, perDayCheckBox);

        // Ad Type section
        Label adTypeLabel = getL("Sort By");
        CheckBox lowToHigh = new CheckBox("Price -Low to High");
        CheckBox highToLow = new CheckBox("Price -High to Low ");
        VBox adTypeBox = new VBox(5, adTypeLabel, lowToHigh, highToLow);

       
       
        highToLow.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("hii");
                lowToHigh.setSelected(false);
                Collections.sort(AgricultureUI.laborData, new Comparator<Map<String, String>>() {
                    @Override
                    public int compare(Map<String, String> map1, Map<String, String> map2) {
                        String price1 = map1.get("HiringFee");
                        String price2 = map2.get("HiringFee");
                        // Convert String to Double for comparison
                        return Double.compare(Double.parseDouble(price2), Double.parseDouble(price1));
                    }
                });
                getGrid();
            }
            else{
                System.out.println("unselected");
                getGrid();
               
            }         
        });

        lowToHigh.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                highToLow.setSelected(false);

                Collections.sort(AgricultureUI.laborData, new Comparator<Map<String, String>>() {
                    @Override
                    public int compare(Map<String, String> map1, Map<String, String> map2) {
                        String price1 = map1.get("HiringFee");
                        String price2 = map2.get("HiringFee");
                        // Convert String to Double for comparison
                        return Double.compare(Double.parseDouble(price1), Double.parseDouble(price2));
                    }
                });
                getGrid();
            }else{
                System.out.println("unseleted");
                getGrid();

            }
               
        });



        // Hiring Fee Range section
        Label hiringFeeRangeLabel = getL("Hiring Fee Range");
        TextField minFeeTextField = new TextField();
        minFeeTextField.setPromptText("₹ Min");
        TextField maxFeeTextField = new TextField();
        maxFeeTextField.setPromptText("₹ Max");
        HBox hiringFeeRangeBox = new HBox(5, minFeeTextField, maxFeeTextField);
        VBox hiringFeeRangeContainer = new VBox(5, hiringFeeRangeLabel, hiringFeeRangeBox);

        // Hiring Fee Type section
        Label hiringFeeTypeLabel = getL("Hiring Fee Type");
        CheckBox fixedCheckBox = new CheckBox("Fixed");
        CheckBox negotiableCheckBox = new CheckBox("Negotiable");
        VBox hiringFeeTypeBox = new VBox(5, hiringFeeTypeLabel, fixedCheckBox, negotiableCheckBox);

        // Locality section
        Label localityLabel =getL("Locality");
        TextField searchLocalityTextField = new TextField();
        searchLocalityTextField.setPromptText("Search Locality");


        

         cityCheckBoxes = FXCollections.observableArrayList();
        for (String cityName : cityNames) {
            cityCheckBoxes.add(new CheckBox(cityName));
        }



      
       
       

        cityCheckBoxes.get(0).selectedProperty().addListener( 
            (observable, oldValue, newValue)->{
                if(newValue){
                    applyFilter(0);
                    //isTwoSelected();
                }else{
                    gp.getChildren().clear();
                    getGrid();
                }
             }
        );
    
        cityCheckBoxes.get(1).selectedProperty().addListener( 
            (observable, oldValue, newValue)->{
                if(newValue){
                    applyFilter(1);
                   // isTwoSelected();
                }else{
                    gp.getChildren().clear();
                    getGrid();
                 }
             }
        );
        cityCheckBoxes.get(2).selectedProperty().addListener( 
            (observable, oldValue, newValue)->{
                if(newValue){
                    applyFilter(2);
                   // isTwoSelected();
                }
                else{
                    gp.getChildren().clear();
                    getGrid();
                }
             }
        );

        
          

        // FilteredList for the search functionality
        FilteredList<CheckBox> filteredCities = new FilteredList<>(cityCheckBoxes, p -> true);
      
        
        // ListView to display city names
        ListView<CheckBox> localityListView = new ListView<>(filteredCities);
        localityListView.setPrefHeight(280); // Set preferred height for the list view

        

        // Add a listener to the search text field to filter the list
        searchLocalityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCities.setPredicate(checkBox -> {
                // If filter text is empty, display all cities
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare city names with the filter text
                String lowerCaseFilter = newValue.toLowerCase();
                return checkBox.getText().toLowerCase().contains(lowerCaseFilter);
            });
        });

        

        VBox localityBox = new VBox(5, localityLabel, searchLocalityTextField, localityListView);

        // Add all sections to the VBox

        vbox.getChildren().addAll(hiringPatternBox, adTypeBox, hiringFeeRangeContainer, hiringFeeTypeBox, localityBox);





        //// Right side workerrs Screen program starts from here 


            progressIndicator = new ProgressIndicator();
            progressIndicator.setMinSize(30, 30);
            progressIndicator.setMaxSize(70, 70);
            gp.getChildren().add(progressIndicator);
            ScrollPane scrollPane = new ScrollPane(gp);
            scrollPane.setPadding(new Insets(10));


        
        // Optional: Customize scroll pane properties
        scrollPane.setFitToWidth(true); // Ensure content width fits the ScrollPane width
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar if not needed
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show vertical scrollbar as needed



        progressVbox = new VBox(progressIndicator);
        progressVbox.setPadding(new Insets(350,0,0,450));
    
        vb.getChildren().add(scrollPane);
        // bottom.setLayoutX(20);
        // bottom.setPrefWidth(1050);
       
        HBox hb = new HBox( );
        hb.getChildren().add(vbox);
        hb.getChildren().add(progressVbox);
      //  hb.getChildren().add(bottom);
        hb.setPadding(new Insets(0,20,20,20));
        
        VBox finalVBox = new VBox(appbarVBox , hb );
       
        // Label btn = new Label("Post Ad");
        //  //btn.setPadding(new Insets(30,00,0,1300));
        // btn.setStyle("-fx-background-color: transparent;");
        // btn.setPrefWidth(120);
        // // btn.setFont(Font.font("Arial", 22));
        // btn.setOnMouseClicked(e->{
        //     finalVBox.setOpacity(0);
        //     AdFormExample obj = new AdFormExample(stage);
        //     obj.getWorkerRegisterForm(stage);
        // });

        
        
        StackPane sp = new StackPane(finalVBox);
        // btn.setPadding(new Insets(-730,0,0,500));
        // Pane p = new Pane(finalVBox,btn);
        // btn.setLayoutX((1300));
        // btn.setLayoutY(0);
        // Group gr = new Group(vb);

      Scene  scene = new Scene(sp);
      stage.setScene(scene);
      stage.setTitle("LoginPage");
      stage.setHeight(900);
      stage.setWidth(1470);
      stage.setX(0);
      stage.setY(0);
      stage.show();
      

      Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(1)));
      timeline2.setOnFinished(p->{

          timeline2.setCycleCount(1);
          
          getGrid();
          hb.getChildren().remove(progressVbox);
          hb.getChildren().add(vb);
       
          });

          timeline2.play();


   

    //    timeline3 = new Timeline(new KeyFrame(Duration.seconds(0.1) , e->{
       
    //     hb.getChildren().remove(bottom);
    //     hb.getChildren().add(progressVbox);
    //     hb.setSpacing(0);

    //     Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(0.5)));
    //     timeline2.setOnFinished(p->{

    //         timeline2.setCycleCount(1);
    //         getGrid();
    //         hb.getChildren().remove(progressVbox);
    //         hb.getChildren().add(vb);
         
    //         });

    //         timeline2.play();


        

    //   }));
    //   timeline3.setCycleCount(1);
      


    

    }


        static Label getNameLabel(String name){
        Label lb = new Label(name);
        lb.setFont(Font.font("Arial", FontWeight.THIN, 25));
        return lb ;
    }


    static Label getLocationLabel(String name){
        Label lb = new Label(name);
        lb.setFont(new Font(22));
        lb.setAlignment(Pos.CENTER);
        return lb ;
    }

    

    static Label getPriceLabel(String name){
        Label lb = new Label(name);
       lb.setFont(Font.font("Arial",  20));
       lb.setAlignment(Pos.CENTER);


        return lb ;
    }

    static Label getDescriptionLabel(String name){
        Label lb = new Label(name);
        lb.setFont(new Font(18));
        lb.setAlignment(Pos.CENTER);
        lb.setMaxWidth(200);
        lb.setWrapText(true);
     //   lb.setStyle("-fx-background-color: green; -fx-padding: 10;-fx-background-radius: 10 ;-fx-border-radius: 10;-FX-TEXT-FILL:WHITE");
        
        

        return lb ;
    }



    public static ImageView takeImage(Image image){
        ImageView img = new ImageView(image);
        img.setFitWidth(160);
        img.setFitHeight(160);
        img.setStyle("-fx-background-color: white; -fx-border-color: transparent;-fx-background-radius: 80 ;-fx-border-radius: 80");

        Circle clip = new Circle(80);
        clip.setCenterX(80); // Center X coordinate
        clip.setCenterY(80); 
        img.setClip(clip);
        return img ;
    }

    public static HBox getHBox(ImageView iv , Label lb,Label lb1 , Label lb2 ){
        HBox hb = new HBox(iv, lb ,lb1 ,lb2);
        hb.setSpacing(10);
        // vb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-background-color:white;-fx-border-color: black;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
        hb.setPadding(new Insets(25));
        hb.setPrefWidth(210);
        hb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-background-color:white;-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 10;-fx-background-radius: 10;");
        hb.setPadding(new Insets(20));


        return hb ;


    }


    public static ImageView takeIconImage(Image image){
        ImageView img = new ImageView(image);
        img.setFitWidth(30);
        img.setFitHeight(30);

      
        return img ;
    }

    static Label getL(String name){
        Label lb = new Label(name);
        lb.setTextFill(Color.GREEN);
        lb.setFont(new Font(20));
        return lb ;
    }

   //  private boolean isBottomSheetVisible = false;

    // public VBox getBottomBar(){

        
    //     // Create the bottom sheet content
    //     VBox bottomSheet = new VBox(30);
    //     bottomSheet.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
    //     bottomSheet.setPadding(new Insets(10));
    //     bottomSheet.setSpacing(10);
     

    //     Label ask = new Label("What are you searching for? Please select one :");
    //     ask.setFont(new Font(25));
    //     bottomSheet.getChildren().add(ask);

    //     CheckBox workerCheckBox = new CheckBox("Worker");

    //     CheckBox farmerCheckBox = new CheckBox("Farmer");

    //     workerCheckBox.setOnAction(e ->{
    //     farmerTy = "labor";
    //     timeline3.play();
    //     toggleBottomSheet(bottomSheet , true);
          
          
    //     });
    //     farmerCheckBox.setOnAction(e ->{
    //         farmerTy = "farmer";
    //         timeline3.play();
    //         toggleBottomSheet(bottomSheet , true);
          
        
    //     });

    //     HBox checkBox = new HBox(50,workerCheckBox , farmerCheckBox);
    //     checkBox.setAlignment(Pos.TOP_CENTER);

    //     bottomSheet.getChildren().add(checkBox);

        
    //     TranslateTransition transition = new TranslateTransition(Duration.seconds(1.3), bottomSheet);
    //     if (isBottomSheetVisible) {
        
    //         transition.setFromY(400);
    //         transition.setToY(1000);
    //         transition.play(); // Adjust this value to move the bottom sheet completely off screen
    //     } else {
    //         transition.setFromY(900);
    //         transition.setToY(600);
          
    //     }
    //     transition.play(); 
    //     bottomSheet.setAlignment(Pos.TOP_CENTER);
    //     return bottomSheet ;
       
    
    // }
    // void toggleBottomSheet(VBox bottomSheet , boolean operation){
    //     TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), bottomSheet);

    //     if(operation==true){

    //         transition.setFromY(600);
    //         transition.setToY(1000);
    //     }

    //     transition.play(); 
        
            

    }
    
       
    

   


    


