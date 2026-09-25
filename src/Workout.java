public class Workout {
    private String workoutType;
    private double distance;
    private String distanceUnit;
    private int durationSeconds;

// Constructor
public Workout(String workoutType, double distance, String distanceUnit, int durationSeconds) {
    this.workoutType = workoutType;
    this.distance = distance;
    this.distanceUnit = distanceUnit;
    this.durationSeconds = durationSeconds;
    }

    //Get Method for workout type
    public String getWorkoutType() {
    return workoutType;
    }
    //Get Method for distance
    public double getDistance() {
    return distance;
    }
    //Get Method for duration in secs
    public int getDurationSeconds() {
    return durationSeconds;
    }

    // Void to display workout details
    public void displayWorkout() {
        System.out.println("Workout Type: " + workoutType);
        System.out.println("Distance: " + distance + " " + distanceUnit);

        // Convert total seconds to minutes and seconds
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;

        System.out.println("Duration: " + minutes + " minutes " + seconds + " seconds");
    }
}
