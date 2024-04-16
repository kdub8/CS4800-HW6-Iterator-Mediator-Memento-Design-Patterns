import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Mediator class for managing user interactions
public class ChatServer {
    private static ChatServer instance;
    private List<User> registeredUsers;
    private Map<User, List<User>> blockedUsers; // Map to store blocked users
    // Private constructor to prevent instantiation from outside
    private ChatServer() {
        this.registeredUsers = new ArrayList<>();
        this.blockedUsers = new HashMap<>();
    }

    public List<User> getUserList() {
        return this.registeredUsers;
    }
    // Get the singleton instance of ChatServer
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
            recipient.receiveMessage(new Message(sender, new User[] { recipient }, messageContent));
        }
    }
    // Block a user from communicating with another user
    public void blockUser(User blocker, User userToBlock) {
        if (!blockedUsers.containsKey(blocker)) {
            blockedUsers.put(blocker, new ArrayList<>());
        }
        blockedUsers.get(blocker).add(userToBlock);
    }

    // Unblock a user, allowing them to communicate again
    public void unblockUser(User unblocker, User userToUnblock) {
        if (blockedUsers.containsKey(unblocker)) {
            List<User> blockedList = blockedUsers.get(unblocker);
            blockedList.remove(userToUnblock);
        }
    }
    // Get a map of blocked users
    public Map<User, List<User>> getBlockedUsers() {
        return blockedUsers;
    }

}
