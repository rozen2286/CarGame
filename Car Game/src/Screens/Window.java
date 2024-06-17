package Screens;

import javax.swing.*;

/**
 * מחלקה זו מייצגת חלון גרפי עיקרי של התוכנית.
 */
public class Window extends JFrame {

    private static Window window;
    private JLayeredPane layeredPane;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    /**
     * יוצר אובייקט חדש של Screens.Window, מגדיר את גודל החלון ואת ההתנהגות הבסיסית שלו.
     */
    public Window() {
        Window.window = this;

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(layeredPane);

        LoginScreen loginScreen = new LoginScreen();
        layeredPane.add(loginScreen, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
    }

    /**
     * מוסיף פאנל חדש בראש החלון.
     *
     * @param newPanel הפאנל החדש להוספה
     */
    public static void addPanelOnTop(JPanel newPanel) {
        window.layeredPane.add(newPanel, JLayeredPane.PALETTE_LAYER);
        window.revalidate();
        window.repaint();
        newPanel.setVisible(true);
    }

    /**
     * מסיר את הפאנל העליון מהחלון.
     *
     * @param panelToRemove הפאנל להסרה
     */
    public static void removeTopPanel(JPanel panelToRemove) {
        window.layeredPane.remove(panelToRemove);
        window.revalidate();
        window.repaint();
    }
}