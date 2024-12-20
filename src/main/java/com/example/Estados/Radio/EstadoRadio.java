package com.example.Estados.Radio; 
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.example.Data.RadioData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;

/**
 * This class represents the Radio state.
 * It handles the different stations that the user can listen to.
 * @apiNote This class implements the IRadio interface.
 */
public class EstadoRadio extends Estado implements IRadio{
    private float station = 90.0f; // Default station
    private final RadioData radioData; 
    private final Map<Float, String> stationMap;
    private final Map<Float, String> favoriteStations;    

    /**
     * Constructor for the Radio state.
     * @param station The current station of the radio.
     * @param radioData The data manager for the radio interface.
     * @param stationMap The map of available stations.
     * @param favoriteStations The map of favorite stations.
     */
    public EstadoRadio() {
        this.radioData = new RadioData();
        this.stationMap = radioData.getStationMap();
        this.favoriteStations = radioData.getFavoriteStations();
    }

    /**
     * This method generates a string of the menu options for the radio state.
     * 
     * @return A string of the menu options.
     */
    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("===== RADIO MENU =====\n");
        menu.append("Current Station: ").append(station).append(" - ").append(stationMap.getOrDefault(station, "Unknown")).append("\n\n");
        menu.append("Options:\n");
        menu.append("1. FM\n");
        menu.append("2. AM\n");
        menu.append("3. Load Favorites \n");
        menu.append("4. Increase Frecuency (+0.5)\n"); //remember it an increase by 0.5
        menu.append("5. Decrease Frecuency (-0.5)\n"); //remember it an decrease by 0.5
        menu.append("6. New Favorite \n");
        menu.append("0. Return to Main Menu \n");
        
        return menu.toString();
    }

/**
 * Handles the transition of the radio state based on the given action.
 * 
 * @param action the action to be performed, which determines the transition.
 *               Possible values are:
 *               1 - Switch to FM,
 *               2 - Switch to AM,
 *               3 - Load favorite stations,
 *               4 - Increase frequency by 0.5,
 *               5 - Decrease frequency by 0.5,
 *               6 - Add current station to favorites,
 *               0 - Return to the main menu.
 * 
 * @return the current state after performing the action. If the action is invalid,
 *         it will print an error message and remain in the current state.
 * 
 * @throws InputMismatchException if the input is not a valid number.
 * @throws Exception for any unexpected errors that occur during the transition.
 */
    @Override
    public Estado transition(int action) {
        do {
            try {
        switch (action) {
            case 1:
                System.out.println(cambiarFM());
                return this;
            case 2:
                System.out.println(cambiarAM());
                return this;
            case 3:
                System.out.println(elejirFavoritas());
                return this;
            case 4:
                System.out.println(cambiarCanalArriba());
                return this;
            case 5:
                System.out.println(cambiarCanalAbajo());
                return this;
            case 6:
                System.out.println(agregarFavoritas());
                return this;
            case 0:
                return new MenuPrincipal();
                    
            default:
                System.out.println("Invalid option. Try again.");
                return this;
            }
            } catch (InputMismatchException e) {
                System.out.println("Error: must enter a valid number.");
                return this;   
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage()); 
                return this;
            }
        } while (action != 0);
    }





    
    /**
     * Increases the current station frequency by 0.5.
     * 
     * If a station is available at the new frequency, it will switch to that station.
     * Otherwise, it will print an error message and remain in the current station.
     * 
     * @return a message indicating the result of the action.
     */
    @Override
    public String cambiarCanalArriba() {
        float newStation = station + 0.5f;
        if (stationMap.containsKey(newStation)) {
            station = newStation;
            return "Changed to Station: " + station + " - " + stationMap.get(station);
        } else {
            return "No station available at: " + newStation;
        }
    }

    /**
     * Decreases the current station frequency by 0.5.
     * 
     * If a station is available at the new frequency, it will switch to that station.
     * Otherwise, it will print an error message and remain in the current station.
     * 
     * @return a message indicating the result of the action.
     */
    @Override
    public String cambiarCanalAbajo() {
        float newStation = station - 0.5f;
        if (stationMap.containsKey(newStation)) {
            station = newStation;
            return "Changed to Station: " + station + " - " + stationMap.get(station);
        } else {
            return "No station available at: " + newStation;
        }
    }

    /**
     * Switches the radio to the AM frequency band.
     * 
     * @return a message indicating that the radio has been switched to AM.
     */
    @Override
    public String cambiarAM() {
        return "Switched to AM";
    }

    /**
    * Switches the radio to the FM frequency band.
    * 
    * @return a message indicating that the radio has been switched to FM.
    */
    @Override
    public String cambiarFM() {
        return "Switched to FM";
    } 

    /**
     * Allows the user to select a favorite station from their list of favorites.
     * 
     * If the user does not have any favorite stations, it will print an error message.
     * 
     * Otherwise, it will show a list of the user's favorite stations and prompt the user
     * to select one. It will then switch the radio to the selected station.
     * 
     * @return a message indicating the result of the action.
     */
    @Override
    public String elejirFavoritas() {
        if (favoriteStations == null || favoriteStations.isEmpty()) {
            return "No favorite stations available. Add some first!";
        }

        System.out.println("Select a favorite station:");
        int index = 1;
        for (Map.Entry<Float, String> entry : favoriteStations.entrySet()) {
            System.out.println(index + ". " + entry.getKey() + " - " + entry.getValue());
            index++;
        }

        @SuppressWarnings("resource") 
        //If the scanner is closed then the program will never be able to detect this input; so the easiest approach it to suppress the warning
        //I agree this is a terrible implementation, and it can lead to serious consequences, but for this exercise it will do.
        //By the way, I did try passing the scanner as a parameter, but it gave me a list of errors. 
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter your choice: ");
            if (!scanner.hasNextLine()) {
                return "No input available.";
            }
            String input = scanner.nextLine().trim();
            int choice = Integer.parseInt(input);
            if (choice < 1 || choice > favoriteStations.size()) {
                return "Invalid selection.";
            }
            Float[] keys = favoriteStations.keySet().toArray(new Float[0]);
            float selectedFrequency = keys[choice - 1];
            station = selectedFrequency;
            return "Switched to favorite station: " + station + " - " + favoriteStations.get(station);
        } catch (NumberFormatException e) {
            return "Invalid input. Please enter a valid number.";
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        } 
    }

    /**
     * Adds the current station to the user's list of favorite stations.
     * 
     * If the user already has 50 favorite stations, it will print an error message.
     * 
     * If the current station is already in the user's list of favorite stations, it
     * will print an error message.
     * 
     * Otherwise, it will add the current station to the user's list of favorite stations
     * and save the changes to the JSON file.
     * 
     * @return a message indicating the result of the action.
     */
    @Override
    public String agregarFavoritas() {
        if (favoriteStations.size() >= 50) {
            return "Favorites limit reached! Cannot add more stations.";
        }
        if (favoriteStations.containsKey(station)) {
            return "Station " + station + " is already in favorites.";
        }
        favoriteStations.put(station, stationMap.get(station));
        radioData.saveFavoritesToJson();
        return "Added station " + station + " - " + stationMap.get(station) + " to favorites.";
    }

}
    

