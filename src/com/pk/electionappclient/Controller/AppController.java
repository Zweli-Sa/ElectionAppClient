package com.pk.electionappclient.Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
        for (int i = 0; i <textFields.length; i++) {
            if(textFields[i].getText().trim().isEmpty()==true) {
                button.setDisable(true);
            } else
                button.setDisable(false);
            }
        }
        //        int flag = 0, counter = 0;
//        for (TextField textField : textFields) {
//            if (textField.getText().trim().isEmpty()) {
//                button.setDisable(true);
//                flag=-1;
//            }
//        }
//        if (flag==-1) {
//           // button.setDisable(false);
//        }


    public void closeLoginPanelOnAction(ActionEvent actionEvent, Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public static String wyboryPrezydenckie = "Wybory prezydenckie";
    protected static String wyboryParlamentarne = "Wybory parlamentarne";



}


