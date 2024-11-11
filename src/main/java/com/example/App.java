package com.example;
import java.util.Scanner;
import com.example.Estados.ManejadorDeEstados;

/**
 * This is LAB -4 _ Adrian Arimany - 211063
 * 
 *
 */
public class App {
    private static boolean running = true; 

    public static void main(String[] args) {
        ManejadorDeEstados mEstados = new ManejadorDeEstados();
        Scanner sc = new Scanner(System.in);

        try {
            while (running) {
                if (mEstados.isSystemOn()) {
                    try {
                        System.out.println(mEstados.showMenu());
                        String input = sc.nextLine(); // Read input as a string
                        int action = Integer.parseInt(input); // Attempt to parse it
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
