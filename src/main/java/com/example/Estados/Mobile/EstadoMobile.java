package com.example.Estados.Mobile;

import com.example.Estados.Estado;

public class EstadoMobile extends Estado {
    
    /**
     * Generates a string of the menu options for the mobile state.
     * 
     * @return A string of the menu options.
     */
    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Connect Telephone\n");
        menu.append("2. Disconnect Telephone\n");
        menu.append("3. Show Telephone Contacts\n");
        menu.append("4. Call Contact\n");
        menu.append("5. End Call\n");
        menu.append("6. Switch Speakers\n");
        menu.append("7. Switch Headphones\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }
    @Override
    public Estado transition(int action) {
        return null;
        
    }
}