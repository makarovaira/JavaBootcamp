package ex03;

import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String week;
        int countWeek = 1;
        int ans = 0;
        while (countWeek <= 18 && !(week = scan.nextLine()).equals("42")) {
            if (week.equals("Week " + countWeek)) {
                countWeek += 1;
            } else {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
           
            int minVal = scan.nextInt();
            for (int i = 0; i < 4; i++) {
                int num2 = scan.nextInt();
                if (checkNum(minVal) && checkNum(num2)) {
                    if (num2 < minVal) {
                        minVal = num2;
                    }
                } else {
                    System.err.println("Illegal Argument");
                    System.exit(-1);
                }
            }
            ans = ans * 10 + minVal;
            scan.nextLine();
        }
        drawGraph(ans);
        scan.close();
    }
    
    public static boolean checkNum(int n) {
        return (n > 0 && n < 10);
    }

    public static void drawGraph(int nums) {
        int length = findLength(nums);
        int l = length;
        int countWeek = 1;
        while (l > 0) {
            String outLine = "Week ";
            outLine = outLine + countWeek + " ";
            int n = nums / pow(10, l - 1); 
            for (int i = 0; i < n; i++) {
                outLine += "=";
            }
            outLine += ">";
            nums = nums - (n * pow(10, l - 1));
            l--;
            countWeek += 1;
            System.out.println(outLine);
        }
        
    }

    public static int findLength(int nums) {
        int length = 0;
        int temp = 1;
        while (temp <= nums) {
            length++;
            temp *= 10;
        }
        return length;
    }

    public static int pow(int value, int powVal) {
        int res = 1;
        for (int i = 1; i <= powVal; i++) {
            res = res * value;
        }
        return res;
     }
}

