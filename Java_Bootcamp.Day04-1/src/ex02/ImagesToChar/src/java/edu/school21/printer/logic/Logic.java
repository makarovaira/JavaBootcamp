package edu.school21.printer.logic;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Logic {
    private final Attribute white;
    private final Attribute black;

    public Logic(String white, String black) {
        this.white = chooseColor(white);
        this.black = chooseColor(black);
    }

    private Attribute chooseColor(String input) {
        switch (input) {
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "RED":
                return Attribute.RED_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "WHITE":
                return Attribute.WHITE_BACK();
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "BRIGHT_RED":
                return Attribute.BRIGHT_RED_BACK();
            case "BRIGHT_GREEN":
                return Attribute.BRIGHT_GREEN_BACK();
            case "BRIGHT_BLUE":
                return Attribute.BRIGHT_BLUE_BACK();
            case "BRIGHT_BLACK":
                return Attribute.BRIGHT_BLACK_BACK();
            case "BRIGHT_WHITE":
                return Attribute.BRIGHT_WHITE_BACK();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void returnImage(BufferedImage image) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                if (pixel == Color.BLACK.getRGB()) {
                    System.out.print(Ansi.colorize(" ", black));
                } else if (pixel == Color.WHITE.getRGB()) {
                    System.out.print(Ansi.colorize(" ", white));
                }
            }
            System.out.println();
        }
    }
}