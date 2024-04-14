import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static ChatServer instance;
    private List<User> registeredUsers;

    private ChatServer() {
        this.registeredUsers = new ArrayList<>();
    }

    public static ChatServer getInstance() {
        if (instance == null) {
            instance = new ChatServer();
        }
        return instance;
    }

    public void registerUser(User user) {
        registeredUsers.add(user);
    }

    public void unregisterUser(User user) {
        registeredUsers.remove(user);
    }

    public void sendMessage(User sender, User recipient, String messageContent) {
        if (registeredUsers.contains(sender) && registeredUsers.contains(recipient)) {
            recipient.receiveMessage(new Message(sender, new User[]{recipient}, messageContent));
        }
    }

    public void blockUser(User blocker, User userToBlock) {
        if (registeredUsers.contains(blocker) && registeredUsers.contains(userToBlock)) {
            userToBlock.blockUser(blocker);
        }
    }
}
