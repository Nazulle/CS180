import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMain extends JComponent implements Runnable {
    JButton clrButton; // a button to clear screen
    JButton fillButton; // a button to change paint color

    private DefaultListModel<Datum> dataModel = new DefaultListModel<>();
    private JList<Datum> datumList = new JList<>(dataModel);

    GUIMain guimain; // variable of the type GUIMain

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clrButton) {
                guimain.clear();
            }
            if (e.getSource() == fillButton) {
                guimain.fill();
            }
        }
    };

    /* open profile */
    public void clear() {
        JOptionPane.showMessageDialog(null, "Test1",
                "Order Form",JOptionPane.INFORMATION_MESSAGE);
    }

    public void fill() {
        JOptionPane.showMessageDialog(null, "Test2",
                "Order Form",JOptionPane.INFORMATION_MESSAGE);
    }

    //--------------------------------
    public GUIMain() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIMain());
    }

    public void run() {
        /* set up JFrame */
        JFrame frame = new JFrame("Challenge Exercise");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        guimain = new GUIMain();
        content.add(guimain, BorderLayout.CENTER);

        JPanel panel = new JPanel(); //top panel
        clrButton = new JButton("Clear");
        clrButton.addActionListener(actionListener);
        panel.add(clrButton);

        fillButton = new JButton("Fill");
        fillButton.addActionListener(actionListener);
        panel.add(fillButton);
        content.add(panel, BorderLayout.NORTH);

        String profileList[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" };

        JList userProfile = new JList(profileList);
        JScrollPane scrollPane = new JScrollPane(userProfile);

        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
