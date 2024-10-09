package abstractFactory.uiFactory;

import abstractFactory.Button;
import abstractFactory.Checkbox;


public interface UIFactory {
    Button createButton();

    Checkbox createCheckbox();
}
