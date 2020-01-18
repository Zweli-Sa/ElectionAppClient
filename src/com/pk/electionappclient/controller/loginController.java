package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.controller.ClientController.getUsersDB;

public class loginController extends AppController {
    @FXML
    AnchorPane loginAnchorPane;

    @FXML
    Button loginButton;

    @FXML
    Button exitButton;

    @FXML
    TextField passwordInput;

    @FXML
    TextField peselInput;

    @FXML
    Button adminButton;

    @FXML
    Button userButton;


    public String getPeselInput() {
        return getTextFromField(peselInput);
    }

    public String getPasswordInput() {
        return getTextFromField(passwordInput);
    }

    public void loadUserPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(loginAnchorPane, "user/userPanel.fxml");
    }

    public void loadAdminPanel(ActionEvent actionEvent) throws IOException {
        loadAnchorPane(loginAnchorPane, "admin/adminPanel.fxml");
    }

    public void closeLoginPanel(ActionEvent actionEvent) {
        closeLoginPanelOnAction(exitButton);
    }


    public List<User> isLoginDataCorrect(String login, String password) {
        List<User> users = new ArrayList();
         users = getUsersDB().stream().filter(u -> u.getPesel().equals(login) && u.getPassword().equals(password)).collect(Collectors.toList());
         return users;
    }

    public User getLogin() {
        User user = null;
        try {
            user = isLoginDataCorrect(getPeselInput(), getPasswordInput()).get(0);
        } catch (NullPointerException e) {
            popUpError("Login lub haslo jest bledne lub takiego uzytkownika nie ma w bazie");
        }
        return user;
    }

    public void getPanel(User user) throws IOException {
        if (user.getAdmin()) {
            loadAnchorPane(loginAnchorPane, "admin/adminPanel.fxml");
        } else {
            loadAnchorPane(loginAnchorPane, "user/userPanel.fxml");
        }
    }

    public void getPanelAfterLogin(ActionEvent actionEvent) throws IOException {
        try {
            getPanel(getLogin());
        } catch (IndexOutOfBoundsException e) {
            popUpError("Wpisz poprawne dane");
        }
    }

}
