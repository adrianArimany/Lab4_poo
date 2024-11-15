package com.example;
import java.util.Scanner;

import com.example.Estados.ManejadorDeEstados;

/**
 * @Title LAB4 - Polimorfismo a través de Interfaces
 * @version 1.0
 * @Author Adrian Arimany
 * @Date 14/12/2024
 * 
 */
public class App {
    private static boolean running = true; 

/**
 * The main method is the entry point of the application, responsible for managing
 * the system's state transitions. It initializes the state manager and a scanner
 * for user input. The application continuously checks if the system is on and
 * displays the current menu, allowing the user to input actions to transition
 * between states. If the system is off, it prompts the user to turn it on.
 * Handles invalid input by catching NumberFormatException.
 */
    public static void main(String[] args) {
        ManejadorDeEstados mEstados = new ManejadorDeEstados();
        Scanner sc = new Scanner(System.in);

        try {
            while (running) {
                if (mEstados.isSystemOn()) {
                    try {
                        System.out.println(mEstados.showMenu());
                        String input = sc.nextLine();
                        int action = Integer.parseInt(input);
                        mEstados.transition(action);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: You must enter a valid integer.");
                    }
                } else {
                    System.out.println("Press any key to turn on the system.");
                    sc.nextLine();
                    mEstados.setSystemOn(true);
                }
            }
        } finally {
            sc.close();
        }
    }
}
