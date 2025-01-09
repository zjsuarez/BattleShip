import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        char[][] playerBoard = new char[5][5];
        char[][] machineBoard = new char[5][5];
        int playerShips, machineShips, quantity = 0;

        System.out.println("__________         __    __  .__           _________.__    .__        ");
        System.out.println("\\______   \\_____ _/  |__/  |_|  |   ____  /   _____/|  |__ |__|_____  ");
        System.out.println(" |    |  _/\\__  \\\\   __\\   __\\  | _/ __ \\ \\_____  \\ |  |  \\|  \\____ \\ ");
        System.out.println(" |    |   \\ / __ \\|  |  |  | |  |_\\  ___/ /        \\|   Y  \\  |  |_> >");
        System.out.println(" |______  /(____  /__|  |__| |____/\\___  >_______  /|___|  /__|   __/ ");
        System.out.println("        \\/      \\/                     \\/        \\/      \\/   |__|    ");
        System.out.println(" Justin Suárez ʕ•́ᴥ•̀ʔっ");
        System.out.println("");System.out.println("");
        quantity = numberOfShips();
        playerShips = quantity;
        machineShips = quantity;
        initializeBoard(playerBoard);
        initializeBoard(machineBoard);
        placePlayerShips(playerBoard, quantity);
        placeMachineShips(machineBoard, quantity);

        System.out.println("Game starts");

        do {
            System.out.println("");
            System.out.println("--Player's Turn");
            if (playerShoots(machineBoard)) {
                machineShips--;
            }
            System.out.println("Machine's board:");
            displayBoard(machineBoard, playerShips, machineShips);
            System.out.println("");
            System.out.println("--Machine's Turn");
            if (machineShoots(playerBoard)) {
                playerShips--;
            }
            System.out.println("Player's board:");
            displayBoard(playerBoard, playerShips, machineShips);
        } while(playerShips > 0 && machineShips > 0);
        displayWinner(playerShips, machineShips);
    }

    public static int numberOfShips(){
        Scanner sc = new Scanner(System.in);
        int number;
        while (true){
            System.out.println("--> [How many ships do you want (min 1 and max 5)]");
            number = sc.nextInt();

            if (number < 1 || number > 5) {
                System.out.println("Incorrect number! (min 1 and max 5)");
            } else{
                break;
            }
        }
        return number;
    }

    public static void initializeBoard(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '.';
            }
        }
    }

    public static void placePlayerShips(char[][] board, int quantity){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < quantity; i++) {
            while (true){
                System.out.println("---Coordinates for ship number "+(i+1)+"---");
                System.out.println("-> Enter row:");
                int row = sc.nextInt();
                System.out.println("-> Enter column:");
                int column = sc.nextInt();

                if (board[row][column] == 'B') {
                    System.out.println("ERROR: There's already a ship at that coordinate!");
                    continue;
                } else {
                    System.out.println("Ship placed");
                    board[row][column] = 'B';
                    break;
                }
            }
        }
    }

    public static void placeMachineShips(char[][] board, int quantity){
        Random rnd = new Random();
        for (int i = 0; i < quantity; i++) {
            while (true){
                int row = rnd.nextInt(0,5);
                int column = rnd.nextInt(0,5);

                if (board[row][column] == '.') {
                    board[row][column] = 'B';
                    break;
                }
            }
        }
    }

    public static boolean playerShoots(char[][] board){
        Scanner sc = new Scanner(System.in);
        int row;
        while (true){
            System.out.println("--> Enter row:");
            row = sc.nextInt();

            if (row < 0 || row > 5) {
                System.out.println("ERROR: Invalid value.");
                continue;
            } else{
                break;
            }
        }

        int column;
        while (true){
            System.out.println("--> Enter column:");
            column = sc.nextInt();

            if (column < 0 || column > 5) {
                System.out.println("ERROR: Invalid value.");
                continue;
            } else{
                break;
            }
        }

        boolean hit;
        if (board[row][column] == 'B') {
            System.out.println("SHIP SUNK!");
            board[row][column] = 'A';
            hit = true;
        } else {
            System.out.println("Hmm... No ship at that coordinate.");
            board[row][column] = 'A';
            hit = false;
        }
        return hit;
    }

    public static boolean machineShoots(char[][] board){
        Random rnd = new Random();
        boolean hit;
        int row = rnd.nextInt(0,5);
        int column = rnd.nextInt(0,5);
        System.out.println("---The machine decided to hit at:");
        System.out.println("-Row: " + row);
        System.out.println("-Column: " + column);
        if (board[row][column] == 'B') {
            System.out.println("The machine has sunk a ship!");
            board[row][column] = 'A';
            hit = true;
        } else {
            System.out.println("Ha ha! The machine missed.");
            board[row][column] = 'A';
            hit = false;
        }
        return hit;
    }

    public static void displayBoard(char[][] board, int player, int machine){
        System.out.println("");
        System.out.println("=============");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            if (i == 0) {
                System.out.print("             Player ships: " + player);
            }

            if (i == 4) {
                System.out.print("             Machine ships: " + machine);
            }
            System.out.println("");
        }
        System.out.println("=============");
        System.out.println("");
    }

    public static void displayWinner(int player, int machine){
        System.out.println("------RESULTS------");
        if (player <= 0 && machine <= 0) {
            System.out.println("Player ships: " + player);
            System.out.println("Machine ships: " + machine);
            System.out.println("");
            System.out.println("It's a tie!");
        } else if (player <= 0) {
            System.out.println("Player ships: " + player);
            System.out.println("Machine ships: " + machine);
            System.out.println("");
            System.out.println("The machine wins!");
        } else {
            System.out.println("Player ships: " + player);
            System.out.println("Machine ships: " + machine);
            System.out.println("");
            System.out.println("The player wins!");
        }
    }
}
