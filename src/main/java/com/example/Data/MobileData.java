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


public class MobileData {
    private final String MOBILE_PATH = "src/main/java/com/example/JSON/mobilecontacts.json";
    private final Map<Integer, String> contactMap = new HashMap<>();
    
    private static final Logger logger = Logger.getLogger(PersonalAudioData.class.getName());  

    public MobileData() {
        loadFromJson();
    } 

    public Map<Integer, String> getConctactMap() {
        return contactMap;    
    } 
    
    public void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(MOBILE_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            contactMap.clear();
            contactMap.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }   
    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(MOBILE_PATH)) {
            gson.toJson(contactMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    } 
}
