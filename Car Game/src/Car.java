public class Car {
    private int lanenum;
    private CarProperty curCar;
    private Road road;

    public Car(int lanenum ,Road road) {
        this.lanenum = lanenum;
        this.road= road;
    }
    public CarProperty EnemyGen() {
        this.curCar = new CarProperty(this.road.laneStartEndWidth(this.lanenum).get(1).getX(), road.laneStartEndWidth(this.lanenum).get(2).getY(), this.road,this.lanenum);
        return curCar;
    }
}
