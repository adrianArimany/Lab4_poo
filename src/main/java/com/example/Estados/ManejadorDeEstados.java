package com.example.Estados;

import com.example.Estados.MenuPrincipal.MenuPrincipal;

public class ManejadorDeEstados {
    Estado currEstado;
    boolean systemOn = true;
    
    public ManejadorDeEstados() {
        this.currEstado = new MenuPrincipal();
    }

    public String showMenu(){
        return this.currEstado.showMenu();
    }

    public Estado getEstado(){
        return this.currEstado;
    }

    public void transition(int action){
        if (action == -1){
            systemOff();
            return;
        }
        this.currEstado = this.currEstado.transition(action);
    }

    public void systemOff(){
        this.currEstado = new MenuPrincipal();
    }

    public boolean isSystemOn() {
        return systemOn;
    }

    public void setSystemOn(boolean systemOn) {
        this.systemOn = systemOn;
    }

    


}