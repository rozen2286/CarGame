package Screens;

import Utilities.GraphicsUtils;
import Utilities.MyFont;
import Utilities.MyMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;

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

        MyMusic.playMusic(idMusic, "Car Game/resources/music/mixkit-melodic-game-over-956.wav", 1.0f, false);

        // יצירת טיימר להבהוב הטקסט של "סיום המשחק"
        blinkTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTextVisible = !isTextVisible;
                repaint();
            }
        });
        blinkTimer.start();

        // יצירת טיימר להפסקת ההבהוב ומחיקת הפאנל אחרי 3 שניות
        removePanelTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blinkTimer.stop();  // עצירת הטיימר של ההבהוב
                removeCurrentPanel();  // מחיקת הפאנל הנוכחי
            }
        });
        removePanelTimer.setRepeats(false);  // הטיימר ירוץ רק פעם אחת
        removePanelTimer.start();
    }

    /**
     * מצייר את רכיבי הפאנל
     * @param g - אובייקט הגרפיקה לציור
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ציור הרקע
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 30, 30, new Color(255, 255, 255, 128));

        // ציור טקסט "Game Over" אם הוא נראה
        if (isTextVisible) {
            g.setFont(MyFont.getCustomFont(MyFont.eFont.FasterOne, 100));
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth("Game Over");
            int textHeight = fm.getHeight();
            int x = (WIDTH - textWidth) / 2;
            int y = (HEIGHT - textHeight) / 2 + fm.getAscent();
            g.drawString("Game Over", x, y);
        }
    }

    /**
     * מחיקת הפאנל הנוכחי
     */
    private void removeCurrentPanel() {
        // הסרת הפאנל הנוכחי מהמכולה שלו
        Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }

        Window.removeTopPanel(scene);

        Window.addLoginScreen();
    }
}