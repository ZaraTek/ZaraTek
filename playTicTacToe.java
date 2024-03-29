import java.util.*;
public class playTicTacToe {
    public static char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
        };
    public static char marker = ' ';
    public static char cpuMarker = ' ';

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
    
    public static void main(String[] args) {
        System.out.println("Tic Tac Toe");
        chooseMarker();
        printBoard();
        while (checkWin() < 0) {
            if (marker == 'X'){
                playUser();
                clearScreen();
                printBoard();
                if (checkWin() < 0) {
                    playCPU();
                    clearScreen();
                    printBoard();
                }
            }
            else if (marker == 'O') {                
                playCPU();
                clearScreen();
                printBoard();

                if (checkWin() < 0) {
                    playUser();
                    clearScreen();
                    printBoard();
                }
            }
        }

        if (checkWin() == 0)
            System.out.println("Player Wins!");
        else if (checkWin() == 1)   
            System.out.println("CPU Wins!");
        
    }
    
    public static void chooseMarker() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Would you like to play as X or O?");
        marker = kb.next().charAt(0);
        marker = Character.toUpperCase(marker);
        while (marker != 'X' && marker != 'O') {
            System.out.println("Please enter a valid marker.");
            marker = kb.next().charAt(0);
            marker = Character.toUpperCase(marker);
        }
        if (marker == 'X')
            cpuMarker = 'O';
        if (marker == 'O')
            cpuMarker = 'X';
    }

    public static void playUser(){
        Scanner kb = new Scanner(System.in);
        String input = "";
        char colChar = ' ';
        char rowChar = ' ';
        int colInt = -1;
        int rowInt = -1;
        System.out.println("Please input your move. Ex: A 1");
        input = kb.nextLine();


        while (input.length() != 3) {
            System.out.println("Please enter a valid position.");
            input = kb.nextLine().toUpperCase();
        }
        colChar = input.substring(0,1).toUpperCase().charAt(0);
        rowChar = input.substring(2).charAt(0);
        switch (colChar) {
            case 'A': colInt = 0;
                break;
            case 'B': colInt = 1;
                break;
            case 'C': colInt = 2;
                break;
        }
        switch (rowChar) {
            case '1': rowInt = 0;
                break;
            case '2': rowInt = 1;
                break;
            case '3': rowInt = 2;
                break;
        }
        boolean valid = true;
        if (!(colChar >= 65 || colChar <= 90) || !(rowChar >=1 || rowChar <= 3)) {
            valid = false; 
            //System.out.println("statement entered.");
        }
        while (!valid || board[rowInt][colInt] != '-' ) {
            System.out.println("Please enter a valid position.");
            input = kb.nextLine();
            colChar = input.substring(0,1).toUpperCase().charAt(0);
            rowChar = input.substring(2).charAt(0);
            switch (colChar) {
                case 'A': colInt = 0;
                    break;
                case 'B': colInt = 1;
                    break;
                case 'C': colInt = 2;
                    break;
            }
            switch (rowChar) {
                case '1': rowInt = 0;
                    break;
                case '2': rowInt = 1;
                    break;
                case '3': rowInt = 2;
                    break;
            }
        } 
        
        board[rowInt][colInt] = marker;
    }

    public static void playCPU() {
        boolean placed = false;
        int[][] prefferedMoves =  {{1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
        {0, 1}, {1, 0}, {1, 2}, {2, 1}};

        

        for (int[] x : prefferedMoves) {
            if (board[x[0]][x[1]] == '-' && !placed) {
                board[x[0]][x[1]] = cpuMarker;
                placed = true;
            }
        }


    }
    
    public static int checkWin() {
        //check rows and columns
        for (int i = 0; i < board.length; i++) {
            char check = board[i][0];
            if (board[i][0] == check &&
                board[i][1] == check &&
                board[i][2] == check) {
                if (check == marker)
                    return 0; //player wins
                if (check == cpuMarker)
                    return 1; //cpu wins
            }
            check = board[0][i];
            if (board[0][i] == check &&
                board[1][i] == check &&
                board[2][i] == check) {
                if (check == marker)
                    return 0; //player wins
                if (check == cpuMarker)
                    return 1; //cpu wins
            }
        }
        
        //check diagonals
        char check = board[0][0];
        if (board[0][0] == check &&
            board[1][1] == check &&
            board[2][2] == check) {
            if (check == marker)
                return 0;
            if (check == cpuMarker) 
                return 1;
        }
        check = board[2][0];
        if (board[2][0] == check &&
            board[1][1] == check &&
            board[0][2] == check) {
            if (check == marker)
                return 0;
            if (check == cpuMarker) 
                return 1;
        }
        return -1;
    }
    
    public static void printBoard () {
        System.out.println("  A B C");
        int count = 1;
        for (int i = 0; i < board.length; i++) {
            System.out.print(count + " ");
            count++;
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
