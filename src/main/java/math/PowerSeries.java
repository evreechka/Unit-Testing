package math;

public class PowerSeries {
    private int iterationCount;

    public PowerSeries(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public double acos(double x) {
        if (Math.abs(x) > 1)
            return Double.NaN;
        double result = Math.PI / 2;
        if (iterationCount > 50)
            iterationCount = 50;
        for (int i = 0; i <= iterationCount; i++) {
            result -= (factorial(2 * i) / (Math.pow(4, i) * Math.pow(factorial(i), 2) * (2 * i + 1))) * Math.pow(x, 2 * i + 1);
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
