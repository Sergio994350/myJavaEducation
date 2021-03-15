import java.util.ArrayList;
import java.util.List;

class Line extends  ForJson {

    private List<Station> stationsList = new ArrayList<>();

    public Line(String name, String number) {
        super(name, number);
    }


    public void addListStation(Station station){
        stationsList.add(station);
    }

    public List<Station> getListStation() {
        return stationsList;
    }
}