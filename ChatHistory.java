import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ChatHistory implements IterableByUser {
    private List<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }


    // public Message getLastMessage() {
    //     return messages.get(messages.size() - 1);
    // }

    public Message getLastMessage() {
        return messages.isEmpty() ? null : messages.get(messages.size() - 1);
    }

    public List<Message> getMessageList() {
        return messages;
    }

    public void removeLastMessage() {
        if (!messages.isEmpty()) {
            messages.remove(messages.size() - 1);
        }
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new ChatHistoryIterator(this.messages, userToSearchWith);
    }

     public static class ChatHistoryIterator implements Iterator<Message> {
        private List<Message> messages;
        private int index;
        private User userToSearchWith;
        public ChatHistoryIterator(List<Message> messages, User userToSearchWith) {
            this.messages = messages;
            this.userToSearchWith = userToSearchWith;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            // Implement hasNext() to check if there are more messages for the specified user
            while (index < messages.size()) {
                Message message = messages.get(index);
                if (message.getSender().equals(userToSearchWith) ||  containsRecipient(message, userToSearchWith)) {
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override
        public Message next() {
            // Implement next() to return the next message for the specified user
            while (index < messages.size()) {
                Message message = messages.get(index);
                index++;
                if (message.getSender().equals(userToSearchWith) || Arrays.asList(message.getRecipients()).contains(userToSearchWith)) {
                    return message;
                }
            }
            return null;
        }

        private boolean containsRecipient(Message message, User user) {
            for (User recipient : message.getRecipients()) {
                if (recipient.equals(user)) {
                    return true;
                }
            }
            return false;
        }
    }
}
