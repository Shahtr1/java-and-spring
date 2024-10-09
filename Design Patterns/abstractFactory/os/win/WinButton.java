package abstractFactory.os.win;

import abstractFactory.Button;

public class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Win button");
    }
}
