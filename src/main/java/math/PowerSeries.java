package math;

public class PowerSeries {
    private int iterationCount;

    public PowerSeries(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public double arccos(double x) {
        double result = Math.PI / 2;
        for (int i = 1; i <= iterationCount; i++) {
            if (i % 2 != 0)
                result += (-1 * Math.pow(x, i)) / factorial(i);
        }
        return result;
    }

    private long factorial(int x) {
        long result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }
}
