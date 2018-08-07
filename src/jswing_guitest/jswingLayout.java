package jswing_guitest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class jswingLayout {
    public static void main(String[] args) {

        //Buttons
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

        //Player ComboBox
        String[] players = new String[] {"1", "2", "3", "4"};
        JComboBox<String> comboPlayers = new JComboBox<String>(players);
        comboPlayers.setAlignmentY(Component.TOP_ALIGNMENT);
        comboPlayers.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPlayers.setMaximumSize(new Dimension(40, 20));

        //Game ComboBox
        String[] games = new String[] {"Fortnite", "Mario", "CSGO", "Super Smash"};
        JComboBox<String> comboGames = new JComboBox<String>(games);
        comboGames.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboGames.setMaximumSize(new Dimension(100, 20));

        //All TextFields
        JTextField fName = new JTextField("First Name");
        fName.setForeground(Color.GRAY);
        JTextField lName = new JTextField("Last Name");
        lName.setForeground(Color.GRAY);
        JTextField address = new JTextField("Address");
        address.setForeground(Color.GRAY);
        JTextField pCode = new JTextField("Postal Code");
        pCode.setForeground(Color.GRAY);
        JTextField province = new JTextField("Province (2 letters)");
        province.setForeground(Color.GRAY);
        JTextField phone = new JTextField("Phone Number");
        phone.setForeground(Color.GRAY);
        JTextField score = new JTextField("Score");
        score.setForeground(Color.GRAY);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(buttonA);
        topPanel.add(buttonB);
        topPanel.add(buttonC);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Bottom Panel"));


        Box aBox = Box.createVerticalBox();
        //aBox.setLayout(new BoxLayout(aBox,BoxLayout.LINE_AXIS));
        aBox.setPreferredSize(new Dimension(100,150));

        JScrollPane scrollPane = new JScrollPane(aBox,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBorder(BorderFactory.createTitledBorder("Database"));

        DisplayTable aTable = new DisplayTable();

        buttonA.addActionListener(
                e -> {
                    scrollPane.setBorder(BorderFactory.createTitledBorder("INSERT"));
                    //label.setText("INSERT");
                    fName.setVisible(true);
                    lName.setVisible(true);
                    address.setVisible(true);
                    pCode.setVisible(true);
                    province.setVisible(true);
                    phone.setVisible(true);
                    comboGames.setVisible(true);
                    buttonConfirm.setVisible(true);
                    score.setVisible(true);
                    comboPlayers.setVisible(false);
                    aBox.remove(comboPlayers);
                    aBox.add(fName);
                    aBox.add(lName);
                    aBox.add(address);
                    aBox.add(pCode);
                    aBox.add(province);
                    aBox.add(phone);
                    aBox.add(comboGames);
                    aBox.add(score);
                    aBox.add(buttonConfirm);

                    aBox.remove(aTable);
                }
        );
        buttonB.addActionListener(
                e -> {
                    scrollPane.setBorder(BorderFactory.createTitledBorder("UPDATE"));
                    //label.setText("UPDATE");
                    fName.setVisible(false);
                    lName.setVisible(false);
                    address.setVisible(false);
                    pCode.setVisible(false);
                    province.setVisible(false);
                    phone.setVisible(false);
                    score.setVisible(false);
                    comboGames.setVisible(false);

                    comboPlayers.setVisible(true);
                    aBox.add(comboPlayers);


                    buttonConfirm.setVisible(true);
                    aBox.add(buttonConfirm);

                    aBox.remove(aTable);
                }
        );
        buttonC.addActionListener(
                e -> {
                    scrollPane.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
                    //label.setText("DISPLAY");
                    fName.setVisible(false);
                    lName.setVisible(false);
                    address.setVisible(false);
                    pCode.setVisible(false);
                    province.setVisible(false);
                    phone.setVisible(false);
                    score.setVisible(false);
                    comboGames.setVisible(false);
                    buttonConfirm.setVisible(false);
                    aBox.remove(buttonConfirm);

                    comboPlayers.setVisible(false);
                    //aBox.add(comboPlayers);

                    aBox.add(aTable);

                }
        );

        fName.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        fName.setText("");
                        fName.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
        lName.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        lName.setText("");
                        lName.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
        address.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        address.setText("");
                        address.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
        pCode.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        pCode.setText("");
                        pCode.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
        province.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        province.setText("");
                        province.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
        phone.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        phone.setText("");
                        phone.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
        score.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        score.setText("");
                        score.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                }
        );
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

        frame.setMinimumSize(new Dimension(340, 400));
        frame.setVisible(true);


    }
}
