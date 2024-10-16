/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

/**
 *
 * @author Lenovo
 */
public class Tateti {
    private char[][] tablero;
    private char ganador;
    public final static char x = 'X';
    public final static char o = 'O';
    
    public char getGanador() {
        return ganador;
    }
    
    Tateti() {
        char[][] enBlanco = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
        };
        this.tablero = enBlanco;
        this.ganador = ' ';
    }
    
    Tateti(char g) {
        //BORRAR
        char[][] no = {
            {'X','O',' '},
            {'X','O',' '},
            {'X',' ','O'}
        };
        this.tablero = no;
        this.ganador = g;
    }
    
    public void jugada(int posX, int posY, char turno) {
        if (this.esVacio(posX,posY)) {
            tablero[posX][posY] = turno;
        }
    }
    
    public void jugada(char turno) {
        int posXRng = (int)Math.floor(Math.random()*4);
        int posYRng = (int)Math.floor(Math.random()*4);
        while(!this.esVacio(posXRng, posYRng)) {
            posXRng = (int)Math.floor(Math.random()*4);
            posYRng = (int)Math.floor(Math.random()*4);
        }
        tablero[posXRng][posYRng] = turno;
    }
    
    public boolean esVacio(int posX, int posY) {
        return (tablero[posX][posY] == ' ');
    }
    
    public boolean checkGanar() {
        boolean result = false;
        for(int i = 0; i < tablero.length && !result; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != ' ') {
                //x x x
                //y y y
                //z z z
                result = true;
            }
        }
        for(int j = 0; j < tablero[0].length && !result; j++) {
            if (tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j] && tablero[j][0] != ' ') {
                //x y z
                //x y z
                //x y z
                result = true;
            }
        }
        if(!result) {
            if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != ' ') {
                //x - -
                //- x -
                //- - x
                result = true;
            } else
            if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != ' ') {
                //- - x
                //- x -
                //x - -
                result = true;
            }
        }
        return result;
    }
    
    public String[][] display() {
        String result[][] = new String[5][5];
        String reset = "\u001B[0m";
        String color = "";
        if (this.getGanador() == Tateti.x) {
            color = "\u001B[31m";
        }
        else if (this.getGanador() == Tateti.o) {
            color = "\u001B[34m";
        }
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                if(i%2 == 0 && j%2 == 0){
                    result[i][j] = reset + color + this.tablero[i/2][j/2];
                }
                else if (i%2 == 0 && j%2 == 1){
                    result[i][j] = reset + "|";
                }
                else if (i%2 == 1 && j%2 == 0){
                    result[i][j] = reset + "-";
                }
                else {
                    result[i][j] = reset + "+";
                }
            }
        }
        return result;
    }
}
