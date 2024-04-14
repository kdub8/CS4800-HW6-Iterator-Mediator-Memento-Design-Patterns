import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatApplication {
    public static void main(String[] args) throws InterruptedException {
        // Create users
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Deby");
        User user4 = new User("Kevin");

        // Register users with the chat server
        ChatServer.getInstance().registerUser(user1);
        ChatServer.getInstance().registerUser(user2);
        ChatServer.getInstance().registerUser(user3);
        ChatServer.getInstance().registerUser(user4);

        // Send messages between users

        // Block user2 by user1
        user1.blockUser(user2);

        user1.sendMessage(user2, "Hello, Bob!");
        user1.save();
        user1.sendMessage(user4, "UNDO THIS MESSAGE");

        // undo the last message from user1
        user1.undo();

        user2.sendMessage(user1, "Hi, Alice!");
        user2.save();
        user3.sendMessage(user1, "What's going on, Alice!");
        user3.save();
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.SECONDS.sleep(1);
        user4.sendMessage(user3, "Hey Deby, it's me Kevin!"); // this message is sent 2 seconds after
        user3.save();

        // Attempt to send a message after blocking
        user2.sendMessage(user1, "THIS MESSAGE SHOULD BE GETTING BLOCKED AND SHOULD NOT BE SEEN");
        System.out.println();
        for (User user : ChatServer.getInstance().getUserList()) {

            System.out.println("Chat history for " + user.getUsername() + ":");

            for (Message message : user.getChatHistory().getMessageList()) {
                System.out.println(message.getSender().getUsername() + " -> " + message.getContent());
                System.out.println();
            }

        }

        System.out.println();

        System.out.println("-------------------------Viewing the chat history for " + user3.getUsername()
                + "-------------------------");
        List<Message> msgList = user3.getChatHistory().getMessageList();

        for (Message msg : msgList) {
            System.out.println("Timestamp: " + msg.getTimestamp());
            System.out.println("Message content: " + msg.getContent());
            System.out.println();
        }

    }
}
