package com.pk.electionappclient.Controller.Admin.parlimentaryElectionControllers;

import com.pk.electionappclient.Controller.AppController;
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
        loadAnchorPane(constituencyAnchorPane, "Admin/adminPanel.fxml");
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
