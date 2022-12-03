package repository.hibernateRepository;

import model.comparators.ProgramLineComparator;
import model.data.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramRepository implements repository.interfaces.ProgramRepository {
    private List<Program> programList;

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        programList = manager.createQuery("SELECT program FROM Program program").getResultList();
    }


    public ProgramRepository(){
        this.programList = new ArrayList<>();
        fetch();
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
