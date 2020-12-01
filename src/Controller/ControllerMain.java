package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * The ControllerMain class coordinates interactions between the View and Model, controller of main page
 * @author Michal Goral
 * @version 1.0
 */
public class ControllerMain {

    /**
     * The model object from MVC model
     */
    private Model theModel = new Model();

    /**
     * A textField where user can type in his name
     */
    @FXML
    private TextField nameTextField;

    /**
     * label to display user's name
     */
    @FXML
    private Label nameLabel;

    /**
     * This method will update existing model
     * @param theModel model to update
     */
    public void updateModel(Model theModel){
        this.theModel = theModel;
    }

    /**
     * This method handles action after clicking send name button
     * @param event the event to be processed
     */
    @FXML
    public void setNameTextField(ActionEvent event) {

        theModel.setUserName(nameTextField.getText());

        nameLabel.setText("Hello " + theModel.getUserName() + "!");
        nameLabel.setAlignment(Pos.CENTER);
    }

    /**
     * This method handles action after clicking add new exercise button
     * @param event the event to be processed
     * @throws IOException in/out exception
     */
    @FXML
    private void openExerciseWindow(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("View/exercisePage.fxml")));
        Parent root = loader.load();

        Scene exerciseScene = new Scene(root);

        //access the controller and call a method
        ControllerExercise controller = loader.getController();
        controller.initData(this.theModel);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(exerciseScene);
        window.show();
    }

    /**
     * This method handles action after clicking calculate BMI button
     * @param event the event to be processed
     * @throws IOException in/out exception
     */
    @FXML
    private void openBmiWindow(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("View/bmiPage.fxml")));
        Parent root = loader.load();

        Scene exerciseScene = new Scene(root);

        //access the controller and call a method
        ControllerBmi controller = loader.getController();
        controller.initData(this.theModel);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(exerciseScene);
        window.show();
    }


    /**
     * This method handles action after clicking show stats button
     * @param event the event to be processed
     * @throws IOException in/out exception
     */
    @FXML
    public void openStatsWindow(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("View/statsPage.fxml")));
        Parent root = loader.load();

        Scene historyScene = new Scene(root);

        ControllerStats controller = loader.getController();
        controller.initData(this.theModel);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(historyScene);
        window.show();
    }

    /**
     * This method handles action after clicking show history button
     * @param event the event to be processed
     * @throws IOException in/out exception
     */
    @FXML
    public void openHistoryWindow(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("View/historyPage.fxml")));
        Parent root = loader.load();

        Scene historyScene = new Scene(root);

        ControllerHistory controller = loader.getController();
        controller.initData(this.theModel);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(historyScene);
        window.show();
    }

}
