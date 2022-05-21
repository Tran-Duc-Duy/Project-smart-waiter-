package com.example.smartwaiter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SmartWaiterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SmartWaiterApplication.class.getResource("home-view.fxml"));
        stage.setTitle("Smart Waiter!");
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.getIcons().add(new Image(Objects.requireNonNull(SmartWaiterApplication.class.getResourceAsStream("img/Chef.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Thread a =new Thread(() -> {
            Sound_cdjv sound=new Sound_cdjv("D:\\DuyStudy\\FILE_Ky2_LapTrinh\\SmarterWaiter\\src\\main\\resources\\com\\example\\smarterwaiter\\audio.wav");
            sound.run();
        });
        a.setDaemon(true);
        a.start();
        launch();
    }
}