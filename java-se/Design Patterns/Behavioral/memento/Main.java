package Behavioral.memento;

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current state: " + originator.getState());

        originator.getStateFromMemento(careTaker.getMemento(0));
        System.out.println("First saved state: " + originator.getState());
        originator.getStateFromMemento(careTaker.getMemento(1));
        System.out.println("Second saved state: " + originator.getState());

    }
}
