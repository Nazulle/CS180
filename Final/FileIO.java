import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class FileIO {

    ArrayList<String> registeredAcc;
    ArrayList<String> registeredProf;
    ArrayList<String> registeredFr;
    String [] pf;
    String [] s;
    ArrayList <String> userNameList;
    ArrayList <String> passWordList;
    ArrayList<Profile> userProfilesList;

    public void writeAccountFile(ArrayList<Profile> profiles) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("accountsFileName.txt")))) {
            for (Profile p : profiles) {
                out.write(p.getUsername() + "," + p.getPassword() + "\n");
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileIO f = new FileIO();
        f.readAccountFile();
        f.readProfileFile();
    }
    public void readAccountFile() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("accountsFileName.txt"));
            registeredAcc = new ArrayList<String>();
            String line;

            while ((line = bfr.readLine()) != null) {
                registeredAcc.add(line);
            }
        } catch (IOException b) {
            b.printStackTrace();
        }
        // these are the two arrays that contain all the usernames and passwords and will be sent to server when it is launched
        userNameList = new ArrayList<String>();
        passWordList = new ArrayList<String>();

        for (int i = 0; i < registeredAcc.size(); i++) {
            s = registeredAcc.get(i).split(",");
            userNameList.add(s[0]);
            passWordList.add(s[1]);

        }
        //System.out.println(Arrays.toString(userNameList.toArray()));
        //System.out.println(Arrays.toString(passWordList.toArray()));

    }

    public void writeProfileFile(ArrayList<Profile> userProfiles) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("profilesFileName.txt")))) {
            for (Profile p : userProfiles) {
                out.write(p.getName() + "/" +p.getAge() + "/" + p.getPhone() + "/" + p.getEmail() +  "/" +
                        p.getLikes() + "/" + p.getDislikes() + "/" + p.getAboutMe() + "\n");
            }
        } catch (IOException c) {
            c.printStackTrace();
        }

    }

    public ArrayList<Profile> readProfileFile() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("profilesFileName.txt"));
            registeredProf = new ArrayList<String>();
            String line;

            while ((line = bfr.readLine()) != null) {
                registeredProf.add(line);
            }
        } catch (IOException d) {
            d.printStackTrace();
        }
        // this is the array that contains all the profile information for a particular user
        userProfilesList = new ArrayList<Profile>();

        for (int i = 0; i < registeredProf.size(); i++) {
            pf = registeredProf.get(i).split("/");

            Profile n = new Profile(userNameList.get(i), passWordList.get(i), pf[0], pf[1], pf[2], pf[3], pf[4], pf[5], pf[6]);
            userProfilesList.add(n);

        }
        return userProfilesList;
    }

    public void writeFriendListFile(ArrayList<Profile> friendsList) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("friendsListFileName.txt")))) {
            for (Profile p : friendsList) {
                for (Profile friend : p.getFriends()) {
                    out.write(friend.getUsername() + ",");
                }
                out.newLine();
            }
        } catch (IOException d) {
            d.printStackTrace();
        }
    }

    public void readFriendListFile() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("friendsListFileName.txt"));
            registeredFr = new ArrayList<String>();
            String line;

            while ((line = bfr.readLine()) != null) {
                registeredFr.add(line);
            }
        } catch (IOException d) {
            d.printStackTrace();
        }
        // this is the arraylist that has all the list of friends for a particular user.
        for (int i = 0; i < registeredFr.size(); i++) {
            String[] fr = registeredFr.get(i).split(",");
            for (String friendUsername : fr) {
                for (Profile p: userProfilesList) {
                    if (p.getUsername().equals(friendUsername))
                        userProfilesList.get(i).getFriends().add(p);
                }
            }
        }
    }
}
