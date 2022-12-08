package repository.inMemoryRepository;

import model.comparators.ProgramLineComparator;
import model.data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProgramRepository implements repository.interfaces.ProgramRepository {
    private List<Program> programList;

    public ProgramRepository(){
        this.programList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);

        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        Line line2 = new Line("327", "Bus", "");
        List<Station> stationList1 = new ArrayList<>();
//        station1.addLine(line2);
//        station2.addLine(line2);
        stationList1.add(station1);
        stationList1.add(station2);
        Line line1 = new Line("327", "Bus", "", stationList1);

        Program program = new Program(1, bus, line1, "Morning", LocalDate.now());
        this.programList.add(program);
    }

    @Override
    public boolean add(Program entity) {
        boolean found = false;
        for(Program program : this.programList){
            if(program.getId() == entity.getId()){
                found = true;
                break;
            }
        }
        if(!found){
            this.programList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Program remove(Integer integer) {
        Program temp = this.find(integer);
        if(temp != null){
            this.programList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Program newEntity, Integer integer) {
        for(int i=0; i<this.programList.size(); i++){
            if(this.programList.get(i).getId() == integer){
                this.programList.set(i, newEntity);
            }
        }
    }

    @Override
    public Program find(Integer integer) {
        for(Program program : this.programList){
            if(program.getId() == integer){
                return program;
            }
        }
        return null;
    }

    @Override
    public List<Program> filterByVehicle(String vin) {
        List<Program> result = new ArrayList<>();
        for(Program program : programList){
            if(program.getV().getVin().equals(vin)){
                result.add(program);
            }
        }
        return result;
    }

    @Override
    public List<Program> sortByLine(boolean ascending) {
        if(ascending){
            this.programList.sort(new ProgramLineComparator());
        } else {
            this.programList.sort(new ProgramLineComparator().reversed());
        }
        return this.programList;
    }
    @Override
    public Integer getNextProgramID(){
        int next = 0;
        for(Program program: programList){
            next = program.getId();
        }
        return next + 1;
    }
}
