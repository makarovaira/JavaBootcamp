class Main {
    public static void main(String[] args) {
        int a = 479598;
        int sum = 0;
        sum += a % 10;
        a /= 10;
        sum += a % 10;
        a /= 10;
        sum += a % 10;
        a /= 10;
        sum += a % 10;
        a /= 10;
        sum += a % 10;
        a /= 10;
        sum += a % 10;
        System.out.println(sum);
    }
}