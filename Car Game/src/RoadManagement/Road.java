package RoadManagement;

import Utilities.MyPoint;

import java.awt.*;
import java.util.LinkedList;

public class Road {

    private final int panelWidth;
    private final int panelHeight;

    public static final int START_ROD_Y = -150;
    private static ShapeDrawer road;
    private static ShapeDrawer leftLine;
    private static ShapeDrawer rightLine;
    private static ShapeDrawer leftCenterLine;
    private static ShapeDrawer rightCenterLine;

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

        int halfPanelWidth = panelWidth / 2;
        int roadWidth = halfPanelWidth - 125;
        int sideOffset = 80;
        int centerOffset = 25;

        int leftStartX = 0;
        int leftEndX = roadWidth;
        int leftMidX1 = leftEndX - 10;
        int leftMidX2 = leftStartX - sideOffset;

        int rightStartX = panelWidth - 20;
        int rightEndX = halfPanelWidth + 125;
        int rightMidX1 = rightEndX + 10;
        int rightMidX2 = rightStartX + sideOffset;

        int startXCenter = (rightEndX - leftEndX) / 3;

        int bottomY = panelHeight;
        int topY = START_ROD_Y;

        // יצירת רשימת נקודות עבור הכביש
        LinkedList<MyPoint> pointsRoad = new LinkedList<>();
        pointsRoad.add(new MyPoint(leftStartX, bottomY));
        pointsRoad.add(new MyPoint(rightStartX, bottomY));
        pointsRoad.add(new MyPoint(rightEndX, topY));
        pointsRoad.add(new MyPoint(leftEndX, topY));
        this.road = new ShapeDrawer(pointsRoad.toArray(new MyPoint[0]));

        // יצירת רשימת נקודות עבור קו שמאלי
        LinkedList<MyPoint> pointsLeftLine = new LinkedList<>();
        pointsLeftLine.add(new MyPoint(leftStartX, bottomY));
        pointsLeftLine.add(new MyPoint(leftEndX, topY));
        pointsLeftLine.add(new MyPoint(leftMidX1, topY));
        pointsLeftLine.add(new MyPoint(leftMidX2, bottomY));
        this.leftLine = new ShapeDrawer(pointsLeftLine.toArray(new MyPoint[0]));
        leftLineColor = new QuadrilateralPainter(leftLine.getPoints(), deltaSteps, sideLinePrimaryColor, sideLineSecondaryColor);

        // יצירת רשימת נקודות עבור קו ימני
        LinkedList<MyPoint> pointsRightLine = new LinkedList<>();
        pointsRightLine.add(new MyPoint(rightStartX, bottomY));
        pointsRightLine.add(new MyPoint(rightEndX, topY));
        pointsRightLine.add(new MyPoint(rightMidX1, topY));
        pointsRightLine.add(new MyPoint(rightMidX2, bottomY));
        this.rightLine = new ShapeDrawer(pointsRightLine.toArray(new MyPoint[0]));
        rightLineColor = new QuadrilateralPainter(rightLine.getPoints(), deltaSteps, sideLinePrimaryColor, sideLineSecondaryColor);

        // יצירת רשימת נקודות עבור קו מרכזי שמאלי
        LinkedList<MyPoint> pointsLeftCenterLine = new LinkedList<>();
        pointsLeftCenterLine.add(new MyPoint(panelWidth / 3 - centerOffset, bottomY));
        pointsLeftCenterLine.add(new MyPoint(startXCenter + leftEndX, topY));
        pointsLeftCenterLine.add(new MyPoint(startXCenter + leftEndX, topY));
        pointsLeftCenterLine.add(new MyPoint(panelWidth / 3 + centerOffset, bottomY));
        this.leftCenterLine = new ShapeDrawer(pointsLeftCenterLine.toArray(new MyPoint[0]));
        leftCenterLineColor = new QuadrilateralPainter(leftCenterLine.getPoints(), deltaSteps, centerlineMainColor, secondaryColorMidline);

        // יצירת רשימת נקודות עבור קו מרכזי ימני
        LinkedList<MyPoint> pointsRightCenterLine = new LinkedList<>();
        pointsRightCenterLine.add(new MyPoint(2 * panelWidth / 3 - centerOffset, bottomY));
        pointsRightCenterLine.add(new MyPoint(startXCenter * 2 + leftEndX, topY));
        pointsRightCenterLine.add(new MyPoint(startXCenter * 2 + leftEndX, topY));
        pointsRightCenterLine.add(new MyPoint(2 * panelWidth / 3 + centerOffset, bottomY));
        this.rightCenterLine = new ShapeDrawer(pointsRightCenterLine.toArray(new MyPoint[0]));
        rightCenterLineColor = new QuadrilateralPainter(rightCenterLine.getPoints(), deltaSteps, centerlineMainColor, secondaryColorMidline);
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

        this.leftLineColor.paint(g);
        this.rightLineColor.paint(g);
        this.leftCenterLineColor.paint(g);
        this.rightCenterLineColor.paint(g);
    }

    public void moveDown() {
        this.leftLineColor.moveDown();
        this.rightLineColor.moveDown();
        this.leftCenterLineColor.moveDown();
        this.rightCenterLineColor.moveDown();
    }

    public static ShapeDrawer getRoad() {
        return road;
    }


    public static ShapeDrawer getLeftLine() {
        return leftLine;
    }

    public static ShapeDrawer getRightLine() {
        return rightLine;
    }

    public static ShapeDrawer getLeftCenterLine() {
        return leftCenterLine;
    }

    public static ShapeDrawer getRightCenterLine() {
        return rightCenterLine;
    }
}