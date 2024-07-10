package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String inputStr = scan.nextLine();
        char[] charArr = inputStr.toCharArray();
        int[] countChar = new int[65535];
        for(char i: charArr){
            countChar[i] += 1; 
        }
        char [] maxCharList = new char[10];
        int [] maxCountList = new int[10];
        char maxChar = ' ';
        int maxCount = 0;
        int maxIndex = 0;
        
        for (int i = 0; i < 10; i++) {
            maxChar = ' ';
            maxCount = 0;
            for (int j = 0; j < countChar.length; j++) {
                if (countChar[j] > maxCount) {
                    maxCount = countChar[j];
                    maxChar = (char) j;
                    maxIndex = j;
                }
            }
            maxCharList[i] = maxChar;
            maxCountList[i] = countChar[maxIndex];
            countChar[maxIndex] = 0;
        }

        System.out.println();
        drawGraph(maxCharList, maxCountList);
    }

    public static void drawGraph(char[] maxCharList, int[] maxCountList) {
        for (int i = 0; i < 10; i++) {
            if (maxCountList[i] == maxCountList[0]) {
                System.out.print(maxCountList[i] + "  ");
            }
        }
        System.out.println();
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (maxCountList[j] * 10 / maxCountList[0] >= i) {
                    System.out.print("#  ");
                }
                if (maxCountList[j] * 10 / maxCountList[0] == i - 1) {
                    if (maxCountList[j] != 0) {
                        if (maxCountList[j] >= 10) {
                            System.out.print(maxCountList[j] + " ");
                        } else {
                            System.out.print(maxCountList[j] + "  ");
                        }
                        
                    }
                } 
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++){
            System.out.print(maxCharList[i] + "  ");
    }
    }
}
