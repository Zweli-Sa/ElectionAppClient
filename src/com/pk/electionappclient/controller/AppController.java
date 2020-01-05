package com.pk.electionappclient.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public abstract class AppController {


    protected <E> void populateComboBoxList(ComboBox comboBox, List<E> list) {
        comboBox.getItems().setAll(list);
    }
    protected boolean validateDate(LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(end)) {
            return true;
        } else {
            popUpError("Data zakończenia głosowania musi być późniejsza niż jego startu!");
        }
        return false;

    }

    protected LocalDateTime combinesDateTime(LocalDate date, LocalTime time) throws NullPointerException {
        try {
            date.atTime(time);
        } catch (NullPointerException e) {
            popUpError("Popraw datę startu głosowania");
        }
        return date.atTime(time);
    }

    public static void popUpError(String text) {  ///statyczna czy rozszerzyć klasę ClientController?
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(text));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    protected boolean isEmpty(Button button) {
        return button.getText().isEmpty();
    }

    protected boolean isEmpty(ComboBox comboBox) {
        return comboBox.getSelectionModel().isEmpty();
    }


    protected void changeButtonStyle(Button button, String color, String text, String id) {
        button.setText(text);
        button.setStyle("-fx-background-color:" + color);
        button.setId(id);
    }

    protected String getTextFromField(TextField textField) {
        System.out.println(textField.getText());
        return textField.getText();
    }

    protected Object getComboBoxValue(ComboBox comboBox) {
        return comboBox.getSelectionModel().getSelectedItem();
    }

    protected void closeLoginPanelOnAction(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    protected void loadAnchorPane(AnchorPane root, String path) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/pk/electionappclient/GUI/" + path));
        root.getChildren().setAll(pane);
    }

    protected <E> String listToString(List<E> list, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str ="";
        for (E e : list) {
            Method method = e.getClass().getMethod(methodName);
            str +=  method.invoke(e) + ", ";
        }
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    protected String parliamentaryElectionDateName(LocalDateTime date) {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()
                + " " + date.getHour() + ":" + date.getMinute() +":" + date.getSecond();
    }

}


