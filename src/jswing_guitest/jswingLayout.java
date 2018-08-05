package jswing_guitest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jswingLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonA = new JButton("INSERT");
        buttonA.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton buttonB = new JButton("UPDATE");
        buttonB.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton buttonC = new JButton("DISPLAY");
        buttonC.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(buttonA);
        topPanel.add(buttonB);
        topPanel.add(buttonC);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Bottom Panel"));


        Box aBox = Box.createVerticalBox();
        //aBox.setLayout(new BoxLayout(aBox,BoxLayout.LINE_AXIS));
        aBox.setPreferredSize(new Dimension(200,300));

        JLabel label = new JLabel("Nothing to display here");
        buttonA.addActionListener(
                e -> {
                    label.setText("INSERT");
                    label.setFont( new Font("serif", Font.PLAIN, 20));
                    aBox.setPreferredSize(new Dimension(200,300));
                    aBox.add(buttonConfirm);
                }
        );
        buttonB.addActionListener(
                e -> {
                    label.setText("UPDATE");
                    label.setFont( new Font("serif", Font.PLAIN, 20));
                    aBox.setPreferredSize(new Dimension(200,300));
                    aBox.add(buttonConfirm);
                }
        );
        buttonC.addActionListener(
                e -> label.setText("DISPLAY")
        );


        aBox.add(label);

        JScrollPane scrollPane = new JScrollPane(aBox,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBorder(BorderFactory.createTitledBorder("Bottom Panel"));

        scrollPane.setPreferredSize(new Dimension(200,100));

        bottomPanel.add(scrollPane);

        //Container container = frame.getContentPane();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5,5,5,5);

        c.gridx=0;
        c.gridy=0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1;
        frame.add(topPanel,c);

        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = c.weighty = 1;
        //frame.add(bottomPanel,c);
        frame.add(scrollPane,c);
        //container.add(topPanel);
        //container.add(bottomPanel);

        frame.setMinimumSize(new Dimension(340, 300));
        frame.setVisible(true);


    }
}
