package com.example.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.FileWriter;


public class PersonalAudioData {
    private final String CD_PATH = "src/main/java/com/example/JSON/CDSONGS.json";
    private final String MP3_PATH = "src/main/java/com/example/JSON/MP3SONGS.json";
    private final String SPOTIFY_PATH = "src/main/java/com/example/JSON/SPOTIFYSONGS.json";
    private final Map<Integer, String> cdMap = new HashMap<>();
    private final Map<Integer, String> mp3Map = new HashMap<>();
    private final Map<Integer, String> spotifyMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(PersonalAudioData.class.getName());  

    public PersonalAudioData() {
        loadFromJson();
    }

    public Map<Integer, String> getCDMap() {
        return cdMap;    
    }   

    public Map<Integer, String> getMP3Map() {
        return mp3Map;
    }   

    public Map<Integer, String> getSpotifyMap() {
        return spotifyMap;
    }   
    public void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CD_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            cdMap.clear();
            cdMap.putAll(loadedMap);
            logger.log(Level.INFO, "CD song data loaded from: {0}", CD_PATH);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
        try (FileReader reader = new FileReader(MP3_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            mp3Map.clear();
            mp3Map.putAll(loadedMap);
            logger.log(Level.INFO, "MP3 song data loaded from: {0}", MP3_PATH);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
        try (FileReader reader = new FileReader(SPOTIFY_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            spotifyMap.clear();
            spotifyMap.putAll(loadedMap);
            logger.log(Level.INFO, "Spotify song data loaded from: {0}", SPOTIFY_PATH);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }
    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CD_PATH)) {
            gson.toJson(cdMap, writer);
            logger.log(Level.INFO, "CD song data saved to: {0}", CD_PATH);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
        try (FileWriter writer = new FileWriter(MP3_PATH)) {
            gson.toJson(mp3Map, writer);
            logger.log(Level.INFO, "MP3 song data saved to: {0}", MP3_PATH);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
        try (FileWriter writer = new FileWriter(SPOTIFY_PATH)) {
            gson.toJson(spotifyMap, writer);
            logger.log(Level.INFO, "Spotify song data saved to: {0}", SPOTIFY_PATH);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }   
}
