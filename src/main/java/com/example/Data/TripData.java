package com.example.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;

/**
 * This class handles the management of the data for the trip interface, including the trip list.
 * Note that Logger is just a package that is used to log errors.
 */
public class TripData {
    private static final Logger logger = Logger.getLogger(RadioData.class.getName());
    private final String tripList = "src/main/java/com/example/JSON/trips.json"; // path to JSON file
    private final Map<Integer, String> tripMap = new HashMap<>();
    
    /**
     * constructor for TripData
     * @param tripList The path to the JSON file containing the trip data.
     * @param logger The logger used for logging errors.
     * @param tripMap The map containing the trip data.
     */
    public TripData() {
        loadFromJson();
    }
    
    /**
     * Gets the map of trips.
     * 
     * @return The map of trips. The keys are the trip numbers and the values are the trip descriptions.
     */
    public Map<Integer, String> getTripMap() {
        return tripMap;
    }
    
    /**
     * Loads trip data from a JSON file into the tripMap.
     * 
     * This method reads the JSON file specified by the tripList path,
     * deserializes its contents into a Map of trip numbers and descriptions,
     * clears the existing tripMap, and populates it with the loaded data.
     * 
     * If an error occurs during file reading or JSON parsing, it logs a severe error message.
     */
    public void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(tripList)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            tripMap.clear();
            tripMap.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }

    /**
     * Saves the trip data to a JSON file.
     * 
     * This method serializes the tripMap into a JSON string
     * and writes it to the file specified by the tripList path.
     * If an error occurs during file writing, it logs a severe error message.
     */
    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(tripList)) {
            gson.toJson(tripMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }
}
