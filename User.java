import java.util.Map;
import java.util.Stack;
import java.util.HashMap;
import java.util.Iterator;

public class User implements IterableByUser {
    private String username;
    private ChatHistory chatHistory;
    private Map<User, Boolean> blockedUsers; // Map to store blocked users
    private Stack<MessageMemento> messageHistory;

    public User(String username) {
        this.username = username;
        this.chatHistory = new ChatHistory();
        this.blockedUsers = new HashMap<>();
    }


    public void sendMessage(User recipient, String messageContent) {
        if (!blockedUsers.containsKey(recipient)) {
            Message message = new Message(this, new User[]{recipient}, messageContent);
            ChatServer.getInstance().sendMessage(this, recipient, messageContent);
            chatHistory.addMessage(message);
        } else {
            System.out.println("Cannot send message to blocked user: " + recipient.getUsername());
        }

        
    }

    public void undoLastMessage() {
        Message lastMessage = chatHistory.getLastMessage();
        if (lastMessage != null && lastMessage.getSender().equals(this)) {
            chatHistory.removeLastMessage();
        }
    }

    public void blockUser(User userToBlock) {
        blockedUsers.put(userToBlock, true);
    }

    public void unblockUser(User userToUnblock) {
        blockedUsers.remove(userToUnblock);
    }

    public void receiveMessage(Message message) {
        if (!blockedUsers.containsKey(message.getSender())) {
            chatHistory.addMessage(message);
        }
    }

    public ChatHistory getChatHistory() {
        return this.chatHistory;
    }

    public Iterator<Message> iterator(User userToSearchWith) {
        return this.chatHistory.iterator(userToSearchWith);
    }

    public String getUsername() {
        return this.username;
    }
}
