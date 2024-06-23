package Screens;

import EnemyCar.CarGenerator;
import EnemyCar.EnemyCarFactory;
import RoadManagement.QuadrilateralPainter;
import RoadManagement.Road;
import Utilities.GraphicsUtils;
import Utilities.MyMusic;
import player.CarPlayer;
import player.Controller;
import player.OvertakeMeter;
import player.Speedometer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Scene extends JPanel {
    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;
    private final Road road;
    private final CarPlayer carPlayer;
    private final Controller controller;
    private final Speedometer speedometer;
    private final OvertakeMeter overtakeMeter;
    private final EnemyCarFactory enemyCarFactory;

    private int speedMove;
    private int indirectQuantity;
    private boolean running = true;

    private static final String idMusic = "musicBackgroundGame";
    private static final String idSoundCar = "soundCar";

    /**
     * קונסטרקטור של הפאנל הראשי של המשחק
     *
     * @param x - המיקום האופקי של הפאנל
     * @param y - המיקום האנכי של הפאנל
     */
    public Scene(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);

        playMusic();

        road = new Road(WIDTH, HEIGHT);
        carPlayer = new CarPlayer();
        controller = new Controller(this, carPlayer);
        addKeyListener(controller);
        setFocusable(true);
        requestFocusInWindow();

        speedometer = new Speedometer(0, 0, 0);
        speedometer.setMinSpeed(0);
        speedometer.setSpeedLimit(3);

        overtakeMeter = new OvertakeMeter(WIDTH - 220, 0, 0);
        overtakeMeter.setLevel(0);

        enemyCarFactory = new EnemyCarFactory();

        startThreads();

        this.speedMove = 4;
        CarGenerator.setSpeed(speedMove);
        QuadrilateralPainter.setSpeed(speedMove);
        this.indirectQuantity = 10;
    }

    /**
     * מצייר את רכיבי הפאנל
     *
     * @param g - אובייקט הגרפיקה לציור
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 0, 0, Color.WHITE);
        road.drawShape(g);
        carPlayer.paint(g);
        speedometer.paint(g);
        overtakeMeter.paint(g);
        enemyCarFactory.paintCars(g);
    }

    /**
     * מגדיל את מהירות המשחק
     */
    private void increaseGameSpeed() {
        QuadrilateralPainter.setSpeed(speedMove++);
        CarGenerator.setSpeed(speedMove);
        speedometer.setMinSpeed(speedometer.getSpeed() + 50);
        overtakeMeter.setLevel(overtakeMeter.getLevel() + 3);
        this.indirectQuantity += 10;
    }

    /**
     * יוצר ומתחיל את התהליך להזזת הכביש והמכוניות האויבות
     *
     * @return אובייקט Thread שמבצע את הפעולה
     */
    private Thread moveRoadAndCars() {
        Thread thread = new Thread(() -> {
            while (running) {
                road.moveDown();
                enemyCarFactory.start();

                if (checkCollision()) {
                    GameOverPanel gameOverPanel = new GameOverPanel(this, 0,0);
                    this.add(gameOverPanel);
                    gameOverPanel.requestFocus();

                    stopThreads();

                    stopMusic();
                }

                repaint();

                if (EnemyCarFactory.getCounter() > this.indirectQuantity) {
                    if (this.speedMove < 7) {
                        increaseGameSpeed();
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    /**
     * יוצר ומתחיל את התהליך להזזת רכב השחקן
     *
     * @return אובייקט Thread שמבצע את הפעולה
     */
    private Thread moveCarPlayer() {
        Thread thread = new Thread(() -> {
            while (running) {
                if (carPlayer.getIsMoving()) {
                    carPlayer.move();
                    repaint();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    /**
     * יוצר ומתחיל את התהליך להזזת מד המהירות
     *
     * @return אובייקט Thread שמבצע את הפעולה
     */
    private Thread moveOvertakeMeter() {
        Thread thread = new Thread(() -> {
            while (running) {
                this.speedometer.move();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    /**
     * מתחיל את כל התהליכים של המשחק
     */
    private void startThreads() {
        running = true;
        Thread moveRoad = moveRoadAndCars();
        moveRoad.start();
        Thread moveCarPlayer = moveCarPlayer();
        moveCarPlayer.start();
        Thread moveOvertakeMeter = moveOvertakeMeter();
        moveOvertakeMeter.start();
    }

    /**
     * עוצר את כל התהליכים של המשחק
     */
    public void stopThreads() {
        running = false;
    }

    /**
     * מחדשת את כל התהליכים של המשחק
     */
    public void resumeThreads() {
        startThreads();
    }

    /**
     * בודק אם יש התנגשות בין רכב השחקן למכוניות האויבות
     *
     * @return true אם יש התנגשות, אחרת false
     */
    private boolean checkCollision() {
        Rectangle playerBounds = carPlayer.getBounds();
        LinkedList<CarGenerator> enemyCars = enemyCarFactory.getCarsArray();

        for (CarGenerator enemyCar : enemyCars) {
            if (playerBounds.intersects(enemyCar.getBounds())) {

                MyMusic.playMusic("Collision", "Car Game/resources/music/SoundColiision.wav", 1.0f, false);

                return true;
            }
        }
        return false;
    }

    public static void playMusic() {
        MyMusic.playMusic(idMusic, "Car Game/resources/music/BeckgroundMusicGame.wav", 0.9f, true);
        MyMusic.playMusic(idSoundCar, "Car Game/resources/music/mixkit-driving-in-the-rain-1242.wav", 0.9f, true);
    }

    public static void stopMusic() {
        MyMusic.stopMusic(idMusic);
        MyMusic.stopMusic(idSoundCar);
    }
}
