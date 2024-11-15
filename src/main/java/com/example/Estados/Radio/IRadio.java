package com.example.Estados.Radio;
/**
 * Interface for the EstadoRadio
 */
public interface IRadio {
    public String cambiarAM(); //changes AM
    public String cambiarFM(); //changes FM
    public String cambiarCanalArriba(); //changes station up 
    public String cambiarCanalAbajo(); //changes station down
    public String elejirFavoritas(); //select favorite station
    public String agregarFavoritas(); //add favorite station
}
