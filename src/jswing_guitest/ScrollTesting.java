package jswing_guitest;

import javax.swing.*;
import java.awt.*;

public class ScrollTesting {
    public static void main(String[] args) {
        Frame aframe = new Frame();
    }
}

class Frame extends JFrame{
    Frame(){
        setTitle("JPanel and JScrollPane");
        setLayout(new FlowLayout());
        setPanelAndScroll();
        setSize(700,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void setPanelAndScroll(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,400));
        for (int i = 0; i < 100; i++) {
            panel.add(new JButton("Button : " + (i+1)));
        }
        JScrollPane scrollPane = new JScrollPane(
                panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        scrollPane.setPreferredSize(new Dimension(400,300));
        add(scrollPane);
    }
}
