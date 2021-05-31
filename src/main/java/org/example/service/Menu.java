package org.example.service;

import org.example.controller.StdinController;
import org.example.controller.UserInputController;
import org.example.domain.Board;

public class Menu {

    private final Board board = new Board();
    private final UserInputController inputController = new StdinController();
    private final Board screenBoard = new Board();

    public void start() {
        int count = inputController.inputCountMines();
        board.createBoard();
        board.createMines(count);
        board.numberMines();
        screenBoard.createBoard();
        screenBoard.printBoard();
        play();
    }


    public void play() {
        MAIN:
        for (; ; ) {
            String[] strings = inputController.inputCoordinates().split(" ");
            int j = Integer.parseInt(strings[0]) - 1;
            int i = Integer.parseInt(strings[1]) - 1;
            String mode = strings[2];
            switch (mode) {
                case "mine":
                    if (screenBoard.getCharacter(i,j).equals(".")){
                        if (board.getCharacter(i,j).equals("X")){
                            board.setCharacter(i, j, "*");
                        }
                        screenBoard.setCharacter(i, j, "*");
                    } else if (screenBoard.getCharacter(i,j).equals("*")) {
                        if (board.getCharacter(i,j).equals("*")){
                            board.setCharacter(i, j, "X");
                        }
                        screenBoard.setCharacter(i, j, ".");
                }
                    screenBoard.printBoard();
                    break;
                case "free":
                    if (board.getCharacter(i, j).equals("X")) {
                        printMines();
                        screenBoard.printBoard();
                        System.out.println("You stepped on a mine and failed!");
                        break MAIN;
                    } else logic(i, j);
                    screenBoard.printBoard();

                    break;
            }
            if (finishGameMethodOne()){
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
            if (finishGameMethodTwo()){
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }
    }

    public void logic(int x, int y) {

        if (existMines(x, y)) {
             screenBoard.setCharacter(x, y, board.getCharacter(x,y));
        } else { if (board.getCharacter(x, y).equals(".")) {
            screenBoard.setCharacter(x, y, "/");
        }
            for (int i = x - 1; i <= x + 1; i++) {
                if (i < 0 || i >= 9) continue;
                for (int j = y - 1; j <= y + 1; j++) {
                    if (j < 0 || j >= 9) continue;
                    if (i == x && j == y) continue;
                    if (screenBoard.getCharacter(i, j).equals(".") || screenBoard.getCharacter(i, j).equals("*"))
                        logic(i, j);
                }
            }
        }
    }


    public boolean existMines(int mi, int mj) {
        for (int i = mi - 1; i <= mi + 1; i++) {
            if (i < 0 || i >= 9) continue;
            for (int j = mj - 1; j <= mj + 1; j++) {
                if (j < 0 || j >= 9) continue;
                if (board.getCharacter(i, j).equals("X") || board.getCharacter(i, j).equals("*")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean finishGameMethodOne(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getCharacter(i,j).equals("X"))
                    return false;
            }
        }
        return true;
    }
    public boolean finishGameMethodTwo(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (screenBoard.getCharacter(i,j).equals(".")){
                    if (!board.getCharacter(i,j).equals("X")){
                        return false;
                    }
                }
            }
        }
        return true;
    }




    public void printMines() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getCharacter(i, j).equals("X")) {
                    screenBoard.setCharacter(i, j, "X");
                }
            }
        }
    }
}





