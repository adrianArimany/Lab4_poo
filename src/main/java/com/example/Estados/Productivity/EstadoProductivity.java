package com.example.Estados.Productivity;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.example.Data.TripData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;
/**
 * The Productivity class handles the state of the system for productivity mode.
 * It provides options to start a new trip or return to the main menu.
 * @apiNote This class implements the IProductivity interface.
 */
public class EstadoProductivity extends Estado implements IProductivity {
    private TripData tripData;
    private Map<Integer, String> tripMap;
    private String currentTrip; 

    /**
     * @param tripData is an instance of TripData class.
     * @param tripMap looks for the trip from the tripData.
     * @param currentTrip is the current trip the user is on.
     */
    public EstadoProductivity() {
        this.tripData = new TripData();
        this.tripMap = tripData.getTripMap();
        this.currentTrip = "No trip selected"; //the dafult trip.
    }

    /**
    * Generates a string of the menu options for the productivity state.
    * 
    * Displays the current trip and provides options to start a new trip
    * or return to the main menu.
    * 
    * @return A string of the menu options.
    */
    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("===== Productivity MENU =====\n");
        menu.append("Current Trip: ").append(currentTrip).append("\n\n");
        menu.append("1. Start Trip\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }

    /**
     * Handles the transition of the productivity state based on the given action.
     * 
     * @param action the action to be performed, which determines the transition.
     *               Possible values are:
     *               1 - Show the list of trips and ask the user to select one by index.
     *               0 - Return to the main menu.
     * 
     * @return the current state after performing the action. If the action is invalid,
     *         it will print an error message and remain in the current state.
     */
    @Override
    public Estado transition(int action) {
        try {
            switch (action) {
                case 1:
                    if (tripMap == null) {
                        throw new NullPointerException("Enter  a valid trip index");
                    }
                    for (Map.Entry<Integer, String> entry : tripMap.entrySet()) {
                        System.out.println("Index " + entry.getKey() + ": " + entry.getValue());
                    }
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the index to start the trip: \n");
                    int index = scanner.nextInt();
                    System.out.println(startTrip(index));
                    return this;
                case 0:
                    return new MenuPrincipal();
                default:
                    return this;
            }
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
            return this;
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException: " + e.getMessage());
            return this;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return this;
        }
    }

    /**
    * Starts a trip by selecting the destination based on the provided index.
    *
    * If the specified index does not exist in the trip map, it returns an error message.
    * Otherwise, it updates the current trip to the destination associated with the index
    * and confirms the addition of the destination.
    *
    * @param index the index of the trip to start.
    * @return a message indicating the result of the operation.
    */
    @Override
    public String startTrip(int index) {
        if (!tripMap.containsKey(index)) {
            return "Trip does not exist";
        }
        currentTrip = tripMap.get(index);
        return "destination added.";
    }
    
}
