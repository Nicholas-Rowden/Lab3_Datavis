package data;

// Class representing an activity record from the dataset
public class Activity {
    private final String id;
    private final String date;
    private final int totalSteps;
    private final int veryActiveMinutes;
    private final int sedentaryMinutes;
    private final int calories;

    // Constructor
    public Activity(String id, String date, int totalSteps, int veryActiveMinutes, int sedentaryMinutes, int calories) {
        this.id = id;
        this.date = date;
        this.totalSteps = totalSteps;
        this.veryActiveMinutes = veryActiveMinutes;
        this.sedentaryMinutes = sedentaryMinutes;
        this.calories = calories;
    }

    // Getters for table display
    public String getId() { return id; }
    public String getDate() { return date; }
    public int getTotalSteps() { return totalSteps; }
    public int getVeryActiveMinutes() { return veryActiveMinutes; }
    public int getSedentaryMinutes() { return sedentaryMinutes; }
    public int getCalories() { return calories; }

    // Override toString for debugging
    @Override
    public String toString() {
        return String.format("ID: %s, Date: %s, Steps: %d, Active Min: %d, Sedentary Min: %d, Calories: %d",
                id, date, totalSteps, veryActiveMinutes, sedentaryMinutes, calories);
    }
}
