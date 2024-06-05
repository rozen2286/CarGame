import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * מחלקה זו משמשת לציור צורות על בסיס נקודות נתונות.
 */
public class ShapeDrawer {
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
}
