import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;
// тест с 2мя линиями с 2мя переходами
public class TwoCrossTest extends TestCase {

        private List<Station> expected = new ArrayList<>();
        private List<Station> cross1 = new ArrayList<>();
        private List<Station> cross2 = new ArrayList<>();
        private StationIndex stationIndex = new StationIndex();
        private RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Line line1 = new Line(1,"Оранжевая");
        Line line2 = new Line(2,"Серая");
        Line line3 = new Line(3,"Зеленая");
        private Station station1 = new Station("Медведково",line1);
        private Station station8 = new Station("Сокол",line3);
        @Before
        public void setUp() throws Exception {
            Station station2 = new Station("Рижская",line1);
            Station station3 = new Station("Ясенево",line1);
            Station station4 = new Station("Алтуфьево",line2);
            Station station5 = new Station("Бибирево",line2);
            Station station6 = new Station("Динамо",line3);
            Station station7 = new Station("Аэропорт",line3);

            expected.add(station1);
            expected.add(station2);
            expected.add(station3);
            expected.add(station4);
            expected.add(station5);
            expected.add(station6);
            expected.add(station7);
            expected.add(station8);

            cross1.add(station3);
            cross1.add(station4);
            cross2.add(station5);
            cross2.add(station6);
            stationIndex.addConnection(cross1);
            stationIndex.addConnection(cross2);

            line1.addStation(station1);
            line1.addStation(station2);
            line1.addStation(station3);
            line2.addStation(station4);
            line2.addStation(station5);
            line3.addStation(station6);
            line3.addStation(station7);
            line3.addStation(station8);
        }

        public void testTwoCrossLines() {
            List<Station> actual = routeCalculator.getShortestRoute(station1,station8);
            assertEquals(expected,actual);
        }
    }