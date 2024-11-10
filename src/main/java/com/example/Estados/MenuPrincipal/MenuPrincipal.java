package com.example.Estados.MenuPrincipal;

import com.example.Estados.Estado;
import com.example.Estados.Mobile.EstadoMobile;
import com.example.Estados.PersonalAudio.EstadoPersonalAudio;
import com.example.Estados.Productivity.EstadoProductivity;
import com.example.Estados.Radio.EstadoRadio;

public class MenuPrincipal extends Estado {

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Mobile\n");
        menu.append("2. Radio\n");
        menu.append("3. Personal Audio\n");
        menu.append("4. Productivity\n");
        menu.append("-1. Sleep mode (Press at any time) \n");
        
        return menu.toString();
    }

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
        default:
            return this;
       }
    }
}