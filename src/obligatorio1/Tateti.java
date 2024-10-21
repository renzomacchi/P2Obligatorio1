/* Santiago Clavere
Renzo macchi
*/
package obligatorio1;

public class Tateti {
    private char[][] tablero;
    private char ganador;
    public final static char x = 'X';
    public final static char o = 'O';
    
    public char getGanador() {
        return ganador;
    }
    
    private void setCharAt(int posX, int posY, char c) {
        this.tablero[posX][posY] = c;
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
    
    public boolean jugada(int posX, int posY, char turno) {
        //Retorna true o false como status, indicando si la jugada es valida.
        boolean status = false;
        if (this.esVacio(posX,posY)) {
            this.setCharAt(posX, posY, turno);
            status = true;
            if(this.checkGanar()) {
                this.ganador = turno;
            }
        }
        return status;
    }
    
    public void jugada(char turno) {
        //Genera una jugada Random hasta que sea valida
        int posXRng = (int)Math.floor(Math.random()*4);
        int posYRng = (int)Math.floor(Math.random()*4);
        while(!this.esVacio(posXRng, posYRng)) {
            posXRng = (int)Math.floor(Math.random()*4);
            posYRng = (int)Math.floor(Math.random()*4);
        }
        this.setCharAt(posXRng, posYRng, turno);
    }
    
    public boolean esVacio(int posX, int posY) {
        return (this.tablero[posX][posY] == ' ');
    }
    
    public boolean checkGanar() {
        boolean result = false;
        if (this.getGanador() == ' ') {
            for(int i = 0; i < this.tablero.length && !result; i++) {
                if(tablero[i][0] != ' ') {
                    if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                        //x x x
                        //y y y
                        //z z z
                        result = true;
                    }
                }
            }
            for(int j = 0; j < tablero[0].length && !result; j++) {
                if(tablero[0][j] != ' ') {
                    if (tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                        //x y z
                        //x y z
                        //x y z
                        result = true;
                    }
                }
            }
            if(!result) {
                if (tablero[1][1] != ' ') {
                    if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
                        //x - -
                        //- x -
                        //- - x
                        result = true;
                    } else
                    if (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
                        //- - x
                        //- x -
                        //x - -
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    
    public String[][] display() {
        //Retornara una matriz con un tablero de Tateti
        String result[][] = new String[5][5];
        
        //Obtener color ganador, si es que alguien gano
        String colorGanador = "";
        if (this.getGanador() == Tateti.x) {
            colorGanador = Color.ROJO;
        }
        else if (this.getGanador() == Tateti.o) {
            colorGanador = Color.AZUL;
        }
        //Recorremos matriz
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                //Posiciones de i, j que son pares
                if(i%2 == 0 && j%2 == 0){
                    result[i][j] = Color.RESET + colorGanador + this.tablero[i/2][j/2];
                }
                //Posiciones de i par y j impar
                else if (i%2 == 0 && j%2 == 1){
                    result[i][j] = Color.RESET + "|";
                }
                //Posiciones de i impar y j par
                else if (i%2 == 1 && j%2 == 0){
                    result[i][j] = Color.RESET + "-";
                }
                //El resto de posiciones son i, j impar
                else {
                    result[i][j] = Color.RESET + "+";
                }
            }
        }
        return result;
    }
}
