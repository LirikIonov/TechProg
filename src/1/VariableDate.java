public class VariableDate {
    private int day;
    private int month;
    private int year;

    public VariableDate() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public VariableDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public VariableDate(long day, long month, long year) {
        this.day = (int) day;
        this.month = (int) month;
        this.year = (int) year;
    }

    public VariableDate(String day, String month, String year) {
        this.day = Integer.parseInt(day);
        this.month = Integer.parseInt(month);
        this.year = Integer.parseInt(year);
    }

    public void increaseYearsByOne() {
        year++;
    }

    public void decreaseDaysByTwo() {
        day -= 2;
    }

    @Override
    public String toString() {
        return "VariableDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
