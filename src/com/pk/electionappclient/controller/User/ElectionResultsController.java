package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig4.Anchor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.getPartyByConstituency;
import static com.pk.electionappclient.controller.ClientController.getPartyDB;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituencyByElectionID;
import static com.pk.electionappclient.controller.ElectionController.*;
import static com.pk.electionappclient.controller.VoteResultsController.getResults;

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
    TableColumn<Candidate, String> nameColumn;
    @FXML
    TableColumn<Candidate, String> lastNameColumn;
    @FXML
    TableColumn<Candidate, String> partyColumn;

    @FXML
    AnchorPane electionResultsAnchorPane;


    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }
    public void populateElectionComboBox() {
        populateComboBoxList(electionComboBox, getFinishedElections());
    }
    public void populatePartiesComboBox() {
        Constituency constituency = (Constituency) constituencyComboBox.getSelectionModel().getSelectedItem();
        populateComboBoxList(partyComboBox, getPartyByConstituency(constituency));
    }

    public void populateConstituencyComboBox() {
        Election election = (Election) electionComboBox.getSelectionModel().getSelectedItem();
        populateComboBoxList(constituencyComboBox, getConstituencyByElectionID(election));
    }

    public void loadResultTableView() {
        Election election = (Election) electionComboBox.getSelectionModel().getSelectedItem();
        Constituency constituency = (Constituency) constituencyComboBox.getSelectionModel().getSelectedItem();
        ElectoralParty electoralParty = (ElectoralParty) partyComboBox.getSelectionModel().getSelectedItem();
        for(Candidate c : getCandidatesElection(election, constituency, electoralParty)) {
            getResults(election, c, constituency);
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        partyColumn.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        resultTableView.getItems().setAll(getCandidatesElection(election, constituency, electoralParty));
    }

    public void loadUserPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionResultsAnchorPane, "admin/adminPanel.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateElectionComboBox();
    }
}
