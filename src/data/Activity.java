package data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Class representing an activity record from the dataset
class Activity {
    String id, date;
    int totalSteps, veryActiveMinutes, sedentaryMinutes, calories;

    // Constructor to initialize an activity instance
    public Activity(String id, String date, int totalSteps, int veryActiveMinutes, int sedentaryMinutes, int calories) {
        this.id = id;
        this.date = date;
        this.totalSteps = totalSteps;
        this.veryActiveMinutes = veryActiveMinutes;
        this.sedentaryMinutes = sedentaryMinutes;
        this.calories = calories;
    }

    // Override toString() to format output for console display
    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Steps: " + totalSteps + ", Active Minutes: " + veryActiveMinutes + ", Sedentary Minutes: " + sedentaryMinutes + ", Calories: " + calories;
    }
}
