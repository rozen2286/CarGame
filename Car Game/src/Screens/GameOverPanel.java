package Screens;

import EnemyCar.EnemyCarFactory;
import Utilities.GraphicsUtils;
import Utilities.MyFont;
import Utilities.MyMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {

    public final int WIDTH = Window.WIDTH;
    public final int HEIGHT = Window.HEIGHT;

    private Scene scene;
    private boolean isTextVisible = true;
    private Timer blinkTimer;
    private Timer removePanelTimer;

    private static final String idMusic = "gameOverMusic";

    /**
     * בונה את הפאנל של סיום המשחק
     * @param scene - האובייקט של הסצנה שבה הפאנל נמצא
     * @param x - המיקום האופקי של הפאנל
     * @param y - המיקום האנכי של הפאנל
     */
    public GameOverPanel(Scene scene, int x, int y) {

        setBounds(x, y, WIDTH, HEIGHT);
        this.setOpaque(false);
        setLayout(null);
        this.scene = scene;

        addMusic();

        startBlinkTimer();

        startRemovePanelTimer();
    }

    /**
     * מפעיל את טיימר ההבהוב
     */
    private void startBlinkTimer() {
        blinkTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTextVisible = !isTextVisible;
                repaint();
            }
        });
        blinkTimer.start();
    }

    /**
     * מפעיל את טיימר הסרת הפאנל
     */
    private void startRemovePanelTimer() {
        removePanelTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBlinkTimer();
                removeCurrentPanel();
            }
        });
        removePanelTimer.setRepeats(false);
        removePanelTimer.start();
    }

    /**
     * עוצר את טיימר ההבהוב
     */
    private void stopBlinkTimer() {
        blinkTimer.stop();
    }

    /**
     * מצייר את רכיבי הפאנל
     * @param g - אובייקט הגרפיקה לציור
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        if (isTextVisible) {
            paintGameOverText(g);
        }
        paintStaticText(g);
    }

    /**
     * מצייר את הרקע
     */
    private void paintBackground(Graphics g) {
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 30, 30, new Color(255, 255, 255, 128));
    }

    /**
     * מצייר את טקסט "Game Over"
     */
    private void paintGameOverText(Graphics g) {
        g.setFont(MyFont.getCustomFont(MyFont.eFont.FasterOne, 100));
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth("Game Over");
        int textHeight = fm.getHeight();
        int x = (WIDTH - textWidth) / 2;
        int y = (HEIGHT - textHeight) / 2 + fm.getAscent();
        g.drawString("Game Over", x, y);
    }

    /**
     * מצייר את טקסט "יוסף4"
     */
    private void paintStaticText(Graphics g) {

        String s = "You overtook " + EnemyCarFactory.getCounter() + " cars!";

        g.setFont(MyFont.getCustomFont(MyFont.eFont.PoetsenOne, 50));
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(s);
        int x = (WIDTH - textWidth) / 2;
        int y = HEIGHT - 100; // מיקום הטקסט בתחתית הפאנל
        g.drawString(s, x, y);
    }

    /**
     * מחיקת הפאנל הנוכחי
     */
    private void removeCurrentPanel() {
        Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }
        Window.removeTopPanel(scene);
        Window.addLoginScreen();
    }

    /**
     * מוסיף מוזיקה
     */
    private void addMusic() {
        MyMusic.playMusic(idMusic, "Car Game/resources/music/mixkit-melodic-game-over-956.wav", 1.0f, false);
    }
}