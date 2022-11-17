package com.example.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 This is the GymManagerMain class, which launches the GUI interface for
 running the gym manager software.
 @author Someshwar Ramesh Babu, Amrinderpal Akal
 */
public class GymManagerMain extends Application {
    /**
     This method loads the FXML file and sets the scene and stage for the GUI interface.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gym Manager Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     This method launches the GUI interface.
     * @param args - Command line input
     */
    public static void main(String[] args) {
        launch();
    }
}