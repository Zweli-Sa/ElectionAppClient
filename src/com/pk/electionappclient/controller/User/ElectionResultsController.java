package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.VoteResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig4.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.getPartyDB;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituencyByElectionID;
import static com.pk.electionappclient.controller.ElectionController.getActiveElections;
import static com.pk.electionappclient.controller.ElectionController.getInActiveElections;

public class ElectionResultsController extends AppController implements Initializable {

    com.pk.electionappclient.controller.loginController loginController;

    @FXML
    Button exitButton;

    @FXML
    ComboBox electionComboBox;
    @FXML
    ComboBox constituencyComboBox;
    @FXML
    ComboBox partyComboBox;

    @FXML
    TableView resultTableView;
    @FXML
    TableColumn resultColumn;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn lastNameColumn;
    @FXML
    TableColumn partyColumn;

    @FXML
    AnchorPane electionResultsAnchorPane;

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void populatePartiesComboBox() {
        populateComboBoxList(partyComboBox, getPartyDB());
    }
    public void populateConstituencyComboBox() {
        Election election = (Election) electionComboBox.getSelectionModel().getSelectedItem();
        populateComboBoxList(constituencyComboBox, getConstituencyByElectionID(election));
    }


    public void loadUserPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionResultsAnchorPane, "admin/adminPanel.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateConstituencyComboBox();
        populatePartiesComboBox();
    }
}
