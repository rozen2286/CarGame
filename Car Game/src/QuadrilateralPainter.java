import java.awt.*;
import java.util.ArrayList;

import static Utilities.CalculusMethods.*;
import static Utilities.CalculusMethods.delta;

public class QuadrilateralPainter {

    private int deltaSteps;

    private Color color1;

    private Color color2;
    private ArrayList<Double> leftShoulderX;
    private ArrayList<Double> leftShoulderY;
    private ArrayList<Double> rightShoulderX;
    private ArrayList<Double> rightShoulderY;

    public QuadrilateralPainter(int[] xPoints, int[] yPoints, int deltaSteps, Color c1, Color c2) {

        if (xPoints.length != yPoints.length) {
            throw new IllegalArgumentException("The length of xPoints and yPoints must be the same");
        }

        if (xPoints.length != 4) {
            throw new IllegalArgumentException("The length of xPoints and yPoints must be 4");
        }

        this.deltaSteps = deltaSteps;

        this.leftShoulderX = new ArrayList<>();
        this.leftShoulderY = new ArrayList<>();
        this.rightShoulderX = new ArrayList<>();
        this.rightShoulderY = new ArrayList<>();

        this.color1 = c1;
        this.color2 = c2;

        calculateShoulderPoints(xPoints[0], yPoints[0], xPoints[2], yPoints[2], leftShoulderX, leftShoulderY);
        calculateShoulderPoints(xPoints[1], yPoints[1], xPoints[3], yPoints[3], rightShoulderX, rightShoulderY);
    }

    public QuadrilateralPainter(int[] xPoints, int[] yPoints, int deltaSteps) {
        this(xPoints, yPoints, deltaSteps, Color.BLACK, Color.BLACK);
    }

    protected void paint(Graphics g) {
        drawShoulders(g);
    }

    private void calculateShoulderPoints(int x1, int y1, int x2, int y2, ArrayList<Double> arrayListX, ArrayList<Double> arrayListY) {

        double m = calculateSlope(x1, y1, x2, y2);
        double b = calculateIntercept(x1, y1, m);

        double deltaX = delta(x1, x2, this.deltaSteps);
        double deltaY = delta(y1, y2, this.deltaSteps);

        for (int i = 0; i <= this.deltaSteps; i++) {
            double xi = x1 + i * deltaX;
            double yi = y1 + i * deltaY;

            arrayListX.add(xi);
            arrayListY.add(yi);
        }
    }

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
                g.setColor(this.color1);
            } else {
                g.setColor(this.color2);
            }

            g.fillPolygon(xRect, yRect, 4);
        }
    }
}