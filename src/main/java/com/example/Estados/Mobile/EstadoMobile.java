package com.example.Estados.Mobile;

import java.util.Map;
import java.util.Scanner;

import com.example.Data.MobileData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;

public class EstadoMobile extends Estado implements IMobile {
    private final String[] audioFormat = {"Speakers", "Earphones"};
    private MobileData mobileData;
    private Map<Integer, String> contactMap;
    private boolean isPhoneConnected = false;
    private Integer currentCallIndex = null;
    private int currentAudioFormat = 0;


    public EstadoMobile() {
        this.mobileData = new MobileData();
        this.contactMap = mobileData.getConctactMap();
    }

    /**
     * Generates a string of the menu options for the mobile state.
     * 
     * @return A string of the menu options.
     */
    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("===== Mobile MENU =====\n");
        menu.append("\n\n");
        menu.append("1. Connect Telephone\n");
        menu.append("2. Disconnect Telephone\n");
        menu.append("3. Show Telephone Contacts\n");
        menu.append("4. Call Contact\n");
        menu.append("5. End Call\n");
        menu.append("6. Switch Audio Format\n");
        menu.append("0. GOTO MENU\n");
        
        return menu.toString();
    }
    @Override
    public Estado transition(int action) {
        switch (action) {
            case 1:
                System.out.println(connectarTelefono());
                return this;
            case 2:
                System.out.println(desconectarTelefono());
                return this;
            case 3: 
                System.out.println(mostrarListaContactos());
                return this;
            case 4:
            //This might not be the "most" efficient approach, but it does the job.  
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the index of the contact to call: ");
                int index = scanner.nextInt();
                System.out.println(llamarContacto(index));
                return this;
            case 5:
                System.out.println(terminarLlamada());
                return this;
            case 6:
                System.out.println(cambiarAuricualares());
                return this;
            case 0:
                return new MenuPrincipal();
            default:
                return this;
        }
        
    }

    @Override
    public String connectarTelefono() {
        isPhoneConnected = true;
        return "Telephone connected.";
    }

    @Override
    public String desconectarTelefono() {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        isPhoneConnected = false;
        return "Telephone disconnected.";
    }

    @Override
    public String llamarContacto(int index) {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        if (!contactMap.containsKey(index)) {
            return "Contact does not exist";
        }
        currentCallIndex = index;
        return "Calling " + contactMap.get(index);
    }

    @Override
    public String mostrarListaContactos() {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        StringBuilder builder = new StringBuilder();
        contactMap.forEach((key, value) -> builder.append(key).append(" : ").append(value).append("\n"));
        return builder.toString();
    }


    @Override
    public String terminarLlamada() {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        String contact = contactMap.get(currentCallIndex);
        currentCallIndex = null;
        return "Call ended with " + contact;
    }
    
    
    
    @Override
    public String cambiarAuricualares() {
        currentAudioFormat = (currentAudioFormat + 1) % audioFormat.length;
        return "Audio format changed to: " + audioFormat[currentAudioFormat];
    }


    

}