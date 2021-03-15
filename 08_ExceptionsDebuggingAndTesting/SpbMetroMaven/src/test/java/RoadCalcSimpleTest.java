import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

    // тест с 1 линией из 3х станций
    public class RoadCalcSimpleTest extends TestCase {
        List<Station> route;

        @Override
        protected void setUp() throws Exception {
            route = new ArrayList<>();
            Line line1 = new Line(1, "Оранжевая");

            route.add(new Station("Медведково", line1));
            route.add(new Station("Рижская", line1));
            route.add(new Station("Ясенево", line1));
        }

        public void testCalculateDuration() {
            double actual = RouteCalculator.calculateDuration(route);
            double expected = 5;
            assertEquals(expected, actual);
        }

        @Override
        protected void tearDown() throws Exception {

        }
    }