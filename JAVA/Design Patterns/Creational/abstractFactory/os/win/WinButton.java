package Creational.abstractFactory.os.win;

import Creational.abstractFactory.Button;

public class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Win button");
    }
}
