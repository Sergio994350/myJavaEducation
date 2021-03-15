import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwoConTest extends TestCase {
    private List<Station> expected = new ArrayList<>();
    private List<Station> con = new ArrayList<>();
    private List<Station> con2 = new ArrayList<>();
    private StationIndex stationIndex = new StationIndex();
    private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
    Line line1 = new Line(1,"Первая");
    Line line2 = new Line(2,"Вторая");
    Line line3 = new Line(3,"Третья");
    private Station station1 = new Station("Иваново1",line1);
    private Station station7 = new Station("Маяк2",line3);
    @Before
    public void setUp() throws Exception {
        Station station2 = new Station("Иваново2",line1);
        Station station3 = new Station("Обухово1",line1);
        Station station4 = new Station("Обухово2",line2);
        Station station5 = new Station("Иваново4",line2);
        Station station6 = new Station("Маяк",line3);

        expected.add(station1);
        expected.add(station2);
        expected.add(station3);
        expected.add(station4);
        expected.add(station5);
        expected.add(station6);
        expected.add(station7);

        con.add(station3);
        con.add(station4);

        con2.add(station5);
        con2.add(station6);

        stationIndex.addConnection(con);
        stationIndex.addConnection(con2);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line2.addStation(station4);
        line2.addStation(station5);
        line3.addStation(station6);
        line3.addStation(station7);
    }

    public void testTwoCon() {
        List<Station> actual = routeCalculator.getShortestRoute(station1,station7);

        assertEquals(expected,actual);
    }
}
