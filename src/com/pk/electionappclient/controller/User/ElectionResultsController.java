package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static com.pk.electionappclient.controller.ClientController.getPartyByConstituency;
import static com.pk.electionappclient.controller.ClientController.getPresCandidates;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituencyByElectionID;
import static com.pk.electionappclient.controller.ElectionController.*;
import static com.pk.electionappclient.controller.VoteResultsController.getParlResults;
import static com.pk.electionappclient.controller.VoteResultsController.getPresResults;


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
    TableColumn<VoteResultsCandidate, String> resultColumn;
    @FXML
    TableColumn<VoteResultsCandidate, String> nameColumn;
    @FXML
    TableColumn<VoteResultsCandidate, String> lastNameColumn;
    @FXML
    TableColumn<VoteResultsCandidate, String> partyColumn;


    @FXML
    AnchorPane electionResultsAnchorPane;

    ObservableList<VoteResultsCandidate> resultList = FXCollections.observableArrayList();

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
        if (election.getConstituencies() != null) {
            Constituency constituency = (Constituency) constituencyComboBox.getSelectionModel().getSelectedItem();
            ElectoralParty electoralParty = (ElectoralParty) partyComboBox.getSelectionModel().getSelectedItem();
            for(Candidate c : getCandidatesByElection(election, constituency, electoralParty)) {
                resultList.add(new VoteResultsCandidate(c, getParlResults(election, c, constituency)));
            }
            initTable();
        } else {
            for(Candidate c : getPresCandidates(election)) {
                resultList.add(new VoteResultsCandidate(c, getPresResults(election, c)));
            }
            initTable();
        }



    }

    public void initTable() {
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
        nameColumn.setCellValueFactory(
                c -> {
                    SimpleObjectProperty name = new SimpleObjectProperty();
                    name.setValue(c.getValue().getCandidate().getName());
                    return name;
                }
        );
        lastNameColumn.setCellValueFactory(
                c -> {
                    SimpleObjectProperty lastname = new SimpleObjectProperty();
                    lastname.setValue(c.getValue().getCandidate().getLastname());
                    return lastname;
                }
        );
        lastNameColumn.setCellValueFactory(
                c -> {
                    SimpleObjectProperty party = new SimpleObjectProperty();
                    party.setValue(c.getValue().getCandidate().getElectoralParty().getName());
                    return party;
                }
        );
        resultTableView.setItems(resultList);
    }

    public void loadUserPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionResultsAnchorPane, "admin/adminPanel.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateElectionComboBox();
    }
}
