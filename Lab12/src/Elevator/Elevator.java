package Elevator;

public class Elevator {
    // tworzymy 3 wątki
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor, boolean directionUp) {
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor, directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor) {
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // uruchomienie wątków
    static void init() {
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    // miesjce na kod testowy
    public static void main(String[] args) throws InterruptedException {
        init();
//        makeExternalCall(4, false);
//        Thread.currentThread().sleep(100);
//        makeInternalCall(2);
//        makeInternalCall(3);
//        makeInternalCall(6);
//        makeExternalCall(4, true);
//        makeInternalCall(3);
        makeExternalCall(0,true);
        makeInternalCall(8);
        makeExternalCall(4,false);
        Thread.currentThread().sleep(100);
        makeInternalCall(2);
    }
}