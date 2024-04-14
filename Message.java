import java.util.Date;

public class Message {
    private User sender;
    private User[] recipients;
    private Date timestamp;
    private String content;

    public Message(User sender, User[] recipients, String content) {
        this.sender = sender;
        this.recipients = recipients;
        this.timestamp = new Date();
        this.content = content;

    }


    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User[] getRecipients() {
        return this.recipients;
    }

    public void setRecipients(User[] recipients) {
        this.recipients = recipients;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    

}
