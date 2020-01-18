package com.pk.electionappclient.controller.admin;

import com.pk.electionappclient.controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminPanelController extends AppController {

    @FXML
    Button exitButton;

    @FXML
    AnchorPane adminAnchorPane;



    public void closePanel(ActionEvent actionEvent) throws IOException {

        //closeLoginPanelOnAction(exitButton);
        loadAnchorPane(adminAnchorPane, "/login.fxml");

    }

    public void loadConstituencyFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "admin/parliamentaryElection/newParliamentaryElections.fxml");
    }
    public void loadNewCandidateFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "admin/newCandidate.fxml");
    }

    public void loadCandidatesListFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "admin/presidentialElection/presidentialCandidatesList.fxml");
    }

    public void loadLoginPanelFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "/login.fxml");
    }

    public void loadNewElectoralPartyFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(adminAnchorPane, "admin/newElectoralParty.fxml");
    }

}