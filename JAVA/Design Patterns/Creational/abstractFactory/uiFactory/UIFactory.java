package Creational.abstractFactory.uiFactory;

import Creational.abstractFactory.Button;
import Creational.abstractFactory.Checkbox;


public interface UIFactory {
    Button createButton();

    Checkbox createCheckbox();
}
