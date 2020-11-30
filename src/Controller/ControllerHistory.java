package Controller;

import Model.Exercise;
import Model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.ResourceBundle;


public class ControllerHistory implements Initializable {

    private Model theModel;

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

        historyTableView.setEditable(true);
        historyTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initData(Model theModel){
        this.theModel = theModel;
        //load data
        historyTableView.setItems(this.theModel.getExercisesToTableView());
    }

    public void exitToMainPage(ActionEvent event) throws IOException{

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

    public void changeCategoryNameEvent(TableColumn.CellEditEvent edittedCell){

        Exercise exerciseSelected = historyTableView.getSelectionModel().getSelectedItem();

        exerciseSelected.setExerciseName(edittedCell.getNewValue().toString());
    }

    public void changeCommentEvent(TableColumn.CellEditEvent edittedCell){

        Exercise exerciseSelected = historyTableView.getSelectionModel().getSelectedItem();

        exerciseSelected.setComment(edittedCell.getNewValue().toString());
    }

    public void deleteExerciseButtonPushed(){

        ObservableList<Exercise> selectedRows, allExercises;
        allExercises = historyTableView.getItems();

        selectedRows = historyTableView.getSelectionModel().getSelectedItems();

        try{

            for(Exercise ex : selectedRows){
                allExercises.remove(ex);
                theModel.deleteExercise(ex);
            }

        }catch(NoSuchElementException e){

        }
    }


}
