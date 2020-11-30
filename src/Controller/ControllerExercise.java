package Controller;

import Exceptions.MyException;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerExercise implements Initializable {

    private Model theModel;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            Vector<String> names = theModel.readCategoriesFromFile("categories.txt");

            for(String s : names){

                categoryComboBox.getItems().add(s);
            }
        }catch (NullPointerException e){

        }
    }

    public void initData(Model theModel){

        this.theModel = theModel;

        Vector<String> names = this.theModel.readCategoriesFromFile("categories.txt");

        for(String s : names){

            categoryComboBox.getItems().add(s);
        }
    }

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

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter right distance and duration value");
            alert.showAndWait();
        }

        return -1.0;
    }

    @FXML
    public void handleCloseAndSaveExerciseButtonAction(ActionEvent event) throws IOException {

        if(getValuesAndSave()){

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("View/mainPage.fxml")));
            Parent root = loader.load();

            Scene exerciseScene = new Scene(root);

            //access the controller and call a method
            ControllerMain controller = loader.getController();
            controller.updateModel(this.theModel);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(exerciseScene);
            window.show();
        }
    }

    @FXML
    public void handleCloseExerciseButtonAction(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/mainPage.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    private boolean getValuesAndSave(){

        String cat = "", dat = "";
        boolean canSave = true;
        boolean canSaveDisDur = false;
        boolean errorAlertShown = false;

        Alert alert;

        if(categoryComboBox.getValue() != null){
            cat = categoryComboBox.getValue().toString();
        }
        if(cat.equals("") || categoryComboBox.getValue() == null){
            canSave = false;

            if(!errorAlertShown){
                alert = new Alert(Alert.AlertType.ERROR, "Please select category");
                alert.showAndWait();
                errorAlertShown = true;
            }

        }
        if(exerciseDatePicker.getValue() == null){
            canSave = false;

            if(!errorAlertShown){
                alert = new Alert(Alert.AlertType.ERROR, "Please select date");
                alert.showAndWait();
                errorAlertShown = true;
            }

        }
        else{
            dat = exerciseDatePicker.getValue().toString();
        }

        String com = commentTextField.getText();
        Double dis = -1.0, dur = -1.0;
        if(!errorAlertShown){
            dis = getDisOrDurInDouble(distanceTextField.getText());
            if(dis >= 0.0){
                dur = getDisOrDurInDouble(durationTextField.getText());
            }
        }

        if(dur >= 0.0 && dis >= 0.0){
            canSaveDisDur = true;
        }

        if(canSave && canSaveDisDur){
            theModel.addExercise(cat, com, dat, dis, dur);
            alert = new Alert(Alert.AlertType.INFORMATION, "Exercise saved");
            alert.showAndWait();
            return true;
        }

        return false;
    }


}
