import core.Line;
import core.Station;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;
// тест с 2мя линиями с 1м переходом
public class OneCrossTest extends TestCase {

        private List<Station> actual = new ArrayList<>();
        private List<Station> cross = new ArrayList<>();
        private StationIndex stationIndex = new StationIndex();
        private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Line line1 = new Line(1,"Оранжевая");
        Line line2 = new Line(2,"Серая");
        private Station station1 = new Station("Медведково",line1);
        private Station station6 = new Station("Алтуфьево",line2);
        @Override
        public void setUp() throws Exception {
            Station station2 = new Station("Рижская",line1);
            Station station3 = new Station("Ясенево",line1);
            Station station4 = new Station("Бибирево",line2);
            Station station5 = new Station("Серпуховская",line2);

            actual.add(station1);
            actual.add(station2);
            actual.add(station3);
            actual.add(station4);
            actual.add(station5);
            actual.add(station6);

            cross.add(station3);
            cross.add(station4);
            stationIndex.addConnection(cross);

            line1.addStation(station1);
            line1.addStation(station2);
            line1.addStation(station3);
            line2.addStation(station4);
            line2.addStation(station5);
            line2.addStation(station6);
        }

        public void testOneCrossLine() {
            List<Station> expected = routeCalculator.getShortestRoute(station1,station6);

            assertEquals(expected,actual);
        }
    }