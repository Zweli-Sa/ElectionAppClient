package com.pk.electionappclient.controller.admin.parlimentaryElectionControllers;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
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
import java.util.List;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ConstituencyController.*;
import static com.pk.electionappclient.controller.ElectionController.*;
import static com.pk.electionappclient.controller.ElectionListController.clearElectionList;
import static com.pk.electionappclient.controller.ElectionListController.newParlElectionList;

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
        addCandidateToTempList(candidate);
        loadTempCandidates();
    }

    public void populatePartiesComboBox() {
        populateComboBoxList(partyComboBox, getPartyDB());
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
        candidatesTempTableView.getItems().setAll(getTempCandidateList());
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
        clearCandidateTempList();
    }

    public void actionOnPartyChange(ActionEvent actionEvent) {
        loadCandidates();
        clearCityTempList();
        clearCandidateTempList();
        loadTempCandidates();
    }

    public void createParlElectionList(ActionEvent actionEvent) {
        try {
            ElectoralParty electoralParty = (ElectoralParty) getComboBoxValue(partyComboBox);
            Election election = (Election) getComboBoxValue(electionComboBox);
            Constituency constituency = (Constituency) getComboBoxValue(constituencyComboBox);
            System.out.println(constituency);
            constituency.setElectionLists(newParlElectionList(2, candidateTempList, electoralParty, constituency));
            //showConstituencies();
            show();
            System.out.println(election);
        } catch (NullPointerException n) {

        }
    }

    private void validateInputFields(Button button) {
        button.disableProperty().bind(
                Bindings.isNull(constituencyComboBox.valueProperty())
                        .or(Bindings.isNull(electionComboBox.valueProperty()))
                        .or(Bindings.isNull(partyComboBox.valueProperty()))
                        );
    }
}
