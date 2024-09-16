/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gustavogoncalves
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TesteMainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        JPanel panel = new JPanel();
        JButton button = new JButton("Say 'Hello World'");
        button.addActionListener(e -> System.out.println("Hello World!"));
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }
}
