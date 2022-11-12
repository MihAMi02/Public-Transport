package model.comparators;

import model.data.Depot;

import java.util.Comparator;

public class DepotNameComparator implements Comparator<Depot> {

    @Override
    public int compare(Depot o1, Depot o2) {
        return CharSequence.compare(o1.getName(), o2.getName());
    }
}
