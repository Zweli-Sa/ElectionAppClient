package com.pk.electionappclient.controller;

import com.pk.electionappclient.controller.AppController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class loginController extends AppController {

    @FXML
    AnchorPane loginAnchorPane;

    @FXML
    Button loginButton;

    @FXML
    Button exitButton;

    @FXML
    TextField passwordInput;

    @FXML
    TextField peselInput;

    @FXML
    Button adminButton;

    @FXML
    Button userButton;


    public TextField getPeselInput() {
        return peselInput;
    }

    public TextField getPasswordInput() {
        return passwordInput;
    }

    public void loadUserPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(loginAnchorPane, "user/userPanel.fxml");
    }

    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(loginAnchorPane, "admin/adminPanel.fxml");
    }

    public void closeLoginPanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }


}
