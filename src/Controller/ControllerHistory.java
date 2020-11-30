package Controller;

import Model.Exercise;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ControllerHistory implements Initializable {

    /**
     * The model object from MVC model
     */
    private final Model theModel = new Model();

    @FXML
    private TableView<Exercise> historyTableView;

    @FXML
    private TableColumn<Exercise, String> categoryColumn;

    @FXML
    private TableColumn<Exercise, String> commentColumn;

    @FXML
    private TableColumn<Exercise, String> dateColumn;

    @FXML
    private TableColumn<Exercise, Double> distanceColumn;

    @FXML
    private TableColumn<Exercise, Double> durationColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setup columns in tableview
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseName"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("exerciseDate"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        //load data
        historyTableView.setItems(theModel.getExercise());

        historyTableView.setEditable(true);
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void exitToMainPage(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/mainPage.fxml")));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void changeCategoryNameEvent(TableColumn.CellEditEvent edittedCell){

        Exercise exerciseSelected = historyTableView.getSelectionModel().getSelectedItem();

        exerciseSelected.setExerciseName(edittedCell.getNewValue().toString());
    }

    public void changeCommentEvent(TableColumn.CellEditEvent edittedCell){

        Exercise exerciseSelected = historyTableView.getSelectionModel().getSelectedItem();

        exerciseSelected.setComment(edittedCell.getNewValue().toString());
    }


}
