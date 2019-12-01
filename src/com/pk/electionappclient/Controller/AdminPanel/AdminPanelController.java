package com.pk.electionappclient.Controller.AdminPanel;

import com.pk.electionappclient.Controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPanelController extends AppController {

    @FXML
    Button exitButton;

    public void closeLoginPanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(actionEvent, exitButton);
    }
}
