package com.example.Estados.Productivity;
import com.example.Estados.Estado;

public class EstadoProductivity extends Estado implements IProductivity {

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Start Trip\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transition'");
    }

    @Override
    public String startTrip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startTrip'");
    }
    
}
