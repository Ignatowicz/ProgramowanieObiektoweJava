package Elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {

    static class InternalCall {

        private final int toFloor;

        InternalCall(int toFloor) {
            this.toFloor = toFloor;
        }
    }

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);

    ElevatorCar elevatorCar;


    InternalPanelAgent(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }


    public void run() {
        while (true) {

            // odczytaj wezwanie z kolejki
            InternalPanelAgent.InternalCall ec = null;

            try {
                ec = input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // ignorujemy wezwanie na piętro, na którym winda się znajduje
            if (ec.toFloor == elevatorCar.getFloor())
                continue;

            // w zależności od aktualnego piętra, na którym jest winda,
            // umieść przystanek w odpowiedniej tablicy ''EleveatorStops''
            if (ec.toFloor > elevatorCar.floor) {
                ElevatorStops.get().setLiftStopUp(ec.toFloor);
            } else {
                ElevatorStops.get().setLiftStopDown(ec.toFloor);
            }
        }
    }
}