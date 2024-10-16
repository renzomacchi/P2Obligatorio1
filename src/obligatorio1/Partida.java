/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1;

/**
 *
 * @author Lenovo
 */
public class Partida {
    private Tateti[][] subTableros;
    private Tateti granTablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private String turno;
    private int[] tatetiPos;
    private static String verde = "\u001B[42m";
    private static String amarillo = "\u001B[43m";

    public String getTurno() {
        return turno;
    }
    
    public int getPosX() {
        return this.tatetiPos[0];
    }
    
    public int getPosY() {
        return this.tatetiPos[1];
    }
    
    public Tateti getGranTablero() {
        return this.granTablero;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    public void setPos(int posX, int posY) {
        int[] pos = {posX, posY};
        this.tatetiPos = pos;
    }
    
    Partida(Jugador j1, Jugador j2) {
        Tateti[][] enBlanco = {
            {new Tateti(), new Tateti(), new Tateti()},
            {new Tateti(), new Tateti(), new Tateti()},
            {new Tateti(), new Tateti(), new Tateti()}
        };
        this.subTableros = enBlanco;
        this.granTablero = new Tateti();
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.turno = j1.getAlias();
    }
    
    Partida(char g) {
        //BORRAR
        Tateti[][] enBlanco = {
            {new Tateti('X'), new Tateti(), new Tateti()},
            {new Tateti(), new Tateti(), new Tateti()},
            {new Tateti(), new Tateti(), new Tateti()}
        };
        this.subTableros = enBlanco;
        this.granTablero = new Tateti();
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();
        this.turno = this.jugador1.getAlias();
    }
    
    public void jugada(int posX, int posY) {
        char letra = Tateti.x;
        if (this.turno.equals(this.jugador2.getAlias())) {
            letra = Tateti.o;
        }
        //Juega en el tateti seleccionado
        Tateti tateti = this.subTableros[this.getPosX()][this.getPosY()];
        tateti.jugada(posX, posY, letra);
        if(tateti.checkGanar()) {
            this.getGranTablero().jugada(this.getPosX(), this.getPosY(), letra);
            if(this.getGranTablero().checkGanar()) {
                System.out.println("GANARON: " + letra);
            }
        }
        //Cambia al tateti segun las posiciones
        this.setPos(this.getPosX(),this.getPosY());
    }
    
    
    
    public void display() {
        String[][] result = new String[19][19];
        String reset = "\u001B[0m";
        for(int i = 0; i<result.length; i++) {
            for(int j = 0; j<result[0].length; j++) {
                if(i+(i%5) > i && j+(j%5) > j) {
                    result[i][j] = this.subTableros[0][0].display()[(i%5)-1][(j%5)-1];
                } else {
                    result[i][j] = reset + "\u001B[42m*";
                }
            }
        }
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println("");
        }
//        System.out.println("*******************");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*-+-+-*-+-+-*-+-+-*");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*-+-+-*-+-+-*-+-+-*");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*******************");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*-+-+-*-+-+-*-+-+-*");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*-+-+-*-+-+-*-+-+-*");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*******************");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*-+-+-*-+-+-*-+-+-*");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*-+-+-*-+-+-*-+-+-*");
//        System.out.println("* | | * | | * | | *");
//        System.out.println("*******************");
    }
    
}
