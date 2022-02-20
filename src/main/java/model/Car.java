package model;

public class Car {
    private String name;
    private int condition = 10;
    private int currentRevolutionNumber = 0;
    private Transmission currentTransmission = Transmission.NEUTRAL;
    public Car(String name) {
        this.name = name;
    }
    public String changeTransmission(int transmissionNumber) {
        if (currentRevolutionNumber == 0)
            return "First of all, you should start the car";
        if (transmissionNumber < 0 || transmissionNumber > 6)
            return "There is no transmission with number=" + transmissionNumber;
        if (currentRevolutionNumber < 2000)
            condition--;
        if (currentRevolutionNumber > 3500)
            condition--;
        if (currentRevolutionNumber != 800 && transmissionNumber == 6)
            condition--;
        else if (Math.abs(currentTransmission.getNumberTransmission() - transmissionNumber) > 1) //Todo
            condition--;
        if (condition <= 0) { //Todo
            return emergencyStop();
        }
        currentTransmission = Transmission.values()[transmissionNumber];
        currentRevolutionNumber = 800;
        return "Success";
    }

    private String emergencyStop() {
        currentRevolutionNumber = 0;
        currentTransmission = Transmission.NEUTRAL;
        return "Car is Broken. You cannot continue driving";
    }

    public String startDriving() {
        if (condition <= 0)
            return "Car is Broken. You cannot start driving"; //Todo
        if (currentRevolutionNumber != 0)
            return "You have already start the car";
        this.currentRevolutionNumber = 800;
        return "Success";
    }
    public void stopDriving() {
        this.currentRevolutionNumber = 0;
        this.currentTransmission = Transmission.NEUTRAL;
    }

    public String stepGas() {
        if (currentRevolutionNumber == 0)
            return "Your car isn't start";
        if (currentRevolutionNumber > 5000)
            return "Change transmission!!!";
        currentRevolutionNumber += 500;
        return "Success";
    }
    public String stepBrake() {
        if (currentRevolutionNumber > 800) {
                currentRevolutionNumber -= 500;
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
