package Model;

/**
 * This is the Exercise class
 * It contains information about a exercise
 * and provides us to set those values
 * @author Michał Góral
 * @version 1.0
 */
public class Exercise {

    private final String exerciseName;
    private final String comment;
    private final String exerciseDate;

    private final Double distance;      //in kilometers
    private final Double duration;      //in minutes

    public Exercise(String name, String comment, String exerciseDate, Double distance, Double duration){

        this.exerciseName = name;
        this.comment = comment;
        this.exerciseDate = exerciseDate;
        this.distance = distance;
        this.duration = duration;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getComment() {
        return comment;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getDuration() {
        return duration;
    }
}

