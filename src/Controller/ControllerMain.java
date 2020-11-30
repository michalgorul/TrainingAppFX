package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private Model theModel = new Model();

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

    public void updateModel(Model theModel){
        this.theModel = theModel;
    }

    @FXML
    public void setNameTextField(javafx.event.ActionEvent actionEvent) {

        theModel.setUserName(nameTextField.getText());

        nameLabel.setText("Hello " + theModel.getUserName() + "!");
        nameLabel.setAlignment(Pos.CENTER);
    }

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

    @FXML
    private void openBmiWindow(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/bmiPage.fxml")));
        Scene tableViewScene = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void openHistoryWindow(ActionEvent event) throws IOException{

        //Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/historyPage.fxml")));

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
