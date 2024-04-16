import java.util.Iterator;
// Interface for classes that can be iterated over by a specific user
public interface IterableByUser {
    Iterator iterator(User userToSearchWith);
}
