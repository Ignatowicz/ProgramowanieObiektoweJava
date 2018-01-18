package lab12;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        initArray(100000000);
//
//        new MeanCalc(24,55).run();
//
//        MeanCalc.parallelMean(16);
//    }


    public static void main(String[] args) throws InterruptedException {
        initArray(128000000);

        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            MeanCalc.parallelMean(cnt);
        }
        //  srednia wyszla taka sama dla kazdej ilosci watkow
        //  najkrotszy czas zostal uzyskany dla 4 watkow, potem 8,1,2,16,31,64,128
    }



    static class MeanCalc extends Thread {
        private final int start;
        private final int end;
        double mean = 0;

        static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

        MeanCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for(int i=start; i<end; i++) {
                mean += array[i];
            }

            mean = mean/(end-start);
            System.out.printf(Locale.US, "%d-%d mean=%f\n", start, end, mean);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /**
         * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
         * Wypisuje czasy operacji
         * @param cnt - liczba wątków
         */
        static void parallelMean(int cnt) throws InterruptedException {
            // utwórz tablicę wątków
            MeanCalc threads[] = new MeanCalc[cnt];

            // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
            // załóż, że array.length dzieli się przez cnt)
            int offset = array.length/cnt;
            for (int i=0; i<cnt; i++) {
                threads[i] = new MeanCalc(i*offset, (i+1)*offset);
            }

            double t1 = System.nanoTime()/1e6;


            double mean = 0;

            //uruchom wątki
            for(MeanCalc thread : threads) {
                thread.start();
                mean += results.take();
            }
            double t2 = System.nanoTime()/1e6;

            // czekaj na ich zakończenie używając metody ''join''
            for(MeanCalc mc:threads) {
                mc.join();
            }


            // oblicz średnią ze średnich


            /*
            for (MeanCalc thread : threads) {
                mean += thread.mean;
            }
            */
            mean = mean/cnt;

            double t3 = System.nanoTime()/1e6;

            System.out.printf(Locale.US,"size = %d \ncnt = %d >  \nt2-t1 = %f \nt3-t1 = %f \nMEAN = %f\n",
                    array.length,
                    cnt,
                    t2-t1,
                    t3-t1,
                    mean);
        }
    }
}