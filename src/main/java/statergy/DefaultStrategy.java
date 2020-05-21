package statergy;

public class DefaultStrategy implements ParkingCostStrategy {
    @Override
    public int getCost(int parkHours) {
        if (parkHours < 2) {
            return 10;
        }
        return (parkHours - 2) * 10 + 10;
    }
}
