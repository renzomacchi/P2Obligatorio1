/*Santiago Clavere
  Renzo Machi
*/
package obligatorio1;

import java.util.*;
// [BORRAR] antes de entregar
public class Obligatorio1 {
    private static final String separador = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static final Scanner in = new Scanner(System.in);
    private static final Sistema sistema = new Sistema();
    
    public static void main(String[] args) {
        String input = "";
        //Animacion("Bienvenido",20,20);
        //BORRAR debugData();
        debugData();
        while (!input.equals("e")) {
            Menu();
            input = in.nextLine().toLowerCase();
            switch(input){
                case "a":
                    agregarJugador();
                    break;
                case "b":
                    partida(false);
                    break;
                case "c":
                    partida(true);
                    break;
                case "d":
                    ranking();
                    break;
                case "e":
                    System.out.println("Saliendo...");
                    break;
                default: 
                    System.out.println("Por favor ingrese un caracter valido");
            }
        }
        
        
    }
    
    public static void agregarJugador() {
        System.out.println("Agregar Jugador");
        System.out.println("--------------------------");
        
        System.out.println("Ingrese Nombre");
        String nombre = in.nextLine();
        while(nombre.trim().equals("")) {
            System.out.println("Nombre invalido");
            nombre = in.nextLine();
        }
        
        System.out.println("Ingrese Alias");
        String alias = in.nextLine();
        while(alias.trim().equals("")) {
            System.out.println("Alias invalido");
            alias = in.nextLine();
        }
        while(sistema.existeAlias(alias)) {
            System.out.println("Ya existe ese Alias, Ingrese uno nuevo");
            alias = in.nextLine();
        }
        
        System.out.println("Ingrese Edad");
        int edad = in.nextInt();
        in.nextLine();
        while(edad < 0) {
            System.out.println("Edad invalida");
            edad = in.nextInt();
            in.nextLine();
        }
        
        sistema.agregarJugador(alias.trim(), nombre.trim(), edad);
        System.out.println("Agregado Correctamente");
        System.out.println("--------------------------");
    }
    
    public static void partida(boolean contraPC) {
        if(contraPC) {
            System.out.println("Gran tateti contra PC");
        } else {
            System.out.println("Gran tateti 2 jugadores");
        }
        System.out.println("--------------------------");
        System.out.println("Proceder? (Y por defecto) Y/N");
        
        if (!in.nextLine().toLowerCase().trim().equals("n")) {
            
            //Obtener Jugador 1
            System.out.println("Ingrese Alias del Jugador 1");
            String alias1 = in.nextLine();
            while(!sistema.existeAlias(alias1)) {
                System.out.println("Ingrese un Alias existente");
                alias1 = in.nextLine();
            }
            Jugador j1 = sistema.getJugador(alias1);
            
            //Obtener Jugador 2
            Jugador j2 = sistema.getPC();
            if(!contraPC) {
                System.out.println("Ingrese Alias del jugador 2");
                String alias2 = in.nextLine();
                while(!sistema.existeAlias(alias2) || alias2.equals(alias1)) {
                    System.out.println("Ingrese un Alias existente o diferente");
                    alias2 = in.nextLine();
                }
                j2 = sistema.getJugador(alias2);
            }
            
            //Creacion de partida
            System.out.println("Creando partida");
            System.out.println("--------------------------");
            sistema.nuevaPartida(j1,j2);
            String input = "";
            boolean fin = false;
            while(!salir(input) && !fin) {
                String turno = sistema.getPartida().getTurno();
                int[] pos;
                
                formatoCorrecto();
                System.out.println(turno + " elige donde empezar!");
                input = in.nextLine();
                while (!salir(input) && stringToCoords(input)[0] == -1) {
                    formatoCorrecto();
                    System.out.println("Ingrese una coordenada valida");
                    input = in.nextLine();
                }
                pos = stringToCoords(input);
                sistema.getPartida().setPos(pos[0],pos[1]);
                sistema.getPartida().display();
                
                System.out.println(turno + " juega!");
                input = in.nextLine();
                while (!salir(input) && stringToCoords(input)[0] == -1) {
                    formatoCorrecto();
                    System.out.println("Ingrese una coordenada valida ");
                    input = in.nextLine();
                }
                pos = stringToCoords(input);
                int status = sistema.getPartida().jugada(pos[0], pos[1]);
                switch(status) {
                    case 0:
                        
                }
            }
            System.out.println("Partida Finalizada");
            System.out.println("--------------------------");
        }
    }
    
    public static void partidaPC() {
        System.out.println("Gran tateti contra pc");
    }
    
    public static void ranking() {
        System.out.println("Ranking");
        System.out.println("--------------------------");
        Iterator<Jugador> it = sistema.getJugadores().iterator();
        while (it.hasNext()){
            Jugador j = it.next();
            String info = j.toString();
            for (int i = info.length(); i<=24; i++) {
                info += " ";
            }
            info += "| ";
            for(int i = 0; i<j.getWins(); i++) {
                info += "#";
            }
            System.out.println(info);
        }
        System.out.println("--------------------------");
        System.out.println("Enter para continuar");
        in.nextLine();
        
    }
    
    
    public static void Menu(){
        System.out.println("Menu Principal");
        System.out.println("--------------------------");
        System.out.println("a) Agregar Jugador");
        System.out.println("b) Jugar al gran Tateti entre 2 personas");
        System.out.println("c) Jugar al gran Tateti vs la Computadora");
        System.out.println("d) Ranking");
        System.out.println("e) Fin");
        System.out.println("--------------------------");
    }
    
    public static void Animacion(String text, int espacios, int frameTime) {
        for(int i = 0; i<espacios; i++){
            text = " " + text;
        }
        for(int i = 0; i<=espacios; i++) {
            String frame = text.substring(i,text.length());
            System.out.println(separador);
            System.out.println(frame);
            try {
                Thread.sleep(frameTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        for(int i = 0; i<=espacios; i++) {
            String frame = text.substring(espacios-i,text.length());
            System.out.println(separador);
            System.out.println(frame);
            try {
                Thread.sleep(frameTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        for(int i = 0; i<=espacios; i++) {
            String frame = text.substring(i,text.length());
            System.out.println(separador);
            System.out.println(frame);
            try {
                Thread.sleep(frameTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
  
    }
    
    public static int[] stringToCoords(String input) {
        int pos[] = {-1, -1};
        boolean valido = true;
        if(input.length() < 2) {
            valido = false;
        }
        if(valido) {
            switch(input.toUpperCase().charAt(0)) {
                case 'A':
                    pos[0] = 0;
                    break;
                case 'B':
                    pos[0] = 1;
                    break;
                case 'C':
                    pos[0] = 2;
                    break;
                default:
                    valido = false;
            }
        }
        if (valido) {
            int entero = -1; 
            try {
                entero = Integer.parseInt("" + input.charAt(1));
            }
            catch (NumberFormatException e) {
                
            }
            if (entero >= 1 && entero <= 3) {
                pos[1] = entero - 1;
            } else {
                pos[0] = -1;
            }
        }
        return pos;
    }
    
    public static void formatoCorrecto() {
        System.out.println("A1|A2|A3");
        System.out.println("--+--+--");
        System.out.println("B1|B2|B3");
        System.out.println("--+--+--");
        System.out.println("C1|C2|C3");
    }
    
    public static boolean salir(String input) {
        return input.toUpperCase().equals("X");
    }
    
    private static void debugData() {
        //BORRAR
        sistema.agregarJugador("Alias", "Nombre", 12);
        sistema.agregarJugador("Pipa", "Nombre", 21);
        sistema.agregarJugador("DarkFlameMaster", "Nombre", 16);
        sistema.agregarJugador("SigmaDiablo", "Nombre", 99);
        sistema.agregarJugador("Greg", "Gregory House", 65);
        sistema.agregarJugador("cumbiaNinja", "ElCumbianinja", 10);
        sistema.agregarJugador("Davinki?", "Absolute", 17);
        sistema.agregarJugador("Santiago se la Come", "Mr. Factos", 17);
    }
    
}



