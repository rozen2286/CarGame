import Utilities.CalculusMethods;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Utilities.CalculusMethods.*;
import static Utilities.CalculusMethods.delta;

/**
 * מחלקה זו משמשת לצביעה של מרובע עם מעברי צבעים.
 */
public class QuadrilateralPainter {

    private final Point[] points;
    private final ArrayList<Double> leftShoulderX;
    private final ArrayList<Double> leftShoulderY;
    private final ArrayList<Double> rightShoulderX;
    private final ArrayList<Double> rightShoulderY;

    private final int deltaSteps;

    private final Color color1;
    private final Color color2;
    private int isEven;

    private int pixelsToRise;

    /**
     * יוצר אובייקט חדש של QuadrilateralPainter עם נקודות, מספר צעדים ושני צבעים.
     *
     * @param points     מערך של ארבע נקודות שמגדירות את המרובע
     * @param deltaSteps מספר הצעדים למעבר בין הצבעים
     * @param c1         הצבע הראשון למעבר
     * @param c2         הצבע השני למעבר
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
        ///////////////////
        this.isEven = 1;
        ///////////////////
        setPixelsToRise(2);

        calculateShoulderPoints(this.points[0], this.points[2], leftShoulderX, leftShoulderY);
        calculateShoulderPoints(this.points[1], this.points[3], rightShoulderX, rightShoulderY);
    }

    /**
     * יוצר אובייקט חדש של QuadrilateralPainter עם נקודות ומספר צעדים, כשהצבעים הם שחור.
     *
     * @param points     מערך של ארבע נקודות שמגדירות את המרובע
     * @param deltaSteps מספר הצעדים למעבר בין הצבעים
     */
    public QuadrilateralPainter(Point[] points, int deltaSteps) {
        this(points, deltaSteps, Color.BLACK, Color.BLACK);
    }

    /**
     * מחזירה את המחרוזת המתארת את המצב הנוכחי של האובייקט.
     *
     * @return מחרוזת המתארת את המצב הנוכחי של האובייקט
     */
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
     * מגדירה את מספר הפיקסלים לעליה.
     *
     * @param pixelsToRise מספר הפיקסלים לעליה
     */
    public void setPixelsToRise(int pixelsToRise) {
        this.pixelsToRise = pixelsToRise;
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
     * @param point1     הנקודה הראשונה במקטע
     * @param point2     הנקודה השנייה במקטע
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
        int i;
        for (i = 0; i < leftShoulderX.size() - 1; i++) {
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

            setColor(g, i);
            g.fillPolygon(xRect, yRect, 4);
        }

        setColor(g, i);
        g.fillPolygon(new int[]{rightShoulderX.get(0).intValue(), points[1].getX(), points[2].getX(), leftShoulderX.get(leftShoulderX.size() - 1).intValue()},
                new int[]{rightShoulderY.get(0).intValue(), points[1].getY(), points[2].getY(), leftShoulderY.get(leftShoulderY.size() - 1).intValue()},
                4);
    }

    /**
     * מגדיר את הצבע למקטע הנוכחי.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     * @param i האינדקס של המקטע הנוכחי
     */
    private void setColor(Graphics g, int i) {
        if (i % 2 == isEven) {
            g.setColor(this.color2);
        } else {
            g.setColor(this.color1);
        }
    }

    /**
     * מזיז את המרובע כלפי מטה.
     */
    public void moveDown() {
        moveShoulder(leftShoulderX, leftShoulderY, points[0], points[1]);
        moveShoulder(rightShoulderX, rightShoulderY, points[3], points[2]);

        if (leftShoulderY.get(1) > points[0].getY()) {
            deleteShape();
            addShape();

            this.isEven = 1 - isEven;
        }
    }

    /**
     * מזיז את ה-Shoulder של המרובע.
     *
     * @param shoulderX רשימת ערכי ה-X של ה-Shoulder
     * @param shoulderY רשימת ערכי ה-Y של ה-Shoulder
     * @param point1    הנקודה הראשונה של ה-Shoulder
     * @param point2    הנקודה השנייה של ה-Shoulder
     */
    private void moveShoulder(ArrayList<Double> shoulderX, ArrayList<Double> shoulderY, Point point1, Point point2) {
        for (int i = 0; i < shoulderY.size(); i++) {
            shoulderY.set(i, shoulderY.get(i) + this.pixelsToRise);
        }

        double slope = CalculusMethods.calculateSlope(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        double intercept = CalculusMethods.calculateIntercept(point1.getX(), point1.getY(), slope);

        for (int i = 0; i < shoulderY.size(); i++) {
            double y = shoulderY.get(i);
            double x = CalculusMethods.calculateX(y, slope, intercept);
            shoulderX.set(i, x);
        }
    }

    /**
     * מוחקת את ה-Shape הנוכחי.
     */
    private void deleteShape() {
        this.leftShoulderX.remove(0);
        this.leftShoulderY.remove(0);
        this.rightShoulderX.remove(rightShoulderY.size() - 1);
        this.rightShoulderY.remove(rightShoulderY.size() - 1);
    }

    /**
     * מוסיפה את ה-Shape החדש.
     */
    private void addShape() {
        this.leftShoulderX.add(rightShoulderY.size(), (double) points[1].getX());
        this.leftShoulderY.add(rightShoulderY.size(), (double) points[1].getY());
        this.rightShoulderX.add(0, (double) points[1].getX());
        this.rightShoulderY.add(0, (double) points[1].getY());
    }
}