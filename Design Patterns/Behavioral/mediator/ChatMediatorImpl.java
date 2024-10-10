package Behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {
    private final List<User> users;

    public ChatMediatorImpl() {
        users = new ArrayList<>();
    }


    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            // message should not be received by the user sending it
            if (u != user) {
                u.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
