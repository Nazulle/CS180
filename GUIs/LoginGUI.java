/**
 * LoginGUI
 *
 * A class that represents the login and create new account panels for the social media app.
 *
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu , edited by Minwoo Jung (jung361@purdue.edu)
 * @version April 24th
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;

public class LoginGUI extends JComponent implements Runnable {
    Socket socket;
    //variables for login/create account Frame
    JFrame frame;
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

    LoginGUI g;

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


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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
                        frame.dispose();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getSource() == newAccount) {
                // The following code creates a new panel when the user clicks new account
                JFrame accountFrame = new JFrame("Create Account");
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
                ProfileClient.createProfile(socket, sentName, sentAge, sentPhone, sentEmail,
                        sentLikes, sentDislikes, sentAboutMe);

            }

        }
    };

    public void run() {
        // This is the first panel that pops up when the program is run
        frame = new JFrame("Login Menu");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        //content.add(BorderLayout.CENTER);

        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);

        newAccount = new JButton("New Account?");
        newAccount.addActionListener(actionListener);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        passText = new JTextField(5);
        passText.setText("");

        userText = new JTextField(5);
        userText.setText("");

        JPanel topPanel = new JPanel();
        topPanel.add(userLabel);
        topPanel.add(userText);

        topPanel.add(passLabel);
        topPanel.add(passText);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        botPanel.add(loginButton);
        botPanel.add(newAccount);
        content.add(botPanel, BorderLayout.CENTER);

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
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        submitButton.setSize(100, 50);
        submitButton.setLocation(200, 500);
        c.add(submitButton);
        pfFrame.setVisible(false);



        try {
            socket = new Socket("10.0.0.234", 4242);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LoginGUI());
    }
}
