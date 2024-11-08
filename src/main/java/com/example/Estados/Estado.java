package com.example.Estados;

public abstract class Estado {
    public String showMenu();
    public Estado transition(int action);
    
}