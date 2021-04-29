/**
 * MergedGUI
 *
 * A GUI for the social media app.
 *
 * @author Saketh Ayyalasomayajula (sayyala@purdue.edu), Gabriel Segura (gsegura@purdue.edu), Abby (write your full name and email here)
 * merged and edited by Minwoo Jung (jung361@purdue.edu)
 * @version April 29th
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MergedGUI extends JComponent implements Runnable {
    Socket socket;
    Profile myProfile;
    ArrayList<Profile> allUsers;

    //variables for login/create account Frame
    JFrame loginFrame;
    JFrame accountFrame;
    JButton loginButton;
    JButton newAccount;
    JButton createAccount;

    JLabel userLabel = new JLabel("<html><b> Username: </b></html>");
    JLabel newUserLabel = new JLabel("<html><b> Create a Username with 6 or more Alpha-Numeric Characters: </b></html>");

    JLabel passLabel = new JLabel("<html><b> Password: </b></html>");
    JLabel newPassLabel = new JLabel("<html><b> Create a Password with 6 or more Alpha-Numeric Characters: </b></html>");

    JLabel message = new JLabel("Make sure to not use common words when creating these!");

    JTextField passText;
    JTextField userText;

    JTextField newPassText;
    JTextField newUserText;


    String userInput;
    String passInput;

    String newUserInput;
    String newPassInput;

    //variables for createProfile Frame

    JFrame pfFrame;
    JButton submitButton;

    JLabel titleScreen;
    JLabel name;
    JLabel userPhone;
    JLabel userLikes;
    JLabel userDislikes;
    JLabel userAge;
    JLabel userEmail;
    JLabel userAboutMe;

    JTextField profName;
    JTextField profAge;
    JTextField profPhone;
    JTextField profEmail;
    JTextField profLikes;
    JTextField profDislikes;
    JTextArea profAboutMe;

    String sentName;
    String sentAge;
    String sentPhone;
    String sentEmail;
    String sentLikes;
    String sentDislikes;
    String sentAboutMe;
    
    //variables of GUIMain
    JFrame mainFrame;
    
    JButton openButton;
    JButton inputButton;
    JButton editButton;

    JComboBox<String> comboBox;
    String profileName;
    ArrayList<String> testArray = new ArrayList<String>(); //if not working add: (Arrays.asList());


    String currentUser = "user123"; //Username for the current user. [Delete later]

    //variables for profileGUI
    JComboBox<String> friendButton = new JComboBox<String>();
    JButton goBack;
    JButton enter;
    JButton denyFriend;
    JButton acceptFriend;
    JButton unFriend;
    JButton sendFriendRequest;

    JScrollPane jsp;
    String choice;
    JFrame profileGUIFrame;

    boolean areTheyYourFriend;
    boolean theySentYouFriendRequest;
    ItemListener ItemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == friendButton) {
                JComboBox getSelection = friendButton;
                choice = (String) getSelection.getSelectedItem();
            }
        }
    };



    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //buttons in LoginGUI
            if (e.getSource() == loginButton) {

                if (passText.getText().isEmpty()) {
                    passText.setText("");
                }
                if (userText.getText().isEmpty()) {
                    userText.setText("");
                }
                ProfileClient.username = userText.getText();
                ProfileClient.password = passText.getText();

                try {
                    boolean b = ProfileClient.sendLoginInfo(socket);
                    if (b) {
                        loginFrame.dispose();
                        setComboBox();
                        mainFrame.setVisible(true);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getSource() == newAccount) {
                // The following code creates a new panel when the user clicks new account
                accountFrame = new JFrame("Create Account");
                Container cont = accountFrame.getContentPane();
                cont.setLayout(new BorderLayout());

                createAccount = new JButton("Create New Account!");
                createAccount.addActionListener(actionListener);

                accountFrame.setSize(600, 400);
                accountFrame.setLocationRelativeTo(null);
                accountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                accountFrame.setVisible(true);

                newPassText = new JTextField(5);
                newPassText.setText("");

                newUserText = new JTextField(5);
                newUserText.setText("");

                JPanel newTop = new JPanel();
                newTop.add(newUserLabel);
                newTop.add(newUserText);
                cont.add(newTop, BorderLayout.NORTH);

                JPanel mid = new JPanel();
                mid.add(newPassLabel);
                mid.add(newPassText);
                cont.add(mid, BorderLayout.CENTER);

                JPanel newBot = new JPanel();
                newBot.add(message);
                newBot.add(createAccount);
                cont.add(newBot, BorderLayout.SOUTH);

            }

            if (e.getSource() == createAccount) {
                newUserInput = newUserText.getText();
                newPassInput = newPassText.getText();
                ProfileClient.username = newUserInput;
                ProfileClient.password = newPassInput;

                if (newUserInput.length() < 6 || newPassInput.length() < 6 || newUserInput.equals("") || newPassInput.equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password specifications. Try again!", "Create Account", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        ProfileClient.createAccount(socket);
                        pfFrame.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            //Button in ProfileCreation Frame
            if (e.getSource() == submitButton) {
                sentName = profName.getText();
                sentAge = profAge.getText();
                sentPhone = profPhone.getText();
                sentEmail = profEmail.getText();
                sentLikes = profLikes.getText();
                sentDislikes = profDislikes.getText();
                sentAboutMe = profAboutMe.getText();

                if (sentName.equals("") || sentAge.equals("") || sentPhone.equals("") || sentEmail.equals("")
                        || sentLikes.equals("") || sentDislikes.equals("") || sentAboutMe.equals("")) {
                    JOptionPane.showMessageDialog(null, "Make sure to fill all specifed fields. Try again!", "Profile Creation", JOptionPane.ERROR_MESSAGE);
                }

                // retrieve filled in profile information from text fields and send to client/server for that account
                else {
                    ProfileClient.createProfile(socket, sentName, sentAge, sentPhone, sentEmail,
                            sentLikes, sentDislikes, sentAboutMe);
                    loginFrame.dispose();
                    accountFrame.dispose();
                    pfFrame.setVisible(false);
                    setComboBox();
                    mainFrame.setVisible(true);
                }

            }
            
            //Buttons in GUIMain
            if (e.getSource() == openButton) {
                String profile = (String)comboBox.getSelectedItem();
                openProfile(profile);

            }
            if (e.getSource() == editButton) {
                //String user = (String)comboBox.getSelectedItem();
                editProfile();
            }
            if (e.getSource() == inputButton) {
                inputProfile();
            }

            //Buttons in profileGUI
            if (e.getSource() == enter) {

            }
            if (e.getSource() == goBack) {
                profileGUIFrame.dispose();
                // Go back to main
            }
            if (e.getSource() == acceptFriend) {
                //DO-STUFF
            }
            if (e.getSource() == denyFriend) {
                //DO-STUFF
            }
            if (e.getSource() == unFriend) {
                //DO-STUFF
            }
            if (e.getSource() == sendFriendRequest) {
                //DO-STUFF
            }

        }
    };

    public void run() {
        // This is the first panel that pops up when the program is run
        loginFrame = new JFrame("Login Menu");
        Container loginContent = loginFrame.getContentPane();
        loginContent.setLayout(new BorderLayout());
        //loginContent.add(BorderLayout.CENTER);

        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);

        newAccount = new JButton("New Account?");
        newAccount.addActionListener(actionListener);

        loginFrame.setSize(600, 400);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setVisible(true);

        passText = new JTextField(5);
        passText.setText("");

        userText = new JTextField(5);
        userText.setText("");

        JPanel topPanel = new JPanel();
        topPanel.add(userLabel);
        topPanel.add(userText);

        topPanel.add(passLabel);
        topPanel.add(passText);
        loginContent.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        botPanel.add(loginButton);
        botPanel.add(newAccount);
        loginContent.add(botPanel, BorderLayout.CENTER);

        //Frame for Profile Creation
        pfFrame = new JFrame("Profile Registration");
        Container c = pfFrame.getContentPane();
        c.setLayout(null);

        pfFrame.setBounds(300, 90, 900, 600);
        pfFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pfFrame.setResizable(true);
        pfFrame.setVisible(true);

        titleScreen = new JLabel("Registration Form");
        titleScreen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        titleScreen.setSize(300, 30);
        titleScreen.setLocation(300, 30);
        c.add(titleScreen);

        name = new JLabel("Name:");
        name.setFont(new Font("Times New Roman", Font.BOLD, 16));
        name.setSize(150, 20);
        name.setLocation(100, 100);
        c.add(name);

        profName = new JTextField();
        profName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profName.setSize(150, 20);
        profName.setLocation(200, 100);
        c.add(profName);

        userPhone = new JLabel("Phone #:");
        userPhone.setFont(new Font("Times New Roman", Font.BOLD, 16));
        userPhone.setSize(100, 20);
        userPhone.setLocation(100, 150);
        c.add(userPhone);

        profPhone = new JTextField();
        profPhone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profPhone.setSize(150, 20);
        profPhone.setLocation(200, 150);
        c.add(profPhone);

        userEmail = new JLabel("Email:");
        userEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
        userEmail.setSize(100, 20);
        userEmail.setLocation(100, 200);
        c.add(userEmail);

        profEmail = new JTextField();
        profEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profEmail.setSize(150, 20);
        profEmail.setLocation(200, 200);
        c.add(profEmail);

        userAge = new JLabel("Age:");
        userAge.setFont(new Font("Times New Roman", Font.BOLD, 16));
        userAge.setSize(100, 20);
        userAge.setLocation(100, 250);
        c.add(userAge);

        profAge = new JTextField();
        profAge.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profAge.setSize(150, 20);
        profAge.setLocation(200, 250);
        c.add(profAge);

        userLikes = new JLabel("Likes:");
        userLikes.setFont(new Font("Times New Roman", Font.BOLD, 16));
        userLikes.setSize(100, 20);
        userLikes.setLocation(100, 300);
        c.add(userLikes);

        profLikes = new JTextField();
        profLikes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profLikes.setSize(250, 20);
        profLikes.setLocation(200, 300);
        c.add(profLikes);

        userDislikes = new JLabel("Dislikes:");
        userDislikes.setFont(new Font("Times New Roman", Font.BOLD, 16));
        userDislikes.setSize(100, 20);
        userDislikes.setLocation(100, 350);
        c.add(userDislikes);

        profDislikes = new JTextField();
        profDislikes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profDislikes.setSize(250, 20);
        profDislikes.setLocation(200, 350);
        c.add(profDislikes);

        userAboutMe = new JLabel("About Me:");
        userAboutMe.setFont(new Font("Times New Roman", Font.BOLD, 16));
        userAboutMe.setSize(100, 20);
        userAboutMe.setLocation(100, 400);
        c.add(userAboutMe);

        profAboutMe = new JTextArea();
        profAboutMe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        profAboutMe.setSize(300, 50);
        profAboutMe.setLocation(200, 400);
        profAboutMe.setLineWrap(true);
        c.add(profAboutMe);

        submitButton = new JButton("Submit!");
        submitButton.addActionListener(actionListener);
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        submitButton.setSize(100, 50);
        submitButton.setLocation(200, 500);
        c.add(submitButton);
        pfFrame.setVisible(false);
        
        
        //Frame of GUIMain
        mainFrame = new JFrame("Main Menu");
        Container mainContent = mainFrame.getContentPane();

        comboBox = new JComboBox<>();
        mainContent.setLayout(new BorderLayout());

        JPanel panel = new JPanel(); //bottom panel buttons
        openButton = new JButton("Select Profile");
        openButton.addActionListener(actionListener);
        panel.add(openButton);

        editButton = new JButton("Edit Your Profile");
        editButton.addActionListener(actionListener);
        panel.add(editButton);

        inputButton = new JButton("Import Profile");
        inputButton.addActionListener(actionListener);
        panel.add(inputButton);
        mainContent.add(panel, BorderLayout.SOUTH);


        mainContent.add(comboBox, BorderLayout.NORTH);

        mainFrame.setSize(400, 250);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(false);

        //ProfileGUI
        profileGUIFrame = new JFrame();
        profileGUIFrame.setTitle("Profile");

        JPanel overallPanel = new JPanel();
        overallPanel.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel name = new JLabel("<html><p>Chad Brad</p><p></p></html>");
        panelTop.add(name);
        if (areTheyYourFriend == true) {
            unFriend = new JButton("unfriend");
            panelTop.add(unFriend);
        } else if (theySentYouFriendRequest == true){
            acceptFriend = new JButton("Accept");
            acceptFriend.addActionListener(actionListener);
            denyFriend = new JButton("Deny");
            denyFriend.addActionListener(actionListener);
            panelTop.add(acceptFriend);
            panelTop.add(denyFriend);
        } else {
            sendFriendRequest = new JButton("Send Request");
            panelTop.add(sendFriendRequest);
        }


        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        ImageIcon icon = new ImageIcon("C:\\Users\\14079\\eclipse-workspace\\CS180\\src\\Project5\\nopic_1921.jpg");
        JLabel picture = new JLabel(icon);
        profilePanel.add(picture, BorderLayout.CENTER);

        JPanel profilePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel email = new JLabel("<html><p>user@gmail.com</p><p></p></html>");
        JLabel phoneNum = new JLabel("<html><p>Phone Number</p><p></p></html>");
        JLabel aboutMe = new JLabel("<html><p>Hi this is my Bio!</p></html>");
        profilePanel2.add(email);
        profilePanel2.add(phoneNum);
        profilePanel2.add(aboutMe);

        JPanel profilePanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JLabel likes = new JLabel("<html><p>Likes:</p><p>Like1</p><p>Like2</p><p>Like3</p></html>");
        JLabel dislikes = new JLabel("<html><p>DisLikes:</p><p>Dislike1</p><p>Dislike2</p><p>Dislike3</p></html>");
        profilePanel3.add(likes);
        profilePanel3.add(dislikes);

        JPanel panelBottom = new JPanel();
        goBack = new JButton("Go Back");
        goBack.addActionListener(actionListener);
        panelBottom.add(goBack);

        JPanel scrollPanel = new JPanel();
        jsp = new JScrollPane(scrollPanel);
        enter = new JButton("Enter");
        enter.addActionListener(actionListener);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendButton.addItemListener(ItemListener);


        scrollPanel.add(friendButton);
        scrollPanel.add(enter, BorderLayout.EAST);
        profilePanel.add(jsp, BorderLayout.NORTH);

        overallPanel.add(profilePanel);
        overallPanel.add(profilePanel2, BorderLayout.CENTER);
        overallPanel.setVisible(true);

        profilePanel2.add(profilePanel3);

        profileGUIFrame.add(overallPanel, BorderLayout.CENTER);
        profileGUIFrame.add(panelBottom, BorderLayout.SOUTH);
        profileGUIFrame.add(panelTop, BorderLayout.NORTH);

        profileGUIFrame.setSize(400, 350);
        profileGUIFrame.setLocationRelativeTo(null);
        profileGUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileGUIFrame.setVisible(false);

        //Socket to perform methods in server (Not sure do we really need it)
        try {
            socket = new Socket("localhost", 4242);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MergedGUI());
    }
    
    //Methods in GUIMain
    public void openProfile(String username) {
        if (username == null) {
            JOptionPane.showMessageDialog(null, "No User Selected!",
                    "Open Profile",JOptionPane.ERROR_MESSAGE);
        } else {
            profileGUIFrame.setVisible(true);
        }
    }

    public void editProfile() {
        //opens the current user's profile
        myProfile = ProfileClient.getProfileFromList(allUsers, ProfileClient.username);
        profName.setText(myProfile.getName());
        pfFrame.setVisible(true);
    }

    public void inputProfile() { //***Use for appending new profiles to main menu***
        profileName = JOptionPane.showInputDialog(null,
                "What is the file name? (ex: username.csv)", "File Name", JOptionPane.QUESTION_MESSAGE);
        testArray.add(profileName);
        comboBox.addItem(profileName);
        repaint();
    }

    //setup comboBox when logined
    public void setComboBox() {
        try {
            allUsers = ProfileClient.getProfileList("allUsers");
            testArray = ProfileClient.getNamesAndUsernames(allUsers);
            for (int i = 0; i < testArray.size(); i++) {
                comboBox.addItem(testArray.get(i));
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: User list not found from server.", "Error", JOptionPane.QUESTION_MESSAGE);

        }
    }
    
    
}
