package Utilities;

/**
 * מחלקה זו מייצגת נקודה דו-ממדית עם קואורדינטות x ו-y.
 */
public class MyPoint {

    private int x;
    private int y;

    /**
     * יוצר אובייקט חדש של Point עם הקואורדינטות הנתונות.
     *
     * @param x ערך הקואורדינטה האופקית
     * @param y ערך הקואורדינטה האנכית
     */
    public MyPoint(int x, int y) {
        setX(x);
        setY(y);
    }

    public MyPoint() {
        this(0,0);
    }

    /**
     * מחזיר מחרוזת שמייצגת את האובייקט.
     *
     * @return מחרוזת שמייצגת את האובייקט
     */
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * משווה את האובייקט הנוכחי עם אובייקט אחר.
     *
     * @param o האובייקט להשוואה
     * @return true אם האובייקטים זהים, אחרת false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPoint myPoint = (MyPoint) o;
        return x == myPoint.x && y == myPoint.y;
    }

    /**
     * מגדיר את ערך הקואורדינטה האופקית.
     *
     * @param x ערך הקואורדינטה האופקית החדש
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * מגדיר את ערך הקואורדינטה האנכית.
     *
     * @param y ערך הקואורדינטה האנכית החדש
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * מחזיר את ערך הקואורדינטה האופקית.
     *
     * @return ערך הקואורדינטה האופקית
     */
    public int getX() {
        return this.x;
    }

    /**
     * מחזיר את ערך הקואורדינטה האנכית.
     *
     * @return ערך הקואורדינטה האנכית
     */
    public int getY() {
        return this.y;
    }
}
