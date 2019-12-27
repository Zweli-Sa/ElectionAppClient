package com.pk.electionappclient.Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AppController {

    public void disableButton(Button button, TextField... textFields) {
        int flag = 0, counter = 0;
        for (TextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                flag++;
            }
            counter++;
        }

        if (flag==counter) {
            button.setDisable(false);
        }
    }

    public void closeLoginPanelOnAction(ActionEvent actionEvent, Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public static String wyboryPrezydenckie = "Wybory prezydenckie";
    protected static String wyboryParlamentarne = "Wybory parlamentarne";



}


