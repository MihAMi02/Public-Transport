package model.comparators;

import model.data.Program;

import java.util.Comparator;



public class ProgramLineComparator implements Comparator<Program> {


    @Override
    public int compare(Program o1, Program o2) {
        return CharSequence.compare(o1.getLine().getLineNumber(),o2.getLine().getLineNumber());
    }
}
