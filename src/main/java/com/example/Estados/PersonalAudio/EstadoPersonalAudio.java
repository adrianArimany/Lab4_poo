package com.example.Estados.PersonalAudio;
import com.example.Estados.Estado;

public class EstadoPersonalAudio extends Estado {

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transition'");
    }
    
}
