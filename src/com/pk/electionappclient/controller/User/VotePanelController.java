package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.ElectionList;
import com.pk.electionappclient.domain.ElectoralParty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;

public class VotePanelController extends AppController implements Initializable {

    public Election election;

    private int userCityId;

    @FXML
    Button confirmButton;

    @FXML
    ListView<ElectionList> electionListListView;

    @FXML
    TableView<Candidate> candidatesTableView;

    @FXML
    TableColumn<Candidate, Long> idColumn;

    @FXML
    TableColumn<Candidate, String> nameColumn;

    @FXML
    TableColumn<Candidate, String> lastNameColumn;

    @FXML
    TableColumn<Candidate, String> educationColumn;

    @FXML
    TableColumn<Candidate, String> placeOfResidenceColumn;

    @FXML
    TableColumn<ElectoralParty, String> politicalParty;

    @FXML
    ComboBox partyComboBox;

    public void loadCandidateTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        placeOfResidenceColumn.setCellValueFactory(new PropertyValueFactory<>("placeOfResidence"));
        politicalParty.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        candidatesTableView.getItems().setAll(getCandidateByElectionListElectoralParty(getElectionListByConstituency(getConstituencyByUserId(election, 20)), (ElectoralParty) partyComboBox.getSelectionModel().getSelectedItem()));
    }

    public void setElection(Election election) {
        this.election = election;
    }

    @FXML
    Button exitButton;

    public void showElection(Election election) {
        System.out.println("show election" + election);

    }
    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void init() {
        System.out.println("INIT");
        populateComboBoxList(partyComboBox, getElectoralPartiesByElectionList(getElectionListByConstituency( getConstituencyByUserId(election, 20))));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userCityId=20;
    }

    public void showEl(ActionEvent actionEvent) {
        System.out.println(election);
        loadCandidateTable();


    }
}
