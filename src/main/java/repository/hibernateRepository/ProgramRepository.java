package repository.hibernateRepository;

import model.comparators.ProgramLineComparator;
import model.data.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramRepository implements repository.interfaces.ProgramRepository
{
    private List<Program> programList;
    EntityManagerFactory factory ;
    EntityManager manager;

    private void fetch(){
     factory = Persistence.createEntityManagerFactory("default");
     manager = factory.createEntityManager();
     programList = manager.createQuery("SELECT program FROM Program program").getResultList();
    }


    public ProgramRepository(){
        this.programList = new ArrayList<>();
        fetch();
    }



    @Override
    public boolean add(Program entity) {
        manager.getTransaction().begin();
        boolean found = false;
        for(Program program : this.programList){
            if(program.getId() == entity.getId()){
                found = true;
                break;
            }
        }
        if(!found){
            this.programList.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Program remove(Integer integer) {
        manager.getTransaction().begin();
        Program temp = this.find(integer);
        if(temp != null){
            this.programList.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Program newEntity, Integer integer)
    {
       manager.getTransaction().begin();
       Program program=new Program();
       program.setId(newEntity.getId());
       manager.find(Program.class,program.getId());
       program.setDate(newEntity.getDate());
       program.setLine(newEntity.getLine());
       program.setV(newEntity.getV());
       program.setShift(newEntity.getShift());
       manager.getTransaction().commit();
       programList = manager.createQuery("SELECT program FROM Program program").getResultList();
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
