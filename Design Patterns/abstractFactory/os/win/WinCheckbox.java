package abstractFactory.os.win;

import abstractFactory.Checkbox;

public class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Win Checkbox");
    }
}
