package com.example.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.logging.Level;
import java.io.FileReader;
import java.io.FileWriter;

public class TemperatueData {
    private final String TEMPERATURE_PATH = "src/main/java/com/example/JSON/temperatures.json";
    private final Map<Integer, String> temperatureMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(TemperatueData.class.getName());
    public TemperatueData() {
        loadFromJson();
    }
    public Map<Integer, String> getTemperatureMap() {
        return temperatureMap;
    }
    
    public void loadFromJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader(TEMPERATURE_PATH)) {
            Map<Integer, String> deserializedMap = gson.fromJson(reader, new TypeToken<HashMap<Integer, String>>() {}.getType());
            temperatureMap.clear();
            temperatureMap.putAll(deserializedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }

    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(TEMPERATURE_PATH)) {
            gson.toJson(temperatureMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }
}
