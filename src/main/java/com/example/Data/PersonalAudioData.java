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

/**
 * This class handles the management of the data for the personal audio interface, including the song list.
 * Note that Logger is just a package that is used to log errors.
 */

public class PersonalAudioData {
    private final String CD_PATH = "src/main/java/com/example/JSON/CDSONGS.json";
    private final String MP3_PATH = "src/main/java/com/example/JSON/MP3SONGS.json";
    private final String SPOTIFY_PATH = "src/main/java/com/example/JSON/SPOTIFYSONGS.json";
    private final Map<Integer, String> cdMap = new HashMap<>();
    private final Map<Integer, String> mp3Map = new HashMap<>();
    private final Map<Integer, String> spotifyMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(PersonalAudioData.class.getName());  

    /**
     * Constructor for the PersonalAudioData class.
     * @param cdMap The map of CD songs.
     * @param mp3Map The map of MP3 songs.
     * @param spotifyMap The map of Spotify songs.
     * @param logger The logger for logging errors.
     */
    public PersonalAudioData() {
        loadFromJson();
    }

    /**
     * Gets the map of CD songs.
     * 
     * @return The map of CD songs. The keys are the song numbers and the values are the song descriptions.
     */
    public Map<Integer, String> getCDMap() {
        return cdMap;    
    }   

    /**
    * Gets the map of MP3 songs.
    * 
    * @return The map of MP3 songs. The keys are the song numbers and the values are the song descriptions.
    */
    public Map<Integer, String> getMP3Map() {
        return mp3Map;
    }   

    /**
     * Gets the map of Spotify songs.
     * 
     * @return The map of Spotify songs. The keys are the song numbers and the values are the song descriptions.
     */
    public Map<Integer, String> getSpotifyMap() {
        return spotifyMap;
    }   
    /**
     * Loads the data from the JSON files into the maps.
     * 
     * This method reads the JSON files specified by the CD_PATH, MP3_PATH, and SPOTIFY_PATH constants,
     * deserializes their contents into three maps of song numbers and descriptions,
     * clears the existing maps, and populates them with the loaded data.
     * 
     * If an error occurs during file reading or JSON parsing, it logs a severe error message.
     */
    public void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CD_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            cdMap.clear();
            cdMap.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
        try (FileReader reader = new FileReader(MP3_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            mp3Map.clear();
            mp3Map.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
        try (FileReader reader = new FileReader(SPOTIFY_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            spotifyMap.clear();
            spotifyMap.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }
    /**
    * Saves the audio data to JSON files.
    * 
    * This method serializes the cdMap, mp3Map, and spotifyMap into JSON strings
    * and writes them to the files specified by the CD_PATH, MP3_PATH, and SPOTIFY_PATH paths.
    * If an error occurs during file writing, it logs a severe error message.
    */
    public void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CD_PATH)) {
            gson.toJson(cdMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
        try (FileWriter writer = new FileWriter(MP3_PATH)) {
            gson.toJson(mp3Map, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
        try (FileWriter writer = new FileWriter(SPOTIFY_PATH)) {
            gson.toJson(spotifyMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }   
}
