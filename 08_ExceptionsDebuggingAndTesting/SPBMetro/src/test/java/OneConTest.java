import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class OneConTest extends TestCase {
    private List<Station> actual = new ArrayList<>();
    private List<Station> con = new ArrayList<>();
    private StationIndex stationIndex = new StationIndex();
    private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
    Line line1 = new Line(1,"Первая");
    Line line2 = new Line(2,"Вторая");
    private Station station1 = new Station("Иваново1",line1);
    private Station station5 = new Station("Иваново4",line2);
    @Override
    public void setUp() throws Exception {
        Station station2 = new Station("Иваново2",line1);
        Station station3 = new Station("Обухово1",line1);
        Station station4 = new Station("Обухово2",line2);

        actual.add(station1);
        actual.add(station2);
        actual.add(station3);
        actual.add(station4);
        actual.add(station5);

        con.add(station3);
        con.add(station4);

        stationIndex.addConnection(con);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line2.addStation(station4);
        line2.addStation(station5);
    }

    public void testOneCon() {
       List<Station> expected = routeCalculator.getShortestRoute(station1,station5);

       assertEquals(expected,actual);
    }
}
