import java.awt.*;
import java.util.Arrays;

public class Road {

    private final int panelWidth;
    private final int panelHeight;

    private final ShapeDrawer road;
    private final ShapeDrawer leftLine;
    private final ShapeDrawer rightLine;

    private final QuadrilateralPainter leftLineColor;
    private final QuadrilateralPainter rightLineColor;
    private final Color sideLinePrimaryColor = Color.RED;
    private final Color sideLineSecondaryColor = Color.WHITE;

    private final int horizonY;
    private final int roadWidth;

    private final int leftStartX;
    private final int leftEndX;
    private final int leftMidX1;
    private final int leftMidX2;

    private final int rightStartX;
    private final int rightEndX;
    private final int rightMidX1;
    private final int rightMidX2;

    private final int bottomY;
    private final int topY;


    QuadrilateralPainter quadrilateralPainter;
    QuadrilateralPainter quadrilateralPainter1;

    public Road(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        this.horizonY = panelHeight / 6;
        this.roadWidth = (int) (panelWidth * 1.5);

        this.leftStartX = panelWidth / 2 - roadWidth / 2;
        this.leftEndX = panelWidth / 2 - 50;
        this.leftMidX1 = leftEndX;
        this.leftMidX2 = leftStartX - 80;

        this.rightStartX = panelWidth / 2 + roadWidth / 2;
        this.rightEndX = panelWidth / 2 + 50;
        this.rightMidX1 = rightEndX;
        this.rightMidX2 = rightStartX + 80;

        this.bottomY = panelHeight;
        this.topY = horizonY;

        int[] roadXPoints = {leftStartX, rightStartX, panelWidth / 2 + 50, panelWidth / 2 - 50};
        int[] roadYPoints = {panelHeight, panelHeight, horizonY, horizonY};
        this.road = new ShapeDrawer(roadXPoints, roadYPoints);

        int[] leftXPoints = {leftStartX, leftEndX, leftMidX1, leftMidX2};
        int[] leftYPoints = {bottomY, topY, topY, bottomY};
        this.leftLine = new ShapeDrawer(leftXPoints, leftYPoints);

        System.out.println(Arrays.toString(leftXPoints));
        System.out.println(Arrays.toString(leftYPoints));

        int[] rightXPoints = {rightStartX, rightEndX, rightMidX1, rightMidX2};
        int[] rightYPoints = {bottomY, topY, topY, bottomY};
        this.rightLine = new ShapeDrawer(rightXPoints, rightYPoints);

        int[] centerLine1XPoints = {250 ,483, 483, 300};
        int[] centerLine1YPoints = {800, 133, 133, 800};
        ShapeDrawer shapeDrawer = new ShapeDrawer(centerLine1XPoints, centerLine1YPoints);
        quadrilateralPainter = new QuadrilateralPainter(shapeDrawer.getXPoints(), shapeDrawer.getYPoints(),10, Color.BLACK , Color.WHITE);

        int[] centerLine1XPoints1 = {750 ,516, 516, 800};
        int[] centerLine1YPoints1 = {800, 133, 133, 800};
        ShapeDrawer shapeDrawer1 = new ShapeDrawer(centerLine1XPoints1, centerLine1YPoints1);
        quadrilateralPainter1 = new QuadrilateralPainter(shapeDrawer1.getXPoints(), shapeDrawer1.getYPoints(),10, Color.BLACK , Color.WHITE);

        leftLineColor = new QuadrilateralPainter(leftLine.getXPoints(), leftLine.getYPoints(), 10, sideLinePrimaryColor, sideLineSecondaryColor);
        rightLineColor = new QuadrilateralPainter(rightLine.getXPoints(), rightLine.getYPoints(), 10, sideLinePrimaryColor, sideLineSecondaryColor);

    }

    public void drawShape(Graphics g) {
        g.setColor(Color.BLACK);

        this.road.drawShape(g);
        this.leftLine.drawShape(g);
        this.rightLine.drawShape(g);

        leftLineColor.paint(g);
        rightLineColor.paint(g);

        quadrilateralPainter.paint(g);
        quadrilateralPainter1.paint(g);
    }
}