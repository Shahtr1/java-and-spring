package abstractFactory.uiFactory;

import abstractFactory.Button;
import abstractFactory.Checkbox;
import abstractFactory.os.mac.MacButton;
import abstractFactory.os.mac.MacCheckbox;

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
