package abstractFactory;

import abstractFactory.uiFactory.WindowsUiFactory;

public class Main {
    public static void main(String[] args) {
        Application application = new Application(new WindowsUiFactory());
        application.paint();
    }
}
