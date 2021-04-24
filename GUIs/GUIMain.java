import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class GUIMain {
    public static void main(String args[]) {
        String profileList[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" };

        String title = "Main Menu";
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JList userProfile = new JList(profileList);
        JScrollPane scrollPane = new JScrollPane(userProfile);

        Container contentPane = f.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        f.setSize(200, 200);
        f.setVisible(true);
    }
}