import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RoadCalcSingleLineTest extends TestCase {
    private StationIndex stationIndex = new StationIndex();
    private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
    List<Station> routeSingle;
    Line line1 = new Line(1, "Оранжевая");
    Station station1 = new Station("Медведково", line1);
    Station station6 = new Station("Серпуховская", line1);
    private List<Station> expected = new ArrayList<>();

    @Override
    protected void setUp() throws Exception {
        routeSingle = new ArrayList<>();
        Station station2 = new Station("Рижская", line1);
        Station station3 = new Station("Ясенево", line1);
        Station station4 = new Station("Алтуфьево", line1);
        Station station5 = new Station("Бибирево", line1);


        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);
        line1.addStation(station5);
        line1.addStation(station6);
        expected.add(station1);
        expected.add(station2);
        expected.add(station3);
        expected.add(station4);
        expected.add(station5);
        expected.add(station6);
    }

    public void testCalculateDurationSingleLine() {
        List<Station> actual = routeCalculator.getShortestRoute(station1, station6);
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }

}


