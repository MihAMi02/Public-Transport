package model.comparators;

import model.data.Vehicle;

import java.util.Comparator;

public class VehicleParkNumberComparators implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return CharSequence.compare(o1.getParkNumber(), o2.getParkNumber());
    }
}
