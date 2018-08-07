package jswing_guitest;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.*;
import james.ResultSetTableModel;

public class DisplayTable extends JPanel {
    public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;database=GameDB;integratedSecurity=true";

    public static final String DEFAULT_QUERY = "SELECT * FROM Game";

    public ResultSetTableModel tableModel;

    // Constructor
    public DisplayTable() {
        //super("Displaying Table");

        // Initialize ResultSetTableModel and display database table
        try {
            tableModel = new ResultSetTableModel(DRIVER, DATABASE_URL, DEFAULT_QUERY);

            // Creating a JTable to display database information
            JTable resultTable = new JTable(tableModel);

            // Place boxes into window (content pane)
            add(new JScrollPane(resultTable));

            // Set window size and display = true
            setSize(50, 100);
            setVisible(true);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Database Driver not Found",
                    "Driver Not Found", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);

            tableModel.disconnectFromDatabase();

            System.exit(1);
        }
    }

    public void updateTable(String query) {
        try {
            tableModel.setQuery(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            try {
                tableModel.setQuery(DEFAULT_QUERY);
                query = DEFAULT_QUERY;
            } catch (SQLException exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }
}

