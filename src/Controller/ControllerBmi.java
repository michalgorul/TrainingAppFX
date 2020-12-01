package Controller;

import Exceptions.MyException;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The ControllerMain class coordinates interactions between the View and Model, controller of calculating BMI page
 * @author Michal Goral
 * @version 1.0
 */
public class ControllerBmi {

    /**
     * The model object from MVC model
     */
    private Model theModel;

    /**
     * A TextField where user can input his height
     */
    @FXML
    private TextField usersHeight;

    /**
     * A TextField where user can input his height
     */
    @FXML
    private TextField usersWeight;

    /**
     * A Label where user's BMI will be displayed
     */
    @FXML
    private Label bmiLabel;

    public void initData(Model theModel){
        this.theModel = theModel;
    }

    /**
     * This method will take us to main page after clicking return button
     * @param event the event to be processed
     * @throws IOException in/out exception
     */
    @FXML
    public void handleCloseBmiButtonAction(ActionEvent event) throws IOException {

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

    /**
     * This method handles displaying result of calculation BMI made by model
     * @param event the event to be processed
     */
    @FXML
    public void handleCalculateBmiButtonAction(ActionEvent event) {

        try{
            theModel.setHeightAndWeight(usersHeight.getText(), usersWeight.getText());
            theModel.checkHeightAndWeight();
            bmiLabel.setText("Your BMI is: " + theModel.calculateBmi());
        }
        catch (MyException e){

            Alert alert = new Alert(Alert.AlertType.ERROR, e.toStringBmi());
            alert.showAndWait();
        }
    }
}
