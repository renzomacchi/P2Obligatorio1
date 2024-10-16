/* Santiago Clavere
Renzo macchi
*/
package obligatorio1;
import java.util.*;


public class Sistema {
    private static Scanner in = new Scanner(System.in);
    private static Jugador PC = new Jugador("PC","PC",0);
    
    private ArrayList<Jugador> Jugadores;
    private Partida partidaActual;
    
    Sistema() {
        this.Jugadores = new ArrayList<>();
        this.Jugadores.add(PC);
    }
  
    public boolean existeAlias(String alias){
        Jugador j = new Jugador(alias,"",0);
        return this.Jugadores.contains(j);
    }
    
    public void agregarJugador(String alias, String nombre, int edad) {
        Jugador nuevo = new Jugador(alias, nombre, edad);
        if (!this.existeAlias(alias)) {
            this.Jugadores.add(nuevo);
        }
    }
    
    public Jugador getJugador(String alias) {
        Jugador result = new Jugador();
        int index = this.Jugadores.indexOf(new Jugador(alias, "", 0));
        if (index >= 0) {
            result = this.Jugadores.get(index);
        }
        return result;
    }
    
    public Jugador getPC() {
        return this.Jugadores.get(this.Jugadores.indexOf(PC));
    }
    
    public ArrayList<Jugador> getJugadores() {
        Collections.sort(this.Jugadores);
        return this.Jugadores;
    }
    
    public Partida getPartida() {
        return this.partidaActual;
    }
    
    public void nuevaPartida(Jugador j1, Jugador j2) {
        this.partidaActual = new Partida(j1,j2);
    }
}
  


      

