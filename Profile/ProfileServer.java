import java.io.*;
import java.net.*;
import java.util.ArrayList;
/**
 * Profile Server
 *
 * This class consists of a server used to make Profiles and accounts for a social media app.
 *	
 * @author Saketh Ayyalasomayajula(sayyala@purdue.edu), Minwoo Jung(jung361@purdue.edu)
 * @version April 20th, 2021
 */

 public class ProfileServer implements Runnable {
    Socket csocket;
    // initilization
    static ArrayList<Profile> profiles = new ArrayList<>();
    static Authentication profilesList = new Authentication(profiles);

    public ProfileServer(Socket csocket) {
        this.csocket = csocket;
    }


    public static void main(String[] args) throws IOException {

        boolean serverChek = true;
        int port = 4242;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("A Client connected");
            System.out.println(profilesList);
            new Thread(new ProfileServer(socket)).start();
        }


    }
    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(csocket.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            PrintWriter writer = new PrintWriter(csocket.getOutputStream());
            ObjectOutputStream ois = new ObjectOutputStream(csocket.getOutputStream());

            do {
                // authentication class code
                try {
                    String function = reader.readLine().trim();
                    String username = reader.readLine().trim();
                    String password = reader.readLine().trim();
                    System.out.println(username + " has requested an action.");
                    if (function.equals("login")) {
                        Profile p = profilesList.login(username, password);
                        System.out.println(p);
                        if (p != null) {
                            writer.println("success");
                        } else {
                            writer.println("fail");
                        }
                        writer.flush();
                    }
                    //Creating a User's account with username & password
                    if (function.equals("createAccount")) {
                        try {
                            Profile p = profilesList.createAccount(username, password);
                            if (p != null) {
                                writer.println("success");
                                System.out.println(profilesList);
                            }
                        } catch (OccupiedProfileException o) {
                            writer.println("Occupied Username");
                        }
                        writer.flush();

                    }
                    //Set the name / age etc. for the first time in the user's account
                    if (function.equals("setProfile")) {
                        Profile p = profilesList.getProfile(username);
                        String name = reader.readLine();
                        String age = reader.readLine();
                        String phone = reader.readLine();
                        String email = reader.readLine();
                        String likes = reader.readLine();
                        String dislikes = reader.readLine();
                        String aboutMe = reader.readLine();
                        if (p != null) {
                            p.setName(name);
                            p.setAge(age);
                            p.setEmail(email);
                            p.setPhone(phone);
                            p.setLikes(likes);
                            p.setDislikes(dislikes);
                            p.setAboutMe(aboutMe);
                            writer.println("Profile successfully created. " + p.getName());
                        }
                        else
                            writer.println("An error occurred: Cannot find account");
                        writer.flush();
                    }

                    if (function.equals("getProfileList")) {
                        ois.writeObject(profilesList.getProfiles());
                        ois.flush();
                    }

                    if (function.equals("getProfile")) {
                        Profile p = profilesList.getProfile(username);
                        ArrayList<Profile> ar = new ArrayList<Profile>();
                        ar.add(p);
                        ois.writeObject(ar);
                        ois.flush();
                    }



                } catch (NullPointerException n) {
                    System.out.println("No Users connected now");
                    break;
                }
            } while (true);
            reader.close();
            writer.close();
            csocket.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
