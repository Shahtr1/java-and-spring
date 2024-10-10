package Creational.abstractFactory.os.mac;

import Creational.abstractFactory.Button;

public class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Mac Button");
    }
}
