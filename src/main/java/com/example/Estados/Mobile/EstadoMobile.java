package com.example.Estados.Mobile;

import com.example.Estados.Estado;

public class EstadoMobile extends Estado implements IMobile {
    
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
    @Override
    public String llamarContacto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'llamarContacto'");
    }
    @Override
    public String mostrarListaContactos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarListaContactos'");
    }
    @Override
    public String terminarLlamada() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'terminarLlamada'");
    }
    @Override
    public String cambiarAuricualares() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarAuricualares'");
    }
    @Override
    public String epezarLlamada() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'epezarLlamada'");
    }
}