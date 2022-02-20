import model.Car;
import model.Human;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HumanTest {
    @Test
    public void buyCar() {
        Human human = new Human("Mari");
        Car car1 = new Car("Dodge");
        Car currentCar = human.getCar();

        human.buyCar(car1);

        assertNull(currentCar);
        assertEquals(car1, human.getCar());
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
    public void workHardToZeroMood() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        while (currentMood != 2) {
            human.work(8);
            currentMood = human.getMood();
        }

        assertEquals("Success", human.work(8));
        assertEquals(0, human.getMood());
    }
    @Test
    public void workWithBadMood() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        while (currentMood != 0) {
            human.work(8);
            currentMood = human.getMood();
        }

        assertEquals("Your mood is bad. Don't work please!", human.work(8));
        assertEquals(0, human.getMood());
    }

    @Test
    public void increaseMoodByEating() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        human.eat();

        assertEquals(currentMood + 3, human.getMood());
    }

    @Test
    public void increaseMoodBySleeping() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        human.sleep();

        assertEquals(currentMood + 6, human.getMood());
    }

    @Test
    public void getSick() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        human.getSick();

        assertEquals(currentMood - 5, human.getMood());
    }
    @Test
    public void getHardSickToZeroMood() {
        Human human = new Human("Mari");
        int currentMood = human.getMood();

        while (currentMood != 0) {
            human.getSick();
            currentMood = human.getMood();
        }

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
}
