package com.example.Estados.Productivity;
import java.util.Map;
import java.util.Scanner;

import com.example.Data.TripData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;

public class EstadoProductivity extends Estado implements IProductivity {
    private TripData tripData;
    private Map<Integer, String> tripMap;
    private String currentTrip;

    public EstadoProductivity() {
        this.tripData = new TripData();
        this.tripMap = tripData.getTripMap();
        this.currentTrip = "No trip selected";
    }

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("===== Productivity MENU =====\n");
        menu.append("Current Trip: ").append(currentTrip).append("\n\n");
        menu.append("1. Start Trip\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
        switch (action) {
            case 1:
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
    }

    @Override
    public String startTrip(int index) {
        if (!tripMap.containsKey(index)) {
            return "Trip does not exist";
        }
        currentTrip = tripMap.get(index);
        return "destination added.";
    }
    
}
