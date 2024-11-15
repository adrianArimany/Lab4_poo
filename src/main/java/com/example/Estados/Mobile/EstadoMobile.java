package com.example.Estados.Mobile;

import java.util.Map;
import java.util.Scanner;

import com.example.Data.MobileData;
import com.example.Estados.Estado;
import com.example.Estados.MenuPrincipal.MenuPrincipal;
/**
 * This class represents the mobile state of the system.
 * It handles the different actions that the user can perform. 
 * 
 * @note the user has to connect the phone before using any other method, (this will be propmpted in the program.)
 * 
 * @implNote This class implements the IMobile interface.
 */
public class EstadoMobile extends Estado implements IMobile {
    private final String[] audioFormat = {"Speakers", "Earphones"};
    private MobileData mobileData;
    private Map<Integer, String> contactMap;
    private boolean isPhoneConnected = false;
    private Integer currentCallIndex = null;
    private int currentAudioFormat = 0; //This would be 0 for speakers and 1 for earphones

    /**
     * Constructor for the Mobile state.
     * @param mobileData The data for the mobile state.
     * @param contactMap The map of contacts.
     * @param isPhoneConnected Whether the phone is connected.
     * @param currentCallIndex The index of the current call.
     * @param currentAudioFormat The current audio format.
     */
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
    /**
    * Handles the transition of the mobile state based on the given action.
    * 
    * @param action the action to be performed, which determines the transition.
    *               Possible values are:
    *               1 - Connect the phone,
    *               2 - Disconnect the phone,
    *               3 - Show the list of contacts,
    *               4 - Call a contact by selecting the index,
    *               5 - End the current call,
    *               6 - Switch the audio format,
    *               0 - Return to the main menu.
    * 
    * @return the current state after performing the action. If the action is invalid,
    *         it will print an error message and remain in the current state.
    */
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

    /**
     * Connects the phone. 
     * This method will return a message indicating that the phone has been connected.
     * @return A message indicating that the phone has been connected.
     */
    @Override
    public String connectarTelefono() {
        isPhoneConnected = true;
        return "Telephone connected.";
    }

    /**
    * Disconnects the phone.
    * 
    * If the phone is not connected, it returns a message indicating so.
    * Otherwise, it disconnects the phone and returns a message indicating
    * that the phone has been disconnected.
    * 
    * @return A message indicating the result of the disconnection attempt.
    */
    @Override
    public String desconectarTelefono() {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        isPhoneConnected = false;
        return "Telephone disconnected.";
    }

    /**
     * Calls a contact based on the given index.
     * 
     * If the phone is not connected, it returns a message indicating so.
     * If the contact does not exist, it returns a message indicating that.
     * Otherwise, it sets the current call to the given index and returns a message
     * indicating that the call has been initiated.
     * 
     * @param index the index of the contact to call.
     * @return A message indicating the result of the call attempt.
     */
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

    /**
    * Displays the list of contacts available on the mobile device.
    * 
    * If the phone is not connected, it returns a message indicating so.
    * Otherwise, it builds a string representation of the contact list,
    * where each contact is displayed with its index and details.
    * 
    * @return A message indicating the phone connection status or a string
    *         representation of the contact list.
    */
    @Override
    public String mostrarListaContactos() {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        StringBuilder builder = new StringBuilder();
        contactMap.forEach((key, value) -> builder.append(key).append(" : ").append(value).append("\n"));
        return builder.toString();
    }


    /**
     * Ends the current call if there is one.
     * 
     * If the phone is not connected, it returns a message indicating so.
     * Otherwise, it ends the current call and returns a message indicating
     * that the call has been ended with the contact.
     * 
     * @return A message indicating the result of the call termination.
     */
    @Override
    public String terminarLlamada() {
        if (!isPhoneConnected) {
            return "No phone connected.";
        }
        String contact = contactMap.get(currentCallIndex);
        currentCallIndex = null;
        return "Call ended with " + contact;
    }
    
    
    
    /**
     * Changes the audio format of the phone.
     * 
     * This method cycles through the available audio formats (from the
     * {@code audioFormat} array) and returns a message indicating the new
     * audio format.
     * 
     * @return A message indicating the new audio format.
     */
    @Override
    public String cambiarAuricualares() {
        currentAudioFormat = (currentAudioFormat + 1) % audioFormat.length;
        return "Audio format changed to: " + audioFormat[currentAudioFormat];
    }


    

}