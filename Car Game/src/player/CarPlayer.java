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
    private final ImageIcon car;

    private int lane = 1;
    private int[] lanePositionsX;
    private boolean isMoving;

    public CarPlayer() {

        point = new MyPoint(0, (Scene.HEIGHT / 3) * 2);

        car = new ImageIcon("Car Game/resources/Photos/CarPlayerImage.png");

        lanePositionsX = setLanePositionsX();
        this.point.setX(this.lanePositionsX[1]);

        isMoving = true;
    }

    public ImageIcon getCar() {
        return car;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(car.getImage(), point.getX(), point.getY(), car.getIconWidth() / 2, car.getIconHeight() / 2, null);
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public int getLane() {
        return lane;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    private int[] setLanePositionsX() {
        int lane0 = (int) Math.round(CalculusMethods.getWidth(Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                Road.getLeftLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - car.getIconWidth() / 4;
        lane0 += Road.getLeftLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());

        int lane1 = (int) Math.round(CalculusMethods.getWidth(Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - car.getIconWidth() / 4;
        lane1 += Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());

        int lane2 = (int) Math.round(CalculusMethods.getWidth(Road.getRightLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - car.getIconWidth() / 4;
        lane2 += Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());

        return new int[]{lane0, lane1, lane2};
    }

    public void move() {
        int targetX = this.lanePositionsX[this.lane];
        if (this.point.getX() < targetX) {
            this.point.setX(this.point.getX() + 1);
            setMoving(true);
        } else if (point.getX() > targetX) {
            this.point.setX(point.getX() - 1);
            setMoving(true);
        } else {
            setMoving(false);
        }
    }
}