package com.example.Estados.Mobile;

public interface IMobile {
    public String connectarTelefono();
    public String desconectarTelefono();
    public String llamarContacto(int index); 
    public String mostrarListaContactos();
    public String terminarLlamada();
    public String cambiarAuricualares();
    
    
}