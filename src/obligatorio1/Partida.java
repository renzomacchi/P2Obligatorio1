/* Santiago Clavere
Renzo macchi
*/
package obligatorio1;

public class Partida {
    //Matriz 3x3 de Tatetis
    private Tateti[][] subTableros;
    //Tateti que representa el gran tateti
    private Tateti granTablero;
    //Jugadores
    private Jugador jugador1;
    private Jugador jugador2;
    //Alias del jugador del cuale es el turno
    private String turno;
    //Coordenadas del tateti seleccionado
    private int[] tatetiPos;

    public String getTurno() {
        return turno;
    }
    
    public int[] getPos() {
        return this.tatetiPos;
    }
    
    public Tateti getGranTablero() {
        return this.granTablero;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    public void setPos(int posX, int posY) {
        this.tatetiPos[0] = posX;
        this.tatetiPos[1] = posY;
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
        this.tatetiPos = new int[2];
    }
    
    public int jugada(int posX, int posY) {
        //Retorna un status indicando:
        //0 -> jugada invalida (posicion ocupada)
        //1 -> jugada realizada
        //2 -> jugada gano el "subTateti"
        //3 -> jugada gano el "subTateti" y el "granTateti"
        int status = 1;
        
        //Obtenemos X u O dependiendo del turno
        char letra = Tateti.x;
        if (this.turno.equals(this.jugador2.getAlias())) {
            letra = Tateti.o;
        }
        
        //Obtenemos el Tateti previamente seleccionado
        Tateti tateti = this.subTableros[this.getPos()[0]][this.getPos()[1]];
        
        //Realizamos jugada
        if (!tateti.jugada(posX, posY, letra)) {
            //Si la jugada es invalida retorna status = 0;
            status = 0;
        }
        if (status != 0) {
            //Si gano en esa jugada, agregar posicion en el granTateti.
            if(tateti.checkGanar()) {
                this.getGranTablero().jugada(this.getPos()[0], this.getPos()[1], letra);
                status = 2;
                
                //Si en esa juagada el granTateti se gano, la partida termina.
                if(this.getGranTablero().checkGanar()) {
                    status = 3;
                }
            }
            //Cambia al tateti segun las posiciones\
            this.setPos(posX,posY);
            
            if(status != 3) {
                //Alternamos los turnos en caso de que no se gane el granTateti
                this.siguienteTurno();
            }
        }
        
        return status;
    }
    
    public void siguienteTurno() {
        //Cambia los turnos al siguiente jugador
        if (this.turno.equals(jugador1.getAlias())) {
            this.turno = this.jugador2.getAlias();
        } else {
            this.turno = this.jugador1.getAlias();
        }
    }
    
    public void display() {
        //Escribe una matriz con un Tablero de Tatetis de 3x3
        String[][] result = new String[19][19];
        //Posiciones del tablero seleccionado
        int tX = this.getPos()[0];
        int tY = this.getPos()[1];
        
        for(int i = 0; i<result.length; i++) {
            for(int j = 0; j<result[0].length; j++) {
                //Insertar Tateties
                if(i+(i%6) > i && j+(j%6) > j) {
                    result[i][j] = this.subTableros[(int)(i/6)][(int)(j/6)].display()[(i%6)-1][(j%6)-1];
                }
                //Insertar marco de seleccion con asteriscos amarillos
                else if (i >= (tX*6) && i <= (tX*6)+6 && j >= (tY*6) && j <= (tY*6)+6){
                    result[i][j] = Color.RESET + Color.FONDO_AMARILLO + "*";
                }
                //Rellenar el resto con asteriscos verdes
                else {
                    result[i][j] = Color.RESET + Color.FONDO_VERDE + "*";
                }
            }
        }
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println("");
        }
    }
    
}
