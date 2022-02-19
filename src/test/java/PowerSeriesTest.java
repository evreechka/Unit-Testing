import math.PowerSeries;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSeriesTest {
    @ParameterizedTest
    @ValueSource(doubles = {-0.98, 0.98, 0, 0.5, -0.5})
    void calculateAcosWithValidValuesByPowerSeries(Double x) {
        PowerSeries powerSeries = new PowerSeries(50);

        double expected = Math.acos(x);
        double input = powerSeries.arccos(x);

        assertTrue(Math.abs(expected - input) <= 0.05);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2, 2, 100, -200})
    void returnNanIfAbsValueMoreOne(Double x) {
        PowerSeries powerSeries = new PowerSeries(50);

        double input = powerSeries.arccos(x);

        assertEquals(Double.NaN, input);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 10, 20, 40, 60})
    void calculateAcosWithValidIterationCount(Integer countIteration) {
        PowerSeries powerSeries = new PowerSeries(countIteration);

        double expected = Math.acos(0.8);
        double input = powerSeries.arccos(0.8);

        assertTrue(Math.abs(expected - input) <= 0.01);
    }
}
