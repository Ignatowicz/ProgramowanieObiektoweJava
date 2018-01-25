package zadanie2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Transform {

    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static double[] array;

    static void initArray(int size) {
        array = new double[size];

        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(1000000000);
        }
    }

    public static void main(String[] args) {
        initArray(100000000);


        UnaryOperator<Double> unaryOpt = i->i*i;

        try {
            parallelOperator(4, unaryOpt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static void parallelOperator(int cnt, UnaryOperator operator) throws InterruptedException {
        // tworzy tablicę wątków
        Operatorki threads[] = new Operatorki[cnt];

        int offset = array.length / cnt;

        for (int i = 0; i < cnt; i++) {
            threads[i] = new Operatorki(i * offset, (i + 1) * offset);
        }

        double t1 = System.nanoTime() / 1e6;


        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        double t2 = System.nanoTime() / 1e6;


        for (int i = 0; i < cnt; i++) {
            double tmp = results.take();
        }

        //array.forEach(x->System.out.println(x));



        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "\nsize = %d\ncnt = %d\nt2-t1 = %f\nt3-t1 = %f\n\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1);
    }


    static class Operatorki extends Thread {
        private final int start;
        private final int end;
        double max;

        Operatorki(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            this.max = array[this.start];

            for (int i = start + 1; i < end; i++) {
                if (array[i] > this.max) this.max = array[i];
            }

            System.out.printf(Locale.US, "%d-%d\n", start, end);

            try {
                results.put(this.max);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}