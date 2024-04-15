// Importing JUnit assert methods for testing
import static org.junit.Assert.assertEquals;

// Importing JUnit test annotation
import org.junit.Test;

// Test class for the Chat Application
public class TestApplication {

    // Test method for checking the initialization of a Burger object
    @Test
    public void userNameTest() {
        // Creating a Burger object with a price of $8.79
        User user1 = new User("NEW USER");
        String actualUserName = "NEW USER";
        // Verifying that the actual username matches the expected username
        assertEquals(actualUserName, user1.getUsername());
    }
}
