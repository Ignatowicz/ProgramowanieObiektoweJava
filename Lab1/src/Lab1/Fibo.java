package Lab1;

import java.util.Scanner;

public class Fibo {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = 0;

        while (n < 1 || 45 < n) {
            System.out.println("Podaj liczbe z zakresu od 1 do 45");
            n = scan.nextInt();
        }

        int[] tab = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0 | i == 1)
                tab[i] = 1;
            else
                tab[i] = tab[i - 1] + tab[i - 2];
        }

        for (int i = 0; i < n; i++) {
            System.out.println(tab[i]);
        }
    }
}
