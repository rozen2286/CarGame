package EnemyCar;

import RoadManagement.Road;
import Utilities.MyPoint;

import javax.swing.*;
import java.awt.*;

public class CarGenerator {

    private MyPoint position;

    private final ImageIcon carIcon;
    private final int width;
    private final int height;

    private final int lane;
    private static int speed;

    /**
     * בונה את האובייקט של מחולל המכונית.
     * @param width הרוחב של התמונה של המכונית.
     * @param height הגובה של התמונה של המכונית.
     * @param fileName שם קובץ התמונה של המכונית.
     * @param lane הנתיב בו המכונית תמוקם.
     */
    public CarGenerator(int width, int height, String fileName, int lane) {
        this.position = new MyPoint();
        this.position.setY(Road.START_ROD_Y);  // מניחים ש- START_ROD_Y הוא קבוע במחלקת Road

        this.carIcon = new ImageIcon(fileName);
        this.lane = lane;
        this.position.setX(EnemyCarFactory.getLanePositionsX(this.lane, this.position, width));

        this.width = width;
        this.height = height;
    }

    /**
     * מצייר את המכונית על המסך.
     * @param g הגרפיקה לציור.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(carIcon.getImage(), position.getX(), position.getY(), width, height, null);
    }

    /**
     * מזיז את המכונית על הציר האנכי.
     */
    public void move() {
        this.position.setY(this.position.getY() + speed);
        this.position.setX(EnemyCarFactory.getLanePositionsX(this.lane, this.position, this.width));
    }

    /**
     * מחזירה את המיקום הנוכחי של המכונית.
     * @return האובייקט MyPoint שמייצג את המיקום הנוכחי של המכונית.
     */
    public MyPoint getPosition() {
        return position;
    }

    /**
     * קובעת את המהירות של המכונית.
     * @param speed המהירות של המכונית.
     */
    public static void setSpeed(int speed) {
        CarGenerator.speed = speed;
    }

    /**
     * מחזירה את המהירות הנוכחית של המכונית.
     * @return המהירות של המכונית.
     */
    public static int getSpeed() {
        return speed;
    }

    /**
     * מחזירה את גבולות המכונית כמלבן.
     * @return אובייקט Rectangle שמייצג את גבולות המכונית.
     */
    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), width - 10, height - 10);
    }
}
