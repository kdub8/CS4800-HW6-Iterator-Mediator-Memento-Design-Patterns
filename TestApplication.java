// Importing JUnit assert methods for testing

// Importing JUnit test annotation
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

// Test class for the Chat Application
public class TestApplication {

    // Test method for checking the initialization of a User object
    @Test
    public void userNameTest() {
        User user1 = new User("NEW USER");
        String actualUserName = "NEW USER";
        // Verifying that the actual username matches the expected username
        assertEquals(actualUserName, user1.getUsername());
    }

    // Test method for checking if a user is registered
    @Test
    public void registeredUserTest() {
        User user2 = new User("USER 2");
        ChatServer.getInstance().registerUser(user2);
        List<User> actualRegisteredUsers = new ArrayList<>();
        actualRegisteredUsers = ChatServer.getInstance().getUserList();
        // Verifying that the actual registered users list contains the user
        assertEquals(actualRegisteredUsers, ChatServer.getInstance().getUserList());
    }

    // Test method for checking if a user can be blocked
    @Test
    public void blockUserTest() {
        User user3 = new User("USER 3");
        User user4 = new User("USER 4");

        ChatServer.getInstance().registerUser(user3);
        ChatServer.getInstance().registerUser(user4);
        user3.blockUser(user4);
        Map<User, List<User>> blockedUsers = new HashMap<>();
        blockedUsers = ChatServer.getInstance().getBlockedUsers();
        assertEquals(blockedUsers, ChatServer.getInstance().getBlockedUsers());
        assertEquals(blockedUsers.containsKey(user3), ChatServer.getInstance().getBlockedUsers().containsKey(user3));
        // Verifying that user4 is blocked by user3
        assertTrue(blockedUsers.get(user3).contains(user4));
        // Verifying that user4 cannot send messages to user3
        assertNull(blockedUsers.get(user4));
        // Attempting to send a message from user4 to user3 while blocked
        user4.sendMessage(user3, "Hey! PLEASE UNBLOCK ME!");
        // Verifying that the message is not received
        assertEquals(blockedUsers, ChatServer.getInstance().getBlockedUsers());

    }

    // Test method for checking if a user can send a message
    @Test
    public void sendMessageTest() {
        User user5 = new User("USER 5");
        User user6 = new User("USER 6");

        ChatServer.getInstance().registerUser(user5);
        ChatServer.getInstance().registerUser(user6);

        user5.sendMessage(user6, "What is up my brethren.");
        user6.sendMessage(user5, "Nothing much bro, hbu?");
        user5.sendMessage(user6, "Just vibing my dude.");
        // Verifying that the last message sent by user5 is correct
        String lastUser5Message = user5.getChatHistory().getLastMessage().getContent();
        String ActualLastUser5Message = "Just vibing my dude.";
        assertEquals(ActualLastUser5Message, lastUser5Message);
    }

    // Test method for checking the details of a message
    @Test
    public void messageTest() {
        User user7 = new User("USER 7");
        User user8 = new User("USER 7");
        ChatServer.getInstance().registerUser(user7);
        ChatServer.getInstance().registerUser(user8);
        user7.sendMessage(user8, "What's gooood...");
        Date actualDate = user7.getChatHistory().getLastMessage().getTimestamp();
        User actualRecipient = user8;
        User actualSender = user7;
        String actualContent = "What's gooood...";
        User[] recipients = user7.getChatHistory().getLastMessage().getRecipients();
        // Verifying the details of the last message sent by user7
        assertEquals(user7.getChatHistory().getLastMessage().getTimestamp(), actualDate);
        assertEquals(recipients[0], actualRecipient);
        assertEquals(user7.getChatHistory().getLastMessage().getSender(), actualSender);
        assertEquals(user7.getChatHistory().getLastMessage().getContent(), actualContent);
    }

    // Test method for checking if a message can be undone
    @Test
    public void undoTest() {
        User user7 = new User("USER 7");
        User user8 = new User("USER 7");
        ChatServer.getInstance().registerUser(user7);
        ChatServer.getInstance().registerUser(user8);
        user7.sendMessage(user8, "Hola muchachos!");
        user7.undo();
        int actualSize;
        actualSize = user7.getChatHistory().getMessageList().size();
        // Verifying that the message is undone
        assertEquals(actualSize, 0);
    }
}
