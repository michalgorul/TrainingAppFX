package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * The ControllerMain class coordinates interactions between the View and Model, controller of stats page
 * @author Michal Goral
 * @version 1.0
 */
public class ControllerStats implements Initializable {

    /**
     * The model object from MVC model
     */
    private Model theModel;

    /**
     * A vector of Labels to display distances in specific category
     */
    private final Vector<Label> distances = new Vector<>();

    /**
     * A vector of Labels to display durations in specific category
     */
    private final Vector<Label> durations = new Vector<>();

    /**
     * A Label where user's running distance stats will be shown
     */
    @FXML private Label runningDistance;

    /**
     * A Label where user's running distance stats will be shown
     */
    @FXML private Label cyclingDistance;

    /**
     * A Label where user's swimming distance stats will be shown
     */
    @FXML private Label swimmingDistance;

    /**
     * A Label where user's walking distance stats will be shown
     */
    @FXML private Label walkingDistance;

    /**
     * A Label where user's nordic walking distance stats will be shown
     */
    @FXML private Label nordicDistance;

    /**
     * A Label where user's winter sports distance stats will be shown
     */
    @FXML private Label winterDistance;

    /**
     * A Label where user's other distance stats will be shown
     */
    @FXML private Label otherDistance;

    /**
     * A Label where user's running distance stats will be shown
     */
    @FXML private Label runningDuration;

    /**
     * A Label where user's running distance stats will be shown
     */
    @FXML private Label cyclingDuration;

    /**
     * A Label where user's swimming distance stats will be shown
     */
    @FXML private Label swimmingDuration;

    /**
     * A Label where user's walking distance stats will be shown
     */
    @FXML private Label walkingDuration;

    /**
     * A Label where user's nordic walking distance stats will be shown
     */
    @FXML private Label nordicDuration;

    /**
     * A Label where user's winter sports distance stats will be shown
     */
    @FXML private Label winterDuration;

    /**
     * A Label where user's other distance stats will be shown
     */
    @FXML private Label otherDuration;


    /**
     * This method will initialize some of class's fields
     * @param location a pointer to a resource
     * @param resources a local-specific object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        distances.add(runningDistance);
        distances.add(cyclingDistance);
        distances.add(swimmingDistance);
        distances.add(walkingDistance);
        distances.add(nordicDistance);
        distances.add(winterDistance);
        distances.add(otherDistance);

        durations.add(runningDuration);
        durations.add(cyclingDuration);
        durations.add(swimmingDuration);
        durations.add(walkingDuration);
        durations.add(nordicDuration);
        durations.add(winterDuration);
        durations.add(otherDuration);

    }

    /**
     * This method will set specific model to work on and set proper labels
     * @param theModel the model to work on
     */
    public void initData(Model theModel){

        this.theModel = theModel;

        for(int i = 0; i < theModel.getExerciseNames().size(); i++){

           Double sumDis = theModel.getSumDistanceForEach(theModel.getExerciseNames().get(i));
           distances.get(i).setText(sumDis.toString());

            Double sumDur = theModel.getSumDurationStream(theModel.getExerciseNames().get(i));
            durations.get(i).setText(sumDur.toString());
        }

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


}
