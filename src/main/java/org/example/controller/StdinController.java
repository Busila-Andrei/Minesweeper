package org.example.controller;

import org.example.controller.utils.ScannerUtils;

public class StdinController implements UserInputController {

    @Override
    public int inputCountMines() {
        System.out.print("How many mines do you want on the field? ");
        int input = ScannerUtils.nextInt();
        System.out.println();
        return input;
    }

    @Override
    public String inputCoordinates() {
        System.out.print("Set/delete mines marks (x and y coordinates): ");
        String input = ScannerUtils.nextLine();
        System.out.println();
        return input;
    }
}
