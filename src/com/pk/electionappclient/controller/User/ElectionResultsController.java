package com.pk.electionappclient.Controller.User;

import com.pk.electionappclient.Controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ElectionResultsController extends AppController {

    com.pk.electionappclient.Controller.login.loginController loginController;

    @FXML
    Button exitButton;

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }
}
