package player;

import RoadManagement.Road;
import RoadManagement.ShapeDrawer;
import Screens.Scene;
import Utilities.CalculusMethods;
import Utilities.MyPoint;

import javax.swing.*;
import java.awt.*;

public class CarPlayer {

    private MyPoint point;
    private final ImageIcon CAR_CENTER;
    private final ImageIcon CAR_RIGHT;
    private final ImageIcon CAR_LEFT;
    private final int CAR_WIDTH;

    private int lane = 1;
    private int[] lanePositionsX;
    private boolean isMoving;

    public CarPlayer() {
        // יוצר אובייקט CarPlayer, מאתחל את מיקום הרכב, קובע את רוחב הרכב, ומגדיר את מיקומי הנתיבים
        point = new MyPoint(0, (Scene.HEIGHT / 3) * 2);

        CAR_CENTER = new ImageIcon("Car Game/resources/Photos/CarPlayerCanterImage.png");
        CAR_LEFT = new ImageIcon("Car Game/resources/Photos/carPlayerleftImage.png");
        CAR_RIGHT = new ImageIcon("Car Game/resources/Photos/carPlayerRightImage.png");

        CAR_WIDTH = 130;

        lanePositionsX = setLanePositionsX();
        this.point.setX(this.lanePositionsX[1]);

        isMoving = true;
    }

    /**
     * מצייר את הרכב על המסך לפי המיקום הנוכחי שלו
     *
     * @param g האובייקט גרפיקה לציור הרכב
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (lanePositionsX[0] == point.getX()) {
            g2d.drawImage(CAR_LEFT.getImage(), point.getX(), point.getY(), CAR_WIDTH, CAR_CENTER.getIconHeight() / 2, null);
        } else if (lanePositionsX[2] == point.getX()) {
            g2d.drawImage(CAR_RIGHT.getImage(), point.getX(), point.getY(), CAR_WIDTH, CAR_CENTER.getIconHeight() / 2, null);
        } else {
            g2d.drawImage(CAR_CENTER.getImage(), point.getX(), point.getY(), CAR_CENTER.getIconWidth() / 2, CAR_CENTER.getIconHeight() / 2, null);
        }
    }

    /**
     * קובע את הנתיב הנוכחי של הרכב
     *
     * @param lane מספר הנתיב (0-שמאל, 1-מרכז, 2-ימין)
     */
    public void setLane(int lane) {
        this.lane = lane;
    }

    /**
     * מחזיר את מספר הנתיב הנוכחי של הרכב
     *
     * @return מספר הנתיב (0-שמאל, 1-מרכז, 2-ימין)
     */
    public int getLane() {
        return lane;
    }

    /**
     * קובע האם הרכב בתנועה או לא
     *
     * @param moving ערך בוליאני המציין אם הרכב בתנועה
     */
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    /**
     * מחזיר האם הרכב בתנועה או לא
     *
     * @return ערך בוליאני המציין אם הרכב בתנועה
     */
    public boolean getIsMoving() {
        return isMoving;
    }

    /**
     * מחשב את המיקומים האופקיים של כל נתיב על הכביש
     *
     * @return מערך של מיקומים אופקיים לכל נתיב (שמאל, מרכז, ימין)
     */
    private int[] setLanePositionsX() {

        int lane0 = getLanePosition(point, this.CAR_CENTER, Road.getLeftLine(), Road.getLeftCenterLine());
        int lane1 = getLanePosition(point, this.CAR_CENTER, Road.getLeftCenterLine(), Road.getRightCenterLine());
        int lane2 = getLanePosition(point, this.CAR_CENTER, Road.getRightCenterLine(), Road.getRightLine());

        return new int[]{lane0, lane1, lane2};
    }

    private static int getLanePosition(MyPoint point, ImageIcon imageIcon, ShapeDrawer startLane, ShapeDrawer endLane) {
        int laneWidth = (int) Math.round(CalculusMethods.getWidth(endLane.getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                startLane.getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - imageIcon.getIconWidth() / 4;
        return laneWidth + startLane.getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());
    }

    /**
     * מעביר את הרכב למיקום האופקי של הנתיב הנוכחי בתנועות קטנות עד הגעה ליעד
     */
    public void move() {
        int targetX = this.lanePositionsX[this.lane];
        if (point.getX() < targetX) {
            point.setX(point.getX() + 2);
            setMoving(true);
        } else if (point.getX() > targetX) {
            point.setX(point.getX() - 2);
            setMoving(true);
        } else {
            setMoving(false);
        }
    }
}