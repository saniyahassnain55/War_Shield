package screens;

import util.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen {
    JFrame frame;
    public WelcomeScreen(){ // Create the main frame
        // Create the main frame with dimensions 400x600
        frame = new JFrame("WarShield");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);  // Set frame size (width: 400px, height: 600px)
        frame.setLayout(null);  // Disable layout manager for absolute positioning

        // Set background image
        frame.setContentPane(new JLabel(new ImageIcon("background.jpg"))); // Add background directly to the frame

        // Create and position the title
        JLabel titleLabel = new JLabel("WarShield");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.RED);  // Set text color to white for contrast
        titleLabel.setBounds(20, 50, 150, 40);  // Position the title on the right side
        frame.add(titleLabel);

        // Create and position the button
        JButton button = new JButton("Start Game");
        button.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Call the method to close the frame
            }
        });
        button.setBounds(20, 100, 100, 30);  // Position the button on the right side
        frame.add(button);

        // Create and position the text area with a scrollbar on the right side
        JTextArea textArea = new JTextArea(20, 20);  // Set rows and columns
        String scores = FileManager.getAllScores();
        textArea.setText(scores);
        textArea.setFont (new Font ("Arial",Font.BOLD,12));
        textArea.setForeground (Color.CYAN);
        textArea.setBackground (Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);  // Make text area editable (optional)
//        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setBounds(190, 50, 150, 200);  // Position the scrollable text area on the right
        frame.add(textArea);

        // Make the frame visible
        frame.setVisible(true);
    }

    void startGame(){
        frame.dispose();
        JFrame frame = new JFrame("war shield");
        MainGame gamePanel = new MainGame();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
