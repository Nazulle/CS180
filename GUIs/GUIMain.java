import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUIMain extends JComponent implements Runnable {
    JButton openButton;
    JButton inputButton;
    JButton editButton;

    JComboBox<String> comboBox;
    String profileName;
    //String profileList[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" };
    ArrayList<String> testArray = new ArrayList<String>(Arrays.asList());

    GUIMain guimain; // variable of the type GUIMain

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == openButton) { //TODO Action Listener
                openProfile();
            }
            if (e.getSource() == editButton) {
                //guimain.inputProfile(); //not needed
                editProfile();
            }
            if (e.getSource() == inputButton) {
                //guimain.inputProfile(); //not needed
                inputProfile();
            }
        }
    };

    /* open profile */
    public void openProfile() {
        JOptionPane.showMessageDialog(null, "[Profile Opens]",
                "Open Profile",JOptionPane.INFORMATION_MESSAGE);

    }

    public void editProfile() {
        JOptionPane.showMessageDialog(null, "[Logged-in User Profile]",
                "Open User Profile",JOptionPane.INFORMATION_MESSAGE);
    }

    public void inputProfile() { //use for appending new profiles to main menu
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

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
