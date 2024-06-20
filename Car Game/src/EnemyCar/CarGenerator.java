package EnemyCar;

import RoadManagement.Road;
import Screens.Scene;
import Utilities.CalculusMethods;
import Utilities.MyPoint;

import javax.swing.*;
import java.awt.*;

public class CarGenerator {

    private MyPoint point;

    private final ImageIcon CAR;
    private int width;
    private int height;

    private int lane;

    public CarGenerator(int width, int height, String fileName, int lane) {

        point = new MyPoint();
        this.point.setY(Road.START_ROD_Y);

        CAR = new ImageIcon(fileName);
        this.lane = lane;
        this.point.setX(EnemyCarFactory.getLanePositionsX(this.lane, this.point, width));

        this.width = -150;
        this.height = height / 4;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(CAR.getImage(), point.getX(), point.getY(), width, height, null);
    }

    public void move() {
        this.point.setY(point.getY() + 3);
        this.point.setX(EnemyCarFactory.getLanePositionsX(this.lane, this.point, width));

        carResizing();
    }

    private void carResizing() {
        if (height < EnemyCarFactory.CAR_HEIGHT) {
            if (point.getY() % 5 == 0) {
                this.height += 2;
            }
        }

        if (lane == 1) {
            if (width < EnemyCarFactory.CENTER_LANE_WIDTH) {
                this.width = EnemyCarFactory.getWidthLane(lane, point.getY()) / 2;
            }
            return;
        }
        if (width < EnemyCarFactory.OTHER_LANE_WIDTH) {
            this.width = EnemyCarFactory.getWidthLane(lane, point.getY()) / 2;
        }
    }

    public MyPoint getPoint() {
        return point;
    }
}
