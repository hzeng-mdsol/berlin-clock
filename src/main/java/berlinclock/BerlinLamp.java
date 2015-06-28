package berlinclock;

public enum BerlinLamp {
    YELLOW("Y"), OFF("O"), RED("R");

    private final String lamp;

    BerlinLamp(String lamp) {
        this.lamp = lamp;
    }

    public String getLamp() {
        return lamp;
    }
}
