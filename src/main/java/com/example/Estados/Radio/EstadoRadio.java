package com.example.Estados.Radio; 
import java.util.HashMap;
import java.util.List;

import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;

/**
 * This class represents the Radio state.
 * It handles the different stations that the user can listen to.
 * 
 */
public class EstadoRadio extends Estado implements IRadio{
    float station = 90;
    HashMap<String, Float> stationName;


    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Current Station: " + stationName + "\n" + "\n");
        menu.append("1. FM\n");
        menu.append("2. AM\n");
        menu.append("3. Load Favorites \n");
        menu.append("4. Increase + \n"); //remember it an increase by 0.5
        menu.append("5. Decrease - \n"); //remember it an decrease by 0.5
        menu.append("6. New Favorite \n");
        menu.append("0. GOTO MENU \n");
        
        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
        switch (action) {
            case 1:
                System.out.println(cambiarFM());
                return this;
            case 2:
                System.out.println(cambiarAM());
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
                return this;
        }
    }





    
    @Override
    public String cambiarCanalArriba() {
        station = station + 0.5f;
        return "Current Station at: " + station;
    }

    @Override 
    public String cambiarCanalAbajo() {
        station = station - 0.5f;
        return "Current Staton at: " + station;
    }

    @Override
    public String cambiarAM() {
        // Implement the logic for switching to AM
        return "Switched to AM";
    }

    @Override
    public String cambiarFM() {
        // Implement the logic for switching to FM
        return "Switched to FM";
    } 

    @Override
    public String elejirFavoritas() {
        // Implement the logic for selecting favorites
        return "Selected favorites";
    }

    @Override
    public String agregarFavoritas() {
        // Implement the logic for adding a favorite
        return "Added favorite";
    }   

}
    

