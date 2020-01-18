package com.pk.electionappclient;

import com.pk.electionappclient.domain.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.pk.electionappclient.controller.ClientController.initCandidateList;
import static com.pk.electionappclient.controller.ClientController.initPartiesList;
import static com.pk.electionappclient.controller.ConstituencyController.initCityDB;
import static com.pk.electionappclient.controller.ConstituencyController.warszawa;
import static com.pk.electionappclient.controller.ElectionController.initElectionsDB;


public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    public static int globalID = 1;
    public static User userTestowy = new User(11l, "Jan", "Kowalski", "ulica", 22553344,11, "11-11","99999999", "113221", "email@email.pl", "111444555", warszawa, "haslo", false);

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/com/pk/electionappclient/GUI/admin/adminPanel.fxml")); -- admin
        //primaryStage.setScene(new Scene(root, 1200, 900));
        Parent root = FXMLLoader.load(getClass().getResource("/com/pk/electionappclient/GUI/login.fxml"));
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setTitle("Logowanie");
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX()-xOffset);
                primaryStage.setY(mouseEvent.getScreenY()-yOffset);
            }
        });
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        initElectionsDB();
        initCityDB();
        initCandidateList();
        initPartiesList();
        launch(args);

    }

}

