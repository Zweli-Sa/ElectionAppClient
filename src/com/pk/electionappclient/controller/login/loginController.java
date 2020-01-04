package com.pk.electionappclient.controller.login;

import com.pk.electionappclient.controller.AppController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public class loginController extends AppController {

    @FXML
    Button loginButton;

    @FXML
    Button exitButton;

    @FXML
    TextField passwordInput;

    @FXML
    TextField peselInput;


    public TextField getPeselInput() {
        return peselInput;
    }

    public TextField getPasswordInput() {
        return passwordInput;
    }

    public void closeLoginPanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }


}
