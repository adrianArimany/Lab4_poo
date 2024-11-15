package com.example.Estados.Climatizacion;

import java.util.Map;

import com.example.Data.TemperatueData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;

/**
 * This class represents the climatization state of the system.
 * It controls the air conditioner of the car.
 * 
 */
public class EstadoClimatizacion extends Estado implements IClimatizacion {
    private final String[] configAC = {"Off", "On"};
    private boolean isACOn = false;
    private int currentAC = 0; //starts as off
    private int currentTemperature = 6; //set defualt Air conditioner to 0 Celcius.
    private TemperatueData temperatueData;
    private Map<Integer, String> temperatureMap; 
    
    /**
     * Constructor for the climatization state.
     * @param configAC an array of strings representing the available options for the air conditioner.
     * @param isACOn a boolean value indicating whether the air conditioner is currently on or off.
     * @param currentAC an integer representing the current index of the air conditioner in the configAC array.
     * @param currentTemperature an integer representing the current temperature of the car.
     * @param temperatueData an instance of the TemperatueData class representing the available temperatures.
     * @param temperatureMap a map of integers to strings representing the available temperatures.
     */
    public EstadoClimatizacion() {
        this.temperatueData = new TemperatueData();
        this.temperatureMap = temperatueData.getTemperatureMap();
    }

    /**
     * Generates a string of the menu options for the ventilation system state.
     * 
     * @return A string of the menu options.
     */
    @Override
    public String showMenu() {
            StringBuilder menu = new StringBuilder();
            menu.append("===== Vitilation System menu =====\n");
            menu.append("\n");
            menu.append("AC is currently: " + configAC[currentAC] + "\n");
            menu.append("\n");
            menu.append("1. Switch AC: On or Off\n");
            menu.append("2. Increase Temperature\n");
            menu.append("3. Decreate Temperature\n");
            menu.append("0. GOTO MENU\n");
            
            return menu.toString();
    }

    /**
     * Handles the transition of the climatization state based on the given action.
     * 
     * @param action the action to be performed, which determines the transition.
     *               Possible values are:
     *               1 - Switch the AC on or off.
     *               2 - Increase the temperature by 1 degree.
     *               3 - Decrease the temperature by 1 degree.
     *               0 - Return to the main menu.
     * 
     * @return the new state after performing the action. If the action is invalid,
     *         it will print an error message and remain in the current state.
     */
    @Override
    public Estado transition(int action) {
        switch (action) {
            case 1:
                System.out.println(switchAC());
                return this;
            case 2:
                System.out.println(increaseTemperature());
                return this;
            case 3:
                System.out.println(decreaseTemperature());
                return this;
            case 0:
                return new MenuPrincipal();
            default:
                System.out.println("Invalid action. Please try again.");
                return this;
        }
    }



    /**
     * Switches the AC on or off.
     * 
     * If the AC is currently on, it will switch it off, and vice versa.
     * 
     * @return a message indicating the result of the action.
     */
    @Override
    public String switchAC() {
        currentAC = (currentAC + 1) % configAC.length;
        if (configAC[currentAC].equals("On")) {
            isACOn = true;
        } else {
            isACOn = false;
        }
        return "AC switched to: " + configAC[currentAC];
    }
    
    /**
     * Increases the current temperature by 1 degree.
     * 
     * If the AC is not turned on, it will return a message indicating that.
     * If the maximum temperature is reached, it will return a message indicating that.
     * Otherwise, it will return a message indicating the new temperature.
     * 
     * @return a message indicating the result of the action.
     */
    @Override
    public String increaseTemperature() {
        if (!isACOn) {
            return "AC is off";
        }
        if (temperatureMap.containsKey(currentTemperature + 1)) {
            currentTemperature++;
            return " Temperature set at: " + temperatureMap.get(currentTemperature);
        } else {
            return " Maximum temeperature increased";
        }
    }
    
    /**
    * Decreases the current temperature by 1 degree.
    * 
    * If the AC is not turned on, it will return a message indicating that.
    * If the minimum temperature is reached, it will return a message indicating that.
    * Otherwise, it will return a message indicating the new temperature.
    * 
    * @return a message indicating the result of the action.
    */
    @Override
    public String decreaseTemperature() {
        if (!isACOn) {
            return "AC is off";
        }
        if (temperatureMap.containsKey(currentTemperature - 1)) {
            currentTemperature--;
            return " Temperature set at: " + temperatureMap.get(currentTemperature);
        } else {
            return " Minimum temeperature increased";
        }
    }
}
