package com.example.dataproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


// Salwa Fayyad 1200430
// Sondos Ashraf 1200905
// Rewa'a Assi   1201419

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Al-Khatib Dental!");
        stage.getIcons().add(new Image("C:\\Users\\User\\Desktop\\DataPROJECT\\src\\main\\resources\\projectPhotos\\icons8-tooth-94.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

