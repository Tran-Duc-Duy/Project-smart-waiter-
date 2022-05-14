package com.example.smartwaiter;

import com.example.smartwaiter.model.Dish;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.smartwaiter.IO.readFile;


public class editController implements Initializable {
    @FXML private TableView<Dish> table;

    @FXML
    private TableColumn<Dish, Double> caloColumn;

    @FXML
    private TableColumn<Dish, Integer> idColumn;

    @FXML
    private TableColumn<Dish, String> imgColumn;

    @FXML
    private TableColumn<Dish, Double> moneyColumn;

    @FXML
    private TableColumn<Dish, String> nameColumn;

    @FXML
    private TableColumn<Dish, Double> timeColumn;

    @FXML
    private TableColumn<Dish, String> typeColumn;
    @FXML
    private TableColumn<Dish, Void> actionColumn;

    @FXML
    private ImageView imageView;
    @FXML
    private TableColumn<Dish, String> descriptionColumn;

    @FXML private TextField moneyText;
    @FXML private TextField caloText;
    @FXML private TextField timeText;
    @FXML private TextArea descriptionAText;
    @FXML private TextField nameText;
    @FXML private ChoiceBox<String> typeChoice;
    @FXML private Label invalidLabel;

    @FXML private BorderPane bp;
    @FXML private Button btAdd;
    private String[] food ={"soup", "salad", "main course"};
    private ObservableList<Dish> dishList2;
    private String linkImage;
    private int flagADD=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dishList2 = readFile();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameDish"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        caloColumn.setCellValueFactory(new PropertyValueFactory<>("totalCalo"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("totalTien"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        imgColumn.setCellValueFactory(new PropertyValueFactory<>("imgView"));
        typeChoice.getItems().addAll(food);
        typeChoice.setValue("soup");
        actionColumn.setCellFactory(param -> new TableCell<Dish, Void>() {
            private final Button editButton = new Button("edit");
            private final Button deleteButton = new Button("delete");
            private final HBox pane = new HBox(deleteButton, editButton);

            {
                deleteButton.setOnAction(event -> {
                    Dish getPatient = getTableView().getItems().get(getIndex());
                    int ids = getPatient.getId();
                    dishList2.remove(getPatient);
                    for (int i = ids - 1; i < dishList2.size(); i++) {
                        dishList2.get(i).setId(i + 1);
                    }
                });

                editButton.setOnAction(event -> {
                    Dish getPatient = getTableView().getItems().get(getIndex());
                    nameText.setText(getPatient.getNameDish());
                    moneyText.setText(String.valueOf(getPatient.getTotalTien()));
                    caloText.setText(String.valueOf(getPatient.getTotalCalo()));
                    timeText.setText(String.valueOf(getPatient.getTime()));
                    btAdd.setText("Update");
                    linkImage=getPatient.getLinkImgString();
                    imageView.setImage(new Image(linkImage));
                    typeChoice.setValue(getPatient.getType());
                    descriptionAText.setText(getPatient.getDescription());
                    flagADD=getPatient.getId();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });/*
        //actionColumn.getColumns().addAll(deleteColumn,updateColumn);
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("buttonDelete"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("buttonUpdate"));*/
        table.setItems(dishList2);
    }
    public static void sort(List<Dish> list) {

        list.sort(Comparator.comparingInt(Dish::getId));
    }
    public void add(ActionEvent event) throws IOException {
        String invalid="";
        if(nameText.getText().equals("")){
            invalid +=" Name is empty,";
        }
        if(moneyText.getText().equals("")||Double.parseDouble(moneyText.getText())<0){
            invalid += " invalid Price,";
        }
        if(caloText.getText().equals("")||Double.parseDouble(caloText.getText())<0){
            invalid += " invalid Calo,";
        }
        if(timeText.getText().equals("")||Double.parseDouble(timeText.getText())<0){
            invalid += " invalid Time,";
        }
        invalidLabel.setText(invalid);
        if(!invalid.equals("")){
            return;
        }
        Dish newDish = new Dish();
        newDish.setId(dishList2.size()+1);

        newDish.setNameDish(nameText.getText());
        newDish.setTotalTien(Double.parseDouble(moneyText.getText()));
        newDish.setTotalCalo(Double.parseDouble(caloText.getText()));
        newDish.setTime(Double.parseDouble(timeText.getText()));
        newDish.setDescription(descriptionAText.getText());
        //System.out.println(linkImage);
        linkImage=linkImage.replace("file:/","");
        linkImage=linkImage.replaceAll("/","\\\\");
        linkImage=linkImage.replaceAll("%20"," ");
        linkImage = IO.copyImg(linkImage,nameText.getText());
        newDish.setLinkImgString(linkImage);
        newDish.setImgView(new ImageView(imageView.getImage()));
        newDish.setType(typeChoice.getValue());

        for(Dish ma:dishList2){
            if(ma.getNameDish().compareToIgnoreCase(newDish.getNameDish())==0&&flagADD==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setGraphic(ma.getImgView());
                alert.setTitle("Loi cu phap");
                alert.setHeaderText("Ket qua:");
                alert.setContentText("trung ten");
                alert.showAndWait();
                return;
            }
        }
        nameText.setText("");
        moneyText.setText("");
        caloText.setText("");
        timeText.setText("");
        descriptionAText.setText("");
        if(flagADD!=0){
            newDish.setId(flagADD);
            for(Dish dis:dishList2){
                if(dis.getId()==flagADD){
                    dis.setNameDish(newDish.getNameDish());
                    dis.setType(newDish.getType());
                    dis.setDescription(newDish.getDescription());
                    dis.setTime(newDish.getTime());
                    dis.setTotalCalo(newDish.getTotalCalo());
                    dis.setTotalTien(newDish.getTotalTien());
                    dis.setLinkImgString(newDish.getLinkImgString());
                    dis.setImgView(newDish.getImgView());
                }
            }
        }
        else{
            dishList2.add(newDish);
        }
        //sort(dishList2);
        imageView.setImage(new Image("D:\\DuyStudy\\FILE_Ky2_LapTrinh\\SmartWaiter\\src\\main\\resources\\com\\example\\smartwaiter\\img\\Chef.png"));
        IO.writeFile(dishList2);
        btAdd.setText("Add");
        flagADD=0;

    }
    public void chooseFile(ActionEvent event){
        Stage stage = (Stage) bp.getScene().getWindow();
        FileChooser fc =new FileChooser();
        fc.setTitle("Choose a image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png");
        fc.getExtensionFilters().add(imageFilter);
        File file = fc.showOpenDialog(stage);
        if(file !=null){
            linkImage=file.toURI().toString();
            String myLinkImage=file.toURI().toString();
            Image image =new Image(myLinkImage,200,200,true,true);
            imageView.setImage(image);
        }
    }
    public void close(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void changeSceneDishHistory(ActionEvent event) throws IOException {
        changeScene(event,"home-view-history.fxml");
    }
    public void changeSceneHome(ActionEvent event) throws IOException {
        changeScene(event,"home-view.fxml");
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
/*    public void changeSceneDishDetail(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dish-view.fxml"));
        Parent monAnViewParent = loader.load();
        Scene scene = new Scene(monAnViewParent);

        DishController detailController = loader.getController();
        Dish selected = table.getSelectionModel().getSelectedItem();
        detailController.setDish(selected);

        stage.setScene(scene);
    }*/