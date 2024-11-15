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

public class TripData {
    private static final Logger logger = Logger.getLogger(RadioData.class.getName());
    private final String tripList = "src/main/java/com/example/JSON/trips.json";
    private final Map<Integer, String> tripMap = new HashMap<>();
    
    public TripData() {
        loadFromJson();
    }
    
    public Map<Integer, String> getTripMap() {
        return tripMap;
    }
    
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

    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(tripList)) {
            gson.toJson(tripMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }
}
