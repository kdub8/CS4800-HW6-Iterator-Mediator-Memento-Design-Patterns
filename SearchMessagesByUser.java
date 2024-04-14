import java.util.Iterator;

public class SearchMessagesByUser implements IterableByUser {
    private User user;

    public SearchMessagesByUser(User user) {
        this.user = user;
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        // Implement iterator logic
        return null;
    }
}
