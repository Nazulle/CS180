import java.util.ArrayList;

/**
 * Authentication Class will be the ArrayList of Profiles in the server.
 *  @author Minwoo Jung - jung361@purdue.edu, Saketh Ayyalasomayajula - sayyala@purdue.edu
 *  @version April 22th
 */

public class Authentication {
    ArrayList<Profile> profiles = new ArrayList<Profile>();

    public Authentication(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    /**
     * Create a new Profile with username & password. If the profile exist in the list, return null.
     * @return new Profile or null
     */
    public Profile createProfile(String username, String password) throws OccupiedProfileException {
        Profile prof = new Profile(username, password);
        boolean isProfileExists = false;
        for (Profile p : profiles) {
            if (p.getUsername().equals(username)) {
                isProfileExists = true;
                break;
            }
        }
        if (isProfileExists)
            throw new OccupiedProfileException();
        else {
            profiles.add(prof);
            return prof;
        }
    }

    /**
     * Find if there is matching profile with username & password. If not, return false.
     * @return profile p from the list
     */
    public Profile login(String username, String password) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Get a profile from the List
     * @return the profile
     */
    public Profile getProfile(String username) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public void removeProfile(String username, String password) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(username)) {
                profiles.remove(p);
            }
        }
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    @Override
    public String toString() {
        return profiles.toString();
    }
}
