import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class User implements IterableByUser {
    private String username;
    private ChatHistory chatHistory;
    private Map<User, Boolean> blockedUsers; // Map to store blocked users
    private String text;
    private MessageMemento memento = new MessageMemento(text);
    private Message message;

    public User(String username) {
        this.username = username;
        this.chatHistory = new ChatHistory();
        this.blockedUsers = new HashMap<>();
        this.message = this.chatHistory.getLastMessage();
    }

    public void sendMessage(User recipient, String messageContent) {
        if (!recipient.blockedUsers.containsKey(this)) {
            Message message = new Message(this, new User[] { recipient }, messageContent);
            ChatServer.getInstance().sendMessage(this, recipient, messageContent);
            MessageMemento memento = new MessageMemento(message);
            memento.setState(message);
            chatHistory.addMessage(message);

        } else {
            System.out.println(recipient.getUsername() + " cannot receive any messages from " + this.username
                    + " because " + this.username + " has been blocked by " + recipient.getUsername());
        }

    }

    public void save() {
        this.memento.setState(this.chatHistory.getLastMessage());
    }

    // public void undo() {
    // Message lastMessage = this.getChatHistory().getLastMessage();
    // chatHistory.removeLastMessage();
    // //chatHistory.addMessage(lastMessage);
    // }

    // public void undo() {
    // Message lastMessage = chatHistory.getLastMessage();
    // if (lastMessage != null && lastMessage.getSender().equals(this)) {
    // chatHistory.removeLastMessage();
    // }
    // }

    // undo so remove last message then add new message
    public void undo() {
        User[] recipients;

        recipients = this.chatHistory.getLastMessage().getRecipients();
        

        for (int i = 0; i < recipients.length; i++) {
            recipients[i].chatHistory.removeLastMessage();
        }
        this.chatHistory.removeLastMessage();

        //this.memento.getState().getContent();
    }

    // public void undo() {
    // this.message = this.chatHistory.getLastMessage();
    // this.message.setContent(this.memento.getState().getContent());
    // System.out.println("Undo has been called.\n");
    // }

    public void blockUser(User userToBlock) {
        this.blockedUsers.put(userToBlock, true);
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
