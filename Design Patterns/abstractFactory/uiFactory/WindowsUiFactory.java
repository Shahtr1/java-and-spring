package abstractFactory.uiFactory;

import abstractFactory.Button;
import abstractFactory.Checkbox;
import abstractFactory.os.win.WinButton;
import abstractFactory.os.win.WinCheckbox;

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
