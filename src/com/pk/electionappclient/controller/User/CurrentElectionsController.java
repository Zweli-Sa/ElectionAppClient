package com.pk.electionappclient.controller.User;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.Election;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.pk.electionappclient.controller.ElectionController.getActiveElections;


public class CurrentElectionsController extends AppController implements Initializable {

    @FXML
    AnchorPane currentElectionsAnchorPane;

    @FXML
    Button exitButton;

    @FXML
    Button backButton;

    @FXML
    Button enterButton;

    @FXML
    TableView<Election> currentElectionsTable;

    @FXML
    TableColumn<Election, String> electionFinishDateColumn;

    @FXML
    TableColumn<Election, String> electionStartDateColumn;

    @FXML
    TableColumn<Election, String> electionNameColumn;


    public void closePanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }

    public void loadCurrentElectionsTable() {
        electionNameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        electionStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        electionFinishDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        electionNameColumn.setCellValueFactory(new PropertyValueFactory<>("electionName"));
        currentElectionsTable.getItems().setAll(getActiveElections());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCurrentElectionsTable();
    }

    public void loadTable(ActionEvent actionEvent) {
        electionNameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        electionStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        electionFinishDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        electionNameColumn.setCellValueFactory(new PropertyValueFactory<>("electionName"));
        currentElectionsTable.getItems().setAll(getActiveElections());;
    }

    public void loadUserPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(currentElectionsAnchorPane, "user/userPanel.fxml");
    }

    public void loadVotePanel(Election election) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pk/electionappclient/GUI/user/votePanel.fxml"));
        Parent root = loader.load();
        VotePanelController votePanelController = loader.getController();
        votePanelController.setElection(election);
        if (election.getConstituencies() != null) {
            votePanelController.initParl();
        } else {
            votePanelController.initPres();
        }


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void getSelectedElection(ActionEvent actionEvent) throws IOException {
        Election election = currentElectionsTable.getSelectionModel().getSelectedItem();
        loadVotePanel(election);
    }
}

