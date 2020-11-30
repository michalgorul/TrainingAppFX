package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerMain {

    /**
     * The model object from MVC model
     */
    private final Model theModel = new Model();

    @FXML
    private TextField nameTextField;

    @FXML
    private Button sendNameButton;

    @FXML
    private Button exerciseButton;

    @FXML
    private Button bmiButton;

    @FXML
    private Button statsButton;

    @FXML
    private Label nameLabel;

    @FXML
    public void setNameTextField(javafx.event.ActionEvent actionEvent) {

        theModel.setUserName(nameTextField.getText());

        nameLabel.setText("Hello " + theModel.getUserName() + "!");
        nameLabel.setAlignment(Pos.CENTER);
    }

    @FXML
    private void openExerciseWindow(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/exercisePage.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Exercise Page");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBmiWindow(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/bmiPage.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Calculating BMI Page");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openStatsWindow(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/statsPage.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Statistics Page");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
