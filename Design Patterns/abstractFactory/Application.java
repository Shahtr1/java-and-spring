package abstractFactory;

import abstractFactory.uiFactory.UIFactory;

public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(UIFactory uiFactory) {
        button = uiFactory.createButton();
        checkbox = uiFactory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
