package com.pk.electionappclient.Controller.Admin;

import com.pk.electionappclient.Controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NewCandidateController extends AppController {

    @FXML
    Button exitButton;

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(actionEvent, exitButton);
    }
}

