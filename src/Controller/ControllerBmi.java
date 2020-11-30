package Controller;

import Exceptions.MyException;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerBmi {

    /**
     * The model object from MVC model
     */
    private final Model theModel = new Model();

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


}
