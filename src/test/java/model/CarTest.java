package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    private Human human;
    private Car car;
    private final int MAX_REVOLUTION_NUMBER = 5000;
    private final int CHANGED_REVOLUTION_NUMBER = 500;
    private final int STARTED_REVOLUTION_NUMBER = 800;
    private final int STOPPED_REVOLUTION_NUMBER = 0;
    private final int MIN_CHANGED_TRANSMISSION_REVOLUTION_NUMBER = 2000;

    @BeforeEach
    public void setUp() {
        human = new Human("Mari");
        car = new Car("Dodge");
    }

    @Test
    public void checkMessageOfStartingCar() {
        human.buyCar(car);

        assertEquals("Success", human.startDriving());
    }

    @Test
    public void checkRevolutionOfStartingCar() {
        human.buyCar(car);
        human.startDriving();

        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void startCar() {
        human.buyCar(car);

        assertEquals("Success", human.startDriving());
        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void checkMessageOfStoppingCar() {
        human.buyCar(car);
        human.startDriving();
        assertEquals("Success", human.stopDriving());
    }

    @Test
    public void checkRevolutionOfStoppingCar() {
        human.buyCar(car);
        human.startDriving();
        human.stopDriving();

        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stopCar() {
        human.buyCar(car);
        human.startDriving();

        assertEquals("Success", human.stopDriving());
        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void startCarWhichNotBoughtYet() {
        assertEquals("You haven't car!!", human.startDriving());
    }

    @Test
    public void stopCarWhichIsNotBought() {
        assertEquals("You haven't car!!", human.stopDriving());
    }

    @Test
    public void checkMessageOfStartingCarMoreThanOneTime() {
        human.buyCar(car);
        human.startDriving();

        assertEquals("You have already start the car", human.startDriving());
    }

    @Test
    public void checkRevolutionNumberOfStartingCarMoreThanOneTime() {
        human.buyCar(car);
        human.startDriving();
        human.startDriving();

        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void startCarMoreThanOneTime() {
        human.buyCar(car);
        human.startDriving();

        assertEquals("You have already start the car", human.startDriving());
        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void startDrivingOnBrokenCar() {
        int carCondition = car.getCondition();
        int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != STOPPED_REVOLUTION_NUMBER) {
            human.changeTransmission(transmissionNumber);
            transmissionNumber = 2 - transmissionNumber;
            carCondition = car.getCondition();
        }
        assertEquals("Car is Broken. You cannot start driving", human.startDriving());
    }

    //step gas and brake
    @Test
    public void checkMessageOfSteppingGasInNotStartedCar() {
        human.buyCar(car);

        assertEquals("Your car isn't start", human.stepGas());
    }

    @Test
    public void checkRevolutionNumberOfSteppingGasInNotStartedCar() {
        human.buyCar(car);
        human.stepGas();

        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepGasInNotStartedCar() {
        human.buyCar(car);

        assertEquals("Your car isn't start", human.stepGas());
        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void checkMessageOfSteppingBrakeInNotStartedCar() {
        human.buyCar(car);

        assertEquals("Do nothing", human.stepBrake());
    }

    @Test
    public void checkRevolutionNumberOfSteppingBrakeInNotStartedCar() {
        human.buyCar(car);
        human.stepBrake();

        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepBrakeInNotStartedCar() {
        human.buyCar(car);

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepGasOnCarWhichIsNotBought() {
        assertEquals("You haven't car!!", human.stepGas());
    }

    @Test
    public void stepBrakeOnCarWhichIsNotBought() {
        assertEquals("You haven't car!!", human.stepBrake());
    }

    @Test
    public void checkMessageOfChangingRevolutionNumberBySteppingGas() {

        human.buyCar(car);
        human.startDriving();

        assertEquals("Success", human.stepGas());
    }

    @Test
    public void checkRevolutionNumberOfChangingRevolutionNumberBySteppingGas() {
        final int currentRevolutionNumber;

        human.buyCar(car);
        human.startDriving();
        currentRevolutionNumber = car.getCurrentRevolutionNumber();
        human.stepGas();

        assertEquals(currentRevolutionNumber + CHANGED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void changeRevolutionNumberBySteppingGas() {
        final int currentRevolutionNumber;

        human.buyCar(car);
        human.startDriving();
        currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepGas());
        assertEquals(currentRevolutionNumber + CHANGED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void checkMessageOfGettingMoreThanMaxCountRevolutionsBySteppingGas() {
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= MAX_REVOLUTION_NUMBER) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }

        assertEquals("Change transmission!!!", human.stepGas());
    }

    @Test
    public void checkRevolutionNumberOfGettingMoreThanMaxCountRevolutionsBySteppingGas() {
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= MAX_REVOLUTION_NUMBER) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        currentRevolutionNumber = car.getCurrentRevolutionNumber();
        human.stepGas();

        assertEquals(currentRevolutionNumber, car.getCurrentRevolutionNumber());
    }

    @Test
    public void getMoreThanMaxCountRevolutionsBySteppingGas() {
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= MAX_REVOLUTION_NUMBER) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Change transmission!!!", human.stepGas());
        assertEquals(currentRevolutionNumber, car.getCurrentRevolutionNumber());
    }

    @Test
    public void checkMessageOfDecreasingRevolutionNumberBySteppingBrake() {
        human.buyCar(car);
        human.startDriving();
        human.stepGas();

        assertEquals("Success", human.stepBrake());
    }

    @Test
    public void checkRevolutionNumberOfDecreasingRevolutionNumberBySteppingBrake() {
        final int currentRevolutionNumber;

        human.buyCar(car);
        human.startDriving();
        human.stepGas();
        currentRevolutionNumber = car.getCurrentRevolutionNumber();
        human.stepBrake();

        assertEquals(currentRevolutionNumber - CHANGED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void decreaseRevolutionNumberBySteppingBrake() {
        final int currentRevolutionNumber;

        human.buyCar(car);
        human.startDriving();
        human.stepGas();
        currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepBrake());
        assertEquals(currentRevolutionNumber - CHANGED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void checkMessageOfSteppingBrakeInStandingCar() {
        human.buyCar(car);
        human.startDriving();

        assertEquals("Do nothing", human.stepBrake());
    }

    @Test
    public void checkRevolutionNumberOfSteppingBrakeInStandingCar() {
        human.buyCar(car);
        human.startDriving();
        human.stepBrake();

        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepBrakeInStandingCar() {
        human.buyCar(car);
        human.startDriving();

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void changeTransmissionOnCarWhichIsNotBought() {
        assertEquals("You haven't car!!", human.changeTransmission(1));
    }

    @Test
    public void checkMessageOfChangingTransmissionOnNotStartCar() {
        human.buyCar(car);

        assertEquals("First of all, you should start the car", human.changeTransmission(1));
    }

    @Test
    public void checkTransmissionOfChangingTransmissionOnNotStartCar() {
        human.buyCar(car);
        human.changeTransmission(1);

        assertEquals(Transmission.NEUTRAL, car.getCurrentTransmission());

    }

    @Test
    public void changeTransmissionOnNotStartCar() {
        human.buyCar(car);

        assertEquals("First of all, you should start the car", human.changeTransmission(1));
        assertEquals(Transmission.NEUTRAL, car.getCurrentTransmission());

    }

    @ParameterizedTest
    @ValueSource(ints = {-6, 10})
    public void checkMessageOfChangingTransmissionOnInvalidValue(Integer transmissionValue) {
        human.buyCar(car);
        human.startDriving();

        assertEquals("There is no transmission with number=" + transmissionValue, human.changeTransmission(transmissionValue));
    }

    @ParameterizedTest
    @ValueSource(ints = {-6, 10})
    public void checkTransmissionOfChangingTransmissionOnInvalidValue(Integer transmissionValue) {
        final Transmission currentTransmission;

        human.buyCar(car);
        human.startDriving();
        currentTransmission = car.getCurrentTransmission();
        human.changeTransmission(transmissionValue);

        assertEquals(currentTransmission, car.getCurrentTransmission());

    }

    @ParameterizedTest
    @ValueSource(ints = {-6, 10})
    public void checkRevolutionNumberTransmissionOnInvalidValue(Integer transmissionValue) {
        final int revolutionNumber;

        human.buyCar(car);
        human.startDriving();
        revolutionNumber = car.getCurrentRevolutionNumber();
        human.changeTransmission(transmissionValue);

        assertEquals(revolutionNumber, car.getCurrentRevolutionNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {-6, 10})
    public void changeTransmissionOnInvalidValue(Integer transmissionValue) {
        final int revolutionNumber;
        final Transmission currentTransmission;

        human.buyCar(car);
        human.startDriving();
        currentTransmission = car.getCurrentTransmission();
        revolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("There is no transmission with number=" + transmissionValue, human.changeTransmission(transmissionValue));
        assertEquals(currentTransmission, car.getCurrentTransmission());
        assertEquals(revolutionNumber, car.getCurrentRevolutionNumber());

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void checkMessageOfChangingTransmissionOnValidValue(Integer transmissionValue) {
        human.buyCar(car);
        human.startDriving();

        assertEquals("Success", human.changeTransmission(transmissionValue));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void checkTransmissionOfChangingTransmissionOnValidValue(Integer transmissionValue) {
        final Transmission currentTransmission;

        human.buyCar(car);
        human.startDriving();
        currentTransmission = car.getCurrentTransmission();
        human.changeTransmission(transmissionValue);

        assertEquals(currentTransmission.getNumberTransmission() + transmissionValue, car.getCurrentTransmission().getNumberTransmission());
    }

    @ParameterizedTest
    @ValueSource(ints = {STOPPED_REVOLUTION_NUMBER, 1, 2, 3, 4, 5, 6})
    public void checkRevolutionNumberOfChangingTransmissionOnValidValue(Integer transmissionValue) {
        human.buyCar(car);
        human.startDriving();
        human.changeTransmission(transmissionValue);

        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {STOPPED_REVOLUTION_NUMBER, 1, 2, 3, 4, 5, 6})
    public void changeTransmissionOnValidValue(Integer transmissionValue) {
        final Transmission currentTransmission;

        human.buyCar(car);
        human.startDriving();
        currentTransmission = car.getCurrentTransmission();

        assertEquals("Success", human.changeTransmission(transmissionValue));
        assertEquals(currentTransmission.getNumberTransmission() + transmissionValue, car.getCurrentTransmission().getNumberTransmission());
        assertEquals(STARTED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }


    @Test
    public void decreaseCarConditionByChangingTransmissionWithLowRevolutionNumber() {
        final int carCondition;

        human.buyCar(car);
        human.startDriving();
        carCondition = car.getCondition();
        human.changeTransmission(1);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void decreaseCarConditionByChangingTransmissionWithHighRevolutionNumber() {
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();
        final int carCondition;

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= 3500) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        carCondition = car.getCondition();
        human.changeTransmission(1);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void decreaseCarConditionByChangingTransmissionOnRevolutionNumberMuchMoreCurrent(Integer transmissionNumber) {
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();
        final int carCondition;

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber < MIN_CHANGED_TRANSMISSION_REVOLUTION_NUMBER) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        carCondition = car.getCondition();
        human.changeTransmission(transmissionNumber);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void decreaseCarConditionByChangingTransmissionOnRearWithoutStopping() {
        final int carCondition = car.getCondition();
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber < MIN_CHANGED_TRANSMISSION_REVOLUTION_NUMBER) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        human.changeTransmission(6);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void emergencyStop() {
        int carCondition = car.getCondition();
        final int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 1) {
            human.changeTransmission(transmissionNumber);
            carCondition = car.getCondition();
        }

        assertEquals("Car is Broken. You cannot continue driving", human.changeTransmission(transmissionNumber));
        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCondition());
        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
        assertEquals(Transmission.NEUTRAL, car.getCurrentTransmission());
    }

    @Test
    public void checkMessageOfEmergencyStop() {
        int carCondition = car.getCondition();
        final int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 1) {
            human.changeTransmission(transmissionNumber);
            carCondition = car.getCondition();
        }

        assertEquals("Car is Broken. You cannot continue driving", human.changeTransmission(transmissionNumber));
    }

    @Test
    public void checkConditionOfEmergencyStop() {
        int carCondition = car.getCondition();
        final int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 1) {
            human.changeTransmission(transmissionNumber);
            carCondition = car.getCondition();
        }
        human.changeTransmission(transmissionNumber);

        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCondition());
    }

    @Test
    public void checkRevolutionNumberEmergencyStop() {
        int carCondition = car.getCondition();
        final int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 1) {
            human.changeTransmission(transmissionNumber);
            carCondition = car.getCondition();
        }
        human.changeTransmission(transmissionNumber);

        assertEquals(STOPPED_REVOLUTION_NUMBER, car.getCurrentRevolutionNumber());
    }

    @Test
    public void checkTransmissionOfEmergencyStop() {
        int carCondition = car.getCondition();
        final int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 1) {
            human.changeTransmission(transmissionNumber);
            carCondition = car.getCondition();
        }
        human.changeTransmission(transmissionNumber);

        assertEquals(Transmission.NEUTRAL, car.getCurrentTransmission());
    }
}
