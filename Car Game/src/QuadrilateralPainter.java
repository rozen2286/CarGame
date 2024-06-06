import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Utilities.CalculusMethods.*;
import static Utilities.CalculusMethods.delta;

/**
 * מחלקה זו משמשת לצביעה של מרובע עם מעברי צבעים.
 */
public class QuadrilateralPainter {

    private Point[] points;
    private ArrayList<Double> leftShoulderX;
    private ArrayList<Double> leftShoulderY;
    private ArrayList<Double> rightShoulderX;
    private ArrayList<Double> rightShoulderY;

    private int deltaSteps;

    private Color color1;
    private Color color2;

    /**
     * יוצר אובייקט חדש של QuadrilateralPainter עם נקודות, מספר צעדים ושני צבעים.
     *
     * @param points    מערך של ארבע נקודות שמגדירות את המרובע
     * @param deltaSteps מספר הצעדים למעבר בין הצבעים
     * @param c1        הצבע הראשון למעבר
     * @param c2        הצבע השני למעבר
     */
    public QuadrilateralPainter(Point[] points, int deltaSteps, Color c1, Color c2) {
        if (points.length != 4) {
            throw new IllegalArgumentException("The length of xPoints and yPoints must be 4");
        }

        this.points = points;
        this.deltaSteps = deltaSteps;

        this.leftShoulderX = new ArrayList<>();
        this.leftShoulderY = new ArrayList<>();
        this.rightShoulderX = new ArrayList<>();
        this.rightShoulderY = new ArrayList<>();

        this.color1 = c1;
        this.color2 = c2;

        calculateShoulderPoints(this.points[0], this.points[2], leftShoulderX, leftShoulderY);
        calculateShoulderPoints(this.points[1], this.points[3], rightShoulderX, rightShoulderY);
    }

    /**
     * יוצר אובייקט חדש של QuadrilateralPainter עם נקודות ומספר צעדים, כשהצבעים הם שחור.
     *
     * @param points    מערך של ארבע נקודות שמגדירות את המרובע
     * @param deltaSteps מספר הצעדים למעבר בין הצבעים
     */
    public QuadrilateralPainter(Point[] points, int deltaSteps) {
        this(points, deltaSteps, Color.BLACK, Color.BLACK);
    }

    @Override
    public String toString() {
        return "QuadrilateralPainter{" +
                "points=" + Arrays.toString(points) +
                ", leftShoulderX=" + Arrays.toString(leftShoulderX.toArray()) +
                ", leftShoulderY=" + Arrays.toString(leftShoulderY.toArray()) +
                ", rightShoulderX=" + Arrays.toString(rightShoulderX.toArray()) +
                ", rightShoulderY=" + Arrays.toString(rightShoulderY.toArray()) +
                ", deltaSteps=" + deltaSteps +
                ", color1=" + color1 +
                ", color2=" + color2 +
                '}';
    }
    /**
     * מחזיר את מערך הנקודות של המרובע.
     *
     * @return מערך הנקודות של המרובע
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * צובע את המרובע.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     */
    protected void paint(Graphics g) {
        drawShoulders(g);
    }

    /**
     * מחשבת את הנקודות על המקטעים ומוסיפה אותן לרשימות.
     *
     * @param point1    הנקודה הראשונה במקטע
     * @param point2    הנקודה השנייה במקטע
     * @param arrayListX רשימת ערכי ה-X של הנקודות
     * @param arrayListY רשימת ערכי ה-Y של הנקודות
     */
    private void calculateShoulderPoints(Point point1, Point point2, ArrayList<Double> arrayListX, ArrayList<Double> arrayListY) {
        double m = calculateSlope(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        double b = calculateIntercept(point1.getX(), point1.getY(), m);

        double deltaX = delta(point1.getX(), point2.getX(), this.deltaSteps);
        double deltaY = delta(point1.getY(), point2.getY(), this.deltaSteps);

        for (int i = 0; i <= this.deltaSteps; i++) {
            double xi = point1.getX() + i * deltaX;
            double yi = point1.getY() + i * deltaY;

            arrayListX.add(xi);
            arrayListY.add(yi);
        }
    }

    /**
     * מצייר את המקטעים (Shoulders) של המרובע.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     */
    private void drawShoulders(Graphics g) {
        for (int i = 0; i < leftShoulderX.size() - 1; i++) {
            int[] xRect = {
                    (int) Math.round(leftShoulderX.get(i)),
                    (int) Math.round(leftShoulderX.get(i + 1)),
                    (int) Math.round(rightShoulderX.get(rightShoulderX.size() - 2 - i)),
                    (int) Math.round(rightShoulderX.get(rightShoulderY.size() - 1 - i))
            };
            int[] yRect = {
                    (int) Math.round(leftShoulderY.get(i)),
                    (int) Math.round(leftShoulderY.get(i + 1)),
                    (int) Math.round(rightShoulderY.get(rightShoulderX.size() - 2 - i)),
                    (int) Math.round(rightShoulderY.get(rightShoulderY.size() - 1 - i))
            };

            if (i % 2 == 0) {
                g.setColor(this.color2);
            } else {
                g.setColor(this.color1);
            }

            g.fillPolygon(xRect, yRect, 4);
        }
    }

    private void moveDawn() {

    }
}