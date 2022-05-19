package com.example.smartwaiter;


import com.example.smartwaiter.model.Dish;
import com.example.smartwaiter.model.Meal;
import com.example.smartwaiter.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class historyController implements Initializable {
    @FXML
    private TableView<Meal> table;
    @FXML private TableColumn<Meal, String> nameColumn;
    @FXML private TableColumn<Meal, Date> dateColumn;
    @FXML private TableColumn<Meal, Double> priceColumn;
    @FXML private TableColumn<Meal, Double> caloColumn;
    @FXML private TableColumn<Meal, Void> actionColumn;
    private ObservableList<Meal> mealList;
    @FXML
    private ToggleButton button_1;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleButton button_2;
    @FXML private TextField ageField;
    @FXML private TextField weightField;
    @FXML private TextField heightField;
    //...
    @FXML private ChoiceBox<String> typeChoice;
    @FXML private Label invalidLabel;
    @FXML private Label adviceLabel;
    @FXML private Label caloLastMonthLabel;
    @FXML private Label caloThisMonthLabel;
    @FXML private Label caloTodayLabel;
    @FXML private Label caloMoreLabel;
    public double getDefaultCalo() {
        return defaultCalo;
    }

    public void setDefaultCalo(double defaultCalo) {
        this.defaultCalo = defaultCalo;
    }

    private double defaultCalo=0;
    int checkGender=1;
    //Daily activities.
    //Do nothing, go nowhere.
    //Office work - study, not sports.
    //Office work - study, sport medium.
    //Moderate physical activity, not sports
    //Moderate physical activity, moderate sport.
    //Moderate physical activity, heavy sports.
    //Heavy work - heavy sports.
    //Super heavy work.
    private String[] activities ={
            "Do nothing, go nowhere",
            "Office work - study, not sports",
            "Office work - study, sport medium",
            "Moderate physical activity, not sports",
            "Moderate physical activity, moderate sport",
            "Moderate physical activity, heavy sports",
            "Heavy work - heavy sports",
            "Super heavy work"
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*List<Dish> list=new ArrayList<Dish>();
        List<Order> listO=new ArrayList<>();
        Dish a =new Dish(11,"Thit ga","soup",150.0,150.0,150.0,"D:\\DuyStudy\\FILE_Ky2_LapTrinh\\SmartWaiter\\src\\main\\resources\\com\\example\\smartwaiter\\img\\Chef.png","alo alo");
        Dish b =new Dish(11,"Thit ga","soup",150.0,150.0,150.0,"D:\\DuyStudy\\FILE_Ky2_LapTrinh\\SmartWaiter\\src\\main\\resources\\com\\example\\smartwaiter\\img\\Chef.png","alo alo");
        Dish c =new Dish(11,"Thit ga","soup",150.0,150.0,150.0,"D:\\DuyStudy\\FILE_Ky2_LapTrinh\\SmartWaiter\\src\\main\\resources\\com\\example\\smartwaiter\\img\\Chef.png","alo alo");
        list.add(a);
        list.add(b);
        list.add(c);
        Order myOrder1 = new Order(list,1);
        Order myOrder2 = new Order(list,1);
        Order myOrder3 = new Order(list,1);
        listO.add(myOrder1);
        listO.add(myOrder2);
        *//*listO.add(myOrder3);*//*
        Calendar cale = Calendar.getInstance();
        Date date = cale.getTime();
        mealList= FXCollections.observableArrayList(
                new Meal(listO,date,100,200),
                new Meal(listO,date,150,500),
                new Meal(listO,date,200,100)
        );*/
        mealList=IO.readFileMeal();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        caloColumn.setCellValueFactory(new PropertyValueFactory<>("calo"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button orderButton = new Button("detail");
            private final HBox pane = new HBox(orderButton);

            {
                orderButton.setOnAction(event -> {
                    /*Meal getPatient = getTableView().getItems().get(getIndex());

                    .add(getPatient);*/
                });

            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });
        typeChoice.getItems().addAll(activities);
        typeChoice.setValue("Do nothing, go nowhere");
        table.setItems(mealList);
    }
    public void calculateBMR(){
        String invalid="";
        if(ageField.getText().equals("")||Double.parseDouble(ageField.getText())<15 ||(Double.parseDouble(ageField.getText())>80)){
            invalid += " invalid Age,";
        }
        if(weightField.getText().equals("")||Double.parseDouble(weightField.getText())<0){
            invalid += " invalid Weight,";
        }
        if(heightField.getText().equals("")||Double.parseDouble(heightField.getText())<0){
            invalid += " invalid Height,";
        }
        //invalid=invalid.substring(0,invalid.length()-1);
        invalidLabel.setText(invalid);
        if(!invalid.equals("")){
            return;
        }
        double resultBMR=0;
        double A = Double.parseDouble(ageField.getText());
        double W = Double.parseDouble(weightField.getText());
        double H = Double.parseDouble(heightField.getText());
        if(checkGender==1){
            resultBMR = 66.6+(13.75*W)+5*H+6.78*A;
        }else {
            resultBMR = 665+(9.56*W)+(1.85*H)+(4.68*A);
        }
        System.out.println(typeChoice.getValue());
        if(typeChoice.getValue().equals("Do nothing, go nowhere")){
            resultBMR=resultBMR*1.2;
        }else if(typeChoice.getValue().equals("Office work - study, not sports")){
            resultBMR=resultBMR*1.29;
        }else if(typeChoice.getValue().equals("Office work - study, sport medium")){
            resultBMR=resultBMR*1.375;
        }else if(typeChoice.getValue().equals("Moderate physical activity, not sports")){
            resultBMR=resultBMR*1.55;
        }else if(typeChoice.getValue().equals("Moderate physical activity, moderate sport")){
            resultBMR=resultBMR*1.63;
        }else if(typeChoice.getValue().equals("Moderate physical activity, heavy sports")){
            resultBMR=resultBMR*1.725;
        }else if(typeChoice.getValue().equals("Heavy work - heavy sports")){
            resultBMR=resultBMR*1.86;
        }else if(typeChoice.getValue().equals("Super heavy work")){
            resultBMR=resultBMR*2;
        }
        adviceLabel.setText("You need: "+Math.ceil(resultBMR* 100) / 100+" calories");
    }
    public double caloLastMonth(){
        return 0;
    }
    public double caloThisMonth(){
        return 0;
    }
    public double caloToday(){
        return 0;
    }
    @FXML
    void toggleButton (ActionEvent event) {
        if (event.getSource() == button_1) {
            button_1.setStyle("-fx-background-color:#BDCEC6;-fx-background-radius: 30;");
            button_2.setStyle("-fx-background-color:#EFE9DB;-fx-background-radius: 30;");
            checkGender=1;

        }
        if (event.getSource() == button_2) {
            button_2.setStyle("-fx-background-color:#BDCEC6;-fx-background-radius: 30;");
            button_1.setStyle("-fx-background-color:#EFE9DB;-fx-background-radius: 30;");
            checkGender=2;
        }
    }
    public void close(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void changeSceneHome(ActionEvent event) throws IOException {
        changeScene(event,"home-view.fxml");
        /*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home-view.fxml"));
        Parent add = loader.load();
        Scene scene = new Scene(add);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);*/
    }
    public void changeSceneDishAdd(ActionEvent event) throws IOException {
        changeScene(event,"home-view-add.fxml");
        /*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home-view-add.fxml"));
        Parent add = loader.load();
        Scene scene = new Scene(add);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);*/

    }
    public void changeScene(ActionEvent event,String nameF) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(nameF));
        Parent add = loader.load();
        Scene scene = new Scene(add);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
    }
}
