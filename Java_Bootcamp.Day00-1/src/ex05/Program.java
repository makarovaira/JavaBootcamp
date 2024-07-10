package ex05;

import java.util.Scanner;

class Program {
    

    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String[] studentList = new String[10];
        String[][] classList = new String[7][6];
        createStList(scan, studentList);
    }

    public static void createStList(Scanner scan, String[] studentList) {
        int countSt = 0;
        while(countSt < 10) {
            String name = scan.nextLine();
            if (name.equals(".")) {
                break;
            }
            if (name.length() > 10) {
                System.err.println("Name is too long. Try again");
            } else {
                studentList[countSt] = name;
                countSt++;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(studentList[i]);
        }
    }

    public static void fillInSchedule(Scanner scan, String[][] classList) {
        int countClass = 0;
        while(countClass < 10) {
            String classSchedule = scan.nextLine();
            if (classSchedule.equals(".")) {
                break;
            }
            String[] timeAndDay = classSchedule.split(" ");
            
        }
    }

}