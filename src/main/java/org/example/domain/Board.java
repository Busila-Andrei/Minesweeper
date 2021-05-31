package org.example.domain;

import java.util.Random;

public class Board {

    private final String[][] board = new String[9][9];
    Random random = new Random();

    public void createBoard(){

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                board[i][j] = ".";
            }
        }

    }

    public void createMines(int count){
        for (int k = 0; k <count; k++){
            for(;;){
                int i = random.nextInt(9);
                int j = random.nextInt(9);
                if (board[i][j].equals(".")){
                    board[i][j] = "X";
                    break;
                }
            }
        }
    }

    public void numberMines() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int k = 0;
                if (board[i][j].equals("."))
                    k = miniMatrix(i,j,k);
                if (k != 0){
                    board[i][j] = String.valueOf(k);
                }
            }
        }
    }

    public int miniMatrix(int mi, int mj, int k){
        for (int i = mi - 1; i <= mi + 1; i++) {
            if (i < 0 || i >= 9) continue;
            for (int j = mj - 1; j <= mj + 1; j++) {
                if (j < 0 || j >= 9) continue;
                if (board[i][j].equals("X"))
                    k++;
            }
        }
        return k;
    }

    public void printBoard(){
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int i = 0; i < 9; i++){
            System.out.print((i+1)+"|");
            for (int j = 0; j < 9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("—│—————————│");
    }

    public String getCharacter(int i, int j) {
        return board[i][j];
    }

    public void setCharacter(int i, int j, String character) {
        this.board[i][j] = character;
    }
}
