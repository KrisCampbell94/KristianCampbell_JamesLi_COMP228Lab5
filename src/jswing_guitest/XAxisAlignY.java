// CODE IS BORROWED FROM : http://www.java2s.com/Code/Java/Swing-JFC/GridLayoutDemo3.htm
// FOR TESTING AND EDUCATIONAL PURPOSES

package jswing_guitest;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class XAxisAlignY {
    private static Container makeIt(String title, float alignment) {
        String labels[] = { "--", "--", "--" };

        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder(title));
        BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(layout);

        for (int i = 0, n = labels.length; i < n; i++) {
            JButton button = new JButton(labels[i]);
            button.setAlignmentY(alignment);
            container.add(button);
        }
        return container;
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Alignment Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container panel1 = makeIt("Top", Component.TOP_ALIGNMENT);
        Container panel2 = makeIt("Center", Component.CENTER_ALIGNMENT);
        Container panel3 = makeIt("Bottom", Component.BOTTOM_ALIGNMENT);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1, 3));
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);

        frame.setSize(423, 171);
        frame.setVisible(true);
    }
}
