import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 CS 180 Spring 2021

 Main Menu GUI that shows all user profiles.

 @author Gabriel Segura, CS 180
  * @version April 20, 2021
 */

public class GUIMain extends JComponent implements Runnable {
    JButton openButton;
    JButton inputButton;
    JButton editButton;

    JComboBox<String> comboBox;
    String profileName;
    ArrayList<String> testArray = new ArrayList<String>(); //if not working add: (Arrays.asList());

    GUIMain guimain; // variable of the type GUIMain

    String currentUser = "user123"; //Username for the current user. [Delete later]

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        }
    };

    /* user actions */
    public void openProfile(String profile) {
        if (profile == null) {
            JOptionPane.showMessageDialog(null, "No Profile Selected!",
                    "Open Profile",JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, profile,
                    "Open Profile",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editProfile() {
        //opens the current user's profile
        JOptionPane.showMessageDialog(null, "Your Profile: " + currentUser,
                "Edit User Profile",JOptionPane.INFORMATION_MESSAGE);
    }

    public void inputProfile() { //***Use for appending new profiles to main menu***
        profileName = JOptionPane.showInputDialog(null,
                "What is the file name? (ex: username.csv)", "File Name", JOptionPane.QUESTION_MESSAGE);
        testArray.add(profileName);
        comboBox.addItem(profileName);
        repaint();
    }

    //--------------------------------
    public GUIMain() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIMain());
    }

    public void run() {
        /* set up JFrame */
        JFrame frame = new JFrame("Main Menu");
        Container content = frame.getContentPane();

        comboBox = new JComboBox<>();
        content.setLayout(new BorderLayout());
        guimain = new GUIMain();
        content.add(guimain, BorderLayout.CENTER);

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
        content.add(panel, BorderLayout.SOUTH);

        for (int i = 0; i < testArray.size(); i++) {
            comboBox.addItem(testArray.get(i));
        }
        content.add(comboBox, BorderLayout.NORTH);

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
