import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route = new ArrayList<>();
    Line line1 = new Line(1,"Первая");
    @Override
    public void setUp() throws Exception {

        route.add(new Station("Иваново1",line1));
        route.add(new Station("Иваново2",line1));
        route.add(new Station("Иваново3",line1));
        route.add(new Station("Иваново4",line1));
        route.add(new Station("Иваново5",line1));


    }

    public void testCalcuratorDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 10.0;
        assertEquals(expected,actual);
    }
}
