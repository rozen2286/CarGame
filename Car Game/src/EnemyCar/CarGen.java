package EnemyCar;

import RoadManagement.Road;
import RoadManagement.ShapeDrawer;
import Utilities.CalculusMethods;
import Utilities.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class CarGen {
    private static final int SPAWN_INTERVAL = 2000;
    private LinkedList<GenertedCar> cars;
    private int speed;
    public CarGen() {
        this.cars = new LinkedList<GenertedCar>();
        startGenerating();
    }

    public void generateACar() {
        synchronized (cars) {
            if (cars.size() < 2) { // בדיקה אם יש פחות מ-2 מכוניות
                Random rand = new Random();
                int lane = rand.nextInt(3); // שינוי כדי לא לכלול את 3
                GenertedCar car = new GenertedCar(lane);
                cars.add(car);
                car.move();
            }
        }
    }
    public static boolean checkCollision(ImageIcon icon1, int x1, int y1, ImageIcon icon2, int x2, int y2) {
        int width1 = icon1.getIconWidth();
        int height1 = icon1.getIconHeight();
        int width2 = icon2.getIconWidth();
        int height2 = icon2.getIconHeight();

        Rectangle rect1 = new Rectangle(x1, y1, width1, height1);
        Rectangle rect2 = new Rectangle(x2, y2, width2, height2);

        return rect1.intersects(rect2);
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


    public void moveCars() {
        synchronized (cars) {
            cars.removeIf(car -> !car.getIsMoving());
            cars.forEach(car -> {
                car.move();
            });
        }
    }

    public void drawCars(Graphics g) {
        synchronized (cars) {
            for (GenertedCar car : cars) {
                car.paint(g);
            }
        }
    }

    private void startGenerating() {
        new Thread(() -> {
            while (true) {
                generateACar();
                try {
                    Thread.sleep(SPAWN_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}