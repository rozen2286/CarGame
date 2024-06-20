package player;

import javax.swing.*;
import java.awt.*;

public class Speedometer {

    private final int x;
    private final int y;
    private static int speed;
    private int speedLimit;
    private int travelSpeed;
    private static final ImageIcon GAUGE = new ImageIcon("Car Game/resources/Photos/speedometerImage1.png");
    private static int minSpeed;

    private static final int MAX_SPEED = 160;
    private static final int ANGLE_OFFSET = 80;
    private static final int OVAL_CENTER_Y_OFFSET = 25;
    private static final int[] BASE_X_POINTS = {0, -10, 10};
    private static final int[] BASE_Y_POINTS = {-62, 0, 0};

    /**
     * בנאי המחוגה
     *
     * @param x        - קואורדינטת X של המחוגה
     * @param y        - קואורדינטת Y של המחוגה
     * @param minSpeed - מהירות מינימלית
     */
    public Speedometer(int x, int y, int minSpeed) {
        this.x = x;
        this.y = y;

        setMinSpeed(minSpeed);
        this.travelSpeed = speed;
    }

    /**
     * מצייר את המחוגה על המסך
     *
     * @param g - אובייקט גרפי לציור
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int ovalCenterX = x + GAUGE.getIconWidth() / 2;
        int ovalCenterY = y + (GAUGE.getIconHeight() * 2 / 3) + OVAL_CENTER_Y_OFFSET;

        double angle = Math.toRadians((speed / (double) MAX_SPEED) * 160 - ANGLE_OFFSET);

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

    public static ImageIcon getImage() {
        return GAUGE;
    }

    /**
     * קובע את מהירות המחוגה
     *
     * @param speed - המהירות החדשה
     */
    private void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * מחזיר את המהירות הנוכחית של המחוגה
     *
     * @return - מהירות נוכחית
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * קובע את מגבלת המהירות
     *
     * @param speedLimit - מגבלת המהירות החדשה
     */
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * מחזיר את מגבלת המהירות הנוכחית
     *
     * @return - מגבלת המהירות הנוכחית
     */
    public int getSpeedLimit() {
        return speedLimit;
    }

    public static void setMinSpeed(int minSpeed) {
        Speedometer.minSpeed = minSpeed;
        Speedometer.speed = minSpeed;
    }

    /**
     * מחזיר את המהירות המינימלית הנוכחית
     *
     * @return - מהירות מינימלית נוכחית
     */
    public static int getMinSpeed() {
        return minSpeed;
    }

    /**
     * מזיז את המחוגה בהתאם למהירות הנסיעה הנוכחית
     */
    public void move() {

        if (speed == travelSpeed) {
            travelSpeed = getRandomSpeed();
        }

        if (speed < travelSpeed) {
            setSpeed(getSpeed() + 1);
        } else if (speed > travelSpeed) {
            setSpeed(getSpeed() - 1);
        }
    }

    /**
     * מחזיר מהירות אקראית חדשה בטווח המהירות המינימלית ומגבלת המהירות
     *
     * @return - מהירות אקראית חדשה
     */
    private int getRandomSpeed() {
        if (speedLimit == 0) {
            return minSpeed;
        }
        return (int) (Math.random() * (speedLimit) + minSpeed);
    }
}