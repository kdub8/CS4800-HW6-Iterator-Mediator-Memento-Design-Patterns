import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//mediator class
public class ChatServer {
    private static ChatServer instance;
    private List<User> registeredUsers;
    private Map<User, List<User>> blockedUsers; // Map to store blocked users

    private ChatServer() {
        this.registeredUsers = new ArrayList<>();
        this.blockedUsers = new HashMap<>();
    }


    public List<User> getUserList() {
        return this.registeredUsers;
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
            recipient.receiveMessage(new Message(sender, new User[] { recipient }, messageContent));
        }
    }



    public void blockUser(User blocker, User userToBlock) {
        if (!blockedUsers.containsKey(blocker)) {
            blockedUsers.put(blocker, new ArrayList<>());
        }
        blockedUsers.get(blocker).add(userToBlock);
    }


    public void unblockUser(User unblocker, User userToUnblock) {
        if (blockedUsers.containsKey(unblocker)) {
            List<User> blockedList = blockedUsers.get(unblocker);
            blockedList.remove(userToUnblock);
        }
    }

    public Map<User, List<User>> getBlockedUsers() {
        return blockedUsers;
    }

}
