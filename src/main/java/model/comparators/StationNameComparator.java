package model.comparators;

import model.data.Station;

import java.util.Comparator;

public class StationNameComparator implements Comparator<Station> {


    @Override
    public int compare(Station o1, Station o2) {
        return CharSequence.compare(o1.getName(),o2.getName());

    }
}
