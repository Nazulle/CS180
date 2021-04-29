import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class FileIO {

    ArrayList<String> registeredAcc;
    ArrayList<String> registeredProf;
    String [] pf;
    String [] s;
    ArrayList <String> userNameList;
    ArrayList <String> passWordList;
    ArrayList <String> userProfilesList;

    public void writeAccountFile(String username, String password) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("accountsFileName.txt")))) {

            out.write(username + "," password + "\n");

        } catch (IOException a) {
            a.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        FileIO f = new FileIO();
        f.readAccountFile();
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
        System.out.println(Arrays.toString(userNameList.toArray()));
        System.out.println(Arrays.toString(passWordList.toArray()));

    }
    
    public void writeProfileFile(ArrayList<Profile> userProfiles) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("profilesFileName.txt")))) {
            for (int i = 0; i <= userProfiles.getSize(); i++) {
                out.write(userProfiles.get(i).getName() + "|" + userProfiles.get(i).getPhone() + "|" + userProfiles.get(i).getEmail() +  "|" +
                userProfiles.get(i).getLikes() + "|" + userProfiles.get(i).getDislikes() + "|" + userProfiles.get(i).getAboutMe() + "\n");
            }
        } catch (IOException c) {
            c.printStackTrace();
        }

    }
    
    public void readProfileFile() {
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
            pf = registeredAcc.get(i).split("|");

            Profile n = new Profile(userNameList.get(i), passWordList.get(i), pf[0], pf[1], pf[2], pf[3], pf[4], pf[5], pf[6]);
            userProfilesList.add(n);

        }
    }
}
