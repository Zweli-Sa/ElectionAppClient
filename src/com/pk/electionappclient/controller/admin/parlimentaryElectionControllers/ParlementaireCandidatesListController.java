package com.pk.electionappclient.controller.admin.parlimentaryElectionControllers;

import com.pk.electionappclient.controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParlementaireCandidatesListController extends AppController implements Initializable {

    @FXML
    AnchorPane constituencyAnchorPane;

    @FXML
    Button exitButton;


    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(constituencyAnchorPane, "admin/adminPanel.fxml");
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
