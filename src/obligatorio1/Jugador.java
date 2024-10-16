/*Santiago clavere
Renzo Macchi
 */
package obligatorio1;

import java.util.*;

public class Jugador implements Comparable<Jugador> {
    private String alias;
    private String nombre;
    private int edad;
    private int wins;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
    
    public Jugador(String alias, String nombre, int edad){
        this.setAlias(alias);
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setWins((int)(Math.random()*100));
    }
    
    public Jugador() {
        this.setAlias("");
        this.setNombre("");
        this.setEdad(0);
        this.setWins(0);
    }
    
    @Override
    public boolean equals(Object obj) {
        Jugador j = (Jugador)obj;
        return this.getAlias().equals(j.getAlias());
    }

    @Override
    public int compareTo(Jugador j) {
        int dif = j.getWins()-this.getWins();
        if (dif == 0) {
            dif = this.getAlias().toLowerCase().compareTo(j.getAlias().toLowerCase());
        }
        return dif;
    }
    
    @Override
    public String toString() {
        return this.getAlias();
    }
    
}


