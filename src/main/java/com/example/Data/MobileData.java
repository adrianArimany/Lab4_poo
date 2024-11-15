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

/**
 * This class handles the management of the data for the mobile interface, including the contact list.
 * Note that Logger is just a package that is used to log errors.
 */
public class MobileData {
    private final String MOBILE_PATH = "src/main/java/com/example/JSON/mobilecontacts.json";
    private final Map<Integer, String> contactMap = new HashMap<>();
    
    private static final Logger logger = Logger.getLogger(PersonalAudioData.class.getName());  

    /**
     * Constructor for the MobileData class.
     * @param conctactMap The map of contacts to be loaded from the JSON file.
     * @param logger The logger to be used for logging errors.
     */
    public MobileData() {
        loadFromJson();
    } 

    /**
     * Returns the map of contacts loaded from the JSON file.
     * 
     * @return a map where the keys are the contact numbers
     *         and the values are the contact details.
     * 
     */
    public Map<Integer, String> getConctactMap() {
        return contactMap;    
    } 
    
    /**
     * Loads the contact data from a JSON file into the contactMap.
     * 
     * This method reads the JSON file specified by the MOBILE_PATH constant,
     * deserializes its contents into a Map of contact numbers and details,
     * clears the existing contactMap, and populates it with the loaded data.
     * 
     * If an error occurs during file reading or JSON parsing, it logs a severe error message.
     */
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
       
    /**
     * Saves the contact data to the JSON file specified by the MOBILE_PATH constant.
     * 
     * This method serializes the contactMap into a JSON string
     * and writes it to the file specified by the MOBILE_PATH constant.
     * If an error occurs during file writing, it logs a severe error message.
     */
    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(MOBILE_PATH)) {
            gson.toJson(contactMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    } 
}
