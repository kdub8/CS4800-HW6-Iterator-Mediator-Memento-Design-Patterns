import java.util.ArrayList;
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

    public Message getLastMessage() {
        return messages.isEmpty() ? null : messages.get(messages.size() - 1);
    }

    public List<Message> getMessageList() {
        return this.messages;
    }

    public void removeLastMessage() {
        if (!messages.isEmpty()) {
            messages.remove(messages.size() - 1);
        }
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new UserMessageIterator(userToSearchWith);
    }

    private class UserMessageIterator implements Iterator<Message> {
        private int index;
        private User userToSearchWith;

        public UserMessageIterator(User userToSearchWith) {
            this.index = 0;
            this.userToSearchWith = userToSearchWith;
        }

        @Override
        public boolean hasNext() {
            while (index < messages.size()) {
                Message message = messages.get(index);
                if (message.getSender().equals(userToSearchWith) || containsRecipient(message, userToSearchWith)) {
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override
        public Message next() {
            if (hasNext()) {
                Message message = messages.get(index);
                index++;
                return message;
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
