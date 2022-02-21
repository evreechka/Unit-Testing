package model;

public class Car {
    private final static int CHANGED_REVOLUTION_NUMBER = 500;
    private final static int STARTED_REVOLUTION_NUMBER = 800;
    private final static int STOPPED_REVOLUTION_NUMBER = 0;
    private String name;
    private int condition = 10;
    private int currentRevolutionNumber = 0;
    private Transmission currentTransmission = Transmission.NEUTRAL;

    public Car(String name) {
        this.name = name;
    }

    public String changeTransmission(int transmissionNumber) {
        final int MIN_CHANGED_TRANSMISSION_REVOLUTION_NUMBER = 2000;
        final int MAX_CORRECT_CHANGED_TRANSMISSION_REVOLUTION_NUMBER = 3500;
        if (currentRevolutionNumber == 0)
            return "First of all, you should start the car";
        if (transmissionNumber < 0 || transmissionNumber > 6)
            return "There is no transmission with number=" + transmissionNumber;
        if (currentRevolutionNumber < MIN_CHANGED_TRANSMISSION_REVOLUTION_NUMBER)
            condition--;
        if (currentRevolutionNumber > MAX_CORRECT_CHANGED_TRANSMISSION_REVOLUTION_NUMBER)
            condition--;
        if (currentRevolutionNumber != STARTED_REVOLUTION_NUMBER && transmissionNumber == 6)
            condition--;
        else if (Math.abs(currentTransmission.getNumberTransmission() - transmissionNumber) > 1) //Todo
            condition--;
        if (condition <= 0) { //Todo
            return emergencyStop();
        }
        currentTransmission = Transmission.values()[transmissionNumber];
        currentRevolutionNumber = STARTED_REVOLUTION_NUMBER;
        return "Success";
    }

    private String emergencyStop() {
        currentRevolutionNumber = STOPPED_REVOLUTION_NUMBER;
        currentTransmission = Transmission.NEUTRAL;
        return "Car is Broken. You cannot continue driving";
    }

    public String startDriving() {
        if (condition <= 0)
            return "Car is Broken. You cannot start driving"; //Todo
        if (currentRevolutionNumber != STOPPED_REVOLUTION_NUMBER)
            return "You have already start the car";
        this.currentRevolutionNumber = STARTED_REVOLUTION_NUMBER;
        return "Success";
    }

    public void stopDriving() {
        this.currentRevolutionNumber = STOPPED_REVOLUTION_NUMBER;
        this.currentTransmission = Transmission.NEUTRAL;
    }

    public String stepGas() {
        final int MAX_REVOLUTION_NUMBER = 5000;
        if (currentRevolutionNumber == STOPPED_REVOLUTION_NUMBER)
            return "Your car isn't start";
        if (currentRevolutionNumber > MAX_REVOLUTION_NUMBER)
            return "Change transmission!!!";
        currentRevolutionNumber += CHANGED_REVOLUTION_NUMBER;
        return "Success";
    }

    public String stepBrake() {
        if (currentRevolutionNumber > STARTED_REVOLUTION_NUMBER) {
            currentRevolutionNumber -= CHANGED_REVOLUTION_NUMBER;
            return "Success";
        }
        return "Do nothing";
    }

    public void fix(int improvements) {
        condition += improvements;
    }

    public int getCondition() {
        return condition;
    }

    public int getCurrentRevolutionNumber() {
        return currentRevolutionNumber;
    }

    public Transmission getCurrentTransmission() {
        return currentTransmission;
    }
}
