package com.turning.turningworkshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("signin"), 800, 500);
        stage.setScene(scene);
        stage.getIcons().add(new Image("https://encrypted-tbn0.gstatic.com"
                + "/images?q=tbn:ANd9GcRS_0hPCtow4w5xF6hwBZfAqYgF_3OrlPGBNzcUxu"
                + "Mbjp2NiEjOggLfmGEoGy4D4V2pMnU&usqp=CAU"));
        stage.setTitle("Turning workshop");
        stage.show();
        
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class
                .getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}