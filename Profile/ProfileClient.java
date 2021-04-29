
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

    public static String username = "";
    public static String password = "";
    static Socket socket = null;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        JOptionPane.showMessageDialog(null, "Welcome to the Social Media Profile App!", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        try {
            socket = new Socket("localhost", 4242);
            //System.out.println(socket.isConnected());
            //login code
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

    public static boolean sendLoginInfo(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("login");
            writer.println(username);
            writer.println(password);
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

    public static void createAccount(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("createAccount");
            writer.println(username);
            writer.println(password);
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

    public static void setProfile(Socket socket, String name, String age, String phone, String email, String likes,
                                     String dislikes, String aboutMe)  {
        BufferedReader reader;
        PrintWriter writer;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                //System.out.println(username);
                writer.println("setProfile");
                writer.println(username);
                writer.println(password);
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
    public static ArrayList<Profile> getProfileList() {
        PrintWriter writer;
        ObjectInputStream ois;
        try {
            writer = new PrintWriter(socket.getOutputStream());

            if (socket.isConnected()) {
                //System.out.println(username);
                writer.println("getProfileList");
                writer.println(username);
                writer.println(password);
                writer.flush();
                ois = new ObjectInputStream(socket.getInputStream());
                ArrayList<Profile> allUsers = (ArrayList<Profile>) ois.readObject();
                //Works required: the user should be at the first place in this this list.
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
     * Find out a profile from the Arraylist given and construct a new profile
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

