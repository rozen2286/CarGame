package player;

import javax.swing.*;
import java.awt.*;

public class OvertakeMeter {

    private final int x;
    private final int y;
    private static final ImageIcon GAUGE = new ImageIcon("Car Game/resources/Photos/OvertakeMeterImage.png");
    private int level;

    private static final int MAX_SPEED = 10;
    private static final int ANGLE_OFFSET = 80;
    private static final int OVAL_CENTER_Y_OFFSET = 25;
    private static final int[] BASE_X_POINTS = {0, -10, 10};
    private static final int[] BASE_Y_POINTS = {-62, 0, 0};

    /**
     * בנאי למחוון המהירות.
     *
     * @param x     - קואורדינטת X של המחוון
     * @param y     - קואורדינטת Y של המחוון
     * @param level - מהירות מינימלית
     */
    public OvertakeMeter(int x, int y, int level) {
        this.x = x;
        this.y = y;

        setLevel(level);
    }

    /**
     * מחזיר את התמונה של המחוון.
     *
     * @return - אובייקט ImageIcon של המחוון
     */
    public static ImageIcon getImage() {
        return GAUGE;
    }

    /**
     * מצייר את המחוון על המסך.
     *
     * @param g - אובייקט Graphics לציור
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int ovalCenterX = x + GAUGE.getIconWidth() / 2;
        int ovalCenterY = y + (GAUGE.getIconHeight() * 2 / 3) + OVAL_CENTER_Y_OFFSET;

        // התאמת חישוב הזווית
        double angle = Math.toRadians((level - 1) / (double) (MAX_SPEED - 1) * 160 - ANGLE_OFFSET);

        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        for (int i = 0; i < 3; i++) {
            xPoints[i] = (int) (BASE_X_POINTS[i] * Math.cos(angle) - BASE_Y_POINTS[i] * Math.sin(angle)) + ovalCenterX;
            yPoints[i] = (int) (BASE_X_POINTS[i] * Math.sin(angle) + BASE_Y_POINTS[i] * Math.cos(angle)) + ovalCenterY;
        }

        g2d.setColor(Color.RED);
        g2d.fillPolygon(xPoints, yPoints, 3);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(ovalCenterX - 15, ovalCenterY - 15, 30, 30);

        g2d.drawImage(GAUGE.getImage(), x, y, GAUGE.getIconWidth(), GAUGE.getIconHeight(), null);
    }

    /**
     * מגדירה את המהירות המינימלית.
     *
     * @param level - מהירות מינימלית
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * מחזירה את המהירות המינימלית הנוכחית.
     *
     * @return - המהירות המינימלית הנוכחית
     */
    public int getLevel() {
        return level;
    }
}