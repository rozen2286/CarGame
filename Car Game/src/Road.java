import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Road {

    private final int panelWidth;
    private final int panelHeight;

    private final ShapeDrawer road;
    private final ShapeDrawer leftLine;
    private final ShapeDrawer rightLine;
    private final ShapeDrawer leftCenterLine;
    private final ShapeDrawer rightCenterLine;

    private static final int deltaSteps = 20;

    private final QuadrilateralPainter leftLineColor;
    private final QuadrilateralPainter rightLineColor;
    private final QuadrilateralPainter leftCenterLineColor;
    private final QuadrilateralPainter rightCenterLineColor;
    private static final Color sideLinePrimaryColor = Color.RED;
    private static final Color sideLineSecondaryColor = Color.WHITE;
    private static final Color centerlineMainColor = Color.WHITE;
    private static final Color secondaryColorMidline = new Color(51, 51, 51);

    /**
     * יוצר כביש חדש על פי רוחב וגובה הפאנל הנתונים.
     *
     * @param panelWidth  רוחב הפאנל
     * @param panelHeight גובה הפאנל
     */
    public Road(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        int horizonY = panelHeight / 6;
        int roadWidth = (int) (panelWidth * 1.5);

        int leftStartX = panelWidth / 2 - roadWidth / 2;
        int leftEndX = panelWidth / 2 - 50;
        int leftMidX1 = leftEndX;
        int leftMidX2 = leftStartX - 80;

        int rightStartX = panelWidth / 2 + roadWidth / 2;
        int rightEndX = panelWidth / 2 + 50;
        int rightMidX1 = rightEndX;
        int rightMidX2 = rightStartX + 80;

        int startXCenter = (rightEndX - leftEndX) / 3;

        int bottomY = panelHeight;
        int topY = horizonY;

        // יצירת רשימת נקודות עבור הכביש
        LinkedList<Point> pointsRoad = new LinkedList<>();
        pointsRoad.add(new Point(leftStartX, bottomY));
        pointsRoad.add(new Point(rightStartX, bottomY));
        pointsRoad.add(new Point(panelWidth / 2 + 50, topY));
        pointsRoad.add(new Point(panelWidth / 2 - 50, topY));
        this.road = new ShapeDrawer(pointsRoad.toArray(new Point[0]));

        // יצירת רשימת נקודות עבור קו שמאלי
        LinkedList<Point> pointsLeftLine = new LinkedList<>();
        pointsLeftLine.add(new Point(leftStartX, bottomY));
        pointsLeftLine.add(new Point(leftEndX, topY));
        pointsLeftLine.add(new Point(leftMidX1, topY));
        pointsLeftLine.add(new Point(leftMidX2, bottomY));
        this.leftLine = new ShapeDrawer(pointsLeftLine.toArray(new Point[0]));
        leftLineColor = new QuadrilateralPainter(leftLine.getPoints(), deltaSteps, sideLinePrimaryColor, sideLineSecondaryColor);

        // יצירת רשימת נקודות עבור קו ימני
        LinkedList<Point> pointsRightLine = new LinkedList<>();
        pointsRightLine.add(new Point(rightStartX, bottomY));
        pointsRightLine.add(new Point(rightEndX, topY));
        pointsRightLine.add(new Point(rightMidX1, topY));
        pointsRightLine.add(new Point(rightMidX2, bottomY));
        this.rightLine = new ShapeDrawer(pointsRightLine.toArray(new Point[0]));
        rightLineColor = new QuadrilateralPainter(rightLine.getPoints(), deltaSteps, sideLinePrimaryColor, sideLineSecondaryColor);

        // יצירת רשימת נקודות עבור קו מרכזי שמאלי
        LinkedList<Point> pointsLeftCenterLine = new LinkedList<>();
        pointsLeftCenterLine.add(new Point(panelWidth / 4, bottomY));
        pointsLeftCenterLine.add(new Point(startXCenter + leftEndX, topY));
        pointsLeftCenterLine.add(new Point(startXCenter - 2 + leftEndX, topY));
        pointsLeftCenterLine.add(new Point(panelWidth / 4 + 50, bottomY));
        this.leftCenterLine = new ShapeDrawer(pointsLeftCenterLine.toArray(new Point[0]));
        leftCenterLineColor = new QuadrilateralPainter(leftCenterLine.getPoints(), deltaSteps, centerlineMainColor, secondaryColorMidline);

        // יצירת רשימת נקודות עבור קו מרכזי ימני
        LinkedList<Point> pointsRightCenterLine = new LinkedList<>();
        pointsRightCenterLine.add(new Point(panelWidth * 3 / 4, bottomY));
        pointsRightCenterLine.add(new Point(startXCenter * 2 + leftEndX, topY));
        pointsRightCenterLine.add(new Point(startXCenter * 2 + 2 + leftEndX, topY));
        pointsRightCenterLine.add(new Point(panelWidth * 3 / 4 - 50, bottomY));
        this.rightCenterLine = new ShapeDrawer(pointsRightCenterLine.toArray(new Point[0]));
        rightCenterLineColor = new QuadrilateralPainter(rightCenterLine.getPoints(), 20, centerlineMainColor, secondaryColorMidline);
    }

    /**
     * מצייר את כל רכיבי הכביש על המסך.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     */
    public void drawShape(Graphics g) {
        this.road.drawShape(g);
        this.leftLine.drawShape(g);
        this.rightLine.drawShape(g);

        leftLineColor.paint(g);
        rightLineColor.paint(g);
        leftCenterLineColor.paint(g);
        rightCenterLineColor.paint(g);
    }
}