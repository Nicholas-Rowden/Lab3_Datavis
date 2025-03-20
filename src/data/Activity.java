package data;

/**
 * Represents an activity record containing step count, activity minutes, and calorie data.
 * This class is immutable and provides getter methods to access activity details.
 */
public class Activity {
    private final String id;  // Unique identifier for the activity record
    private final String date; // Date of the recorded activity
    private final int totalSteps; // Total steps taken on the recorded day
    private final int veryActiveMinutes; // Minutes spent in high-intensity activity
    private final int sedentaryMinutes; // Minutes spent in sedentary activity
    private final int calories; // Calories burned during the day

    /**
     * Constructs an Activity object with the given parameters.
     *
     * @param id Unique identifier for the activity record
     * @param date Date of the activity
     * @param totalSteps Total steps taken on the recorded day
     * @param veryActiveMinutes Minutes spent in high-intensity activity
     * @param sedentaryMinutes Minutes spent in sedentary activity
     * @param calories Calories burned
     */
    public Activity(String id, String date, int totalSteps, int veryActiveMinutes, int sedentaryMinutes, int calories) {
        this.id = id;
        this.date = date;
        this.totalSteps = totalSteps;
        this.veryActiveMinutes = veryActiveMinutes;
        this.sedentaryMinutes = sedentaryMinutes;
        this.calories = calories;
    }

    // Getter methods to retrieve activity details
    public String getId() { return id; }
    public String getDate() { return date; }
    public int getTotalSteps() { return totalSteps; }
    public int getVeryActiveMinutes() { return veryActiveMinutes; }
    public int getSedentaryMinutes() { return sedentaryMinutes; }
    public int getCalories() { return calories; }

    /**
     * Returns a string representation of the Activity object for debugging.
     *
     * @return A formatted string containing activity details
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Date: %s, Steps: %d, Active Min: %d, Sedentary Min: %d, Calories: %d",
                id, date, totalSteps, veryActiveMinutes, sedentaryMinutes, calories);
    }
}
