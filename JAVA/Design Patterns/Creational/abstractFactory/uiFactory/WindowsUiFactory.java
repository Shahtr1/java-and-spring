package Creational.abstractFactory.uiFactory;

import Creational.abstractFactory.Button;
import Creational.abstractFactory.Checkbox;
import Creational.abstractFactory.os.win.WinButton;
import Creational.abstractFactory.os.win.WinCheckbox;

public class WindowsUiFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}
