package Creational.abstractFactory.os.mac;

import Creational.abstractFactory.Checkbox;

public class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Mac Checkbox");
    }
}
