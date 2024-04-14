import java.util.Date;

public class MessageMemento {
    private Date timestamp;
    private String content;

    public MessageMemento(Date timestamp, String content) {
        this.timestamp = timestamp;
        this.content = content;
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
