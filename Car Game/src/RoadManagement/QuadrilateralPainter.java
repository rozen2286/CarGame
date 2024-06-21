package RoadManagement;

import Utilities.CalculusMethods;
import Utilities.MyPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Utilities.CalculusMethods.*;
import static Utilities.CalculusMethods.delta;

/**
 * מחלקה זו משמשת לצביעה של מרובע עם מעברי צבעים.
 */
public class QuadrilateralPainter {

    private final MyPoint[] myPoints;
    private final ArrayList<Double> leftShoulderX;
    private final ArrayList<Double> leftShoulderY;
    private final ArrayList<Double> rightShoulderX;
    private final ArrayList<Double> rightShoulderY;

    private final int deltaSteps;

    private final Color color1;
    private final Color color2;
    private int isEven;

    private static int speed;

    /**
     * יוצר אובייקט חדש של RoadManagement.QuadrilateralPainter עם נקודות, מספר צעדים ושני צבעים.
     *
     * @param myPoints     מערך של ארבע נקודות שמגדירות את המרובע
     * @param deltaSteps מספר הצעדים למעבר בין הצבעים
     * @param c1         הצבע הראשון למעבר
     * @param c2         הצבע השני למעבר
     */
    public QuadrilateralPainter(MyPoint[] myPoints, int deltaSteps, Color c1, Color c2) {
        if (myPoints.length != 4) {
            throw new IllegalArgumentException("The length of xPoints and yPoints must be 4");
        }

        this.myPoints = myPoints;
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
        setSpeed(6);

        calculateShoulderPoints(this.myPoints[0], this.myPoints[2], leftShoulderX, leftShoulderY);
        calculateShoulderPoints(this.myPoints[1], this.myPoints[3], rightShoulderX, rightShoulderY);
    }

    /**
     * יוצר אובייקט חדש של RoadManagement.QuadrilateralPainter עם נקודות ומספר צעדים, כשהצבעים הם שחור.
     *
     * @param myPoints     מערך של ארבע נקודות שמגדירות את המרובע
     * @param deltaSteps מספר הצעדים למעבר בין הצבעים
     */
    public QuadrilateralPainter(MyPoint[] myPoints, int deltaSteps) {
        this(myPoints, deltaSteps, Color.BLACK, Color.BLACK);
    }

    /**
     * מחזירה את המחרוזת המתארת את המצב הנוכחי של האובייקט.
     *
     * @return מחרוזת המתארת את המצב הנוכחי של האובייקט
     */
    @Override
    public String toString() {
        return "RoadManagement.QuadrilateralPainter{" +
                "points=" + Arrays.toString(myPoints) +
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
     * @param speed מספר הפיקסלים לעליה
     */
    public static void setSpeed(int speed) {
        QuadrilateralPainter.speed = speed;
    }

    public static int getSpeed() {
        return speed;
    }

    /**
     * מחזיר את מערך הנקודות של המרובע.
     *
     * @return מערך הנקודות של המרובע
     */
    public MyPoint[] getPoints() {
        return myPoints;
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
     * @param myPoint1     הנקודה הראשונה במקטע
     * @param myPoint2     הנקודה השנייה במקטע
     * @param arrayListX רשימת ערכי ה-X של הנקודות
     * @param arrayListY רשימת ערכי ה-Y של הנקודות
     */
    private void calculateShoulderPoints(MyPoint myPoint1, MyPoint myPoint2, ArrayList<Double> arrayListX, ArrayList<Double> arrayListY) {
        double m = calculateSlope(myPoint1.getX(), myPoint1.getY(), myPoint2.getX(), myPoint2.getY());
        double b = calculateIntercept(myPoint1.getX(), myPoint1.getY(), m);

        double deltaX = delta(myPoint1.getX(), myPoint2.getX(), this.deltaSteps);
        double deltaY = delta(myPoint1.getY(), myPoint2.getY(), this.deltaSteps);

        for (int i = 0; i <= this.deltaSteps; i++) {
            double xi = myPoint1.getX() + i * deltaX;
            double yi = myPoint1.getY() + i * deltaY;

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
            int rightIndex = rightShoulderX.size() - 2 - i;
            int rightIndexY = rightShoulderY.size() - 1 - i;

            if (rightIndex < 0 || rightIndexY < 0 || rightIndex >= rightShoulderX.size() || rightIndexY >= rightShoulderY.size()) {
                continue;
            }

            int[] xRect = {
                    (int) Math.round(leftShoulderX.get(i)),
                    (int) Math.round(leftShoulderX.get(i + 1)),
                    (int) Math.round(rightShoulderX.get(rightIndex)),
                    (int) Math.round(rightShoulderX.get(rightIndexY))
            };
            int[] yRect = {
                    (int) Math.round(leftShoulderY.get(i)),
                    (int) Math.round(leftShoulderY.get(i + 1)),
                    (int) Math.round(rightShoulderY.get(rightIndex)),
                    (int) Math.round(rightShoulderY.get(rightIndexY))
            };

            setColor(g, i);
            g.fillPolygon(xRect, yRect, 4);
        }

        setColor(g, i);
        g.fillPolygon(new int[]{rightShoulderX.get(0).intValue(), myPoints[1].getX(), myPoints[2].getX(), leftShoulderX.get(leftShoulderX.size() - 1).intValue()},
                new int[]{rightShoulderY.get(0).intValue(), myPoints[1].getY(), myPoints[2].getY(), leftShoulderY.get(leftShoulderY.size() - 1).intValue()},
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
        moveShoulder(leftShoulderX, leftShoulderY, myPoints[0], myPoints[1]);
        moveShoulder(rightShoulderX, rightShoulderY, myPoints[3], myPoints[2]);

        if (leftShoulderY.get(1) > myPoints[0].getY()) {
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
     * @param myPoint1    הנקודה הראשונה של ה-Shoulder
     * @param myPoint2    הנקודה השנייה של ה-Shoulder
     */
    private void moveShoulder(ArrayList<Double> shoulderX, ArrayList<Double> shoulderY, MyPoint myPoint1, MyPoint myPoint2) {
        for (int i = 0; i < shoulderY.size(); i++) {
            shoulderY.set(i, shoulderY.get(i) + speed);
        }

        double slope = CalculusMethods.calculateSlope(myPoint1.getX(), myPoint1.getY(), myPoint2.getX(), myPoint2.getY());
        double intercept = CalculusMethods.calculateIntercept(myPoint1.getX(), myPoint1.getY(), slope);

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
        this.leftShoulderX.add(rightShoulderY.size(), (double) myPoints[1].getX());
        this.leftShoulderY.add(rightShoulderY.size(), (double) myPoints[1].getY());
        this.rightShoulderX.add(0, (double) myPoints[1].getX());
        this.rightShoulderY.add(0, (double) myPoints[1].getY());
    }
}