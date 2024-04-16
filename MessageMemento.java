
import java.util.Date;

public class MessageMemento {
    private Date timestamp;
    private String content;
    private Message message;
    private String state;

    public MessageMemento(Message message) {
        this.timestamp = message.getTimestamp();
        this.content = message.getContent();
        this.message = message;
    }

    public MessageMemento(String state) {
        this.state = state;
    }

    public void restoreFromMemento(User sender, User recipient, Message message) {
        Message lastMessage = sender.getChatHistory().getLastMessage();
        ChatHistory senderHistory = sender.getChatHistory();
        senderHistory.removeMessage(lastMessage);
        recipient.getChatHistory().removeMessage(message);
    }

    public Message getState() {
        return message;
    }

    public void setState(Message message) {
        this.message = message;
    }




    public String getContent() {
        return this.content;
    }

    public void print() {
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Content: " + content);
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
