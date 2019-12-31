package com.pk.electionappclient;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.ElectionType;
import com.pk.electionappclient.domain.ElectoralParty;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.pk.electionappclient.controller.ClientController.initCandidateList;


public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/pk/electionappclient/GUI/Admin/adminPanel.fxml"));
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

        initCandidateList();

        launch(args);

    }
}

