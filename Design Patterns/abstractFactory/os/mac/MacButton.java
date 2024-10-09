package abstractFactory.os.mac;

import abstractFactory.Button;

public class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Mac Button");
    }
}
