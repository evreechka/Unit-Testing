package math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSeriesTest {
    @ParameterizedTest
    @ValueSource(doubles = {-0.98, 0.98, 0, 0.5, -0.5})
    void calculateAcosWithValidValuesByPowerSeries(Double x) {
        PowerSeries powerSeries = new PowerSeries(50);

        final double expected = Math.acos(x);
        final double input = powerSeries.acos(x);

        assertTrue(Math.abs(expected - input) <= 0.05);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2, 2, 100, -200})
    void returnNanIfAbsValueMoreOne(Double x) {
        PowerSeries powerSeries = new PowerSeries(50);

        final double input = powerSeries.acos(x);

        assertEquals(Double.NaN, input);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 10, 20, 40, 60})
    void calculateAcosWithValidIterationCount(Integer countIteration) {
        PowerSeries powerSeries = new PowerSeries(countIteration);

        final double expected = Math.acos(0.8);
        final double input = powerSeries.acos(0.8);

        assertTrue(Math.abs(expected - input) <= 0.01);
    }
}
