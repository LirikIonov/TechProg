public class VariableTime {
    private int hours;
    private int minutes;
    private int seconds;

    public VariableTime() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public VariableTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public VariableTime(long hours, long minutes, long seconds) {
        this.hours = (int) hours;
        this.minutes = (int) minutes;
        this.seconds = (int) seconds;
    }

    public VariableTime(String hours, String minutes, String seconds) {
        this.hours = Integer.parseInt(hours);
        this.minutes = Integer.parseInt(minutes);
        this.seconds = Integer.parseInt(seconds);
    }

    public long getSecondCount() {
        return 3600 * hours + 60 * minutes + seconds;
    }

    public void increaseSecondsByFive() {
        seconds += 5;
    }

    @Override
    public String toString() {
        return "VariableTime{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
