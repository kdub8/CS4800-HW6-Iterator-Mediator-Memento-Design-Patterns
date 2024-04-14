
import java.util.Date;

public class MessageMemento {
    private Date timestamp;
    private String content;
    private Message message;
    private String state;

    public MessageMemento(Message message) {
        this.timestamp = message.getTimestamp();
        this.content = message.getContent();
    }

    public MessageMemento(String state) {
        this.state = state;
    }

    public Message getState() {
        return message;
    }

    public void setState(Message message) {
        this.message = message;
        setText(this.message);
    }

    public void setText(Message message) {
        this.timestamp = message.getTimestamp();
        this.content = message.getContent();
    }

    // public MessageMemento getState() {
    //     return this;
    // }

    // public void setState(Message message) {
    //     this.timestamp = message.getTimestamp();
    //     this.content = message.getContent();
    // }

    public String getContent() {
        return this.content;
    }

    public void print() {
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Content: " + content);
    }
}
