package model;

public enum Transmission {
    NEUTRAL(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FORTH(4),
    FIFTH(5),
    REAR(6);
    private final int numberTransmission;
    Transmission(int numberTransmission) {
        this.numberTransmission = numberTransmission;
    }

    public int getNumberTransmission() {
        return numberTransmission;
    }
}
