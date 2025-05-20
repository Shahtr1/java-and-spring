package Creational.abstractFactory.uiFactory;

import Creational.abstractFactory.Button;
import Creational.abstractFactory.Checkbox;
import Creational.abstractFactory.os.mac.MacButton;
import Creational.abstractFactory.os.mac.MacCheckbox;

public class MacUiFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
