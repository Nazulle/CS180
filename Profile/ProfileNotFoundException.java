/**
 * ProfileNotFoundException
 *
 * A class that represents an exception that is thrown when a profile is not able to be located.
 *
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu
 * @version April 20th
 */
public class ProfileNotFoundException extends Exception {
    public ProfileNotFoundException() {
        super();
    }
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
