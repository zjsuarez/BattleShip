import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        char [] [] panelJugador = new char [5][5];
        char [] [] panelMaquina = new char [5][5];
        int barcosJugador, barcosMaquina, cantidad = 0;


        System.out.println("__________         __    __  .__           _________.__    .__        ");
        System.out.println("\\______   \\_____ _/  |__/  |_|  |   ____  /   _____/|  |__ |__|_____  ");
        System.out.println(" |    |  _/\\__  \\\\   __\\   __\\  | _/ __ \\ \\_____  \\ |  |  \\|  \\____ \\ ");
        System.out.println(" |    |   \\ / __ \\|  |  |  | |  |_\\  ___/ /        \\|   Y  \\  |  |_> >");
        System.out.println(" |______  /(____  /__|  |__| |____/\\___  >_______  /|___|  /__|   __/ ");
        System.out.println("        \\/      \\/                     \\/        \\/      \\/   |__|    ");
        System.out.println(" Justin Suárez ʕ•́ᴥ•̀ʔっ");
        System.out.println("");System.out.println("");
        cantidad = cantidadBarcos();
        barcosJugador = cantidad;
        barcosMaquina = cantidad;
        iniciarMatriz(panelJugador);
        iniciarMatriz(panelMaquina);
        situarBarcosjugador(panelJugador, cantidad);
        situarBarcosMaquina(panelMaquina, cantidad);

        System.out.println("Empieza la partida");

        do {
            System.out.println("");
            System.out.println("--Turno Jugador");
            if (disparaJugador(panelMaquina)) {
                barcosMaquina--;
            }
            System.out.println("Panel maquina:");
            mostrarPanel(panelMaquina, barcosJugador, barcosMaquina);
            System.out.println("");
            System.out.println("--Turno Máquina");
            if (disparaMaquina(panelJugador)) {
                barcosJugador--;
            }
            System.out.println("Panel jugador:");
            mostrarPanel(panelJugador, barcosJugador, barcosMaquina);
        } while(barcosJugador > 0 && barcosMaquina>0);
        mostrarGanador(barcosJugador, barcosMaquina);
    }


    public static int cantidadBarcos(){
        Scanner sc = new Scanner(System.in);
        int number;
        while (true){
            System.out.println("--> [Cuantos barcos deseas (min 1 y max 5)]");
            number = sc.nextInt();

            if (number<1 || number>5) {
                System.out.println("¡Número incorrecto! (min 1 y max 5)");
            } else{
                break;
            }
        }

        return number;

    }

    public static void iniciarMatriz(char [][] panel){
        for (int i = 0; i < panel.length; i++) {
            for (int j = 0; j < panel.length; j++) {
                panel[i][j]='.';
            }
        }
    }

    public static void situarBarcosjugador(char [][] panel, int cantidad){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < cantidad; i++) {
            while (true){
                System.out.println("---Coordenadas para el barco numero "+(i+1)+"---");
                System.out.println("-> Introduce fila:");
                int fila = sc.nextInt();
                System.out.println("-> Introduce columna");
                int columna = sc.nextInt();

                if (panel[fila][columna]=='B') {
                    System.out.println("ERROR: Hay un barco en esa coordenada!");
                    System.out.println("");
                    System.out.println("");
                    continue;
                } else{
                    System.out.println("Barco introducido");
                    System.out.println("");
                    System.out.println("");
                    panel[fila][columna]='B';
                    break;
                }
            }

        }
    }

    public static void situarBarcosMaquina(char[][] panel, int cantidad) {
        Random rnd = new Random();
        for (int i = 0; i < cantidad; i++) {
            while (true){
                int fila = rnd.nextInt(0,5);
                int columna = rnd.nextInt(0,5);

                if (panel[fila][columna]=='.') {
                    panel[fila][columna]='B';
                    break;
                } else{
                    continue;
                }

            }
        }
    }

    public static boolean disparaJugador(char [][] panel){
        Scanner sc = new Scanner(System.in);
        int fila;
        while (true){
            System.out.println("--> Inserta fila:");
            fila = sc.nextInt();

            if (fila<0 || fila>5) {
                System.out.println("ERROR: Valor inválido.");
                continue;
            } else{
                break;
            }
        }

        int columna;
        while (true){
            System.out.println("--> Inserta columna:");
            columna = sc.nextInt();

            if (columna<0 || columna>5) {
                System.out.println("ERROR: Valor inválido.");
                continue;
            } else{
                break;
            }
        }

        boolean hit;
        if (panel[fila][columna]=='B') {
            System.out.println("¡BARCO HUNDIDO!");
            panel[fila][columna]='A';
            hit = true;
        } else{
            System.out.println("Hmm...No había ningún barco en esa coordenada.");
            panel[fila][columna]='A';
            hit = false;
        }
        return hit;
    }

    public static boolean disparaMaquina(char [][] panel){
        Random rnd = new Random();
        boolean hit;
        int fila = rnd.nextInt(0,5);
        int columna = rnd.nextInt(0,5);
        System.out.println("---La maquina ha decidido impactar en:");
        System.out.println("-Fila: "+fila);
        System.out.println("-Columna: "+columna);
        if (panel[fila][columna]=='B') {
            System.out.println("¡La máquina te ha hundido un barco!");
            panel[fila][columna]='A';
            hit = true;
        } else{
            System.out.println("¡Jeje! La máquina ha fallado.");
            panel[fila][columna]='A';
            hit = false;
        }

        return hit;
    }

    public static void mostrarPanel(char[][] panel, int jugador, int maquina){
        System.out.println("");
        System.out.println("=============");
        for (int i = 0; i < panel.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < panel.length; j++) {
                System.out.print(panel[i][j]+" ");
            }
            System.out.print("|");
            if (i==0) {
                System.out.print("             Barcos jugador: "+jugador);
            }

            if (i==4) {
                System.out.print("             Barcos máquina: "+maquina);
            }
            System.out.println("");
        }
        System.out.println("=============");
        System.out.println("");
    }

    public static void mostrarGanador(int jugador, int maquina){
        System.out.println("------RESULTADOS------");
        if (jugador<=0 && maquina<=0) {
            System.out.println("Barcos jugador: "+jugador);
            System.out.println("Barcos máquina: "+maquina);
            System.out.println("");
            System.out.println("Hay un empate!");
        } else if (jugador<=0){
            System.out.println("Barcos jugador: "+jugador);
            System.out.println("Barcos máquina: "+maquina);
            System.out.println("");
            System.out.println("La máquina ha ganado!");
        } else{
            System.out.println("Barcos jugador: "+jugador);
            System.out.println("Barcos máquina: "+maquina);
            System.out.println("");
            System.out.println("El jugador ha ganado!");
        }
    }

























}
