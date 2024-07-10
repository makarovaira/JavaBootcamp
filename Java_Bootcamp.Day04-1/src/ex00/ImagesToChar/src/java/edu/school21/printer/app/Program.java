package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Incorrect arguments");
            System.exit(-1);
        }

        char whiteSymbol = args[0].charAt(0);
        char blackSymbol = args[1].charAt(0);
        String imagePath = args[2];

        int[][] imageArray = Logic.imageConversion(whiteSymbol, blackSymbol, imagePath);
        Logic.returnImage(imageArray);
    }
}
