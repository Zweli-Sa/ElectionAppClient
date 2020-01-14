package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.VoteResultsController.voteForCandidate;
import static com.pk.electionappclient.controller.VoteResultsController.voteResultsDB;

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
        candidatesTableView.getItems().setAll(getCandidateByElectionListElectoralParty(getElectionListByConstituency(getConstituencyListByUserCityId(election, 20)), (ElectoralParty) partyComboBox.getSelectionModel().getSelectedItem()));
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
        populateComboBoxList(partyComboBox, getElectoralPartiesByElectionList(getElectionListByConstituency(getConstituencyListByUserCityId(election, 20))));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userCityId=20;
    }


    public void loadCandidatesOnChange(ActionEvent actionEvent) {
        loadCandidateTable();
    }

    public void voteAction(ActionEvent actionEvent) {
        Candidate candidate = candidatesTableView.getSelectionModel().getSelectedItem();
        voteForCandidate(election, candidate, (Constituency) getConstituencyListByUserCityId(election, 20));
        System.out.println(voteResultsDB);
    }
}
