package com.pk.electionappclient.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class AppController {

    public void closeLoginPanelOnAction(ActionEvent actionEvent, Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
