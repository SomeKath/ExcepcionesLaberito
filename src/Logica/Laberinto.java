package Logica;

import static Logica.Laberinto.Matriz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian
 */
public class Laberinto {

    /**
     * @param args the command line arguments
     */
    public static int Matriz[][];
    public int x = 0;
    public int y = 0;

    public static void main(String[] args) {
        try {
            cargarLaberinto();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Jugar jugar = new Jugar();
        Scanner sc = new Scanner(System.in);
        char opc = '1';
        while (opc != '0') {
            jugar.mostrar();
            if (jugar.ganar()){
                System.out.println("Has ganado");
                opc='0';
            }else{
                System.out.println("Ingrese un movimiento \"W\",\"A\",\"S\" o \"D\" o cualquier otra para salir");
                opc=((sc.next()).toUpperCase()).charAt(0);
            }
            System.out.println("------------");
            switch (opc) {
                case 'W':
                    jugar.moverArriba();
                    break;
                case 'S':
                    jugar.moverAbajo();
                    break;
                case 'D':
                    jugar.moverDerecha();
                    break;
                case 'A' :
                    jugar.moverIzquierda();
                    break;
                default:
                    opc='0';
                    break;
            }
        }
       
        System.exit(0);
    }

    public static void cargarLaberinto() throws IOException {
        int filas, columnas, aux = 0;
        int inix, iniy, finx, finy;

        String linea, parte1 = "", parte2 = "";

        /*  archivo es el txt
         fr es el que abre el archivo
         br es el que lee
         */
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("prueba.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura de la linea
            linea = br.readLine();

            // Primer linea: tamaño matriz
            for (int i = 0; i < linea.length(); i++) {
                if (linea.charAt(i) == ' ') {
                    aux++;
                } else if (aux == 0) {
                    parte1 += linea.charAt(i);
                } else {
                    parte2 += linea.charAt(i);
                }
            }

            // Conversión
            filas = Integer.parseInt(parte1);
            columnas = Integer.parseInt(parte2);
            // Creación de matriz
            Matriz = new int[filas][columnas];

            //Ciclo para llenar la matriz
            for (int i = 0; i < filas; i++) {
                linea = br.readLine();
                // Largo correcto
                if (linea.length() != (columnas * 2) - 1) {
                    JOptionPane.showMessageDialog(null, "Ingrese la cantidad"
                            + " correcta de columnas de la fila " + (i + 1));
                    System.exit(0);
                } else {
                    int l = 0;

                    // For para recorrer la linea horizontalmente 
                    for (int j = 0; j < (columnas * 2) - 1; j++) {
                        // Solo permite 1 0 y espacios
                        if (linea.charAt(j) != '1' && linea.charAt(j) != '0'
                                && linea.charAt(j) != ' ') {
                            JOptionPane.showMessageDialog(null, "Solo se permiten"
                                    + " \"1\" y \"0\"");
                            System.exit(0);
                        } else {
                            // Llenar matriz
                            if (linea.charAt(j) != ' ') {
                                Matriz[i][l] = linea.charAt(j) - 48;
                                l++;
                            }
                        }
                    }
                }
            } // Fin ciclo

            // Posición Inicial y Final 
            for (int m = 0; m < 2; m++) {
                linea = br.readLine();
                parte1 = "";
                parte2 = "";
                aux = 0;
                for (int i = 0; i < linea.length(); i++) {
                    if (linea.charAt(i) == ' ') {
                        aux++;
                    } else if (aux == 0) {
                        parte1 += linea.charAt(i);
                    } else {
                        parte2 += linea.charAt(i);
                    }
                }
                if (m == 0) {
                    if (Integer.parseInt(parte1) > filas || Integer.parseInt(parte2) > columnas
                            || Integer.parseInt(parte1) - 1 < 0 || Integer.parseInt(parte2) - 1 < 0) {
                        JOptionPane.showMessageDialog(null, "Posición"
                                + " de inicio fuera de la Matriz.");
                        System.exit(0);
                    } else {
                        // 5 va a representar al muñeco
                        Matriz[Integer.parseInt(parte1) - 1][Integer.parseInt(parte2) - 1] = 5;
                    }

                } else {
                    if (Integer.parseInt(parte1) > filas || Integer.parseInt(parte2) > columnas
                            || Integer.parseInt(parte1) - 1 < 0 || Integer.parseInt(parte2) - 1 < 0) {
                        JOptionPane.showMessageDialog(null, "Posición"
                                + " de meta fuera de la Matriz.");
                        System.exit(0);
                    } else {
                        // 6 va a representar la meta
                        Matriz[Integer.parseInt(parte1) - 1][Integer.parseInt(parte2) - 1] = 6;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
