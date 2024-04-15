import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatApplication {
    public static void main(String[] args) throws InterruptedException {
        // Create users
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Deby");
        User user4 = new User("Kevin");

        // Register users with the chat server
        ChatServer.getInstance().registerUser(user1);
        ChatServer.getInstance().registerUser(user2);
        ChatServer.getInstance().registerUser(user3);
        ChatServer.getInstance().registerUser(user4);

        // Send messages between users

        // Block user2 by user1
        user1.blockUser(user2);
        user3.blockUser(user4);

        user1.sendMessage(user2, "1. Hello, Bob, it's me, Alice!");

        user1.sendMessage(user4, "2. UNDO THIS MESSAGE");

        // undo the last message from user1
        user1.undo();
        
        //bob sends to alice, but he got blocked by her
        user2.sendMessage(user1, "3. Hi, Alice, Bob here!");
    
        
        user3.sendMessage(user1, "4. What's going on, Alice! It's me, Deby!");
        user3.sendMessage(user4, "5. What's going on, Kevin! It's me, Deby!");
        user3.sendMessage(user4, "6. Do you mind if I go through your chat history?");
        
    
      
        TimeUnit.SECONDS.sleep(1);
        user4.sendMessage(user3, "7. Hey Deby, it's me Kevin!"); // this message is sent 2 seconds after
        user4.sendMessage(user3, "8. Hey Deby, you really should stop looking at my chat history!");
        user4.sendMessage(user3, "9. I don't think it's okay for other users to be able to see my history!");
        user4.sendMessage(user3, "10. Hey Deby, what kind of chat messaging app allows other people to see my history using my username!");

        // Bob attempts to send a message after Alice blocked him
        user2.sendMessage(user1, "11. THIS MESSAGE SHOULD BE GETTING BLOCKED AND SHOULD NOT BE SEEN BECAUSE ALICE BLOCKED BOB.");

        System.out.println();
        for (User user : ChatServer.getInstance().getUserList()) {
            String allCapsUserName = user.getUsername().toUpperCase();
            System.out.println("----------------------------------------");
            System.out.println("CHAT HISTORY FOR " + allCapsUserName + ":");

            for (Message message : user.getChatHistory().getMessageList()) {
                User[] recipients = message.getRecipients();
                String recipient = recipients[0].getUsername();
                System.out.println(message.getSender().getUsername() + " -> " + recipient + ": " + message.getContent());
                System.out.println();
            }

        }

        System.out.println();

        System.out.println("-------------------------Viewing the chat history for " + user3.getUsername()
                + "-------------------------");
        List<Message> msgList = user3.getChatHistory().getMessageList();

        for (Message msg : msgList) {
            System.out.println("Timestamp: " + msg.getTimestamp());
            System.out.println("Message content: " + msg.getContent());
            System.out.println();
        }

        System.out.println();

        System.out.println("----------------------Allowing User 3 to view the chat history for " + user4.getUsername()
                + "-------------------------");
        List<Message> userHistory = user3.getChatHistoryForUser(user4);

        for (Message msg : userHistory) {
            System.out.println("Timestamp: " + msg.getTimestamp());
            System.out.println("Message content: " + msg.getContent());
            System.out.println();
        }
        ////////////////////////////////////////////////////////////////////////
        System.out.println("------------------------TESTING ITERATORS------------------------");

        //ChatHistory chatHistory = new ChatHistory();
        // Demonstrate iterator for User's chat history
        System.out.println("Iterating over User 1's chat history:");
        Iterator<Message> userIterator = user1.iterator(user1);
        while (userIterator.hasNext()) {
            Message message = userIterator.next();
            User[] recipients = message.getRecipients();
            String recipient = recipients[0].getUsername();
            System.out.println(message.getSender().getUsername() + " -> " +
                    recipient + ": " +
                    message.getContent());
        }

        // Demonstrate iterator for ChatHistory
        System.out.println("\nIterating over User 4's ChatHistory:");
        ChatHistory chatHistory = user4.getChatHistory();
        Iterator<Message> chatHistoryIterator = chatHistory.iterator(user4);
        while (chatHistoryIterator.hasNext()) {
            Message message = chatHistoryIterator.next();
            User[] recipients = message.getRecipients();
            String recipient = recipients[0].getUsername();
            System.out.println(message.getSender().getUsername() + " -> " +
                    recipient + ": " +
                    message.getContent());
        }

    }
}
