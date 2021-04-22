package com.java21days;

import javax.swing.*;

public class GUIDesign extends JFrame {
    String[] subs = { "Profile1", "Profile2", "Profile3" };
    JList<String> subList = new JList<>(subs);

    public GUIDesign() {
        super("Profiles");
        setSize(150, 335);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 JPanel panel = new JPanel();
                 JLabel subLabel = new JLabel("Profiles:");
                 panel.add(subLabel);
                 subList.setVisibleRowCount(8);
                 JScrollPane scroller = new JScrollPane(subList);
                 panel.add(scroller);
                 add(panel);
                 setVisible(true);
             }
             private static void setLookAndFeel() {
                 try {
                     UIManager.setLookAndFeel(
                         "javax.swing.plaf.nimbus.NimbusLookAndFeel"
                     );
                 } catch (Exception exc) {
                     System.out.println(exc.getMessage());
                 }
             }

             public static void main(String[] arguments) {
                 GUIDesign.setLookAndFeel();
                 GUIDesign app = new GUIDesign();
             }
        }