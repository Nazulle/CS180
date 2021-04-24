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

public class LoginGUI extends JComponent implements Runnable {
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

    boolean buttonCheck = false;

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
                    ProfileClient.sendLoginInfo();
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
                        ProfileClient.createAccount();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    };

    public void run() {
        // This is the first panel that pops up when the program is run
        JFrame frame = new JFrame("Login Menu");
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
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LoginGUI());
    }
}
