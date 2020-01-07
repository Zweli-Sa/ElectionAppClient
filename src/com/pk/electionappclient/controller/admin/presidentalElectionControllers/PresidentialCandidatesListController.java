package com.pk.electionappclient.controller.admin.presidentalElectionControllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.ElectoralParty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ElectionController.*;
import static com.pk.electionappclient.controller.ElectionListController.electionList;
import static com.pk.electionappclient.controller.ElectionListController.newPresElectionList;
import static com.pk.electionappclient.controller.ElectionTypeController.prezydenckie;
import static java.sql.Date.valueOf;

public class PresidentialCandidatesListController extends AppController implements Initializable {

    @FXML
    AnchorPane presidentialListPane;

    @FXML
    Button exitButton;

    @FXML
    Button backButton;

    @FXML
    JFXDatePicker startDatePres;

    @FXML
    JFXDatePicker endDatePres;

    @FXML
    JFXTimePicker startTimePres;

    @FXML
    JFXTimePicker endTimePres;

    @FXML
    TableView<Candidate> candidatesTable;

    @FXML
    TableView<Candidate> addedCandidatesTable;

    @FXML
    TableColumn<Candidate, Long> candidateIdColumn;

    @FXML
    TableColumn<Candidate, String> candidateNameColumn;

    @FXML
    TableColumn<Candidate, String> candidateLastNameColumn;

    @FXML
    TableColumn<ElectoralParty, String> candidatePoliticalPartyColumn;

    @FXML
    TableColumn<Candidate, Long> addedCandidateIdColumn;

    @FXML
    TableColumn<Candidate, String> addedCandidateNameColumn;

    @FXML
    TableColumn<Candidate, String> addedCandidateLastNameColumn;

    @FXML
    TableColumn<ElectoralParty, String> addedCandidatePoliticalPartyColumn;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(id);
        clearCandidateTempList(); //usuwa kandydatów z niezapisanej listy wyborczej
        loadCandidates();
        loadTempList();
        System.out.println(getElections());
    }

    public void createPresElectionDay(ActionEvent actionEvent) throws IOException {
        try {
            if (validateDate(presElectionStartDate(), presElectionEndDate())) {
                createElectionDay(id++, presElectionStartDate(), presElectionEndDate(),
                        prezydenckie, newPresElectionList(id++, candidateTempList));
            }
        } catch (NullPointerException e) {
        }
        System.out.println(electionList);
        //System.out.println(getElections());
//        clearCandidateTempList();
        show();
    }


    public void addCandidateElectionList(ActionEvent actionEvent) {
        addSelectedCandidateToTempList();
        loadTempList();


    }



    private void addSelectedCandidateToTempList() {
        try {
            Candidate candidate = candidatesTable.getSelectionModel().getSelectedItem();
            if (!candidate.equals(null)) {
                addCandidateToTempList(candidate);
            }
        } catch (NullPointerException e) {
            popUpError("Zaznacz kadydata by dodać go do listy");
        }
    }


    private LocalDateTime presElectionStartDate() {
        return combinesDateTime(getPresStartDate(), getPresStartTime());
    }

    private LocalDateTime presElectionEndDate() {
        return combinesDateTime(getPresEndDate(), getPresEndTime());
    }

    private LocalDate getPresStartDate() {
        return startDatePres.getValue();
    }

    private LocalDate getPresEndDate() {
        return endDatePres.getValue();
    }

    private LocalTime getPresStartTime() {
        return startTimePres.getValue();
    }

    private LocalTime getPresEndTime() {
        return endTimePres.getValue();
    }

    private void loadCandidates() {
        candidateIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        candidateNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        candidateLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        candidatePoliticalPartyColumn.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        candidatesTable.getItems().setAll(getCandidates());
    }

    private void loadTempList() {
        addedCandidateIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addedCandidateNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addedCandidateLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        addedCandidatePoliticalPartyColumn.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        addedCandidatesTable.getItems().setAll(getTempCandidateList());
    }

    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(presidentialListPane, "admin/adminPanel.fxml");
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }
}
