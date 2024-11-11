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
 * 
 * 
 */

public class RadioData {
    private static final Logger logger = Logger.getLogger(RadioData.class.getName());
    private final String stationList = "src/main/java/com/example/JSON/stations.json";
    private final String stationFavorite = "src/main/java/com/example/JSON/favoriteradio.json";
    private final Map<Float, String> stationMap = new HashMap<>();
    private final Map<Float, String> favoriteStations = new HashMap<>();

    public RadioData() {
        loadFromJson();
        loadFavoritesFromJson();
    }
    public Map<Float, String> getStationMap() {
        return stationMap;
    }

    public Map<Float, String> getFavoriteStations() {
        return favoriteStations;
    }

    private void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(stationList)) {
            java.lang.reflect.Type type = new TypeToken<Map<Float, String>>() {}.getType();
            Map<Float, String> loadedMap = gson.fromJson(reader, type);
            stationMap.clear();
            stationMap.putAll(loadedMap);
            logger.log(Level.INFO, "Radio stations loaded successfully from: {0}", stationList);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading stations from JSON", e);
        }
    }

    private void loadFavoritesFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(stationFavorite)) {
            java.lang.reflect.Type type = new TypeToken<Map<Float, String>>() {}.getType();
            Map<Float, String> loadedMap = gson.fromJson(reader, type);
            favoriteStations.clear();
            favoriteStations.putAll(loadedMap);
            logger.log(Level.INFO, "Favorite stations loaded from: {0}", stationFavorite);
        } catch (IOException e) {
            logger.log(Level.WARNING, "No favorite stations file found. Starting fresh.");
        }
    }


    public void saveToJson(String stationList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(stationList)) {
            gson.toJson(stationMap, writer);
            logger.log(Level.INFO, "Radio station data saved to: {0}", stationList);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }

    public void loadFromJson(String stationList) {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(stationList)) {
        java.lang.reflect.Type type = new TypeToken<Map<Float, String>>(){}.getType();
        Map<Float, String> loadedMap = gson.fromJson(reader, type);
        stationMap.clear();
        stationMap.putAll(loadedMap);
        logger.log(Level.INFO, "Radio station data loaded from: {0}", stationList);
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error loading data from JSON", e);
    }
    }

    public void saveFavoritesToJson() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(stationFavorite)) {
            gson.toJson(favoriteStations, writer);
            logger.log(Level.INFO, "Favorite stations saved to: {0}", stationFavorite);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving favorite stations to JSON", e);
        }
    }


}


