import java.awt.*;

public class ShapeDrawer {

    private int[] xPoints;
    private int[] yPoints;

    public ShapeDrawer(int[] xPoints, int[] yPoints) {
        if (xPoints.length != yPoints.length) {
            throw new IllegalArgumentException("The length of xPoints and yPoints must be the same");
        }

        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public void drawShape(Graphics g) {
        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }
}
