import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ProfileCreation
 *
 * A class that represents the GUI for the profile registration on the social media app.
 *
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu
 * @version April 26th
 */

public class ProfileCreation extends JComponent implements Runnable {
    // list of variables that will be implemented

    JButton submitButton;
    JButton deleteButton;

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

                // code here will retrieve filled in profile information from text fields and send to client/server for that account


            }
        }
    };
    public void run() {
        // Creating Frame, JLabels and J Text Fields for the Form 
        // Includes Name, Phone, Email, Likes, Dislikes, and About Me sections for user to complete

        JFrame frame = new JFrame("Profile Registration");
        Container c = frame.getContentPane();
        c.setLayout(null);

        frame.setBounds(300, 90, 900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
  
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
        
        deleteButton = new JButton("Delete!");
        deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        deleteButton.setSize(100, 50);
        deleteButton.setLocation(300, 500);
        c.add(deleteButton);
        
        

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ProfileCreation());
    }
}
