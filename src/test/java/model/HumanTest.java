package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanTest {
    private final int SALARY_OF_HOUR = 300;
    private final int MIN_MOOD = 0;
    private Human human;
    private Car car;

    @BeforeEach
    public void setUp() {
        human = new Human("Mari");
        car = new Car("Dodge");
    }

    @Test
    public void buyCar() {
        human.buyCar(car);

        assertEquals(car, human.getCar());
    }
    @ParameterizedTest
    @ValueSource(ints = {-2, 10})
    public void checkMessageOfWorkingIncorrectHours(Integer hours) {
        assertEquals("You cannot work " + hours + " hours", human.work(hours));
    }
    @ParameterizedTest
    @ValueSource(ints = {-2, 10})
    public void checkMoodOfWorkingIncorrectHours(Integer hours) {
        final int currentMood = human.getMood();
        human.work(hours);

        assertEquals(currentMood, human.getMood());
    }
    @ParameterizedTest
    @ValueSource(ints = {-2, 10})
    public void workIncorrectHours(Integer hours) {
        final int currentMood = human.getMood();

        assertEquals("You cannot work " + hours + " hours", human.work(hours));
        assertEquals(currentMood, human.getMood());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8})
    public void checkMessageOfWorkingCorrectHours(Integer hours) {
        assertEquals("Success", human.work(hours));
    }
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8})
    public void checkMoodOfWorkingCorrectHours(Integer hours) {
        final int currentMood = human.getMood();
        human.work(hours);

        assertEquals( (currentMood - hours / 2), human.getMood());
    }
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8})
    public void checkBalanceOfWorkingCorrectHours(Integer hours) {
        final int currentBalance = human.getBankAccount().getBalance();
        human.work(hours);

        assertEquals((currentBalance + (hours * SALARY_OF_HOUR)), human.getBankAccount().getBalance());
    }
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8})
    public void workCorrectHours(Integer hours) {
        final int currentMood = human.getMood();
        final int currentBalance = human.getBankAccount().getBalance();

        assertEquals("Success", human.work(hours));
        assertEquals("Mood: " + (currentMood - hours / 2), "Mood: " + human.getMood());
        assertEquals("Balance: " + (currentBalance + (hours * SALARY_OF_HOUR)), "Balance: " + human.getBankAccount().getBalance());
    }
    @Test
    public void checkMessageOfWorkingHardToZeroMood() {
        int currentMood = human.getMood();

        while (currentMood != 2) {
            human.work(8);
            currentMood = human.getMood();
        }

        assertEquals("Success", human.work(8));
    }
    @Test
    public void checkMoodOfWorkingToZeroMood() {
        int currentMood = human.getMood();

        while (currentMood != 2) {
            human.work(8);
            currentMood = human.getMood();
        }
        human.work(8);

        assertEquals(MIN_MOOD, human.getMood());
    }
    @Test
    public void workHardToZeroMood() {
        int currentMood = human.getMood();

        while (currentMood != 2) {
            human.work(8);
            currentMood = human.getMood();
        }

        assertEquals("Success", human.work(8));
        assertEquals(MIN_MOOD, human.getMood());
    }
    @Test
    public void checkMessageOfWorkingWithBadMood() {
        int currentMood = human.getMood();

        while (currentMood != MIN_MOOD) {
            human.work(8);
            currentMood = human.getMood();
        }

        assertEquals("Your mood is bad. Don't work please!", human.work(8));
    }
    @Test
    public void checkMoodOfWorkingWithBadMood() {
        int currentMood = human.getMood();

        while (currentMood != MIN_MOOD) {
            human.work(8);
            currentMood = human.getMood();
        }
        human.work(8);

        assertEquals(MIN_MOOD, human.getMood());
    }
    @Test
    public void workWithBadMood() {
        int currentMood = human.getMood();

        while (currentMood != MIN_MOOD) {
            human.work(8);
            currentMood = human.getMood();
        }

        assertEquals("Your mood is bad. Don't work please!", human.work(8));
        assertEquals(MIN_MOOD, human.getMood());
    }

    @Test
    public void increaseMoodByEating() {
        final int currentMood = human.getMood();
        final int EAT_MOOD = 3;

        human.eat();

        assertEquals(currentMood + EAT_MOOD, human.getMood());
    }

    @Test
    public void increaseMoodBySleeping() {
        final int currentMood = human.getMood();
        final int SLEEP_MOOD = 6;

        human.sleep();

        assertEquals(currentMood + SLEEP_MOOD, human.getMood());
    }

    @Test
    public void getSick() {
        final int currentMood = human.getMood();
        final int SICK_MOOD = 5;

        human.getSick();

        assertEquals(currentMood - SICK_MOOD, human.getMood());
    }

    @Test
    public void getHardSickToZeroMood() {
        int currentMood = human.getMood();

        while (currentMood != MIN_MOOD) {
            human.getSick();
            currentMood = human.getMood();
        }

        assertEquals(MIN_MOOD, human.getMood());
    }
    @Test
    public void checkMessageOfStartingDrivingCarWithBadMood() {
        int mood = human.getMood();

        human.buyCar(car);
        while (mood > MIN_MOOD) {
            human.getSick();
            mood = human.getMood();
        }

        assertEquals("It is too dangerous to start driving. Your mood is bad", human.startDriving());
    }
    @Test
    public void checkRevolutionNumberOfStartingDrivingCarWithBadMood() {
        int mood = human.getMood();

        human.buyCar(car);
        while (mood > MIN_MOOD) {
            human.getSick();
            mood = human.getMood();
        }
        human.startDriving();

        assertEquals(MIN_MOOD, car.getCurrentRevolutionNumber());
    }
    @Test
    public void startDrivingCarWithBadMood() {
        int mood = human.getMood();

        human.buyCar(car);
        while (mood > MIN_MOOD) {
            human.getSick();
            mood = human.getMood();
        }

        assertEquals("It is too dangerous to start driving. Your mood is bad", human.startDriving());
        assertEquals(MIN_MOOD, car.getCurrentRevolutionNumber());
    }

    @Test
    public void fixCarWhichIsNotBought() {
        assertEquals("You haven't car!!", human.fixCar(500));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 400})
    public void fixCarWithIncorrectAmount(Integer amount) {
        human.buyCar(car);
        assertEquals("You cannot fix something in car. Not enough money for repair", human.fixCar(amount));
    }

    @Test
    public void fixCarWithNotEnoughMoney() {
        human.buyCar(car);

        assertEquals("You haven't enough money on bank account", human.fixCar(1000));
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    public void checkMessageOfFixingCarWithEnoughAmount(Integer amount) {
        int balance = human.getBankAccount().getBalance();

        human.buyCar(car);
        while (balance < amount) {
            human.work(8);
            human.sleep();
            balance = human.getBankAccount().getBalance();
        }
        assertEquals("Success", human.fixCar(amount));
    }
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    public void checkConditionOfFixingCarWithEnoughAmount(Integer amount) {
        int balance = human.getBankAccount().getBalance();
        final int carCondition = car.getCondition();

        human.buyCar(car);
        while (balance < amount) {
            human.work(8);
            human.sleep();
            balance = human.getBankAccount().getBalance();
        }
        human.fixCar(amount);

        assertEquals(carCondition + amount / 500, car.getCondition());
    }
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    public void fixCarWithEnoughAmount(Integer amount) {
        int balance = human.getBankAccount().getBalance();
        final int carCondition = car.getCondition();

        human.buyCar(car);
        while (balance < amount) {
            human.work(8);
            human.sleep();
            balance = human.getBankAccount().getBalance();
        }
        assertEquals("Success", human.fixCar(amount));
        assertEquals(carCondition + amount / 500, car.getCondition());
    }
}
