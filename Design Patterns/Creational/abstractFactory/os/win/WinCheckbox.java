package Creational.abstractFactory.os.win;

import Creational.abstractFactory.Checkbox;

public class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Win Checkbox");
    }
}
