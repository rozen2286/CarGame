import javax.swing.*;

public class Window extends JFrame {

    private static Window window;
    private JLayeredPane layeredPane;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public Window() {
        Window.window = this;

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
        this.add(layeredPane);

        LoginScreen loginScreen = new LoginScreen(0, 0);
        layeredPane.add(loginScreen, JLayeredPane.DEFAULT_LAYER);

        setVisible(true);
    }

    public static void addPanelOnTop(JPanel newPanel) {
        window.layeredPane.add(newPanel, JLayeredPane.PALETTE_LAYER);
        window.revalidate();
        window.repaint();
        newPanel.setVisible(true);
    }

    public static void removeTopPanel(JPanel panelToRemove) {
        window.layeredPane.remove(panelToRemove);
        window.revalidate();
        window.repaint();
    }
}
