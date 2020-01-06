package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.net.URL;
import java.util.ResourceBundle;


public class CurrentElectionsController extends AppController implements Initializable {

    @FXML
    Button exitButton;

    @FXML
    Button backButton;

    @FXML
    Button enterButton;

    @FXML
    TableView<String> currentElectionsTable;

    @FXML
    TableColumn<String, String> electionType;

    @FXML
    TableColumn<String, String> electionDate;


    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        electionType.setCellValueFactory(c -> new SimpleStringProperty("123"));
        electionDate.setCellValueFactory(c -> new SimpleStringProperty("21-12-2020"));
        currentElectionsTable.getColumns();
    }

    public void loadTable(ActionEvent actionEvent) {
        electionType.setCellValueFactory(c -> new SimpleStringProperty(new String("123")));
        electionDate.setCellValueFactory(c -> new SimpleStringProperty(new String("21-12-2020")));
    }
}

