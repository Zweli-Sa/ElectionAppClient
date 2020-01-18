package com.pk.electionappclient.controller.admin;

import com.pk.electionappclient.controller.AppController;
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

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.ClientController.*;

public class NewElectoralPratyController extends AppController implements Initializable {
    @FXML
    Button exitButton;

    @FXML
    Button saveButton;

    @FXML
    Button deleteButton;

    @FXML
    TextField partyNameTextField;

    @FXML
    TableView<ElectoralParty> tableView;

    @FXML
    TableColumn<ElectoralParty, Long> idColumn;

    @FXML
    TableColumn<ElectoralParty, String> nameColumn;

    @FXML
    AnchorPane newPartyAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateInputFields(saveButton);
        loadPartiesTableView();
    }

    private void validateInputFields(Button button) {
        button.disableProperty().bind(
                Bindings.isEmpty(partyNameTextField.textProperty()));
    }

    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(newPartyAnchorPane, "admin/adminPanel.fxml");
    }


    public void createNewElectoralParty(ActionEvent actionEvent) {
        addElectoralParty();
    }

    private void addElectoralParty() {
        addElectoralPartyToDatabase(new ElectoralParty(globalID++, getTextFromField(partyNameTextField)));
        loadPartiesTableView();
    }

    public void deleteParty() {
        ElectoralParty electoralParty = tableView.getSelectionModel().getSelectedItem();
        deleteElectoralParty(electoralParty);
        loadPartiesTableView();
    }

    private void loadPartiesTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getItems().setAll(getPartyDB());
    }
}
