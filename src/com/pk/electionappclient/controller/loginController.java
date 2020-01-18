package com.pk.electionappclient.controller;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;


import static java.util.Optional.ofNullable;

public class loginController extends AppController {
    private static final String URL = "http://localhost:8080/v1/election";


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


    public TextField getPeselInput() {
        return peselInput;
    }

    public TextField getPasswordInput() {
        return passwordInput;
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


    public static User isLoginDataCorrect(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/checkLoginData/" + login + "/" + password)
                .build().encode().toUri();
        User boardResponse = restTemplate.getForObject(uri, User.class);
        return ofNullable(boardResponse).orElse(new User());
    }

    public void getLogin(ActionEvent actionEvent) {
        User user = null;
    }
}
