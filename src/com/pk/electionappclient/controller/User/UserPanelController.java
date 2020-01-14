package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserPanelController extends AppController {

    @FXML
    AnchorPane userPanelAnchorPane;

    @FXML
    Button loadCurrentElectionsButton;


    @FXML
    Button exitButton;

    public void closePanel(ActionEvent actionEvent) throws IOException {
        //closeLoginPanelOnAction(exitButton);
        loadAnchorPane(userPanelAnchorPane, "/login.fxml");
    }

    public void loadCurrentElectionsPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(userPanelAnchorPane, "user/currentElections.fxml");
    }

    public void loadResultsPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(userPanelAnchorPane, "user/electionResults.fxml");
    }
}
