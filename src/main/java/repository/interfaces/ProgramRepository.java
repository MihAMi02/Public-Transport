package repository.interfaces;

import model.data.Program;

import java.util.List;

public interface ProgramRepository extends CrudRepository<Integer, Program>
{
    List<Program> filterByVehicle(String vin);

    List<Program> sortByLine(boolean ascending);

    Integer getNextProgramID();

}




