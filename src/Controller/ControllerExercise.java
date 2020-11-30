package Controller;

import Exceptions.MyException;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerExercise {

    /**
     * The model object from MVC model
     */
    private final Model theModel = new Model();

    @FXML
    private ComboBox categoryComboBox;

    @FXML
    private TextField commentTextField;

    @FXML
    private DatePicker exerciseDatePicker;

    @FXML
    private TextField distanceTextField;

    @FXML
    private TextField durationTextField;

    @FXML
    private Button saveAndExitExerciseButton;


    private Double getDisOrDurInDouble(String value){

        try{

            value = value.replace(",",".");
            Double val = Double.parseDouble(value);

            try{
                theModel.checkDoublesIfNegative(val);

            } catch (MyException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR, e.toStringValues());
                alert.showAndWait();
            }
            return val;

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter right value");
            alert.showAndWait();
        }

        return 0.0;
    }

    @FXML
    public void handleCloseAndSaveExerciseButtonAction(ActionEvent event) throws IOException {

        if(getValuesAndSave()){

            Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/mainPage.fxml")));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }

    private boolean getValuesAndSave(){

        String cat = "", dat = "";
        boolean canSave = true;

        Alert alert;

        if(categoryComboBox.getValue() != null){
            cat = categoryComboBox.getValue().toString();
        }
        if(cat.equals("") || categoryComboBox.getValue() == null){
            canSave = false;
            alert = new Alert(Alert.AlertType.ERROR, "Please select category");
            alert.showAndWait();
        }
        if(exerciseDatePicker.getValue() == null){
            canSave = false;
            alert = new Alert(Alert.AlertType.ERROR, "Please select date");
            alert.showAndWait();
        }
        else{
            dat = exerciseDatePicker.getValue().toString();
        }

        String com = commentTextField.getText();
        Double dis = getDisOrDurInDouble(distanceTextField.getText());
        Double dur = getDisOrDurInDouble(durationTextField.getText());

        if(canSave){
            theModel.addExercise(cat, com, dat, dis, dur);
            alert = new Alert(Alert.AlertType.INFORMATION, "Exercise saved");
            alert.showAndWait();
            return true;
        }

        return false;
    }


}
