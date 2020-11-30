package Model;

import Exceptions.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Vector;

public class Model {

    /**
     * Holds the string of the user's name entered in the view
     */
    private String userName;

    private Double height = 0.0;
    private Double weight = 0.0;

    /**
     * Vector of exercises
     */
    private final Vector<Exercise> exercises = new Vector<>();

    /**
     * Vector of exercise names
     */
    private final Vector<String> exerciseNames;

    /**
     * Vector of duration units
     */
    private final Vector<String> durationUnits = new Vector<>();

    public Model(){

        /*
          Vector of distance units
         */
        Vector<String> distanceUnits = new Vector<>();
        distanceUnits.add("km");
        distanceUnits.add("m");
        distanceUnits.add("cm");

        durationUnits.add("h");
        durationUnits.add("min");
        durationUnits.add("s");

        exerciseNames = readCategoriesFromFile("C:\\Users\\micha\\IdeaProjects\\TrainingAppSWING\\categories.txt");
    }

    /**
     * This method will set passed name
     * @param userName name to set
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * This method will give user's name who wants it
     * @return user's name
     */
    public String getUserName(){

        return this.userName;
    }

    /**
     * This method will give us vector of all exercises
     * @return vector of all exercises
     */
    public Vector<Exercise> getExercises() {
        return exercises;
    }

    /**
     * This method will read categories from file
     * @param name name of file we want to read from
     * @return vector of category names
     */
    public Vector<String> readCategoriesFromFile(String name) {

        Vector<String> categories = new Vector<>();
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                categories.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * This method will add new exercise to exercises vector
     * @param name name of category
     * @param comment comment user wanted to add
     * @param date date of exercise
     * @param distance distance that user made that exercise
     * @param duration how long user was exercising
     */
    public void addExercise(String name, String comment, String date, Double distance, Double duration){

        exercises.add(new Exercise(name, comment, date, distance, duration));

    }



    /**
     * This method will check if user's weight and height are in reasonable bounds
     * @throws MyException thrown if height or weight arent proper
     */
    public void checkHeightAndWeight() throws MyException {
        if(this.height < 1.0 || this.height > 320.0 || this.weight < 1.0 || this.weight > 320.0){

            throw new MyException(this.height, this.weight);
        }
    }

    /**
     * This method will save user's weight and height in this class
     * @param height user's height
     * @param weight user's weight
     */
    public void setHeightAndWeight(String height, String weight){

        try {

            height = height.replace(",",".");
            weight = weight.replace(",",".");
            this.height = Double.parseDouble(height);
            this.weight = Double.parseDouble(weight);

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter right value");
            alert.showAndWait();
        }
    }

    /**
     * This method will calculate BMI of user
     * @return user's BMI
     */
    public Double calculateBmi(){
        double bmi;

        if (this.height <= 0 || this.weight <= 0) {
            throw new ArithmeticException("incorrect values");
        }
        else {
            bmi = this.weight/((this.height/100)*(this.height/100));
        }

        return BigDecimal.valueOf(bmi).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * This method will check if integers are negative and throw an exception if so
     * @param args values to check
     * @throws MyException thrown if value is negative
     */
    public void checkDoublesIfNegative(Double... args) throws MyException {

        for (Double arg : args) {

            if (arg < 0) {
                throw new MyException(arg);
            }
        }
    }


    public ObservableList<Exercise> getExercise(){

        ObservableList<Exercise> exerciseObservableList = FXCollections.observableArrayList();
        exerciseObservableList.add(new Exercise("elo6","", "null",123.0, 123.0));
        exerciseObservableList.add(new Exercise("elo1","", "null",123.0, 123.0));
        exerciseObservableList.add(new Exercise("elo2","", "null",123.0, 123.0));
        exerciseObservableList.add(new Exercise("elo3","", "null",123.0, 123.0));
        exerciseObservableList.add(new Exercise("elo4","", "null",123.0, 123.0));
        exerciseObservableList.add(new Exercise("elo5","", "null",123.0, 123.0));


        for(Exercise ex : this.exercises){

            exerciseObservableList.add(new Exercise(ex.getExerciseName(), ex.getComment(), ex.getExerciseDate(), ex.getDistance(), ex.getDuration()));
        }

        return exerciseObservableList;
    }

}
