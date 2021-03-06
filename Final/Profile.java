import java.util.ArrayList;
import java.io.Serializable;

/**
 * Profile
 *
 * A class that represents various user profiles based on user information.
 *
 * @author Saketh Ayyalasomayajula - sayyala@purdue.edu, Minwoo Jung - jung361@purdue.edu
 * @version May 2nd
 */

public class Profile implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String age;
    private String likes;
    private String dislikes;
    private String aboutMe;


    private ArrayList<Profile> friends = new ArrayList<Profile>();
    private ArrayList<Profile> receivedFriendRequest = new ArrayList<Profile>();
    private ArrayList<Profile> sentFriendRequest = new ArrayList<Profile>();


    /** General Constructor
     */
    public Profile(String username, String password, String name, String age, String phone, String email,
                   String likes, String dislikes, String aboutMe) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.aboutMe = aboutMe;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    /**
     * Constructor for creating a Profile with only username & password
     */
    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "";
        this.phone = "";
        this.name = "";
        this.aboutMe = "";
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
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    public String getAboutMe() {
        return this.aboutMe;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }
    public ArrayList<Profile> getReceivedFriendRequest() {
        return receivedFriendRequest;
    }
    public ArrayList<Profile> getSentFriendRequest() {
        return sentFriendRequest;
    }

    /** toString method that displays profile
     * Mino: I modified this because when
     */
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s", username, name, age, email, phone, likes, dislikes, aboutMe);
    }

    /**
     * equals method with only username & password
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (username != null ? !username.equals(profile.username) : profile.username != null) return false;
        return (password != null ? password.equals(profile.password) : profile.password != null);
    }

    /**
     * If I send friend request, add it to sentFriendRequest in my Profile / receivedFriendRequest in their Profile
     * If cannot find the profile, throw ProfileNotFoundException
     */
    public void sendFriendRequest(Profile prof) throws ProfileNotFoundException {
        try {
            this.sentFriendRequest.add(prof);
            prof.getReceivedFriendRequest().add(this);
        } catch (NullPointerException n) {
            throw new ProfileNotFoundException();
        }
    }

    /**
     * If I accept friend request in receivedFriendRequest, delete it from receivedFriendRequest
     * in my Profile / sentFriendRequest in their Profile and add it into Friends list of both
     * If cannot find the profile, throw ProfileNotFoundException
     */
    public void acceptFriendRequest (Profile prof) throws ProfileNotFoundException {
        try {
            this.receivedFriendRequest.remove(prof);
            prof.getSentFriendRequest().remove(this);
            this.friends.add(prof);
            prof.getFriends().add(this);
        } catch (NullPointerException n) {
            throw new ProfileNotFoundException();
        }
    }

    /**
     * remove friend request from unwanted user
     */
    public void removeFriendRequest(Profile profile) throws ProfileNotFoundException {
        try {
            this.receivedFriendRequest.remove(profile);
            profile.getSentFriendRequest().remove(this);
        } catch (NullPointerException n) {
            throw new ProfileNotFoundException();
        }
    }

    /**
     * remove friend from each other's friend list
     */
    public void unFriend(Profile profile) throws ProfileNotFoundException {
        try {
            this.getFriends().remove(profile);
            profile.getFriends().remove(this);
        } catch (NullPointerException n) {
            throw new ProfileNotFoundException();
        }
    }



}
