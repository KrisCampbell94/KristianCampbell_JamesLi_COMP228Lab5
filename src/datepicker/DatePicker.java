package datepicker;

// ASSISTED/BORROWED FROM https://examples.javacodegeeks.com/desktop-java/swing/java-swing-date-picker-example/
// FOR EDUCATIONAL PURPOSES

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// DatePicker Class
public class DatePicker {
    // Set the variables of month, year, monthYearLabel, day, pickerDialog, and dayButtons
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private JLabel monthYearLabel = new JLabel("",JLabel.CENTER);
    private String day = "";
    private JDialog pickerDialog;
    private JButton[] dayButtons = new JButton[49];

    // DATEPICKER is a dialog that when a button is selected, it'll return a date format.

    public DatePicker(JFrame parent){
        // Initializes the pickerDialog
        pickerDialog = new JDialog();
        // Specifies whether this dialog should be modal
        // Forces the user to interact with DatePicker before going back to the main/PARENT application
        pickerDialog.setModal(true);
        // Headers for the days of the week
        String[] daysOfWeekHeader = {
          "SUN","MON","TUE","WED","THU","FRI","SAT"
        };
        // A JPanel to display the calendar and all its buttons
        JPanel calendarPanel = new JPanel(new GridLayout(7,7));
        calendarPanel.setPreferredSize(new Dimension(430,120));

        // Setting up each of the dayButtons
        for (int i = 0; i < dayButtons.length; i++) {
            int selection = i;
            dayButtons[i] = new JButton();
            dayButtons[i].setFocusPainted(false);
            dayButtons[i].setBackground(Color.WHITE);
            // If it's not a day of week header (eg. Monday, Wednesday)
            if(i > 6){
                dayButtons[i].addActionListener(e -> {
                    day = dayButtons[selection].getActionCommand();
                    pickerDialog.dispose();
                });
            }
            // If it's a day of week header
            else{
                dayButtons[i].setText(daysOfWeekHeader[i]);
                dayButtons[i].setForeground(Color.RED);
            }
            // Add it all to the calendar panel
            calendarPanel.add(dayButtons[i]);
        }
        // Calls the method to display the date
        displayDate();

        // A JPanel to display the month options
        JPanel monthOptionsPanel = new JPanel(new GridLayout(1,3));
        // A JButton to go to the previous month
        JButton previous = new JButton("<< Previous");
        previous.addActionListener(e->{
            month--;
            displayDate();
        });
        // A JButton to go to the next month
        JButton next = new JButton("Next >>");
        next.addActionListener( e -> {
            month++;
            displayDate();
        });

        // Add previous, monthYearLabel, and next to the monthOptions Panel
        monthOptionsPanel.add(previous);
        monthOptionsPanel.add(monthYearLabel);
        monthOptionsPanel.add(next);

        // Combine both panels to the picker dialog
        pickerDialog.add(calendarPanel,BorderLayout.CENTER);
        pickerDialog.add(monthOptionsPanel,BorderLayout.SOUTH);

        // Show all the variables added to the dialog
        // Similar to the SHOW function
        pickerDialog.pack();
        // Sets the location of the dialog
        pickerDialog.setLocationRelativeTo(parent);
        // Sets the visibility of the dialog to true
        pickerDialog.setVisible(true);
    }

    // The method to display the date on the DatePicker
    private void displayDate(){
        // Set the text of the numbered days to blank
        for (int i = 7; i < dayButtons.length; i++) {
            dayButtons[i].setText("");
        }

        // Creates an instance of a SimpleDateFormat class
        // Change the format of the date to : ex/ January 2018
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        // Creates a instance of a Calendar class
        Calendar cal = Calendar.getInstance();
        // Sets the calendar date to the selected year, and month
        cal.set(year,month,1);

        // Sets the days of week and days in month
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
        // Days in month must have a plus one in order for the for loop to give out the proper results

        // Add each day in correspondence to the week date
        for (int i = 6 + dayOfWeek, day = 1; day < daysInMonth; i++, day++) {
            dayButtons[i].setText("" + day);
        }
        // Sets the label to the Formatted time from the calendar instance
        monthYearLabel.setText(sdf.format(cal.getTime()));
        // Sets the title of the dialog
        pickerDialog.setTitle("Date Picker");
    }

    // The method to set the picked date
    // In reality we're GETTING the picked date
    // However, we're setting it to a textfield so, technically it's correct
    public String setPickedDate(){
        // If day doesn't equal into anything, return nothing
        if(day.equals(""))
            return day;

        // If day does equal to something, set the correct format, the calendar, and return it all
        SimpleDateFormat sdf = new SimpleDateFormat(
          "yyyy-MM-dd"
        );
        // The format " yyyy-MM-dd " MUST be used for any SQL Date transfers.
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}


class DatePickerExample{
    public static void main(String[] args) {
        JLabel label = new JLabel("Selected Date : ");
        // Use to get the text of the datePicker
        final JTextField text = new JTextField(20);
        // Use to call the Dialog of the datePicker
        JButton popup = new JButton("Popup");

        JPanel mainPanel = new JPanel();
        mainPanel.add(label);
        mainPanel.add(text);
        mainPanel.add(popup);

        final JFrame mainFrame = new JFrame();
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);

        // If the button is click,
        // then set the text by adding a new instance of a datepicker with the parameter of the main frame,
        // and lastly call the setPickedDate function
        popup.addActionListener(e ->{
            text.setText(new DatePicker(mainFrame).setPickedDate());

            // NEEDED FOR SQL CONNECTION
            Date sqlDate = Date.valueOf(text.getText());
            System.out.println(sqlDate);
        });

    }
}