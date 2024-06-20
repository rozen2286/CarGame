package EnemyCar;

import RoadManagement.Road;
import RoadManagement.ShapeDrawer;
import Screens.Scene;
import Utilities.CalculusMethods;
import Utilities.MyPoint;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class EnemyCarFactory {

    private final String[] CANTER_CAR_FILE_NAME;
    private final String[] LEFT_CAR_FILE_NAME;
    private final String[] RIGHT_CAR_FILE_NAME;
    private LinkedList<CarGenerator> carsArray;

    public static final int CENTER_LANE_WIDTH = 107;
    public static final int OTHER_LANE_WIDTH = 130;
    public static final int CAR_HEIGHT = 148;

    private static Random randomCars;

    private int lastLane;
    private boolean valid;

    public EnemyCarFactory() {

        this.CANTER_CAR_FILE_NAME = new String[]{
                "Car Game/resources/Photos/CarBlueCanterImage.png",
                "Car Game/resources/Photos/CarGreenCanterImage.png",
                "Car Game/resources/Photos/CarOrangeCanterImage.png",
                "Car Game/resources/Photos/CarYellowCanterImage.png"
        };
        this.LEFT_CAR_FILE_NAME = new String[]{
                "Car Game/resources/Photos/CarBlueLeftImage.png",
                "Car Game/resources/Photos/CarGreenLeftImage.png",
                "Car Game/resources/Photos/CarOrangeLeftImage.png",
                "Car Game/resources/Photos/CarYellowLeftImage.png"
        };
        this.RIGHT_CAR_FILE_NAME = new String[]{
                "Car Game/resources/Photos/CarBlueRightImage.png",
                "Car Game/resources/Photos/CarGreenRightImage.png",
                "Car Game/resources/Photos/CarOrangeRightImage.png",
                "Car Game/resources/Photos/CarYellowRightImage.png"
        };

        this.carsArray = new LinkedList<>();

        randomCars = new Random();
    }

    public void paintCars(Graphics g) {
        if (!carsArray.isEmpty()) {
            for (CarGenerator cars : carsArray) {
                cars.paint(g);
            }
        }
    }

    public void moveCars() {
        if (!carsArray.isEmpty()) {
            for (CarGenerator cars : carsArray) {
                cars.move();
            }
        }
    }

    public void removeCars() {
        if (!carsArray.isEmpty()) {
            Iterator<CarGenerator> iterator = carsArray.iterator();
            while (iterator.hasNext()) {
                CarGenerator car = iterator.next();
                if (car.getPoint().getY() > Scene.HEIGHT) {
                    iterator.remove();
                }
            }
        }
    }


    private boolean distanceCheck() {
        if (carsArray.isEmpty()) {
            return true;
        } else if (carsArray.get(carsArray.size() - 1).getPoint().getY() > CAR_HEIGHT * 1.5) {
            return true;
        }
        return false;
    }

    public void addCar() {

        int lane = getRandomLane();

        if (distanceCheck()) {

            String fileName = getRandomCarImage(lane);
            this.carsArray.add(new CarGenerator(widthCar(lane), CAR_HEIGHT, fileName, lane));

            this.lastLane = lane;
            this.valid = true;

        } else if (lane != lastLane && valid) {

            String fileName = getRandomCarImage(lane);
            this.carsArray.add(new CarGenerator(widthCar(lane), CAR_HEIGHT, fileName, lane));

            this.lastLane = lane;
            this.valid = false;
        }
    }

    private int widthCar(int lane) {
        return lane == 1 ? CENTER_LANE_WIDTH : OTHER_LANE_WIDTH;
    }

    private int getRandomLane() {
        return new Random().nextInt(3);
    }

    private String getRandomCarImage(int lane) {
        int index;
        switch (lane) {
            case 0:
                index = randomCars.nextInt(LEFT_CAR_FILE_NAME.length);
                return LEFT_CAR_FILE_NAME[index];
            case 1:
                index = randomCars.nextInt(CANTER_CAR_FILE_NAME.length);
                return CANTER_CAR_FILE_NAME[index];
            case 2:
                index = randomCars.nextInt(RIGHT_CAR_FILE_NAME.length);
                return RIGHT_CAR_FILE_NAME[index];
            default:
                throw new IllegalArgumentException("Invalid lane: " + lane);
        }
    }

    public static int getLanePositionsX(int lane, MyPoint point, int width) {
        switch (lane) {
            case 0:
                return getLanePosition(point, width, Road.getLeftLine(), Road.getLeftCenterLine());
            case 1:
                return getLanePosition(point, width, Road.getLeftCenterLine(), Road.getRightCenterLine());
            case 2:
                return getLanePosition(point, width, Road.getRightCenterLine(), Road.getRightLine());
        }
        return 0;
    }

    private static int getLanePosition(MyPoint point, int width, ShapeDrawer startLane, ShapeDrawer endLane) {
        int laneWidth = (int) Math.round(CalculusMethods.getWidth(endLane.getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                startLane.getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - width / 2;
        return laneWidth + startLane.getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());
    }

    public static int getWidthLane(int lane, int y) {
        switch (lane) {
            case 0:
                return (int) Math.round(CalculusMethods.getWidth(Road.getLeftLine().getLineEquation(ShapeDrawer.Side.RIGHT, y), y, Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, y), y));
            case 1:
                return (int) Math.round(CalculusMethods.getWidth(Road.getLeftCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, y), y, Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.LEFT, y), y));
            case 2:
                return (int) Math.round(CalculusMethods.getWidth(Road.getRightCenterLine().getLineEquation(ShapeDrawer.Side.RIGHT, y), y, Road.getRightLine().getLineEquation(ShapeDrawer.Side.LEFT, y), y));
            default:
                throw new IllegalArgumentException("Invalid lane: " + lane);
        }
    }
}