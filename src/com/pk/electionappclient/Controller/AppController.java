package com.pk.electionappclient.Controller;


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


public abstract class AppController {


    protected void popUpWindow(String text) {
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

    protected void closeLoginPanelOnAction(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    protected void loadAnchorPane(AnchorPane root, String path) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/pk/electionappclient/GUI/" + path));
        root.getChildren().setAll(pane);
    }

}


