package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserPanelController extends AppController {

    com.pk.electionappclient.controller.login.loginController loginController;

    @FXML
    Button exitButton;

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }


}