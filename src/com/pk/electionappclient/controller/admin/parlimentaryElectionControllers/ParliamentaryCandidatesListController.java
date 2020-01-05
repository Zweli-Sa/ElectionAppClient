package com.pk.electionappclient.controller.admin.parlimentaryElectionControllers;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.ElectoralParty;
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

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituenciesDB;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituencyByElectionID;
import static com.pk.electionappclient.controller.ElectionController.getInActiveElections;

public class ParliamentaryCandidatesListController extends AppController implements Initializable {

    @FXML
    AnchorPane newParliamentaryListAnchorPane;

    @FXML
    Button exitButton;
    @FXML
    Button submitButton;
    @FXML
    Button loadButton;
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

    private void loadCandidates() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        placeOfResidenceColumn.setCellValueFactory(new PropertyValueFactory<>("placeOfResidence"));
        politicalParty.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        tableView.getItems().setAll(getCandidatesByParty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearField();
        populatePartiesComboBox();
        populateElectionComboBox();
    }
}
