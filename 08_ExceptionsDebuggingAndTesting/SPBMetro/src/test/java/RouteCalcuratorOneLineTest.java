import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalcuratorOneLineTest extends TestCase {
    private StationIndex stationIndex = new StationIndex();
   private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
   private Line line1 = new Line(1,"Первая");
   private List<Station> expected = new ArrayList<>();
   private Station station1 = new Station("Иваново1",line1);
   private Station station4 = new Station("Иваново4",line1);
    @Override
    public void setUp() throws Exception {
        Station station2 = new Station("Иваново2",line1);
        Station station3 = new Station("Иваново3",line1);

        expected.add(station1);
        expected.add(station2);
        expected.add(station3);
        expected.add(station4);
        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);
    }

    public void testGetRouteOnTheLine() {
        List<Station> actual = routeCalculator.getShortestRoute(station1,station4);

        assertEquals(expected,actual);
    }

}
