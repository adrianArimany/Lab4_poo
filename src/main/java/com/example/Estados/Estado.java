package com.example.Estados;

/**
 * This class represents the state of the system.
 * It handles the different actions that the user can perform.
 * This a Super class.
 */
public abstract class Estado {
    public abstract String showMenu();
    public abstract Estado transition(int action);
    
}