import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        int countPrNumb = 0;
        while (true) {
            int n = scan.nextInt();
            if (n == 42) {
                break;
            }
            int sumOfDigits = 0;
            while (n > 0) {
                int digit = n % 10;
                sumOfDigits += digit;
                n = n / 10;
            }
            int i = 2;
            int j = 0;
            while(i * i <= sumOfDigits && j != 1) {
                if (sumOfDigits % i == 0) {
                    j = 1;
                }
                i += 1;
            }
            if (j != 1) {
                countPrNumb += 1;
            } 
        }
        System.out.println("Count of coffee-request - " + countPrNumb);
    }
}