/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static Logica.Laberinto.Matriz;

/**
 *
 * @author Estudiantes
 */
public class Jugar {

    private int posX;
    private int posY;
    private int posFinX;
    private int posFinY;
    public Jugar() {
        for (int i = (Matriz.length) - 1; i > -1; i--) {
            for (int j = 0; j < Matriz[1].length; j++) {
                if (Matriz[i][j] == 5) {
                    posX = i;
                    posY = j;
                }
                if (Matriz[i][j] == 6) {
                    posFinX = i;
                    posFinY = j;
                }
            }
        }
    }

    public void moverAbajo() {
        try {
            puedeMoverse(posX+1, posY);
            Matriz[posX][posY] = 0;
            posX++;
            Matriz[posX][posY] = 5;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void moverArriba() {
        try {
            puedeMoverse(posX-1, posY);
            Matriz[posX][posY] = 0;
            posX--;
            Matriz[posX][posY] = 5;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void moverIzquierda() {
        //evaluarposicion
        try {
            puedeMoverse(posX, posY - 1);
            Matriz[posX][posY] = 0;
            posY--;
            Matriz[posX][posY] = 5;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
}

public void moverDerecha() {
        try {
            puedeMoverse(posX, posY +1);
            Matriz[posX][posY] = 0;
            posY++;
            Matriz[posX][posY] = 5;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean ganar() {
        if(posX==posFinX && posY==posFinY)
            return true;
        return false;
    }
    public void mostrar() {
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[1].length; j++) {
                System.out.print(Matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }
private static void puedeMoverse(int i, int j) throws MiExcepcion {
        if(i<0 || j<0 || i>=Matriz.length || j>= Matriz[0].length){
           throw new MiExcepcion("Se sale del laberinto");
        }else if(Matriz[i][j]==1){
           throw new MiExcepcion("No puedes atravezar paredes!!!!!!"); 
        }
        
    }
}