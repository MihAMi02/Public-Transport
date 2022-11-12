package repository.inMemoryRepository;

import model.data.Line;
import model.data.Station;

import java.util.ArrayList;
import java.util.List;

public class LineRepository implements repository.interfaces.LineRepository {

    List<Line> lineList;

    public LineRepository() {
        this.lineList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);
        Line line1 = new Line("327", "Bus", "", stationList1);
        this.lineList.add(line1);
    }

    @Override
    public boolean add(Line entity) {
        boolean found = false;
        for(Line line : this.lineList){
            if(line.getLineNumber().equals(entity.getLineNumber())){
                found = true;
                break;
            }
        }
        if(!found){
            this.lineList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Line remove(String s) {
        Line temp = this.find(s);
        if(temp != null){
            this.lineList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Line newEntity, String s) {
        for(int i=0; i<this.lineList.size(); i++){
            if(this.lineList.get(i).getLineNumber().equals(s)){
                this.lineList.set(i, newEntity);
            }
        }
    }

    @Override
    public Line find(String s) {
        for(Line line : this.lineList){
            if(line.getLineNumber().equals(s)){
                return line;
            }
        }
        return null;
    }

    @Override
    public List<Line> filterByType(String type) {
        return null;
    }

    @Override
    public List<Line> sortByLineNumber(boolean ascending) {
        return null;
    }

    @Override
    public List<Line> sortNumberUsedTickets(boolean ascending) {
        return null;
    }
}
