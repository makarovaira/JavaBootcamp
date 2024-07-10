package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Incorrect arguments");
            System.exit(-1);
        }

        char whiteSymbol = args[0].charAt(0);
        char blackSymbol = args[1].charAt(0);

        try {
            int[][] imageArray = Logic.imageConversion(whiteSymbol, blackSymbol, "src/java/resources/it.bmp");
            Logic.returnImage(imageArray);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

    }
}

