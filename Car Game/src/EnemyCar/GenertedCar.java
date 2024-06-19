package EnemyCar;

import RoadManagement.Road;
import RoadManagement.ShapeDrawer;
import Screens.Scene;
import Utilities.CalculusMethods;
import Utilities.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class GenertedCar
{
    private MyPoint point;
    private ImageIcon carimage;
    private int lane;
    private int[] lanePositionsX;
    private boolean isMoving;
    private int speed;

    public GenertedCar(int lane) {
        this.lane = lane;
        this.carimage = new ImageIcon("Car Game/resources/Photos/CarPlayerImage.png");
        this.point = new MyPoint(0, 0);
        this.lanePositionsX = setLanePositionsX();
        this.point.setY(-50); // Start off-screen
        this.point.setX(this.lanePositionsX[lane]);
        this.isMoving = true;
        this.speed=1;
        this.carimage=rotateImageIcon(this.carimage,this.lane);
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(carimage.getImage(), point.getX(), point.getY(), carimage.getIconWidth() / 2, carimage.getIconHeight() / 2, null);
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
    public void setSpeed(int yInervals)
    {
        switch (yInervals)
        {
            case 2:
                speed=2;
                break;
            case 3:
                speed=3;
                break;
            default:
                speed=1;
                System.out.println("too fast");
        }
    }
    public static ImageIcon rotateImageIcon(ImageIcon icon, int lane) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        int angle = 0;
        switch (lane)
        {
            case 0:
                angle=15;
                break;
            case 1:
                angle=0;
                break;
            case 2:
                angle=-15;
                break;
        }
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        at.rotate(rads, w / 2.0, h / 2.0);
        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(bufferedImage, rotatedImage);
        return new ImageIcon(rotatedImage);
    }

    private int[] setLanePositionsX() {
        int lane0 = (int) Math.round(CalculusMethods.getWidth(Road.getLeftLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - carimage.getIconWidth() / 4;
        lane0 += Road.getLeftLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY())-50;

        int lane1 = (int) Math.round(CalculusMethods.getWidth(Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY(),
                Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY())) / 2 - carimage.getIconWidth() / 4;
        lane1 += Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());

        int lane2 = (int) Math.round(CalculusMethods.getWidth(Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY(),
                Road.getRightLine().getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY())) / 2 - carimage.getIconWidth() / 4;
        lane2 += Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, point.getY())+50;

        return new int[]{lane0, lane1, lane2};
    }

    public MyPoint getPoint() {
        return point;
    }


    public void move() {
        if (point.getY() < Scene.HEIGHT) {
            this.point.setY(point.getY() + speed);
            this.point.setX(this.setLanePositionsX()[this.lane]);


        } else {
            setMoving(false);
        }
    }
}