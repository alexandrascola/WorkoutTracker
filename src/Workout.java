import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Workout {
    private String workoutType;
    private LocalDate workoutDate;
    private double distance;
    private String distanceUnit;
    private int durationSeconds;

// Constructor
public Workout(String workoutType, LocalDate workoutDate, double distance, String distanceUnit, int durationSeconds) {
    this.workoutType = workoutType;
    this.workoutDate = workoutDate;
    this.distance = distance;
    this.distanceUnit = distanceUnit;
    this.durationSeconds = durationSeconds;
    }


    //Getter Methods
    public String getWorkoutType() {

    return workoutType;
    }

    //Get Method for workout date
    public LocalDate getWorkoutDate() {
    return workoutDate;
    }

    //Get Method for distance
    public double getDistance() {
    return distance;
    }
    //Get Method for duration in secs
    public int getDurationSeconds() {
    return durationSeconds;
    }


    // Public void to display workout details
    public void displayWorkout() {
        System.out.println("Workout Type: " + workoutType);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("Workout Date: " + workoutDate.format(formatter));
        System.out.println("Distance: " + distance + " " + distanceUnit);

        // Convert total seconds to minutes and seconds
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;

        System.out.println("Duration: " + minutes + " minutes " + seconds + " seconds");
    }
}
