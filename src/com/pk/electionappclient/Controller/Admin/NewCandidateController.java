package com.pk.electionappclient.Controller.Admin;

import com.pk.electionappclient.Controller.AppController;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NewCandidateController extends AppController implements Initializable {

    @FXML
    Button exitButton;
    @FXML
    Button saveButton;
    @FXML
    TextField candidateSurnameTextField;
    @FXML
    TextField candidateNameTextField;
    @FXML
    TextField candidateCityTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        saveButton.disableProperty().bind(
//                Bindings.isEmpty(candidateSurnameTextField.textProperty())
//                        .or(Bindings.isEmpty(candidateNameTextField.textProperty()))
//                        .or(Bindings.isEmpty(candidateCityTextField.textProperty()))
//        );
        disableButton(saveButton, candidateSurnameTextField,candidateNameTextField,candidateCityTextField);
    }

    public void createNewCandidate(ActionEvent actionEvent) {

    }


    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(actionEvent, exitButton);
    }
}

