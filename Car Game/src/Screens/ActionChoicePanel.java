package Screens;

import Utilities.GraphicsUtils;
import Utilities.MyFont;
import Utilities.RoundedCornerButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionChoicePanel extends JPanel {

    public static final int WIDTH = Window.WIDTH / 2;
    public static final int HEIGHT = Window.HEIGHT / 4 * 2;

    private final String[] messages;
    private final Color colorText;
    private static RoundedCornerButton cancelButton;
    private static final int WIDTH_BUTTON = 50;
    private static final int HEIGHT_BUTTON = 50;

    private static RoundedCornerButton yesButton;
    private static RoundedCornerButton noButton;
    private static final int WIDTH_START_BUTTON = 150;
    private static final int HEIGHT_START_BUTTON = 50;
    private static final String YES_TEXT = "YES";
    private static final String NO_TEXT = "NO";
    private final Font customFontButton;
    private Float sizeFont;
    private final Color colorButton;

    public ActionChoicePanel(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);
        this.setOpaque(false);
        setLayout(null);

        messages = new String[] {"Are you sure you want to",
                "leave the game?",
                "All will be lost!"};

        colorText = Color.black;

        sizeFont = 35F;

        customFontButton = MyFont.getCustomFont(MyFont.eFont.PoetsenOne, sizeFont);
        colorButton = LoginScreen.COLOR_BUTTON;

        addCancelButton();
        addNoButton();
        addYesButton();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 30, 30, Color.WHITE);
        GraphicsUtils.paintRoundedBorder(g, WIDTH, HEIGHT, 10, 20, 20, Color.RED);

        drawCenteredMessage(g, customFontButton, colorText);
    }

    /**
     * מתודה להוספת כפתור ביטול
     * יוצרת כפתור עגול לביטול ומוסיפה אותו לפאנל
     */
    private void addCancelButton() {
        cancelButton = RoundedCornerButton.myButton(WIDTH - WIDTH_BUTTON, 0, WIDTH_BUTTON, HEIGHT_BUTTON, "✕", Color.WHITE, Color.RED, this);
    }

    /**
     * מתודה להוספת כפתור "לא"
     * יוצרת כפתור עגול עם המילה "לא" ומוסיפה אותו לפאנל
     */
    private void addNoButton() {
        noButton = RoundedCornerButton.myButton(WIDTH / 2 - WIDTH_START_BUTTON, HEIGHT - HEIGHT_BUTTON * 2,
                WIDTH_START_BUTTON, HEIGHT_START_BUTTON, NO_TEXT, colorButton, this);
        noButton.setFont(customFontButton);
    }

    /**
     * מתודה להוספת כפתור "כן"
     * יוצרת כפתור עגול עם המילה "כן" ומוסיפה אותו לפאנל ליד כפתור ה"לא"
     */
    private void addYesButton() {
        yesButton = RoundedCornerButton.myButton(noButton.getX() + WIDTH_START_BUTTON, noButton.getY(),
                WIDTH_START_BUTTON, HEIGHT_START_BUTTON, YES_TEXT, colorButton, this);
        yesButton.setFont(customFontButton);
    }

    /**
     * מתודה להוספת מאזין לאירוע לכפתורי הביטול וה"לא"
     * מקשרת מאזין לכפתור הביטול ולכפתור ה"לא"
     * @param actionListener - המאזין לאירוע שמוסף לכפתורים
     */
    public void addCancelButtonActionListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
        noButton.addActionListener(actionListener);
    }

    /**
     * מתודה להוספת מאזין לאירוע לכפתור ה"כן"
     * מקשרת מאזין לכפתור ה"כן"
     * @param actionListener - המאזין לאירוע שמוסף לכפתור
     */
    public void addYesButtonActionListener(ActionListener actionListener) {
        yesButton.addActionListener(actionListener);
    }

    /**
     * מתודה לציור הודעה ממורכזת
     * מציירת את ההודעות במרכז הפאנל
     * @param g - אובייקט הגרפיקה לציור
     * @param font - הגופן של הטקסט
     * @param color - צבע הטקסט
     */
    private void drawCenteredMessage(Graphics g, Font font, Color color) {
        FontMetrics fm = g.getFontMetrics(font);

        int y = HEIGHT / 3;

        for (String message : this.messages) {
            int messageWidth = fm.stringWidth(message);
            int x = (WIDTH - messageWidth) / 2;
            setMessage(g, x, y, font, color, message);
            y += fm.getHeight() + 5;
        }
    }

    /**
     * מתודה להוספת הודעה לציור
     * מציירת הודעה נתונה במיקום נתון עם גופן וצבע נתונים
     * @param g - אובייקט הגרפיקה לציור
     * @param x - המיקום האופקי של ההודעה
     * @param y - המיקום האנכי של ההודעה
     * @param font - הגופן של הטקסט
     * @param color - צבע הטקסט
     * @param message - ההודעה לציור
     */
    private void setMessage(Graphics g, int x, int y, Font font, Color color, String message) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(message, x, y);
    }
}