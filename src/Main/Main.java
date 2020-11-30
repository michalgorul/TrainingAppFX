package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class containing main method
 * @author Michal Goral
 * @version 1.1
 */
public class Main extends Application {

    /**
     * This method will initialize main page
     * @param primaryStage main page stage
     * @throws Exception basic exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/mainPage.fxml"));
        primaryStage.setTitle("Training App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * The main method
     * @param args i dont use calling parameter
     */
    public static void main(String[] args) {
        launch(args);
    }
}
