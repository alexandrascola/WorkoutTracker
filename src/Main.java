import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        //Scanner and workout list
        Scanner scanner = new Scanner(System.in);
        ArrayList<Workout> workouts = new ArrayList<>();
        //Formatter for manually entered dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //Formatter for the strava dates
        DateTimeFormatter stravaDateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        // File object for data
        File activityFile = new File("data/activities_capstone.csv");

        boolean stravaImported = false;
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
            System.out.println("4. Import Strava Data");
            System.out.println("5. Exit");


            //Validate user menu choice
            int choice = 0;

            while (choice < 1 || choice > 5) {
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice < 1 || choice > 5) {
                        System.out.println("Please enter a number from 1 to 5.");
                    }
                } else {
                    System.out.println("Please enter a number from 1 to 5.");
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

                //Validation of date
                LocalDate workoutDate = null;

                while (workoutDate == null) {
                    System.out.print("Please enter a workout date (MM/dd/yyyy): ");
                    String dateInput = scanner.nextLine();
                    try {
                        workoutDate = LocalDate.parse(dateInput, dateFormatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid format. Please enter a valid workout date (MM/dd/yyyy): ");
                    }
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
                Workout workout1 = new Workout(workoutType, workoutDate, distance, distanceUnit, totalDurationSeconds);

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


                double totalMiles = 0;
                double totalYards = 0;
                int totalDurationSeconds = 0;
                int runCount = 0;
                int walkCount = 0;
                int swimCount = 0;

                //Counts each workout type
                for (Workout workout : workouts) {
                    if (workout.getWorkoutType().equalsIgnoreCase("Run")) {
                        runCount++;
                    }
                    if (workout.getWorkoutType().equalsIgnoreCase("Walk")) {
                        walkCount++;
                    }
                    if (workout.getWorkoutType().equalsIgnoreCase("Swim")) {
                        swimCount++;
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

                //Calculate average duration
                int averageDurationSeconds = 0;
                if (!workouts.isEmpty()) {
                    averageDurationSeconds = totalDurationSeconds / workouts.size();
                }
                int averageMinutes = averageDurationSeconds / 60;
                int averageSeconds = averageDurationSeconds % 60;

                //Prints statistics OPTION THREE
                System.out.println("Total workouts: " + workouts.size());
                System.out.println("Runs: " + runCount);
                System.out.println("Walks: " + walkCount);
                System.out.println("Swims: " + swimCount);
                System.out.printf("Total run/walk distance: %.2f miles%n", totalMiles);
                System.out.printf("Total swim distance: %.0f yards%n", totalYards);
                System.out.println("Total workout time: " + totalHours + " hours " + totalMinutes + " minutes " + totalSeconds + " seconds");
                System.out.println("Average workout duration: " + averageMinutes + " minutes " + averageSeconds + " seconds");
            }

            // Option FOUR: Import Strava data
            if (choice == 4) {

                if (stravaImported) {
                    System.out.println("Strava data has already been imported.");
                } else {
                    try {
                        Scanner fileScanner = new Scanner(activityFile);

                        // Skip CSV header row
                        if (fileScanner.hasNextLine()) {
                            fileScanner.nextLine();
                        }

                        // Read activity rows from CSV file
                        int rowCount = 0;
                        int supportedCount = 0;
                        int skippedCount = 0;

                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            rowCount++;

                            String[] parts = line.split(",");

                            //Cleaned after ActivityID removal from csv
                            String activityType = parts[0];
                            int elapsedTime = Integer.parseInt(parts[1]);
                            double distanceMeters = Double.parseDouble(parts[2]);
                            LocalDate workoutDate = LocalDate.parse(parts[3], stravaDateFormatter); //accepts strava date format

                            // Import SUPPORTED activity types
                            if (activityType.equalsIgnoreCase("Run")
                                    || activityType.equalsIgnoreCase("Walk")
                                    || activityType.equalsIgnoreCase("Swim")) {

                                double convertedDistance;
                                String distanceUnit;

                                if (activityType.equalsIgnoreCase("Swim")) {
                                    convertedDistance = Math.round(distanceMeters * 1.09361);
                                    distanceUnit = "yards";
                                } else {
                                    convertedDistance = Math.round(distanceMeters * 0.000621371 * 100.0) / 100.0;
                                    distanceUnit = "miles";
                                }

                                Workout importedWorkout = new Workout(
                                        activityType,
                                        workoutDate,
                                        convertedDistance,
                                        distanceUnit,
                                        elapsedTime
                                );

                                workouts.add(importedWorkout);
                                supportedCount++;

                            } else {
                                skippedCount++;
                            }
                        }

                        System.out.println("Activity rows found: " + rowCount);
                        System.out.println("Imported activities: " + supportedCount);
                        System.out.println("Skipped activities: " + skippedCount);

                        fileScanner.close();
                        stravaImported = true;

                    } catch (FileNotFoundException e) {
                        System.out.println("Could not find the file.");
                    }
                }
            }



            //Option FIVE: Exit program
            if (choice == 5) {
                running = false;
            }
        }
        //DON'T FORGET TO CLOSE YOUR SCANNER!!!
        scanner.close();
    }
}
