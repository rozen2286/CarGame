package Utilities;

import java.awt.*;

public class CalculusMethods {

    /**
     * מחשבת שיפוע בין שתי נקודות במישור.
     *
     * @param x1 הקואורדינטה האופקית של הנקודה הראשונה.
     * @param y1 הקואורדינטה האנכית של הנקודה הראשונה.
     * @param x2 הקואורדינטה האופקית של הנקודה השנייה.
     * @param y2 הקואורדינטה האנכית של הנקודה השנייה.
     * @return השיפוע בין שתי הנקודות.
     * @throws IllegalArgumentException אם הקואורדינטות האופקיות שוות (x1 == x2).
     */
    public static double calculateSlope(double x1, double y1, double x2, double y2) {
        if (x1 == x2) {
            throw new IllegalArgumentException("The x coordinates must be different to calculate the slope.");
        }
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * מחשבת את חיתוך הציר ה-y (נקודת החיתוך עם ציר ה-y) של קו ישר על פי נקודה ושיפוע.
     *
     * @param x הקואורדינטה ה-x של הנקודה
     * @param y הקואורדינטה ה-y של הנקודה
     * @param m השיפוע של הקו
     * @return חיתוך הציר ה-y של הקו
     */
    public static double calculateIntercept(double x, double y, double m) {
        return y - m * x;
    }

    /**
     * מחשבת את השינוי (delta) הנדרש כדי לחלק את המרחק בין שתי נקודות למספר חלקים שווים.
     * <p>
     * מתודה זו מחשבת את הדלתא, שהיא השינוי הנדרש כדי לחלק את המרחק בין
     * שתי נקודות (x1 ו-x2) למספר חלקים שווים. התוצאה היא גודל כל חלק כאשר
     * המרחק בין x1 ל-x2 מחולק על ידי הערך הדלתא הנתון.
     *
     * @param start הקואורדינטה של הנקודה הראשונה.
     * @param end   הקואורדינטה של הנקודה השנייה.
     * @param steps מספר החלקים שבהם יש לחלק את המרחק בין x1 ל-x2.
     * @return גודל כל חלק (delta).
     * @throws IllegalArgumentException אם delta הוא אפס, כדי למנוע חלוקה באפס.
     */
    public static double delta(int start, int end, int steps) {
        if (steps == 0) {
            throw new IllegalArgumentException("The delta value must not be zero to avoid division by zero.");
        }
        return (double) (end - start) / steps;
    }

    /**
     * מחשבת את ערך ה-x על פי ערך y, שיפוע הקו וחיתוך הציר y.
     *
     * @param y ערך ה-y.
     * @param m השיפוע של הקו.
     * @param b חיתוך הציר y של הקו.
     * @return ערך ה-x המתאים לערך ה-y לפי הקו הישר.
     * @throws IllegalArgumentException אם השיפוע הוא אפס, כדי למנוע חלוקה באפס.
     */
    public static double calculateX(double y, double m, double b) {
        if (m == 0) {
            throw new IllegalArgumentException("The slope must not be zero to avoid division by zero.");
        }
        return (y - b) / m;
    }

    /**
     * מחשבת את ערך ה-y לפי ערך ה-x, שיפוע הקו וחיתוך הציר y.
     *
     * @param x ערך ה-x.
     * @param m השיפוע של הקו.
     * @param b חיתוך הציר y של הקו.
     * @return ערך ה-y המתאים לערך ה-x לפי הקו הישר.
     */
    public static double calculateY(double x, double m, double b) {
        return x * m + b;
    }

    public static double getWidth(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2.0));
    }
}