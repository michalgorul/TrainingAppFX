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
    private void openExerciseWindow(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/exercisePage.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void openBmiWindow(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/bmiPage.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void openHistoryWindow(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/historyPage.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
