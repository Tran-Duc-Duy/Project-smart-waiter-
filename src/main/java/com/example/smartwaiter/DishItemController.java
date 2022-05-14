package com.example.smartwaiter;

import com.example.smartwaiter.model.Dish;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DishItemController {
    @FXML
    private Label caloLabel;

    @FXML
    private Label giaLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label timeLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label descriptionLabel;
    public void setDishItem(Dish dish){
        caloLabel.setText(String.valueOf(dish.getTotalCalo()));
        giaLabel.setText(String.valueOf(dish.getTotalTien()));
        nameLabel.setText(dish.getNameDish());
        typeLabel.setText(dish.getType());
        timeLabel.setText(String.valueOf(dish.getTime())+"h");
        img.setImage(dish.getImgView().getImage());
        descriptionLabel.setText(dish.getDescription());

    }

}
