/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2;

import javax.swing.JOptionPane;
import practica2.PantallaJuego;
import static practica2.PantallaJuego.DatosPantallaCulebra;
import static practica2.PantallaJuego.TextoParaPantalla;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Luisda
 */
public class HiloCulebra extends Thread {

    static int PosicionCabeza[][] = new int[2][1];//DECLARACION de variables para el funcionamiento
    static int Timer[][][] = new int[1][10][10];
    static int SiclosDefault = 1;
    static int VelocidadCulebra = 1000;
    public static boolean Yesono = false;

    static boolean JuegoTerminado = false;//Indica que el juego ha terminado

    public static String[][] MueveCulebra(String Direccion, String[][] DisCul, int[][] Ps) {

        switch (Direccion) {
            case "+x":
                Timer[0][Ps[0][0]][Ps[1][0]] = SiclosDefault;
                PantallaJuego.DatosPantallaCulebra[Ps[0][0] + 1][Ps[1][0]] = "<font style=\"color:green;\">■</font>";
                PosicionCabeza[0][0] = PosicionCabeza[0][0] + 1;

                break;
            case "-x":
                Timer[0][Ps[0][0]][Ps[1][0]] = SiclosDefault;
                PantallaJuego.DatosPantallaCulebra[Ps[0][0] - 1][Ps[1][0]] = "<font style=\"color:green;\">■</font>";
                PosicionCabeza[0][0] = PosicionCabeza[0][0] - 1;

                break;
            case "+y":
                Timer[0][Ps[0][0]][Ps[1][0]] = SiclosDefault;
                PantallaJuego.DatosPantallaCulebra[Ps[0][0]][Ps[1][0] - 1] = "<font style=\"color:green;\">■</font>";
                PosicionCabeza[1][0] = PosicionCabeza[1][0] - 1;

                break;
            case "-y":
                Timer[0][Ps[0][0]][Ps[1][0]] = SiclosDefault;
                PantallaJuego.DatosPantallaCulebra[Ps[0][0]][Ps[1][0] + 1] = "<font style=\"color:green;\">■</font>";
                PosicionCabeza[1][0] = PosicionCabeza[1][0] + 1;

                break;

        }

        return DisCul;
    } //MUEVE culebra un espacio

    public static void CreceCulebra() {
        int ColaX = 0;
        int ColaY = 0;
        int Cola2X = 99;
        int Cola2Y = 99;
        int NColaX = 0;
        int NColaY = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (Timer[0][i][j] == 1) {
                    ColaX = i;
                    ColaY = j;
                }
                if (Timer[0][i][j] == 2) {
                    Cola2X = i;
                    Cola2Y = j;
                }

            }
        }
        if (ColaX == Cola2X) {
            if (Timer[0][Cola2X][Cola2Y] > Timer[0][ColaX][ColaY]) {
                NColaX = ColaX - 1;
                NColaY = ColaY;
                Timer[0][NColaX][NColaY] = 1;
            } else {
                NColaX = ColaX + 1;
                NColaY = ColaY;
                Timer[0][NColaX][NColaY] = 1;
            }
        } else if (ColaY == Cola2Y) {
            if (Timer[0][Cola2X][Cola2Y] > Timer[0][ColaX][ColaY]) {
                NColaY = ColaY - 1;
                NColaX = ColaX;
                Timer[0][NColaX][NColaY] = 1;
            } else {
                NColaY = ColaY + 1;
                NColaX = ColaX;
                Timer[0][NColaX][NColaY] = 1;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (Timer[0][i][j] != 0 && (NColaX != i || NColaY != j)) {
                    Timer[0][i][j]++;
                }
            }
        }

    }

    public static void ReseteaVariables() {
        SiclosDefault = 1;
        VelocidadCulebra = 1000;
        PantallaJuego.ReporteParaArchivo = "";
        PantallaJuego.Movimientos = "<br>";
    }

    public static void llenaReporte() {
        PantallaJuego.ReporteParaArchivo = PantallaJuego.ReporteInicial + "<p><center>" + PantallaJuego.TextoParaPantalla2(DatosPantallaCulebra) + "</center></p>"
                + "<p><b>Nivel de dificultad: </b>" + PantallaJuego.DifEnString + "<br><b>Tiempo Transcurrido: </b>" + PantallaJuego.RelojParaReporte
                + "<br><b>Intervalo: </b>" + VelocidadCulebra + "<br><b>Tamaño de la Serpiente </b>" + SiclosDefault + "<br></p>" + "<h1><center>Movimientos de la Culebra  <br> <br>	</center></h1>\n"
                + "<p>" + PantallaJuego.Movimientos + "</p>";
    }

    public static void GeneraReporte() {
        System.out.println(PantallaJuego.ReporteParaArchivo);
        File archivo = new File("202103105.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(PantallaJuego.ReporteParaArchivo);
            bw.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        System.out.println("inicia culebra");
        for (int j = 0; j < 10; j++) { //Asigna timer en 0 para todos los espacios
            for (int k = 0; k < 10; k++) {
                Timer[0][j][k] = 0;
            }
        }

        for (int i = 0; i < 10; i++) { // llena inicialmente pantalla de vacios
            for (int j = 0; j < 10; j++) {
                PantallaJuego.DatosPantallaCulebra[i][j] = "□";
            }
        }

        PantallaJuego.DatosPantallaCulebra[4][4] = "<font style=\"color:green;\">■</font>"; //Caracter de Posicion incial cabeza

        PosicionCabeza[0][0] = 4;//posicion inicial cabeza X
        PosicionCabeza[1][0] = 4;//posicion inicial cabeza Y
        PantallaJuego.PosicionFruta[0][0] = PantallaJuego.FrutaRandomInicial[0];//Posicion inicial Fruta X
        PantallaJuego.PosicionFruta[1][0] = PantallaJuego.FrutaRandomInicial[1];//Posicion inicial Fruta Y
        PantallaJuego.DatosPantallaCulebra[PantallaJuego.FrutaRandomInicial[0]][PantallaJuego.FrutaRandomInicial[1]] = "<font style=\"color:red;\">▣</font>";//caracter posicion inicial fruta

        PantallaJuego.PantallaPrincipal.setText(TextoParaPantalla(DatosPantallaCulebra)); //DEBUG IMPRIME CULEBRA ANTES 
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        while (JuegoTerminado == false) { //motor principal, bucle inicia////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            try {
                MueveCulebra(PantallaJuego.Dpad, PantallaJuego.DatosPantallaCulebra, PosicionCabeza);//Mueve la culebra un espacio
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
                PantallaJuego.INICIORELOJ = false;
                JOptionPane.showMessageDialog(null, "La serpiente ha chocado con la pared y falleció!", "Advertencia", JOptionPane.WARNING_MESSAGE);

                JuegoTerminado = true;
                PantallaJuego.my.setEnabled(false);
                PantallaJuego.mx.setEnabled(false);
                PantallaJuego.py.setEnabled(false);
                PantallaJuego.px.setEnabled(false);
                PantallaJuego.INICIO = true;
                PantallaJuego.INICIORELOJ = false;

            }

            if (PosicionCabeza[0][0] == PantallaJuego.PosicionFruta[0][0] && PosicionCabeza[1][0] == PantallaJuego.PosicionFruta[1][0]) { // Detecta si la culebra comio una fruta, luego teletransporta la fruta a lugar aleatorio 
                PantallaJuego.FrutaRandomInicial[0] = (int) (Math.random() * (9 - 0 + 1) + 0);
                PantallaJuego.FrutaRandomInicial[1] = (int) (Math.random() * (9 - 0 + 1) + 0);
                PantallaJuego.DatosPantallaCulebra[PantallaJuego.FrutaRandomInicial[0]][PantallaJuego.FrutaRandomInicial[1]] = "<font style=\"color:red;\">▣</font>";//caracter posicion inicial fruta
                PantallaJuego.PosicionFruta[0][0] = PantallaJuego.FrutaRandomInicial[0];//Posicion inicial Fruta X
                PantallaJuego.PosicionFruta[1][0] = PantallaJuego.FrutaRandomInicial[1];//Posicion inicial Fruta Y
                SiclosDefault++;
                System.out.println("Tamano>" + SiclosDefault);
                VelocidadCulebra = (int) (VelocidadCulebra * (1 - PantallaJuego.MultiplicadorDificultad));
                System.out.println(VelocidadCulebra);
                System.out.println("fruta recogida");

                try {
                    if (SiclosDefault > 2) {
                        CreceCulebra();
                        System.out.println("SE APLICA CRECE CULEBRA");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                }

            }

            for (int i = 0; i < 10; i++) { // Resta en array timer o elimina cola de la culebra, ignorando FRUTA Y CABEZA
                for (int j = 0; j < 10; j++) {
                    if (Timer[0][i][j] != 0) {
                        Timer[0][i][j] = Timer[0][i][j] - 1;
                    }
                    if (Timer[0][i][j] == 0 && ((i != PosicionCabeza[0][0] || j != PosicionCabeza[1][0]) && (i != PantallaJuego.PosicionFruta[0][0] || j != PantallaJuego.PosicionFruta[1][0]))) {
                        DatosPantallaCulebra[i][j] = "□";
                    }

                }
            }

            for (int i = 0; i < 10; i++) {//Imprime array timer en consola para monitoreo
                for (int j = 0; j < 10; j++) {
                    System.out.print(Timer[0][j][i]);
                }
                System.out.println("");
            }
            System.out.println("");
            PantallaJuego.PantallaPrincipal.setText(TextoParaPantalla(DatosPantallaCulebra)); //imprime en pantalla la culebra
            PantallaJuego.LabelTamano.setText(String.valueOf(SiclosDefault));
            PantallaJuego.LabelIntervalo.setText(String.valueOf(VelocidadCulebra));

            if (SiclosDefault == 25) { //Revisa si el juego termino y mata al hilo, habilitando nuevamente el boton inicio
                PantallaJuego.INICIORELOJ = false;
                JOptionPane.showMessageDialog(null, "La serpiente ha se ha llenado y ha alcanzado su tamaño máximo", "Felicidades!", JOptionPane.INFORMATION_MESSAGE);

                JuegoTerminado = true;
                PantallaJuego.my.setEnabled(false);
                PantallaJuego.mx.setEnabled(false);
                PantallaJuego.py.setEnabled(false);
                PantallaJuego.px.setEnabled(false);
                PantallaJuego.INICIO = true;
                PantallaJuego.INICIORELOJ = false;

            }

            try {
                Thread.sleep(VelocidadCulebra);
            } catch (InterruptedException ex) {

                ex.printStackTrace();

            }

        }
        llenaReporte();

        System.out.println("termino culebra");
        GeneraReporte();
        PantallaJuego.BotonIniciar.setEnabled(true);
        JOptionPane.showMessageDialog(null, "Se ha generado el reporte HTML", "Información", JOptionPane.INFORMATION_MESSAGE);
        JuegoTerminado = false;

        ReseteaVariables();

    }

}
