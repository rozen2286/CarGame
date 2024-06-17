package player;

import RoadManagement.Road;
import RoadManagement.ShapeDrawer;
import Utilities.CalculusMethods;

import javax.swing.*;
import java.awt.*;

public class CarPlayer {

    private int x;
    private int y;
    private final ImageIcon car;

    private int lane = 1;
    private int[] lanePositionsX;
    private boolean isMoving;

    public CarPlayer() {

        car = new ImageIcon("Car Game/resources/Photos/CarPlayerImage.png");

        this.y = 500;
        lanePositionsX = setLanePositionsX();
        this.x = this.lanePositionsX[1];

        isMoving = true;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(car.getImage(), x, y, car.getIconWidth() / 2, car.getIconHeight() / 2, null);
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
        int lane0 = (int) Math.round(CalculusMethods.getWidth(Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, this.y), this.y,
                Road.getLeftLine().getLineEquation(ShapeDrawer.Side.RIGHT, this.y), this.y)) / 2 - car.getIconWidth() / 4;
        lane0 += Road.getLeftLine().getLineEquation(ShapeDrawer.Side.RIGHT, this.y);

        int lane1 = (int) Math.round(CalculusMethods.getWidth(Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, this.y), this.y,
                Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, this.y), this.y)) / 2 - car.getIconWidth() / 4;
        lane1 += Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, this.y);

        int lane2 = (int) Math.round(CalculusMethods.getWidth(Road.getRightLine().getLineEquation(ShapeDrawer.Side.LEFT, this.y), this.y,
                Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, this.y), this.y)) / 2 - car.getIconWidth() / 4;
        lane2 += Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, this.y);

        return new int[] {lane0, lane1, lane2};
    }

    public void move() {
        int targetX = this.lanePositionsX[this.lane];
        if (x < targetX) {
            x++;
            setMoving(true);
        } else if (x > targetX) {
            x--;
            setMoving(true);
        } else {
            setMoving(false);
        }
    }
}