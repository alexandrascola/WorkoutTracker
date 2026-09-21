public class Workout {
    private String workoutType;
    private double distance;
    private int durationMinutes;

public Workout(String workoutType, double distance, int durationMinutes) {
    this.workoutType = workoutType;
    this.distance = distance;
    this.durationMinutes = durationMinutes;
    }
    public void displayWorkout() {
        System.out.println("Workout Type: " + workoutType);
        System.out.println("Distance: " + distance + " miles");
        System.out.println("Duration: " + durationMinutes + " minutes");
    }
}
