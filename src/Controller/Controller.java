package Controller;

import Exceptions.MyException;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {

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
    private Button saveAndExitExerciseButton;

    //########################################### BMI FIELDS

    @FXML
    private TextField usersHeight;

    @FXML
    private TextField usersWeight;

    @FXML
    private Label bmiLabel;

    @FXML
    private Button closeBmiButton;

    @FXML
    private Label nameLabel;

    public Controller() {
    }

    //########################################### EXERCISE PAGE


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
    public void handleCloseAndSaveExerciseButtonAction(ActionEvent event) {
        Stage stage = (Stage) saveAndExitExerciseButton.getScene().getWindow();
        stage.close();
    }


    //########################################### BMI PAGE

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
    public void handleCloseBmiButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeBmiButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCalculateBmiButtonAction(ActionEvent event) {

        try{
            theModel.setHeightAndWeight(usersHeight.getText(), usersWeight.getText());
            theModel.checkHeightAndWeight();

        }catch (MyException e){

            Alert alert = new Alert(Alert.AlertType.ERROR, e.toStringBmi());
            alert.showAndWait();
        }

        bmiLabel.setText("Your BMI is: " + theModel.calculateBmi());
    }


    //########################################### STATS PAGE

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

    @FXML
    public void setNameTextField(javafx.event.ActionEvent actionEvent) {

        nameLabel.setText("Hello " + nameTextField.getText() + "!");
        nameLabel.setAlignment(Pos.CENTER);
    }

}
