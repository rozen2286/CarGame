import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarGen {
    private ArrayList<CarProperty> cars;
    private Road road;
    private static final int SPAWN_INTERVAL = 2000; // Milliseconds between car spawns
    private static final int UPDATE_INTERVAL = 5; // Milliseconds between car updates

    public CarGen(Road road) {
        this.road = road;
        this.cars = new ArrayList<>();
        startGenerating();
        startUpdating();
    }

    public void updateCars() {
        synchronized (cars) {
            ArrayList<CarProperty> carsToRemove = new ArrayList<>();

            for (CarProperty car : cars) {
                switch (car.getLane()) {
                    case 3:
                        car.MovingCarLeft();

                        break;
                    case 2:
                        car.MovingCarCenter();

                        break;
                    case 1:
                        car.MovingCarRight();

                        break;

                }

                if (car.getY() > road.getPanelHeight() || car.getY() < 0) {
                    carsToRemove.add(car);
                }
            }

            cars.removeAll(carsToRemove);
        }
    }

    private void generateCar() {
        Random random = new Random();
        int lane = random.nextInt(3) + 1;
        Car generatedCar = new Car(lane, this.road);
        synchronized (cars) {
            cars.add(generatedCar.EnemyGen());
        }
    }

    private void startGenerating() {
        new Thread(() -> {
            while (true) {
                generateCar();
                try {
                    Thread.sleep(SPAWN_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void startUpdating() {
        Timer timer = new Timer(UPDATE_INTERVAL, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                updateCars();
            }
        });
        timer.start();
    }

    public void drawCars(Graphics g) {
        synchronized (cars) {
            for (CarProperty car : cars) {
                car.Carprint(g);
            }
        }
    }


}
