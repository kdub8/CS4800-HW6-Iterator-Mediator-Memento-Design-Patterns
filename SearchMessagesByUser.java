
import java.util.Iterator;
import java.util.List;

public class SearchMessagesByUser implements IterableByUser {
    private List<Message> messages;
    private  User user;
    public SearchMessagesByUser(User user) {
        this.user = user;
        this.messages = user.getChatHistoryForUser(user);
    }


    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUserIterator(this.user.getChatHistory().getMessageList(), userToSearchWith);
    }

    private static class SearchMessagesByUserIterator implements Iterator<Message> {
        private List<Message> messages;
        private int index;
        private User userToSearchWith;
        public SearchMessagesByUserIterator(List<Message> messages, User userToSearchWith) {
            this.messages = messages;
            this.userToSearchWith = userToSearchWith;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            // Implement hasNext() to check if there are more messages for the specified user
            while (index < messages.size()) {
                Message message = messages.get(index);
                if (message.getSender().equals(userToSearchWith) ) {
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
                if (message.getSender().equals(userToSearchWith) ) {
                    return message;
                }
            }
            return null;
        }
    }
}