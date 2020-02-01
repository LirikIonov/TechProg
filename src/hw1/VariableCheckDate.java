package hw1;

public class VariableCheckDate {
    private int day;
    private int month;
    private int year;

    public VariableCheckDate() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public VariableCheckDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public VariableCheckDate(long day, long month, long year) {
        this.day = (int) day;
        this.month = (int) month;
        this.year = (int) year;
    }

    public VariableCheckDate(String day, String month, String year) {
        this.day = Integer.parseInt(day);
        this.month = Integer.parseInt(month);
        this.year = Integer.parseInt(year);
    }

    public boolean isDayAndMonthAreEqual() {
        return day == month;
    }

    public void increaseMonthsByOne() {
        month++;
    }

    @Override
    public String toString() {
        return "VariableCheckDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
