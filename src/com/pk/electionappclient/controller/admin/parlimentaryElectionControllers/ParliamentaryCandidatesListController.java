package com.pk.electionappclient.Controller.admin.parlimentaryElectionControllers;

import com.pk.electionappclient.Controller.AppController;
import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.ElectoralParty;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.Controller.ClientController.*;
import static com.pk.electionappclient.Controller.ConstituencyController.getConstituencyByElectionID;
import static com.pk.electionappclient.Controller.ElectionController.*;
import static com.pk.electionappclient.Controller.ElectionListController.candidateInAnotherConstituency;
import static com.pk.electionappclient.Controller.ElectionListController.newParlElectionList;

public class ParliamentaryCandidatesListController extends AppController implements Initializable {

    @FXML
    AnchorPane newParliamentaryListAnchorPane;

    @FXML
    Button exitButton;
    @FXML
    Button loadButton;
    @FXML
    Button submitButton;
    @FXML
    ComboBox electionComboBox;
    @FXML
    ComboBox partyComboBox;
    @FXML
    ComboBox constituencyComboBox;

    @FXML
    TableView<Candidate> tableView;

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
    TableView<Candidate> candidatesTempTableView;
    @FXML
    TableColumn<Candidate, Long> idColumnTemp;

    @FXML
    TableColumn<Candidate, String> nameColumnTemp;

    @FXML
    TableColumn<Candidate, String> lastNameColumnTemp;

    @FXML
    TableColumn<Candidate, String> educationColumnTemp;

    @FXML
    TableColumn<Candidate, String> placeOfResidenceColumnTemp;

    @FXML
    TableColumn<ElectoralParty, String> politicalPartyTemp;

    @FXML
    TableView<Candidate> tableViewDB;
    @FXML
    TableColumn<Candidate, Long> idColumnDB;

    @FXML
    TableColumn<Candidate, String> nameColumnDB;

    @FXML
    TableColumn<Candidate, String> lastNameColumnDB;

    @FXML
    TableColumn<Candidate, String> educationColumnDB;

    @FXML
    TableColumn<Candidate, String> politicalPartyDB;

    @FXML
    TableColumn<ElectoralParty, String> placeOfResidenceColumnDB;

    public void loadActualElectionList() {
        ElectoralParty electoralParty = (ElectoralParty) getComboBoxValue(partyComboBox);
        Election election = (Election) getComboBoxValue(electionComboBox);
        Constituency constituency = (Constituency) getComboBoxValue(constituencyComboBox);
        try {
            loadElectionListDB(election, constituency, electoralParty);
        } catch (NullPointerException E) {
            loadElectionListDB(null, null, null);
        }
    }

    public void addSelectedCandidateToTempList() {
        Candidate candidate = tableView.getSelectionModel().getSelectedItem();
        createCandidate(candidate);
        loadTempCandidates();
    }

    public void populatePartiesComboBox() {
        populateComboBoxList(partyComboBox, getParties());
    }
    public void populateConstituencyComboBox() {
        Election election = (Election) electionComboBox.getSelectionModel().getSelectedItem();
        populateComboBoxList(constituencyComboBox, getConstituencyByElectionID(election));
    }

    public void populateElectionComboBox() {
        populateComboBoxList(electionComboBox, getInActiveElections());
    }

    public void clearField() {
        partyComboBox.getSelectionModel().clearSelection();
        constituencyComboBox.getSelectionModel().clearSelection();
        electionComboBox.getSelectionModel().clearSelection();
    }
    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(newParliamentaryListAnchorPane, "admin/adminPanel.fxml");
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void loadCandidates() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        placeOfResidenceColumn.setCellValueFactory(new PropertyValueFactory<>("placeOfResidence"));
        politicalParty.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        tableView.getItems().setAll(getCandidatesByParty((ElectoralParty) partyComboBox.getSelectionModel().getSelectedItem()));
    }
    public void loadTempCandidates() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnTemp.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumnTemp.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        educationColumnTemp.setCellValueFactory(new PropertyValueFactory<>("education"));
        placeOfResidenceColumnTemp.setCellValueFactory(new PropertyValueFactory<>("placeOfResidence"));
        politicalPartyTemp.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        candidatesTempTableView.getItems().setAll(getCandidates());
    }

    public void loadElectionListDB(Election election, Constituency constituency, ElectoralParty electoralParty) {
        idColumnDB.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnDB.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumnDB.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        educationColumnDB.setCellValueFactory(new PropertyValueFactory<>("education"));
        placeOfResidenceColumnDB.setCellValueFactory(new PropertyValueFactory<>("placeOfResidence"));
        politicalPartyDB.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        tableViewDB.getItems().setAll(getCandidatesElection(election, constituency, electoralParty));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateInputFields(submitButton);
        clearField();
        populatePartiesComboBox();
        populateElectionComboBox();
    }

    public void actionOnPartyChange(ActionEvent actionEvent) {
        loadCandidates();
        loadTempCandidates();
    }

    public void createParlElectionList(ActionEvent actionEvent) {
            ElectoralParty electoralParty = (ElectoralParty) getComboBoxValue(partyComboBox);
            Election election = (Election) getComboBoxValue(electionComboBox);
            Constituency constituency = (Constituency) getComboBoxValue(constituencyComboBox);
            if (candidateInAnotherConstituency(constituency, getCandidates())) {
                popUpError("Kandydat jest już dodany do innego okregu wyborczego");
            } else{
                newParlElectionList(globalID++, getCandidates(), electoralParty, constituency);
                constituency.setElectionLists(getElectionIdByConstituencyID(constituency));
            }
    }

    private void validateInputFields(Button button) {
        button.disableProperty().bind(
                Bindings.isNull(constituencyComboBox.valueProperty())
                        .or(Bindings.isNull(electionComboBox.valueProperty()))
                        .or(Bindings.isNull(partyComboBox.valueProperty()))
                        );
    }

    public void deleteCandidateFromTempList(ActionEvent actionEvent) {
        Candidate candidate = candidatesTempTableView.getSelectionModel().getSelectedItem();
        removeCadidate(candidate); //TODO: check it
        loadTempCandidates();
    }
}
