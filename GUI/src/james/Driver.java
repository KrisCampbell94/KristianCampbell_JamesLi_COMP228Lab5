package james;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        mainWindow.setSize(300, 200);
    }
}
