
import java.util.Iterator;
import java.util.List;

public class SearchMessagesByUser implements IterableByUser {
    private List<Message> messages;
    private  User user;
    public SearchMessagesByUser(User user) {
        this.user = user;
        this.messages = user.getChatHistoryForUser(user);
        System.out.println("WEEWOO Searchmessagesbyuser object has been created!");
        System.out.println("WEEWOO Searchmessagesbyuser object has been created!");
        System.out.println("WEEWOO Searchmessagesbyuser object has been created!");
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


////////////////////////////////////////////////////////////////////////////////////////

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
//public class SearchMessagesByUser implements IterableByUser {
//    private User user;
//    private List<Message> messageSenderList;
//    private int collectionSize = 0;
//    private String userName;
//    public SearchMessagesByUser(User user) {
//        //this.messages.addAll(user.getChatHistory().getMessageList());
//                    for (Message message : user.getChatHistory().getMessageList()) {
//                if (message.getSender().equals(user) ) {
//                    if (messageSenderList != null) {
//                        messageSenderList.add(message);
//                    }
//                }
//            }
//        if (messageSenderList != null) {
//            System.out.println("Size of messages: " + messageSenderList.size());
//        }
//        userName = user.getUsername();
//        collectionSize = messageSenderList.size();
//    }
//
//    public int getCollectionSize() {
//        return this.collectionSize;
//    }
//
//    @Override
//    public Iterator<Message> iterator(User userToSearchWith) {
//        // Implement iterator logic
//        return new UserMessagesIterator(this.messageSenderList, userToSearchWith);
//    }
//
//    public static class UserMessagesIterator implements Iterator<Message> {
//        private int index;
//        private List<Message> messages;
//        private User userToSearchWith;
//
//        public UserMessagesIterator(List<Message> messages, User userToSearchWith) {
//            // Find all messages sent to or received from the specified user
//            this.messages = messages;
//            this.userToSearchWith = userToSearchWith;
//            this.index = 0;
////            for (Message message : messages) {
////                if (message.getSender().equals(userToSearchWith) || Arrays.asList(message.getRecipients()).contains(userToSearchWith)) {
////                    messages.add(message);
////                }
////            }
//        }
//
//        @Override
//        public boolean hasNext() {
//            // Implement hasNext() to check if there are more messages for the specified user
//            while (index < messages.size()) {
//                Message message = messages.get(index);
////                if (message.getSender().equals(userToSearchWith) ||  (userToSearchWith != null && containsRecipient(message, userToSearchWith))) {
//                    if (message.getSender().equals(userToSearchWith) ) {
//                    return true;
//                }
//                index++;
//            }
//            return false;
//        }
//
//        @Override
//        public Message next() {
//            // Implement next() to return the next message for the specified user
//            while (index < messages.size()) {
//                Message message = messages.get(index);
//                index++;
////                if (message.getSender().equals(userToSearchWith) || Arrays.asList(message.getRecipients()).contains(userToSearchWith)) {
//                    if (message.getSender().equals(userToSearchWith) ) {
//                    return message;
//                }
//            }
//            return null;
//        }
//
//        private boolean containsRecipient(Message message, User user) {
//            for (User recipient : message.getRecipients()) {
//                if (recipient.equals(user)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//}
///////////////////////////////

