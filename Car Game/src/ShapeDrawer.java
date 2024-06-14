import Utilities.CalculusMethods;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

import static Utilities.CalculusMethods.calculateIntercept;
import static Utilities.CalculusMethods.calculateSlope;

/**
 * מחלקה זו משמשת לציור צורות על בסיס נקודות נתונות.
 */
public class ShapeDrawer {

    public enum Side {LEFT, RIGHT}

    private Point[] points;
    private LinkedList<Integer> xPoints;
    private LinkedList<Integer> yPoints;

    /**
     * יוצר אובייקט חדש של ShapeDrawer עם מערך נקודות נתון.
     *
     * @param points מערך של נקודות שמגדירות את הצורה
     */
    public ShapeDrawer(Point[] points) {
        this.points = points;
        this.xPoints = new LinkedList<>();
        this.yPoints = new LinkedList<>();

        extractCoordinates(this.points);
    }

    /**
     * מחזיר מחרוזת שמייצגת את האובייקט.
     *
     * @return מחרוזת שמייצגת את האובייקט
     */
    @Override
    public String toString() {
        return "ShapeDrawer{" +
                "points=" + Arrays.toString(points) +
                ", xPoints=" + Arrays.toString(xPoints.toArray()) +
                ", yPoints=" + Arrays.toString(yPoints.toArray()) +
                '}';
    }

    /**
     * מחלץ את הקואורדינטות (x ו-y) מהנקודות ושומר אותן ברשימות נפרדות.
     *
     * @param points מערך של נקודות
     */
    private void extractCoordinates(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            this.xPoints.add(points[i].getX());
            this.yPoints.add(points[i].getY());
        }
    }

    /**
     * מצייר את הצורה על המסך.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     */
    public void drawShape(Graphics g) {
        int[] xArray = xPoints.stream().mapToInt(Integer::intValue).toArray();
        int[] yArray = yPoints.stream().mapToInt(Integer::intValue).toArray();
        g.fillPolygon(xArray, yArray, xArray.length);
    }

    /**
     * מחזיר את מערך הנקודות של הצורה.
     *
     * @return מערך הנקודות של הצורה
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * מחזיר את רשימת ערכי ה-X של נקודות הצורה.
     *
     * @return רשימת ערכי ה-X של נקודות הצורה
     */
    public LinkedList<Integer> getXPoints() {
        return xPoints;
    }

    /**
     * מחזיר את רשימת ערכי ה-Y של נקודות הצורה.
     *
     * @return רשימת ערכי ה-Y של נקודות הצורה
     */
    public LinkedList<Integer> getYPoints() {
        return yPoints;
    }

    /**
     * מחשב את ערך ה-X עבור קו ישר מסוים ו-y נתון.
     *
     * @param side הצד של הקו (LEFT = שמאל, RIGHT = ימין)
     * @param y ערך ה-y
     * @return ערך ה-X
     */
    public int getLineEquation(Side side, int y) {
        return side == Side.RIGHT ? Math.round(getLineEquationRight(y)) : Math.round(getLineEquationLeft(y));
    }

    /**
     * מחשב את ערך ה-X עבור קו ישר שמאלי ו-y נתון.
     *
     * @param y ערך ה-y
     * @return ערך ה-X
     */
    private int getLineEquationLeft(int y) {
        return calculateXForLine(points[0], points[1], y);
    }

    /**
     * מחשב את ערך ה-X עבור קו ישר ימני ו-y נתון.
     *
     * @param y ערך ה-y
     * @return ערך ה-X
     */
    private int getLineEquationRight(int y) {
        return calculateXForLine(points[2], points[3], y);
    }

    /**
     * מחשב את ערך ה-X עבור קו ישר נתון ו-y נתון.
     *
     * @param p1 נקודת התחלה של הקו
     * @param p2 נקודת סיום של הקו
     * @param y ערך ה-y
     * @return ערך ה-X
     */
    private int calculateXForLine(Point p1, Point p2, int y) {
        double slope = calculateSlope(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        double intercept = calculateIntercept(p1.getX(), p1.getY(), slope);
        return (int) CalculusMethods.calculateX(y, slope, intercept);
    }
}