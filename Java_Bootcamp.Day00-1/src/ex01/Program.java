import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        int n = scan.nextInt();
        int i = 2;
        int j = 0;
        int count_it = 0;
        if (n <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            while(i * i <= n && j != 1) {
                count_it += 1;
                if (n % i == 0) {
                    j = 1;
                }
                i += 1;
            }
            if (j == 1) {
                System.out.println("false" + " " + count_it);
            } else {
                System.out.println("true" + " " + count_it);
            }
        }
    }
}