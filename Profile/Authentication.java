public class Authentication {
    private ArrayList <Profile> profiles;
    
    public Authentication() {
        profiles = new ArrayList<Profile>();
    }

    public Profile createProfile(String username, String password) {
        Profile prof = new Profile(username, password);
        profiles.add(prof);
    }

}
