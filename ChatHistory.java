import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ChatHistory implements IterableByUser {
    private List<Message> messages;
    // Constructor to initialize the ChatHistory object
    public ChatHistory() {
        this.messages = new ArrayList<>();
    }
    // Method to add a message to the chat history
    public void addMessage(Message message) {
        messages.add(message);
    }
    // Method to remove a message from the chat history
    public void removeMessage(Message message) {
        messages.remove(message);
    }

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
    // Method to create an iterator for iterating over messages in the chat history
    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new ChatHistoryIterator(this.messages, userToSearchWith);
    }
    // Iterator class for iterating over messages in the chat history
     public static class ChatHistoryIterator implements Iterator<Message> {
        private List<Message> messages;
        private int index;
        private User userToSearchWith;

        // Constructor to initialize the iterator
        public ChatHistoryIterator(List<Message> messages, User userToSearchWith) {
            this.messages = messages;
            this.userToSearchWith = userToSearchWith;
            this.index = 0;
        }
        // Method to check if there are more messages for the specified user
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
        // Method to return the next message for the specified user
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
        // Method to check if a message contains a specific recipient
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
