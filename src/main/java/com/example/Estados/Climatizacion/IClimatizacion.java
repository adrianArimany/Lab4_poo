package com.example.Estados.Climatizacion;
/**
 * Interface for the EstadoClimatizacion
 */
public interface IClimatizacion {
    public String switchAC(); //Switches the AC on or off, returning a string indicating the current state of the AC
    public String increaseTemperature(); //Increases the temperature by 1 degree, returning a string indicating the new temperature
    public String decreaseTemperature(); //Decreases the temperature by 1 degree, returning a string indicating the new temperature
    
} 