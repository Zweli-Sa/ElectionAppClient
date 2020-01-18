package com.pk.electionappclient.controller;

import com.pk.electionappclient.controller.AppController;
import com.pk.electionappclient.domain.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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


    public User isLoginDataCorrect(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/checkLoginData/" + login + "/" + password)
                .build().encode().toUri();
        User boardResponse = restTemplate.getForObject(uri, User.class);
        return ofNullable(boardResponse).orElse(new User());
    }

    public User getLogin() {
        User user = null;
        try {
            user = isLoginDataCorrect(getPeselInput(), getPasswordInput());
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
        getPanel(getLogin());
    }
    public static List<HttpMessageConverter<?>>  getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        return messageConverters;
    }
}
