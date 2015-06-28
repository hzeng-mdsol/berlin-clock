package berlinclock;

public enum Lamp {
    YELLOW("Y"), OFF("O"), RED("R");

    private final String lamp;

    Lamp(String lamp) {
        this.lamp = lamp;
    }

    public String getLamp() {
        return lamp;
    }
}
