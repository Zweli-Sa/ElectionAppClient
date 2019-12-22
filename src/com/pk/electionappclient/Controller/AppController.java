package com.pk.electionappclient.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AppController {

    public void closeLoginPanelOnAction(ActionEvent actionEvent, Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public static String wyboryPrezydenckie = "Wybory prezydenckie";
    protected static String wyboryParlamentarne = "Wybory parlamentarne";



}


