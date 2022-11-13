package model.comparators;

import model.data.Line;

import java.util.Comparator;

public class LineNumberComparator implements Comparator<Line> {
    @Override
    public int compare(Line o1, Line o2) {
        return CharSequence.compare(o1.getLineNumber(), o2.getLineNumber());
    }
}
