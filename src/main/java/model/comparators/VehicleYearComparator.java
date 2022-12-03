package model.comparators;

import model.data.Vehicle;

import java.util.Comparator;

public class VehicleYearComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return Integer.compare(o1.getBuilt(), o2.getBuilt());
    }
}
