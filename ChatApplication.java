public class ChatApplication {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        // Register users with the chat server
        ChatServer.getInstance().registerUser(user1);
        ChatServer.getInstance().registerUser(user2);

        // Send messages between users
        user1.sendMessage(user2, "Hello, Bob!");
        user2.sendMessage(user1, "Hi, Alice!");

        // Undo last message sent by user1
        user1.undoLastMessage();

        // Block user2 by user1
        user1.blockUser(user2);

        // Attempt to send a message after blocking
        user2.sendMessage(user1, "This message should not be received by Alice.");

        // Iterate over chat history for user1
        System.out.println("Chat history for " + user1.getUsername() + ":");

        for (Message message : user1.getChatHistory().getMessageList()) {
            System.out.println(message.getSender().getUsername() + " -> " + message.getContent());
        }

        
    }
}
