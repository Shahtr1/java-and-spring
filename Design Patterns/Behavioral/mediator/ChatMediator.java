package Behavioral.mediator;

public interface ChatMediator {
    public void sendMessage(String message, User user);

    void addUser(User user);
}
