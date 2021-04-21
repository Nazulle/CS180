/**
 * Profile
 *
 * A class that represents various user profiles based on user information.
 *
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu
 * @version April 20th
 */

public class Profile {
    private String username;
    private String password;
    private String email;
    private long phone;
    private String name;
    private String aboutMe;
    private String likes;

    /** Constructor
     */
    public Profile(String username, String password, String email, long phone, String name, String likes, String aboutMe) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;

        this.name = name;
        this.likes = likes;
        this.aboutMe = aboutMe;
    }
    /** series of getter/settter methods
     */
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public long getPhone() {
        return this.phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setLikes(String likes) {
        this.likes = likes;
    }
    public String getLikes() {
        return this.likes;
    }
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    public String getAboutMe() {
        return this.aboutMe;

    }
    public void removeProfile(Profile profile) {
        
    }
    /** toString method that displays profile
     */
    public String toString() {
        return String.format("%s%n %s%n %d%n %s%n %s%n", name, email, phone, likes, aboutMe);
    }
}
