package com.example.Estados.MenuPrincipal;

import com.example.Estados.Estado;
import com.example.Estados.Climatizacion.EstadoClimatizacion;
import com.example.Estados.Mobile.EstadoMobile;
import com.example.Estados.PersonalAudio.EstadoPersonalAudio;
import com.example.Estados.Productivity.EstadoProductivity;
import com.example.Estados.Radio.EstadoRadio;

/**
 * The MenuPrincipal class represents the main menu of the system.
 * It provides options for Mobile, Radio, Personal Audio, Productivity, and Sleep mode.
 * 
 */
public class MenuPrincipal extends Estado {

    /**
    * Generates a string of the menu options for the main menu.
    * 
    * @return A string of the menu options, including options for Mobile,
    * Radio, Personal Audio, Productivity, and Sleep mode.
    */
    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Mobile\n");
        menu.append("2. Radio\n");
        menu.append("3. Personal Audio\n");
        menu.append("4. Productivity\n");
        menu.append("5. Change Climatization from car\n");
        menu.append("-1. Sleep mode (Press at any time) \n");
        
        return menu.toString();
    }

    /**
     * Handles the transition of the main menu based on the given action.
     * 
     * @param action the action to be performed, which determines the transition.
     *               Possible values are:
     *               1 - Transition to mobile mode.
     *               2 - Transition to radio mode.
     *               3 - Transition to personal audio mode.
     *               4 - Transition to productivity mode.
     *               -1 - Transition to sleep mode. This can be done at any time.
     * 
     * @return the new state after performing the action. If the action is invalid,
     *         it will print an error message and remain in the current state.
     */
    @Override
    public Estado transition(int action) {
       switch (action) {
        case 1:
            return new EstadoMobile();
        case 2:
            return new EstadoRadio();
        case 3:
            return new EstadoPersonalAudio();
        case 4:
            return new EstadoProductivity();
        case 5:
            return new EstadoClimatizacion();
        default:
            return this;
       }
    }
}