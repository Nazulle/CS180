import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUIMain extends JComponent implements Runnable {
    JButton openButton; // a button to clear screen
    JButton inputButton; // a button to change paint color

    JComboBox<String> comboBox;
    String profileName;
    String profileList[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" }; //append profile username to list
    ArrayList<String> testArray = new ArrayList<String>(Arrays.asList(profileList));

    GUIMain guimain; // variable of the type GUIMain

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == openButton) {
                openProfile();
            }
            if (e.getSource() == inputButton) {
                //guimain.inputProfile();
                inputProfile();
            }
            //todo make action listener
        }
    };

    /* open profile */
    public void openProfile() {
        JOptionPane.showMessageDialog(null, "[Profile Opens]",
                "Open Profile",JOptionPane.INFORMATION_MESSAGE);
    }

    public void inputProfile() {
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

        JPanel panel = new JPanel(); //top panel
        openButton = new JButton("Open Profile");
        openButton.addActionListener(actionListener);
        panel.add(openButton);

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
