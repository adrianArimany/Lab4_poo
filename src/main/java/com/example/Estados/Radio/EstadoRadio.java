package com.example.Estados.Radio; 
import com.example.Estados.Estado;

/**
 * This class represents the Radio state.
 * It handles the different stations that the user can listen to.
 * 
 */
public class EstadoRadio extends Estado implements IRadio{

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
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
        // Implement the logic for the transition method here
        return null; // You should return the next state based on the action
    }





    
    @Override
    public String cambiarCanalArriba() {
        // Implement the logic for changing the channel up
        return "Channel changed up";
    }

    @Override 
    public String cambiarCanalAbajo() {
        // Implement the logic for changing the channel down
        return "Channel changed down";
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
    

