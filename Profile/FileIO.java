import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class FileIO {

    ArrayList<String> registeredAcc;
    String [] s;
    ArrayList <String> userNameList;
    ArrayList <String> passWordList;

    public void writeAccountFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("accountsFileName.txt")))) {

            out.write(username + "," password + "\n" );

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
    
    public void writeProfileFile() {
        try {

        } catch (IOException c) {
            c.printStackTrace();
        }

    }
    
    public void readProfileFile() {
        try {

        } catch (IOException c) {
            c.printStackTrace();
        }

    }

}