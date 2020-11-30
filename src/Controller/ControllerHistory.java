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

/**
 * The ControllerMain class coordinates interactions between the View and Model, controller of history page
 * @author Michal Goral
 * @version 1.0
 */
public class ControllerHistory implements Initializable {

    /**
     * The model object from MVC model
     */
    private Model theModel;

    /**
     * A tableView to display history of user's trainings
     */
    @FXML
    private TableView<Exercise> historyTableView;

    /**
     * A column for TableView containing category of a exercise
     */
    @FXML
    private TableColumn<Exercise, String> categoryColumn;

    /**
     * A column for TableView containing comment of a exercise
     */
    @FXML
    private TableColumn<Exercise, String> commentColumn;

    /**
     * A column for TableView containing date of a exercise
     */
    @FXML
    private TableColumn<Exercise, String> dateColumn;

    /**
     * A column for TableView containing distance made during exercise
     */
    @FXML
    private TableColumn<Exercise, Double> distanceColumn;

    /**
     * A column for TableView containing time that user spent on that training
     */
    @FXML
    private TableColumn<Exercise, Double> durationColumn;

    /**
     * This method will initialize some of class's fields
     * @param location a pointer to a resource
     * @param resources a local-specific object
     */
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

    /**
     * This method will set specific model to work on
     * @param theModel the model to work on
     */
    public void initData(Model theModel){
        this.theModel = theModel;
        //load data
        historyTableView.setItems(this.theModel.getExercisesToTableView());
    }

    /**
     * This method will take us to main page after clicking return button
     * @param event the event to be processed
     * @throws IOException in/out exception
     */
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

    /**
     * This method will edit category name in TableView in specific exercise
     * @param editedCell cell that will be edited
     */
    public void changeCategoryNameEvent(TableColumn.CellEditEvent editedCell){

        Exercise exerciseSelected = historyTableView.getSelectionModel().getSelectedItem();

        exerciseSelected.setExerciseName(editedCell.getNewValue().toString());
    }

    /**
     * This method will edit category comment in TableView in specific exercise
     * @param editedCell cell that will be edited
     */
    public void changeCommentEvent(TableColumn.CellEditEvent editedCell){


        Exercise exerciseSelected = historyTableView.getSelectionModel().getSelectedItem();

        exerciseSelected.setComment(editedCell.getNewValue().toString());
    }

    /**
     * This method will delete specific exercise chosen by user in tableView and in vector of exercises
     */
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
