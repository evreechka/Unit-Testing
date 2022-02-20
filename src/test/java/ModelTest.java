import model.Car;
import model.Human;
import model.Transmission;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelTest {
    @Test
    public void buyCar() {
        Human human = new Human("Mari");
        Car car1 = new Car("Dodge");
        Car car2 = new Car("Volvo");

        assertNull(human.getCar());

        human.buyCar(car1);

        assertEquals(car1, human.getCar());

        human.buyCar(car2);

        assertEquals(car2, human.getCar());
    }

    @Test
    public void startAndStopCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("Success", human.startDriving());
        assertEquals(800, car.getCurrentRevolutionNumber());

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

        assertEquals("You have already start the car", human.startDriving());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepGasInNotStartedCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("Your car isn't start", human.stepGas());
        assertEquals(0, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepBrakeInNotStartedCarOrStandingCar() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(0, car.getCurrentRevolutionNumber());

        human.startDriving();

        assertEquals("Do nothing", human.stepBrake());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepGasAndBrakeOnCarWhichIsNotBought() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.stepGas());
        assertEquals("You haven't car!!", human.stepBrake());
    }

    @Test
    public void changeRevolutionNumberBySteppingGas() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();
        int currentRevolutionCount = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepGas());
        assertEquals(currentRevolutionCount + 500, car.getCurrentRevolutionNumber());

        currentRevolutionCount = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepGas());
        assertEquals(currentRevolutionCount + 500, car.getCurrentRevolutionNumber());

    }

    @Test
    public void getMoreThanMaxCountRevolutions() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= 5000) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        int countRevolution = car.getCurrentRevolutionNumber();

        assertEquals("Change transmission!!!", human.stepGas());
        assertEquals(countRevolution, car.getCurrentRevolutionNumber());
    }

    @Test
    public void stepBrakeManyTimes() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();
        human.stepGas();
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("Success", human.stepBrake());
        assertEquals(currentRevolutionNumber - 500, car.getCurrentRevolutionNumber());

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

        human.buyCar(car);
        human.startDriving();
        Transmission currentTransmission = car.getCurrentTransmission();
        int revolutionNumber = car.getCurrentRevolutionNumber();

        assertEquals("There is no transmission with number=" + transmissionValue, human.changeTransmission(transmissionValue));
        assertEquals(currentTransmission, car.getCurrentTransmission());
        assertEquals(revolutionNumber, car.getCurrentRevolutionNumber());

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void changeTransmissionOnValidValue(Integer transmissionValue) {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();
        Transmission currentTransmission = car.getCurrentTransmission();


        assertEquals("Success", human.changeTransmission(transmissionValue));
        assertEquals(currentTransmission.getNumberTransmission() + transmissionValue, car.getCurrentTransmission().getNumberTransmission());
        assertEquals(800, car.getCurrentRevolutionNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, 10})
    public void workIncorrectHours(Integer hours) {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        assertEquals("You cannot work " + hours + " hours", human.work(hours));
        assertEquals(currentMood, human.getMood());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8})
    public void workCorrectHours(Integer hours) {
        Human human = new Human("Mari");
        int currentMood = human.getMood();
        int currentBalance = human.getBankAccount().getBalance();

        assertEquals("Success", human.work(hours));
        assertEquals("Mood: " + (currentMood - hours / 2), "Mood: " + human.getMood());
        assertEquals("Balance: " + (currentBalance + (hours * 300)), "Balance: " + human.getBankAccount().getBalance());
    }

    @Test
    public void workHardOrWithBadMood() {
        Human human = new Human("Mari");
        human.work(8);
        human.work(8);

        assertEquals("Success", human.work(8));
        assertEquals(0, human.getMood());

        assertEquals("Your mood is bad. Don't work please!", human.work(8));
        assertEquals(0, human.getMood());
    }

    @Test
    public void increaseMood() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        human.eat();

        assertEquals(currentMood + 3, human.getMood());

        currentMood = human.getMood();
        human.sleep();

        assertEquals(currentMood + 6, human.getMood());
    }

    @Test
    public void getSick() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        human.getSick();

        assertEquals(currentMood - 5, human.getMood());

        currentMood = human.getMood();
        human.getSick();

        assertEquals(currentMood - 5, human.getMood());

        human.getSick();

        assertEquals(0, human.getMood());
    }

    @Test
    public void startDrivingCarWithBadMood() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int mood = human.getMood();

        human.buyCar(car);
        while (mood > 0) {
            human.getSick();
            mood = human.getMood();
        }

        assertEquals("It is too dangerous to start driving. Your mood is bad", human.startDriving());
        assertEquals(0, car.getCurrentRevolutionNumber());
    }

    @Test
    public void fixCarWhichIsNotBought() {
        Human human = new Human("Mari");

        assertEquals("You haven't car!!", human.fixCar(500));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 400})
    public void fixCarWithIncorrectAmount(Integer amount) {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        assertEquals("You cannot fix something in car. Not enough money for repair", human.fixCar(amount));
    }

    @Test
    public void fixCarWithNotEnoughMoney() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);

        assertEquals("You haven't enough money on bank account", human.fixCar(1000));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    public void fixCarWithEnoughAmount(Integer amount) {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int balance = human.getBankAccount().getBalance();
        int carCondition = car.getCondition();

        human.buyCar(car);
        while (balance < amount) {
            human.work(8);
            human.sleep();
            balance = human.getBankAccount().getBalance();
        }
        assertEquals("Success", human.fixCar(amount));
        assertEquals(carCondition + amount / 500, car.getCondition());
    }

    @Test
    public void decreaseCarConditionByChangingTransmissionWithLowRevolutionNumber() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");

        human.buyCar(car);
        human.startDriving();
        int carCondition = car.getCondition();
        human.changeTransmission(1);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @Test
    public void decreaseCarConditionByChangingTransmissionWithHighRevolutionNumber() {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber <= 3500) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        int carCondition = car.getCondition();
        human.changeTransmission(1);

        assertEquals(carCondition - 1, car.getCondition());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void decreaseCarConditionByChangingTransmissionOnRevolutionNumberMuchMoreCurrent(Integer transmissionNumber) {
        Human human = new Human("Mari");
        Car car = new Car("Dodge");
        int currentRevolutionNumber = car.getCurrentRevolutionNumber();

        human.buyCar(car);
        human.startDriving();
        while (currentRevolutionNumber < 2000) {
            human.stepGas();
            currentRevolutionNumber = car.getCurrentRevolutionNumber();
        }
        int carCondition = car.getCondition();
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
}
