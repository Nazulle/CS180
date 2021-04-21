/**
 * OccupiedProfileException
 *
 * A class that represents an exception that is thrown when a profile is already taken (same username)
 *
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu
 * @version April 20th
 */
public class OccupiedProfileException extends Exception {
    public OccupiedProfileException() {
        super();
    }
    public OccupiedProfileException(String message) {
        super(message);
    }
}
