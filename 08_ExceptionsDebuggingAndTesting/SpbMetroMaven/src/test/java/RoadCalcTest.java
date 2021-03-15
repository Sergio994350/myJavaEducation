import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
// тест с 2мя линиями из 3х станций с 1 переходом
public class RoadCalcTest extends TestCase {
    List<Station> route;

    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();
        Line line1 = new Line(1, "Оранжевая");
        Line line2 = new Line(2, "Серая");

        route.add(new Station("Медведково", line1));
        route.add(new Station("Рижская", line1));
        route.add(new Station("Ясенево", line1));
        route.add(new Station("Алтуфьево", line2));
        route.add(new Station("Бибирево", line2));
        route.add(new Station("Серпуховская", line2));

    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }

}
