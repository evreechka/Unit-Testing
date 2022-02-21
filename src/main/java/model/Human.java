package model;

public class Human {
    private final static int EAT_MOOD = 3;
    private final static int SLEEP_MOOD = 6;
    private final static int SICK_MOOD = 5;
    private final static int SALARY_OF_HOUR = 300;
    private final static int MIN_MOOD = 0;
    private String name;
    private Car car;
    private int mood = 10;
    private final BankAccount bankAccount = new BankAccount();
    public Human(String name) {
        this.name = name;
    }
    public void buyCar(Car car) {
        this.car = car;
    }
    public void getSick() {
        if (mood > SICK_MOOD)
            mood -= SICK_MOOD;
        else
            mood = MIN_MOOD;
    }
    public void eat() {
        mood += EAT_MOOD;
    }
    public void sleep() {
        mood += SLEEP_MOOD;
    }
    public String work(int hours) {
        if (mood <= MIN_MOOD)
            return "Your mood is bad. Don't work please!";
        if (hours < 0 || hours > 8)
            return "You cannot work " + hours + " hours";
        if (mood < hours / 2)
            mood = MIN_MOOD;
        else
            mood -= hours / 2;
        bankAccount.addMoney(SALARY_OF_HOUR * hours);
        return "Success";
    }
    public String fixCar(int amount) {
        if (car == null)
            return "You haven't car!!";
        int MIN_REPAIR_AMOUNT = 500;
        if (amount < MIN_REPAIR_AMOUNT)
            return "You cannot fix something in car. Not enough money for repair";
        if (bankAccount.withdrawMoney(amount)) {
            car.fix(amount / MIN_REPAIR_AMOUNT);
            return "Success";
        }
        return "You haven't enough money on bank account";
    }
    public String startDriving() {
        if (car == null)
            return "You haven't car!!";
        if (mood == MIN_MOOD)
            return "It is too dangerous to start driving. Your mood is bad";
        return car.startDriving();
    }
    public String stopDriving() {
        if (car == null)
            return "You haven't car!!";
        car.stopDriving();
        return "Success";
    }
    public String stepGas() {
        if (car == null)
            return "You haven't car!!";
        return car.stepGas();
    }
    public String stepBrake() {
        if (car == null)
            return "You haven't car!!";
        return car.stepBrake();
    }
    public String changeTransmission(int transmission) {
        if (car == null)
            return "You haven't car!!";
        return car.changeTransmission(transmission);
    }

    public Car getCar() {
        return car;
    }

    public int getMood() {
        return mood;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
