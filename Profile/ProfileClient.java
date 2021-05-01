
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Project 5 Profile Client
 *
 *
 * @author Saketh Ayyalasomayajula(sayyala@purdue.edu), Minwoo Jung(jung361@purdue.edu)
 * @version April 28th, 2021
 */

public class ProfileClient {

    public String username = "";
    public String password = "";
    static Socket socket = null;
    private static ObjectInputStream ois;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        JOptionPane.showMessageDialog(null, "Welcome to the Social Media Profile App!", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        try {

            socket = new Socket("localhost", 4242);
            //System.out.println(socket.isConnected());
            ois = new ObjectInputStream(socket.getInputStream());
            MergedGUI mergedGUI = new MergedGUI();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //System.out.println(socket.getRemoteSocketAddress().toString());
                    mergedGUI.run();

                }
            });



            // create/ edit account

            // code for receiving profile

            // receiving userlist from server

            // sending/accepting friend requests

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendLoginInfo(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("login");
            writer.println(this.username);
            writer.println(this.password);
            writer.flush();
            String result = reader.readLine();
            if (result.contains("success")) {
                JOptionPane.showMessageDialog(null, "Welcome!", "Profile App", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "No matching Username or Password.", "Profile App", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public void createAccount(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("createAccount");
            writer.println(this.username);
            writer.println(this.password);
            writer.flush();
            String result = reader.readLine();
            if (result.contains("success"))
                JOptionPane.showMessageDialog(null, "Created Account Successfully", "Profile App", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Occupied Username! Try something else.", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);

    }

    public void setProfile(Socket socket, String name, String age, String phone, String email, String likes,
                                     String dislikes, String aboutMe)  {
        BufferedReader reader;
        PrintWriter writer;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                //System.out.println(username);
                writer.println("setProfile");
                writer.println(this.username);
                writer.println(this.password);
                writer.println(name);
                writer.println(age);
                writer.println(phone);
                writer.println(email);
                writer.println(likes);
                writer.println(dislikes);
                writer.println(aboutMe);

                writer.flush();
                String result = reader.readLine();
                JOptionPane.showMessageDialog(null, result, "Profile App", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Get Profile ArrayList from server
     *  Four Options: allUsers, friends, sentFriendRequests, receivedFriendRequests
     *  All invalid arguments will return null value
     */
    public ArrayList<Profile> getProfileList() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                //System.out.println(username);
                writer.println("getProfileList");
                writer.println(this.username);
                writer.println(this.password);
                writer.flush();
                ArrayList<Profile> allUsers;
                allUsers = (ArrayList<Profile>) ois.readObject();
                //Works required: the user should be at the first place in this this list.
                System.out.println(allUsers);
                return allUsers;
            }
            else {
                JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendFriendRequest(Socket socket, String friendUsername) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("sendFriendRequest");
            writer.println(this.username);
            writer.println(this.password);
            writer.println(friendUsername);
            writer.flush();
            System.out.println(writer);
            String result = reader.readLine();
            System.out.println(result);
            if (result.contains("success"))
                JOptionPane.showMessageDialog(null, "Friend request sent.", "Profile App", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "An Error Occurred!", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);

    }

    public void beFriend(Socket socket, String friendUsername, boolean accept) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("beFriend");
            writer.println(this.username);
            writer.println(this.password);
            writer.println(friendUsername);
            if (accept)
                writer.println("accept");
            else
                writer.println("deny");
            writer.flush();
            String result = reader.readLine();
            if (result.contains("accept"))
                JOptionPane.showMessageDialog(null, "You two are now friends.", "Profile App", JOptionPane.INFORMATION_MESSAGE);
            else if (result.contains("remove"))
                JOptionPane.showMessageDialog(null, "The friend request now removed.", "Profile App", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "An Error Occurred!", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);

    }
    /**
     * Make a String Arraylist of name and username of profiles in Profile Arraylist
     * in order to put them on the list in GUI
     */
    public static ArrayList<String> getNamesAndUsernames(ArrayList<Profile> profiles) {
        ArrayList<String> strings = new ArrayList<String>();
        for (Profile p : profiles) {
            String s = p.getName() + " <" + p.getUsername() + ">";
            strings.add(s);
        }
        return strings;
    }

    /**
     * Find out a profile from the Arraylist given and return
     */
    public static Profile getProfileFromList(ArrayList<Profile> profiles, String username) {
        for (Profile p : profiles) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

}

