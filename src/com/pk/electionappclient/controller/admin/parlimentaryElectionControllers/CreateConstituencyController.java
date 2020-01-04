package com.pk.electionappclient.controller.admin.parlimentaryElectionControllers;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.City;
import com.pk.electionappclient.domain.Election;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ConstituencyController.*;
import static com.pk.electionappclient.controller.ElectionController.*;

public class CreateConstituencyController extends AppController implements Initializable {

    @FXML
    AnchorPane constituencyAnchorPane;

    @FXML
    Button submitButton;

    @FXML
    Button exitButton;

    @FXML
    TableView<City> cityTempTableView;

    @FXML
    TableColumn<City, String> cityNameTempColumn;

    @FXML
    TableView<City> cityDBTableView;

    @FXML
    TableColumn<City, String> cityNameColumn;

    @FXML
    TextField constituencyIdTextField;

    @FXML
    ComboBox selectElectionComboBox;

    @FXML
    TableView currentConstituency;

    @FXML
    TableColumn idColumn;

    @FXML
    TableColumn nameColumn;

    public void populateElectionComboBox() {
        populateComboBoxList(selectElectionComboBox, getInActiveElections());
    }

    public void newConstituency(ActionEvent actionEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //createConstituency(2l, createConstituencyName(), citiesTempList);
        Election election = (Election) selectElectionComboBox.getSelectionModel().getSelectedItem();
        //setConstituencyElectionController(election);
        createConstituency(getInputId(), createConstituencyName(), citiesTempList, election);
        //addConstituencyElection(election, createConstituency(getInputId(), createConstituencyName(), citiesTempList), getInputId());
        showConstituencies();
        show();
        //show();
        //showConstituencies();
        //loadCurrentConstituency();

        clearCityTempList();

        //showConstituencies(); // metoda do usunięcia
    }

    private String createConstituencyName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return "Lista nr: " + getInputId() + " - " + listToString(citiesTempList, "getName");
    }


    public void addCandidateElectionList(ActionEvent actionEvent) {
        addSelectedCityToTempList();
        loadCityNameTemp();


    }

    private void addSelectedCityToTempList() {
        try {
            City city = cityDBTableView.getSelectionModel().getSelectedItem();
            if (!city.equals(null)) {
                addCityToTempList(city);
            }
        } catch (NullPointerException e) {
            popUpError("Zaznacz kadydata by dodać go do listy");
        }
    }
    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(constituencyAnchorPane, "admin/adminPanel.fxml");
    }


    private void loadCityName() {
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityDBTableView.getItems().setAll(getCitiesDB());
    }

    private void loadCityNameTemp() {
        cityNameTempColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityTempTableView.getItems().setAll(getCitiesTempList());
    }

    private void loadCurrentConstituency() {
        Election election = (Election) selectElectionComboBox.getSelectionModel().getSelectedItem();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        currentConstituency.getItems().setAll(getCurrentConstituency(election));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearCityTempList();
        populateElectionComboBox();
        loadCityName();
        //System.out.println(getCitiesDB());
    }

    public void removeSelectedCityTempList(ActionEvent actionEvent) {
        City city = cityTempTableView.getSelectionModel().getSelectedItem();
        removeCityTempList(city);
        loadCityNameTemp();
    }

    public Long getInputId() {
        return Long.parseLong(getTextFromField(constituencyIdTextField));
    }

    public void refreshTable(ActionEvent actionEvent) {
        try {
            loadCurrentConstituency();
        } catch (NullPointerException e) {

        }
    }
}