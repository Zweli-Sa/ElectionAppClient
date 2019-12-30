package com.pk.electionappclient.Controller.Admin;

import com.pk.electionappclient.Controller.AppController;
import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Education;
import com.pk.electionappclient.domain.ElectoralParty;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;

public class NewCandidateController extends AppController implements Initializable {

    @FXML
    Button exitButton;

    @FXML
    Button saveButton;

    @FXML
    Button editButton;

    @FXML
    Button deleteButton;

    @FXML
    ComboBox<ElectoralParty> partyComboBox;
    @FXML
    ComboBox<Education> educationComboBox;

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

    @FXML
    AnchorPane newCandidatePane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateInputFields(saveButton);
        System.out.println(Education.values().toString());
        populateEducationComboBox();
        populatePartiesComboBox();
        loadCandidates();
        System.out.println(educationComboBox.valueProperty().toString());
        System.out.println(candidateLastNameTextField.textProperty());
    }

    private void validateInputFields(Button button) {
        button.disableProperty().bind(
                Bindings.isEmpty(candidateLastNameTextField.textProperty())
                        .or(Bindings.isEmpty(candidateNameTextField.textProperty()))
                        .or(Bindings.isNull(educationComboBox.valueProperty()))
                        .or(Bindings.isNull(partyComboBox.valueProperty()))
                        .or(Bindings.isEmpty(candidatePlaceOfResidenceTextField.textProperty())));
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

    public void populateEducationComboBox() {
        educationComboBox.getItems().setAll(Education.values());
    }

    public void populatePartiesComboBox() {
        partyComboBox.getItems().setAll(initPartiesList());
    }


    public void createNewCandidate(ActionEvent actionEvent) {
        addCandidate(getTextFromField(candidateNameTextField), getTextFromField(candidateLastNameTextField),
                educationComboBox.getValue(), getTextFromField(candidatePlaceOfResidenceTextField), partyComboBox.getValue());
        loadCandidates();
        if (editButton.getId().equals("acceptChanges")) {
            changeButtonStyle(editButton, "#cecece", "Edytuj", "editButton");
        }
        clearFields();
    }

    public void editCandidate(ActionEvent actionEvent) {
        try {
            Candidate candidate = tableView.getSelectionModel().getSelectedItem();
            if (!candidate.equals(null)) {
                if (editButton.getId().equals("editButton")) {
                    changeButtonStyle(editButton, "#69a04c", "Zapisz", "acceptChanges");
                    setFields(candidate);
                } else if (editButton.getId().equals("acceptChanges")) {
                    changeButtonStyle(editButton, "#cecece", "Edytuj", "editButton");
                    removeCadidateFromList(candidate);
                    createNewCandidate(actionEvent);
                    clearFields();
                }
            }
        } catch (NullPointerException e) {
            editButton.setId("editButton");
            popUpWindow("Zaznacz kadydata by go zedytować");
        }

    }

    public void removeCandidate(ActionEvent actionEvent) {
        try {
            Candidate candidate = tableView.getSelectionModel().getSelectedItem();
            if (!candidate.equals(null)) {
                removeCadidateFromList(candidate);
                loadCandidates();
            }
        } catch (NullPointerException e) {
            popUpWindow("Zaznacz kandydata z listy");
        } finally {
            changeButtonStyle(editButton, "#cecece", "Edytuj", "editButton");
            clearFields();
        }
    }

    private void clearFields() {
        candidateNameTextField.setText("");
        candidateLastNameTextField.setText("");
        candidatePlaceOfResidenceTextField.setText("");
        educationComboBox.getSelectionModel().clearSelection();
        partyComboBox.getSelectionModel().clearSelection();
    }

    private void setFields(Candidate candidate) {
        candidateNameTextField.setText(candidate.getName());
        candidateLastNameTextField.setText(candidate.getLastname());
        candidatePlaceOfResidenceTextField.setText(candidate.getPlaceOfResidence());
        educationComboBox.getSelectionModel().select(candidate.getEducation());
        partyComboBox.getSelectionModel().select(candidate.getElectoralParty());
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(newCandidatePane, "Admin/adminPanel.fxml");
    }
}

