package org.example.controller.utils;

import java.util.Scanner;

public class ScannerUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String nextLine() {
        return SCANNER.nextLine();
    }

    public static int nextInt() {
        int input = SCANNER.nextInt();
        SCANNER.nextLine();
        return input;
    }
}
