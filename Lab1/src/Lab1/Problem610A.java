package Lab1;

import java.util.Scanner;

public class Problem610A {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = 0;
        int counter = 0;

        while (n < 1 || (2 * Math.pow(10, 9)) < n) {
            System.out.println("Podaj liczbe z zakresu od 1 do 2*10^9");
            n = scan.nextInt();
        }

        if (n % 2 == 0) {
            for (int i = 1; i < n / 4 + 1; i++) {
                if ((n - 2 * i) > i && (n - 2 * i) % 2 == 0)
                    counter++;
            }
        }

        if (n % 4 == 0 && counter > 0)
            counter--;

        System.out.println(counter);
    }
}

