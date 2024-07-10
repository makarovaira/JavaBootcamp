package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Logic {
    public static int[][] imageConversion(char white, char black, String filePath) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        int array[][] = new int[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight() ; j++) {
                int color = image.getRGB(i, j);
                if (color == Color.WHITE.getRGB()) {
                    array[i][j] = white;
                } else if (color == Color.BLACK.getRGB()) {
                    array[i][j] = black;
                }
            }
        }
        return array;
    }

    public static void returnImage(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print((char)array[j][i]);
            }
            System.out.println();
        }
    }

}
