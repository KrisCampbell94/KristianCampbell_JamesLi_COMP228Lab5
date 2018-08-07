import datepicker.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;

public class GameDatabase extends JFrame {
    // Buttons
    private final JButton insert, update, display, datePopup, confirm;

    // Boolean for confirmation
    private boolean isInsertNotUpdate;

    // Lists and ComboBoxes
    private List<String> getGameID, getPlayerID;
    private final JComboBox<String> comboGames, comboPlayers;

    // TextFields and Strings for the TextField titles
    private final JTextField[] infoFields;
    private final String[] infoTitles;

    // Panels, Box, and ScrollPane
    private final JPanel topPanel, bottomPanel;
    private final Box tabBox;
    private final JScrollPane scrollPane;

    public GameDatabase(){
        super("Game and Player Database - © Kris Campbell & James Li");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        insert = new JButton("INSERT");
        update = new JButton("UPDATE");
        display = new JButton("DISPLAY");
        confirm = new JButton("CONFIRM");
        datePopup = new JButton("SELECT DATE");
        insert.setAlignmentY(Component.CENTER_ALIGNMENT);
        update.setAlignmentY(Component.CENTER_ALIGNMENT);
        display.setAlignmentY(Component.CENTER_ALIGNMENT);
        confirm.setAlignmentY(Component.CENTER_ALIGNMENT);
        datePopup.setAlignmentY(Component.CENTER_ALIGNMENT);

        getGameID = new LinkedList<>();
        getPlayerID = new LinkedList<>();
        try {
            DatabaseConnection.fillListString(getGameID,
                    false);
            DatabaseConnection.fillListString(getPlayerID,
                    true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        comboGames = new JComboBox<>();
        for (int i = 0; i < getGameID.size(); i++) {
            comboGames.addItem(getGameID.get(i));
        }
        comboPlayers = new JComboBox<>();
        for (int i = 0; i < getPlayerID.size(); i++) {
            comboPlayers.addItem(getPlayerID.get(i));
        }

        infoFields = new JTextField[8];
        infoTitles = new String[]{
                "First Name","Last Name","Address",
                "Postal Code","Province (2 Letters)",
                "Phone Number","Date","Score"
        };
        for (int i = 0; i < infoFields.length; i++) {
            infoFields[i] = new JTextField(infoTitles[i]);
            infoFields[i].setForeground(Color.GRAY);

            int selection = i;
            infoFields[i].addFocusListener(
                    new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            infoFields[selection].setText("");
                            infoFields[selection].setForeground(Color.BLACK);
                        }

                        @Override
                        public void focusLost(FocusEvent e) {

                        }
                    }
            );
        }

        tabBox = Box.createVerticalBox();
        tabBox.setPreferredSize(new Dimension(200,300));
        scrollPane = new JScrollPane(tabBox,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300,300));

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(insert);
        topPanel.add(update);
        topPanel.add(display);
        bottomPanel = new JPanel();
        bottomPanel.add(scrollPane);

        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.insets = new Insets(3,3,3,3);

        grid.gridx=0; grid.gridy=0; grid.gridwidth = 3;
        grid.gridheight =1; grid.weightx = 1;
        add(topPanel,grid);

        grid.gridx=0; grid.gridy=1; grid.gridwidth = 3;
        grid.gridheight =1; grid.weightx = grid.weighty = 1;
        add(bottomPanel,grid);

        insert.addActionListener(
                e -> insertSelected()
        );
        update.addActionListener(
                e -> updateSelected()
        );
        display.addActionListener(
                e -> {
                    scrollPane.setBorder(BorderFactory.createTitledBorder("DELETE"));
                }
        );
        insertSelected();

        datePopup.addActionListener(
                e->{
                    infoFields[6].setText(new DatePicker(this).setPickedDate());
                    infoFields[6].setForeground(Color.BLACK);
                }
        );
    }

    private void insertSelected(){
        scrollPane.setBorder(BorderFactory.createTitledBorder("INSERT"));
        isInsertNotUpdate = true;
        for (int i = 0; i < infoFields.length; i++) {
            infoFields[i].setVisible(true);
        }
        comboGames.setVisible(true);
        confirm.setVisible(true);
        datePopup.setVisible(true);
        comboPlayers.setVisible(false);

        for (int i = 0; i < 6; i++) {
            tabBox.add(infoFields[i]);
        }
        tabBox.add(comboGames);
        tabBox.add(infoFields[6]);
        tabBox.add(datePopup);
        tabBox.add(infoFields[7]);
        tabBox.add(confirm);
    }

    private void updateSelected(){
        scrollPane.setBorder(BorderFactory.createTitledBorder("UPDATE"));
        for (int i = 0; i < 6; i++) {
            infoFields[i].setVisible(true);
        }
        isInsertNotUpdate = false;
        comboGames.setVisible(false);
        confirm.setVisible(true);
        datePopup.setVisible(false);
        comboPlayers.setVisible(true);
        infoFields[6].setVisible(false);
        infoFields[7].setVisible(false);

        tabBox.add(comboPlayers);
        for (int i = 0; i < 6; i++) {
            tabBox.add(infoFields[i]);
        }
        tabBox.add(confirm);
    }

    private void displaySelected(){
        scrollPane.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
        for (int i = 0; i < infoFields.length; i++) {
            infoFields[i].setVisible(false);
        }
        comboGames.setVisible(false);
        confirm.setVisible(false);
        datePopup.setVisible(false);
        comboPlayers.setVisible(false);
    }
}
