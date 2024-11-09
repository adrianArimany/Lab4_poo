package com.example;
import java.util.Scanner;
import com.example.Estados.ManejadorDeEstados;

/**
 * This is LAB -4 _ Adrian Arimany - 211063
 * 
 *
 */
public class App {

    public static void main(String[] args) {
        ManejadorDeEstados mEstados = new ManejadorDeEstados();
        Scanner sc = new Scanner(System.in);
        try {
        while (true) {
            if (mEstados.isSystemOn()) {
                System.out.println(mEstados.showMenu());
                int action = Integer.parseInt(sc.nextLine());
                mEstados.transition(action);
            } else {
                System.out.println("System is off, want to turn it back on? (1-yes, 0-no)");
                int action = Integer.parseInt(sc.nextLine());
                if (action == 1) {
                    mEstados.setSystemOn(true);
                }
            }
        }
    } finally {
        sc.close();
    }

}
}