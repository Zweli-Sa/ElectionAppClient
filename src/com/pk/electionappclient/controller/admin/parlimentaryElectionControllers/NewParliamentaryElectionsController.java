package com.pk.electionappclient.controller.admin.parlimentaryElectionControllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.Election;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.ClientController.getCandidates;
import static com.pk.electionappclient.controller.ElectionController.*;

public class NewParliamentaryElectionsController extends AppController implements Initializable {

    @FXML
    AnchorPane electionAnchorPane;

    @FXML
    Button exitButton;
    @FXML
    JFXDatePicker startDatePres;

    @FXML
    JFXDatePicker endDatePres;

    @FXML
    JFXTimePicker startTimePres;

    @FXML
    JFXTimePicker endTimePres;

    @FXML
    Button submitButton;

    @FXML
    TableView inactiveElectionDayTable;

    @FXML
    TableView activeElectionDayTable;

    @FXML
    TableColumn<Election, Long> inactiveIdColumn;
    @FXML
    TableColumn<Election, LocalDateTime> inactiveStartDateColumn;
    @FXML
    TableColumn<Election, LocalDateTime> inactiveEndDateColumn;
    @FXML
    TableColumn<Election, LocalDateTime> inactiveNameColumn;

    @FXML
    TableColumn<Election, Long> activeIdColumn;
    @FXML
    TableColumn<Election, LocalDateTime> activeStartDateColumn;
    @FXML
    TableColumn<Election, LocalDateTime> activeEndDateColumn;
    @FXML
    TableColumn<Election, LocalDateTime> activeNameColumn;

    @FXML
    TableView finishedElectionDayTable;
    @FXML
    TableColumn<Election, Long> finishedIdColumn;
    @FXML
    TableColumn<Election, LocalDateTime> finishedStartDateColumn;
    @FXML
    TableColumn<Election, LocalDateTime> finishedEndDateColumn;
    @FXML
    TableColumn<Election, LocalDateTime> finishedNameColumn;


    public void addNewElectionDay(ActionEvent actionEvent) {
        try {
            if (validateDate(parliamentaryElectionStartDate(), parliamentaryElectionEndDate())) {
                createElectionDayTest(globalID++, parliamentaryElectionStartDate(), parliamentaryElectionEndDate(), null, null, false,false, createElectionName());
                loadInactiveElections();
                show();
            }
        } catch (NullPointerException e) {
        }

    }

    private LocalDateTime parliamentaryElectionStartDate() {
        return combinesDateTime(getPresStartDate(), getPresStartTime());
    }

    private LocalDateTime parliamentaryElectionEndDate() {
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

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    private String createElectionName() {
        return "Wybory parlamentarne " + parliamentaryElectionDateName(parliamentaryElectionStartDate())
                + " - " + parliamentaryElectionDateName(parliamentaryElectionEndDate());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadInactiveElections();
        loadActiveElections();
        loadFinishedElections();


    }

    private void loadInactiveElections() {
        inactiveIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        inactiveStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        inactiveEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        inactiveNameColumn.setCellValueFactory(new PropertyValueFactory<>("electionName"));
        inactiveElectionDayTable.getItems().setAll(getInActiveElections());
    }
    private void loadActiveElections() {
        activeIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        activeStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        activeEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        activeNameColumn.setCellValueFactory(new PropertyValueFactory<>("electionName"));
        activeElectionDayTable.getItems().setAll(getActiveElections());
    }

    private void loadFinishedElections() {
        finishedIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        finishedStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        finishedEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        finishedNameColumn.setCellValueFactory(new PropertyValueFactory<>("electionName"));
        finishedElectionDayTable.getItems().setAll(getFinishedElections());
    }


    public void loadNewConstituencyFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionAnchorPane, "/admin/parliamentaryElection/createConstituency.fxml");
    }

    public void loadCreateCandidateFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionAnchorPane, "/admin/newCandidate.fxml");
    }

    public void loadNewParliCandidateListFXML(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionAnchorPane, "/admin/parliamentaryElection/parliamentaryCandidatesList.fxml");
    }
    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(electionAnchorPane, "admin/adminPanel.fxml");
    }

    public void activeSelectedElection(ActionEvent actionEvent) {
        try {
            Election election;
            election = (Election) inactiveElectionDayTable.getSelectionModel().getSelectedItem();
            election.setActive(true);
            loadInactiveElections();
            loadActiveElections();
        } catch (NullPointerException e) {
            popUpError("Wybierz wybory by je aktywowac");
        }

    }

    public void finishSelectedElection(ActionEvent actionEvent) {
        try {
            Election election;
            election = (Election) activeElectionDayTable.getSelectionModel().getSelectedItem();
            election.setFinished(true);
            loadFinishedElections();
            loadInactiveElections();
            loadActiveElections();
        } catch (NullPointerException e) {
            popUpError("Wybierz wybory by je zakonczyc");
        }

    }

    public void deleteInactiveElection(ActionEvent actionEvent) {
        Election election;
        election = (Election) inactiveElectionDayTable.getSelectionModel().getSelectedItem();
        removeInactiveElection(election);
        loadInactiveElections();
    }
}