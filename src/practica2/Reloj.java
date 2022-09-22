/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2;

import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.PantallaJuego;

/**
 *
 * @author Luisda
 */
public class Reloj extends Thread {

    int display[] = new int[3];
    String displayString[] = new String[3];
    

    @Override
    public void run() {
        System.out.println("inicia reloj");
        display[1] = 0;
        display[2] = 0;
        while (PantallaJuego.INICIORELOJ) {
            if (display[0] == 60) {
                display[0] = 0;
                display[1]++;
            }
            if (display[1] == 60) {
                display[1] = 0;
                display[2]++;
            }

            if (display[0] < 10) {
                displayString[0] = "0" + String.valueOf(display[0]);
            } else {
                displayString[0] = String.valueOf(display[0]);
            }

            if (display[1] < 10) {
                displayString[1] = "0" + String.valueOf(display[1]) + ":";
            } else {
                displayString[1] = String.valueOf(display[1]) + ":";
            }

            if (display[2] < 10) {
                displayString[2] = "0" + String.valueOf(display[2]) + ":";
            } else {
                displayString[2] = String.valueOf(display[2]) + ":";
            }

            PantallaJuego.PonerTexto(displayString[2] + displayString[1] + displayString[0]);
            PantallaJuego.RelojParaReporte=displayString[2] + displayString[1] + displayString[0];

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            display[0]++;
            
        }
        System.out.println("Termina reloj");

    }

}
