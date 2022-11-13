package model.comparators;

import model.data.Line;

import java.util.Comparator;

public class LineUsedTicketsComparator implements Comparator<Line> {
    @Override
    public int compare(Line o1, Line o2) {
        return Integer.compare(o1.getUsedTickets().size(), o2.getUsedTickets().size());
    }
}
