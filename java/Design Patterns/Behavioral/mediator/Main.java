package Behavioral.mediator;

public class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl("Nikhil", mediator);
        User user2 = new UserImpl("Shivam", mediator);
        User user3 = new UserImpl("Vishrut", mediator);
        User user4 = new UserImpl("Ali", mediator);
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.sendMessage("Subscribe to Daily Code Buffer!!");
    }
}
