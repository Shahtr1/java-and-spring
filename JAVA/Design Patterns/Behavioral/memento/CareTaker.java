package Behavioral.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private final List<Memento> mementos = new ArrayList<>();

    public void add(Memento state) {
        mementos.add(state);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}
