import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Scanner and workout list
        Scanner scanner = new Scanner(System.in);
        ArrayList<Workout> workouts = new ArrayList<>();

        boolean running = true;

        //Loop to repeat until user chooses exit
        while (running) {
            //Display user main menu
            System.out.println("------------------------");
            System.out.println("WORKOUT TRACKER");
            System.out.println("-----------------------");
            System.out.println("1. Add Workout");
            System.out.println("2. View Workouts");
            System.out.println("3. View Statistics");
            System.out.println("4. Exit");


            //Validate user menu choice
            int choice = 0;

            while (choice < 1 || choice > 4) {
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice < 1 || choice > 4) {
                        System.out.println("Please enter a number from 1 to 4.");
                    }
                } else {
                    System.out.println("Please enter a number from 1 to 4.");
                    scanner.nextLine();
                }
            }

            //Option ONE: Add new workout
            if (choice == 1) {
                System.out.println("Enter workout type: ");
                String workoutType = scanner.nextLine();

                //Validation for workout type
                while(!workoutType.equalsIgnoreCase("Run")
                    && !workoutType.equalsIgnoreCase("Walk")
                    && !workoutType.equalsIgnoreCase("Swim")) {
                    System.out.print("Please enter Run, Walk, or Swim: ");
                    workoutType = scanner.nextLine();
                }

                String distanceUnit;
                if (workoutType.equalsIgnoreCase("Swim")) {
                    distanceUnit = "yards";
                } else {
                    distanceUnit = "miles";
                }

                //Validate workout distance
                double distance = 0;

                while (distance <= 0) {
                    System.out.print("Enter distance in " + distanceUnit + ": ");

                    if (scanner.hasNextDouble()) {
                        distance = scanner.nextDouble();
                        scanner.nextLine();

                        if (distance <= 0) {
                            System.out.println("Please enter a distance greater than 0.");
                        }
                    } else {
                        System.out.println("Please enter a valid number.");
                        scanner.nextLine();
                    }
                }
                //System.out.println("Enter distance in " + distanceUnit + ": ");
                //double distance = scanner.nextDouble();

                // Validate duration in minutes
                int durationMinutes = -1;

                while (durationMinutes < 0) {
                    System.out.print("Enter duration minutes: ");

                    if (scanner.hasNextInt()) {
                        durationMinutes = scanner.nextInt();
                        scanner.nextLine();

                        if (durationMinutes < 0) {
                            System.out.println("Duration in minutes must be 0 or greater.");
                        }
                    } else {
                        System.out.println("Please enter a valid whole number.");
                        scanner.nextLine();
                    }
                }

                // Validate duration seconds
                int durationSecondsPart = -1;

                while (durationSecondsPart < 0 || durationSecondsPart > 59) {
                    System.out.print("Enter duration seconds: ");

                    if (scanner.hasNextInt()) {
                        durationSecondsPart = scanner.nextInt();
                        scanner.nextLine();

                        if (durationSecondsPart < 0 || durationSecondsPart > 59) {
                            System.out.println("Please enter a number of seconds between 0 and 59.");
                        }
                    } else {
                        System.out.println("Please enter a valid whole number.");
                        scanner.nextLine();
                    }
                }
                int totalDurationSeconds = (durationMinutes * 60) + durationSecondsPart;

                //Create and store the workout
                Workout workout1 = new Workout(workoutType, distance, distanceUnit, totalDurationSeconds);

                workouts.add(workout1);
                workout1.displayWorkout();
            }


            //Option TWO: View all workouts
            if (choice == 2) {
                if (workouts.isEmpty()) {
                    System.out.println("No workouts have been added.");
                } else {
                    for (Workout workout : workouts) {
                        workout.displayWorkout();
                        System.out.println();
                    }
                }
            }


            //Option THREE: View workout statistics
            if (choice == 3) {
                System.out.println("Total workouts: " + workouts.size());

                double totalMiles = 0;
                double totalYards = 0;
                int totalDurationSeconds = 0;

                for (Workout workout : workouts) {
                    if (workout.getWorkoutType().equalsIgnoreCase("Swim")) {
                        totalYards += workout.getDistance();
                    } else {
                        totalMiles += workout.getDistance();
                    }

                    totalDurationSeconds += workout.getDurationSeconds();
                }
                //Convert total duration
                int totalHours = totalDurationSeconds / 3600;
                int remainingSeconds = totalDurationSeconds % 3600;
                int totalMinutes = remainingSeconds / 60;
                int totalSeconds = remainingSeconds % 60;

                System.out.println("Total run/walk distance: " + totalMiles + " miles");
                System.out.println("Total swim distance: " + totalYards + " yards");
                System.out.println("Total workout time: " + totalHours + " hours " + totalMinutes + " minutes " + totalSeconds + " seconds");
            }



            //Option FOUR: Exit program
            if (choice == 4) {
                running = false;
            }
        }
        //DON'T FORGET TO CLOSE YOUR SCANNER!!!
        scanner.close();
    }
}
