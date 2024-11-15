package com.example.Estados.Mobile;

public interface IMobile {
    public String connectarTelefono(); //Connects the phone
    public String desconectarTelefono(); //Disconnects the phone
    public String llamarContacto(int index);  //Calls a contact
    public String mostrarListaContactos(); // Shows the list of contacts
    public String terminarLlamada(); //Ends the call
    public String cambiarAuricualares(); //Changes the audio format
    
    
}