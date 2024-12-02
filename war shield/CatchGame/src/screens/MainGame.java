package screens;

import Player.Player;
import objects.FallingObjectStar;
import objects.FallingRectangle;
import objects.FallingTriangle;
import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class MainGame extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private ArrayList<FallingRectangle> fallingObjects;
    private Rectangle platform;
    private Player player;
    private boolean left, right;
    private int platformSpeed = 15;
    private int fallingSpeed = 5;
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 600;
    private  int level = 1;
    public MainGame() {
        fallingObjects = new ArrayList<>();
        platform = new Rectangle(SCREEN_WIDTH / 2 - 50, SCREEN_HEIGHT - 50, 100, 20);
        player = new Player();
        left = right = false;

        timer = new Timer(20, this);
        timer.start();

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw falling objects
        for (FallingRectangle obj : fallingObjects) {
            if (obj instanceof FallingTriangle) {
                g.setColor(Color.ORANGE);
            } else if (obj instanceof FallingObjectStar){
                g.setColor (Color.YELLOW);
            }else {
                g.setColor(Color.BLUE);
            }
            obj.draw(g);
        }

        // Draw platform
        g.setColor(Color.GREEN);
        g.fillRect(platform.x, platform.y, platform.width, platform.height);

        // Draw score and missed objects
        g.setColor(Color.white);
        g.drawString("Score: " + player.getScore(), 10, 20);
        g.drawString("Missed: " + player.getMissedObjects(), 10, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move falling objects
        for (int i = 0; i < fallingObjects.size(); i++) {
            FallingRectangle obj = fallingObjects.get(i);
            obj.move();

            // Check for collision with platform
            if (obj.intersects(platform)) {
                fallingObjects.remove(i);
                player.increaseScore();
                if ( level!=2 && player.getScore()  > 10 &&  player.getScore() <20 ) {
                    fallingSpeed++;
                    level = 2;
                    JOptionPane.showMessageDialog(this, "Level 2! Speed Increased!");
                }
                else if ( level!=3 && player.getScore()  > 20 &&  player.getScore() < 30) {
                    fallingSpeed++;
                    level = 3;
                    JOptionPane.showMessageDialog(this, "Level 3! Speed Increased!");
                }
                break;
            }

            // Remove objects that have fallen off the screen
            if (obj.getHeight() > SCREEN_HEIGHT) {
                fallingObjects.remove(i);
                player.increaseMissingObjects();
                if (player.getMissedObjects() > 10) {
                    endGame();
                    return;
                }
                break;
            }
        }

        // Add new falling objects periodically
        if (Math.random() < 0.035) {
            int x = new Random().nextInt(SCREEN_WIDTH - 20);
            if(this.level==1){
                fallingObjects.add(new FallingRectangle(x, fallingSpeed));
            }
            else if(this.level==2){
                fallingObjects.add(new FallingTriangle(x, ++fallingSpeed));
            }
            else if (this.level > 2){
                fallingObjects.add (new FallingObjectStar (x,++fallingSpeed));
            }
        }

        // Move platform
        if (left && platform.x > 0) {
            platform.x -= platformSpeed;
        }
        if (right && platform.x + platform.width < SCREEN_WIDTH) {
            platform.x += platformSpeed;
        }

        repaint();
    }

    private void endGame() {
        timer.stop(); // Stop the game timer
        player.setName(JOptionPane.showInputDialog(this, "Game Over! Enter your name:"));
        FileManager.saveScore(player.getName(), player.getScore());
        JOptionPane.showMessageDialog(this, "Your score: " + player.getScore() + "\nSaved to scores.txt");

        // Dispose of the current window
        SwingUtilities.getWindowAncestor(this).dispose();

        // Open the welcome screen
        SwingUtilities.invokeLater(() -> new WelcomeScreen());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }
}