package com.pk.electionappclient.Controller.Admin;

import com.pk.electionappclient.Controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminPanelController extends AppController {

    @FXML
    Button exitButton;

    @FXML
    AnchorPane adminAnchorPane;



    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void loadConstituencyFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "Admin/parliamentaryElection/createConstituency.fxml");
    }
    public void loadNewCandidateFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "Admin/newCandidate.fxml");
    }

    public void loadCandidatesListFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "Admin/presidentialElection/presidentialCandidatesList.fxml");
    }
}