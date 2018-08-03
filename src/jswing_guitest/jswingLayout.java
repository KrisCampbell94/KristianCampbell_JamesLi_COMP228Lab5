package jswing_guitest;

import javax.swing.*;
import java.awt.*;

public class jswingLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonA = new JButton("BUTTON A");
        buttonA.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton buttonB = new JButton("BUTTON B");
        buttonB.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton buttonC = new JButton("BUTTON C");
        buttonC.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton button1 = new JButton("BUTTON BELOW");
        button1.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel topPanel = new JPanel();
        //topPanel.setBorder(BorderFactory.createTitledBorder("Top"));
        topPanel.add(buttonA);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Bottom"));
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.LINE_AXIS));
        bottomPanel.add(button1);

        //Container container = frame.getContentPane();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5,5,5,5);

        c.gridx=0;
        c.gridy=0;
        c.gridwidth = 1;
        c.gridheight = 1;
        //c.weightx = c.weighty = 1;
        frame.add(buttonA,c);

        c.gridx=1;
        c.gridy=0;
        c.gridwidth = 1;
        c.gridheight = 1;
        //c.weightx = c.weighty = 1;
        frame.add(buttonB,c);

        c.gridx=2;
        c.gridy=0;
        c.gridwidth = 1;
        c.gridheight = 1;
        //c.weightx = c.weighty = 1;
        frame.add(buttonC,c);

        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = c.weighty = 1;
        frame.add(bottomPanel,c);
        //container.add(topPanel);
        //container.add(bottomPanel);

        frame.setSize(340, 300);
        frame.setVisible(true);
    }
}
