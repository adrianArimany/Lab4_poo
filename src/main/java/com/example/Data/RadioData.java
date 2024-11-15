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
 * This class handles the management of the data for the radio interface, including the station list and favorite stations.
 * Note that Logger is just a package that is used to log errors.
 * 
 */

public class RadioData {
    private static final Logger logger = Logger.getLogger(RadioData.class.getName());
    private final String stationList = "src/main/java/com/example/JSON/stations.json";
    private final String stationFavorite = "src/main/java/com/example/JSON/favoriteradio.json";
    private final Map<Float, String> stationMap = new HashMap<>();
    private final Map<Float, String> favoriteStations = new HashMap<>();

    /**
     * Constructor for the RadioData class.
     * @param stationList The path to the JSON file containing the list of radio stations.
     * @param stationFavorite The path to the JSON file containing the list of favorite radio stations.
     * @param logger The logger used for logging errors.
     * @param stationMap The map of radio stations.
     * @param favoriteStations The map of favorite radio stations.
     */
    public RadioData() {
        loadFromJson();
        loadFavoritesFromJson();
    }
    /**
    * Retrieves the map of radio stations.
    * 
    * @return a map where the keys are the station frequencies 
    *         and the values are the station names.
    */
    public Map<Float, String> getStationMap() {
        return stationMap;
    }

    /**
     * Retrieves the map of favorite radio stations.
     * 
     * @return a map where the keys are the station frequencies 
     *         and the values are the station names.
     */
    public Map<Float, String> getFavoriteStations() {
        return favoriteStations;
    }

    /**
     * Loads the radio stations from the JSON file specified by the stationList path.
     * 
     * If the file is read successfully, it clears the existing stationMap and populates it
     * with the loaded data. If an error occurs during file reading or JSON parsing, it 
     * logs a severe error message.
     */
    private void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(stationList)) {
            java.lang.reflect.Type type = new TypeToken<Map<Float, String>>() {}.getType();
            Map<Float, String> loadedMap = gson.fromJson(reader, type);
            stationMap.clear();
            stationMap.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading stations from JSON", e);
        }
    }

    /**
    * Loads the favorite radio stations from the JSON file specified by the stationFavorite path.
    * 
    * This method reads the JSON file, deserializes its contents into a Map of station frequencies 
    * and names, clears the existing favoriteStations map, and populates it with the loaded data.
    * If the file is not found, it logs a warning and initializes an empty favoriteStations map.
    */
    public void loadFavoritesFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(stationFavorite)) {
            java.lang.reflect.Type type = new TypeToken<Map<Float, String>>() {}.getType();
            Map<Float, String> loadedMap = gson.fromJson(reader, type);
            favoriteStations.clear();
            favoriteStations.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.WARNING, "No favorite stations file found. Starting fresh.");
        }
    }


    /**
     * Saves the radio station data to the specified JSON file.
     * 
     * This method serializes the stationMap into a JSON string
     * and writes it to the file specified by the stationList path.
     * If an error occurs during file writing, it logs a severe error message.
     * 
     * @param stationList the path to the JSON file where the data should be saved
     */
    public void saveToJson(String stationList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(stationList)) {
            gson.toJson(stationMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }

    /**
    * Loads radio station data from a JSON file into the stationMap.
    * 
    * This method reads the JSON file specified by the given stationList path,
    * deserializes its contents into a Map of station frequencies and names,
    * clears the existing stationMap, and populates it with the loaded data.
    * 
    * If an error occurs during file reading or JSON parsing, it logs a severe error message.
    * 
    * @param stationList the path to the JSON file containing the station data
    */
    public void loadFromJson(String stationList) {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(stationList)) {
        java.lang.reflect.Type type = new TypeToken<Map<Float, String>>(){}.getType();
        Map<Float, String> loadedMap = gson.fromJson(reader, type);
        stationMap.clear();
        stationMap.putAll(loadedMap);
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error loading data from JSON", e);
    }
    }

    /**
     * Saves the favorite radio stations to a JSON file specified by the stationFavorite path.
     * 
     * This method serializes the favoriteStations map into a JSON string
     * and writes it to the file specified by the stationFavorite path.
     * If an error occurs during file writing, it logs a severe error message.
     * 
     * @see #loadFavoritesFromJson()
     */
    public void saveFavoritesToJson() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(stationFavorite)) {
            gson.toJson(favoriteStations, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving favorite stations to JSON", e);
        }
    }


}


