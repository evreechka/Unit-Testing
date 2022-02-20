package model;

public class Human {
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
        if (mood > 5)
            mood -= 5;
        else
            mood = 0;
    }
    public void eat() {
        mood += 3;
    }
    public void sleep() {
        mood += 6;
    }
    public String work(int hours) {
        if (mood <= 0)
            return "Your mood is bad. Don't work please!";
        if (hours < 0 || hours > 8)
            return "You cannot work " + hours + " hours";
        if (mood < hours / 2)
            mood = 0;
        else
            mood -= hours / 2;
        bankAccount.addMoney(300 * hours);
        return "Success";
    }
    public String fixCar(int amount) {
        if (car == null)
            return "You haven't car!!";
        if (amount < 500)
            return "You cannot fix something in car. Not enough money for repair";
        if (bankAccount.withdrawMoney(amount)) {
            car.fix(amount / 500);
            return "Success";
        }
        return "You haven't enough money on bank account";
    }
    public String startDriving() {
        if (car == null)
            return "You haven't car!!";
        if (mood == 0)
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
