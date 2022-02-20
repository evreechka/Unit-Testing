import model.Car;
import model.Human;
import model.Transmission;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    @Test
    public void startCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("Success", human.startDriving());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stopCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();

        assertEquals("Success", human.stopDriving());
        assertEquals(0, car.getCurrentRevolutionNumber());
    }

    @Test
    public void startCarWhichNotBoughtYet() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.startDriving());
    }

    @Test
    public void stopCarWhichIsNotBought() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.stopDriving());
    }

    @Test
    public void startCarMoreThanOneTime() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();

        assertEquals("You have already start the car", human.startDriving());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @Test
    public void startDrivingOnBrokenCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int carCondition = car.getCondition();
        int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 0) {
            human.changeTransmission(transmissionNumber);
            transmissionNumber = 2 - transmissionNumber;
            carCondition = car.getCondition();
        }
        assertEquals("Car is Broken. You cannot start driving", human.startDriving());
    }

    //step gas and brake

    @Test
    public void stepGasInNotStartedCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("Your car isn't start", human.stepGas());
        assertEquals(0, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepBrakeInNotStartedCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(0, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepGasOnCarWhichIsNotBought() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.stepGas());
    }

    @Test
    public void stepBrakeOnCarWhichIsNotBought() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.stepBrake());
    }

    @Test
    public void changeRevolutionNumberBySteppingGas() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber;

        human.buyCar(car);
        human.startDriving();
        currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepGas());
        assertEquals(currentRevolutionNumber + 500, car.getCurrentRevolutionNumber());
    }

    @Test
    public void getMoreThanMaxCountRevolutionsBySteppingGas() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= 5000) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Change transmission!!!", human.stepGas());
        assertEquals(currentRevolutionNumber, car.getCurrentRevolutionNumber());
    }

    @Test
    public void decreaseRevolutionNumberBySteppingBrake() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber;

        human.buyCar(car);
        human.startDriving();
        human.stepGas();
        currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepBrake());
        assertEquals(currentRevolutionNumber - 500, car.getCurrentRevolutionNumber());

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepBrakeInStandingCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @Test
    public void changeTransmissionOnCarWhichIsNotBought() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.changeTransmission(1));
    }

    @Test
    public void changeTransmissionOnNotStartCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("First of all, you should start the car", human.changeTransmission(1));
        assertEquals(Transmission.NEUTRAL, car.getCurrentTransmission());

    }

    @ParameterizedTest
    @ValueSource(ints = {-6, 10})
    public void changeTransmissionOnInvalidValue(Integer transmissionValue) {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int revolutionNumber;
        Transmission currentTransmission;

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
    public void changeTransmissionOnValidValue(Integer transmissionValue) {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        Transmission currentTransmission;

        human.buyCar(car);
        human.startDriving();
        currentTransmission = car.getCurrentTransmission();

        assertEquals("Success", human.changeTransmission(transmissionValue));
        assertEquals(currentTransmission.getNumberTransmission() + transmissionValue, car.getCurrentTransmission().getNumberTransmission());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }



    @Test
    public void decreaseCarConditionByChangingTransmissionWithLowRevolutionNumber() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int carCondition;

        human.buyCar(car);
        human.startDriving();
        carCondition = car.getCondition();
        human.changeTransmission(1);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void decreaseCarConditionByChangingTransmissionWithHighRevolutionNumber() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();
        int carCondition;

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
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();
        int carCondition;

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber < 2000) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        carCondition = car.getCondition();
        human.changeTransmission(transmissionNumber);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void decreaseCarConditionByChangingTransmissionOnRearWithoutStopping() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int carCondition = car.getCondition();
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();
        int nextTransmissionNumber = car.getCurrentTransmission().getNumberTransmission() + 1;

        human.buyCar(car);
        human.startDriving();
        while (nextTransmissionNumber != 7) {
            while (currentRevolutionNumber < 2000) {
                human.stepGas();
                currentRevolutionNumber = car.getCurrentRevolutionNumber();
            }
            carCondition = car.getCondition();
            human.changeTransmission(nextTransmissionNumber);
            nextTransmissionNumber = car.getCurrentTransmission().getNumberTransmission() + 1;
        }

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void emergencyStop() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int carCondition = car.getCondition();
        int transmissionNumber = 2;

        human.buyCar(car);
        human.startDriving();
        while (carCondition != 1) {
            human.changeTransmission(transmissionNumber);
            carCondition = car.getCondition();
        }

        assertEquals("Car is Broken. You cannot continue driving", human.changeTransmission(transmissionNumber));
        assertEquals(0, car.getCondition());
        assertEquals(0, car.getCurrentRevolutionNumber());
        assertEquals(Transmission.NEUTRAL, car.getCurrentTransmission());
    }
}
