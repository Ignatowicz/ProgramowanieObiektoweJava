package Lab1;

import java.util.Scanner;

public class Problem115A {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = 0;

        while (n < 1 || 2000 < n) {
            System.out.println("Podaj ilość pracowników z zakresu od 1 do 2000:");
            n = scan.nextInt();
        }
        int[] result = new int[n];


        for (int i = 0; i < n; i++) {
            System.out.println("Podaj nr pracownika, który jest menagerem dla osoby nr " + (i+1));
            result[i] = scan.nextInt();
        }

        int total = 0;

        for (int i = 0; i < n; i++) {
            int tmp = 1;
            int szef = result[i];
            while (szef != -1) {
                szef = result[szef-1];
                tmp++;
            }
            if (tmp > total)
                total = tmp;
        }

        System.out.println(total);

    }
}
