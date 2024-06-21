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

    private static int counter;

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

    /**
     * מחזירה את המערך של המכוניות.
     * @return LinkedList<CarGenerator> המערך של המכוניות.
     */
    public synchronized LinkedList<CarGenerator> getCarsArray() {
        return carsArray;
    }

    /**
     * מציירת את כל המכוניות על המסך.
     * @param g הגרפיקה לציור.
     */
    public synchronized void paintCars(Graphics g) {
        if (!carsArray.isEmpty()) {
            for (CarGenerator cars : getCarsArray()) {
                cars.paint(g);
            }
        }
    }

    /**
     * מזיזה את כל המכוניות.
     */
    public synchronized void moveCars() {
        if (!carsArray.isEmpty()) {
            for (CarGenerator cars : getCarsArray()) {
                cars.move();
            }
        }
    }

    /**
     * מסירה מכוניות שעברו את גובה המסך.
     */
    public synchronized void removeCars() {
        if (!getCarsArray().isEmpty()) {
            Iterator<CarGenerator> iterator = carsArray.iterator();
            while (iterator.hasNext()) {
                CarGenerator car = iterator.next();
                if (car.getPosition().getY() > Scene.HEIGHT) {
                    iterator.remove();
                    counter++;
                }
            }
        }
    }

    /**
     * מחזירה את המונה של המכוניות שהוסרו.
     * @return int המונה של המכוניות שהוסרו.
     */
    public static int getCounter() {
        return counter;
    }

    public static void resetCounter() {
        counter = 0;
    }

    /**
     * בודקת אם יש מספיק מרחק בין המכוניות ליצירת מכונית חדשה.
     * @return boolean true אם יש מספיק מרחק, false אחרת.
     */
    private boolean distanceCheck() {
        if (getCarsArray().isEmpty()) {
            return true;
        } else if (carsArray.get(carsArray.size() - 1).getPosition().getY() > CAR_HEIGHT * 1.2) {
            return true;
        }
        return false;
    }

    /**
     * מוסיפה מכונית חדשה למערך.
     */
    public void addCar() {
        int lane = getRandomLane();

        if (distanceCheck()) {
            String fileName = getRandomCarImage(lane);
            addCarToArray(lane, fileName);
        }
    }

    /**
     * מוסיפה מכונית חדשה למערך עם פרטי נתיב וקובץ תמונה.
     * @param lane הנתיב של המכונית החדשה.
     * @param fileName שם קובץ התמונה של המכונית החדשה.
     */
    private synchronized void addCarToArray(int lane, String fileName) {
        this.carsArray.add(new CarGenerator(widthCar(lane), CAR_HEIGHT, fileName, lane));
    }

    /**
     * מחזירה את רוחב המכונית בהתחשב בנתיב.
     * @param lane הנתיב של המכונית.
     * @return int הרוחב של המכונית.
     */
    private int widthCar(int lane) {
        return lane == 1 ? CENTER_LANE_WIDTH : OTHER_LANE_WIDTH;
    }

    /**
     * מחזירה נתיב רנדומלי.
     * @return int נתיב רנדומלי בין 0 ל-2.
     */
    private int getRandomLane() {
        return new Random().nextInt(3);
    }

    /**
     * מחזירה שם קובץ תמונה רנדומלי בהתחשב בנתיב.
     * @param lane הנתיב של המכונית.
     * @return String שם קובץ התמונה של המכונית.
     */
    private synchronized String getRandomCarImage(int lane) {
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

    /**
     * מחזירה את המיקום הרוחבי של המכונית בהתחשב בנתיב.
     * @param lane הנתיב של המכונית.
     * @param point נקודת המיקום של המכונית.
     * @param width הרוחב של המכונית.
     * @return int המיקום הרוחבי של המכונית.
     */
    public static synchronized int getLanePositionsX(int lane, MyPoint point, int width) {
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

    /**
     * מחשבת את המיקום הרוחבי של המכונית בהתחשב בנתיב ומיקום.
     * @param point נקודת המיקום של המכונית.
     * @param width הרוחב של המכונית.
     * @param startLane הנתיב ההתחלתי של המכונית.
     * @param endLane הנתיב הסופי של המכונית.
     * @return int המיקום הרוחבי של המכונית.
     */
    private static synchronized int getLanePosition(MyPoint point, int width, ShapeDrawer startLane, ShapeDrawer endLane) {
        int laneWidth = (int) Math.round(CalculusMethods.getWidth(endLane.getLineEquation(ShapeDrawer.Side.LEFT, point.getY()), point.getY(),
                startLane.getLineEquation(ShapeDrawer.Side.RIGHT, point.getY()), point.getY())) / 2 - width / 2;
        return laneWidth + startLane.getLineEquation(ShapeDrawer.Side.RIGHT, point.getY());
    }

    /**
     * מחזירה את רוחב הנתיב בהתחשב בנתיב וגובה המכונית.
     * @param lane הנתיב של המכונית.
     * @param y הגובה של המכונית.
     * @return int רוחב הנתיב.
     */
    public static synchronized int getWidthLane(int lane, int y) {
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

    /**
     * מפעילה את תהליך יצירת המכוניות.
     */
    public void start() {
        addCar();
        moveCars();
        removeCars();
    }
}