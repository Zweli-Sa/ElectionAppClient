package com.pk.electionappclient.Controller.Admin;

import com.pk.electionappclient.Controller.AppController;
import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Education;
import com.pk.electionappclient.domain.ElectoralParty;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;

public class NewCandidateController extends AppController implements Initializable {

    @FXML
    Button exitButton;

    @FXML
    Button saveButton;

    @FXML
    ComboBox<ElectoralParty> partyComboBox;
    @FXML
    ComboBox educationComboBox;

    @FXML
    TextField candidateLastNameTextField;
    @FXML
    TextField candidateNameTextField;

    @FXML
    TextField candidatePlaceOfResidenceTextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateInput(saveButton);
        System.out.println(Education.values().toString());
        populateEducationComboBox();
        initCandidateList(); // inicjalizacja uzupełnionej listy;
        loadCandidates();
        populatePartiesComboBox();
    }

    private void loadCandidates() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        placeOfResidenceColumn.setCellValueFactory(new PropertyValueFactory<>("placeOfResidence"));
        politicalParty.setCellValueFactory(new PropertyValueFactory<>("electoralParty"));
        tableView.getItems().setAll(getCandidates());
    }

    public void createNewCandidate(ActionEvent actionEvent) {
        addCandidate(getNameFromTextField(actionEvent), getLastNameFromTextField(actionEvent),
                "Podstawowe",getPlaceOfResidenceFromTextField(actionEvent), partyComboBox.getValue());
        loadCandidates();
        System.out.println(partyComboBox.getValue());
    }

    private void validateInput(Button button) {
        button.disableProperty().bind(
                Bindings.isEmpty(candidateLastNameTextField.textProperty())
                        .or(Bindings.isEmpty(candidateNameTextField.textProperty()))
                        .or(Bindings.isEmpty(candidatePlaceOfResidenceTextField.textProperty()))
        );
    }

    public String getNameFromTextField(ActionEvent actionEvent) {
        return getTextFromField(actionEvent, candidateNameTextField);
    }

    public String getLastNameFromTextField(ActionEvent actionEvent) {
        return getTextFromField(actionEvent, candidateLastNameTextField);
    }

    public String getPlaceOfResidenceFromTextField(ActionEvent actionEvent) {
        return getTextFromField(actionEvent, candidatePlaceOfResidenceTextField);
    }


    public void populateEducationComboBox() {
        educationComboBox.getItems().setAll(Education.values());
    }

    public void populatePartiesComboBox() {
        partyComboBox.getItems().setAll(initPartiesList());
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(actionEvent, exitButton);
    }
}

