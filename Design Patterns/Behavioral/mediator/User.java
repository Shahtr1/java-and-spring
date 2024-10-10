package Behavioral.mediator;

public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message);
}
