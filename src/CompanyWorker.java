import java.time.Duration;
import java.time.LocalDate;

public class CompanyWorker {
    String surname;
    double salary;
    int yearOfEmployment;

    public CompanyWorker() {
        this.surname = "";
        this.salary = 0.0;
        this.yearOfEmployment = 0;
    }

    public CompanyWorker(String surname, double salary, int yearOfEmployment) {
        this.surname = surname;
        this.salary = salary;
        this.yearOfEmployment = yearOfEmployment;
    }

    public CompanyWorker(String surname, String salary, String yearOfEmployment) {
        this.surname = surname;
        this.salary = Double.parseDouble(salary);
        this.yearOfEmployment = Integer.parseInt(yearOfEmployment);
    }

    public CompanyWorker(String surname, long salary, long yearOfEmployment) {
        this.surname = surname;
        this.salary = salary;
        this.yearOfEmployment = (int) yearOfEmployment;
    }

    public long getWorkExperienceInYears() {
        return Math.abs(LocalDate.now().getYear() - yearOfEmployment);
    }

    public long getWorkExperienceInDays() {
        return Math.abs(Duration.between(LocalDate.now(),
                LocalDate.ofYearDay(yearOfEmployment, 0)).toDays());
    }

    @Override
    public String toString() {
        return "CompanyWorker{" +
                "surname='" + surname + '\'' +
                ", salary=" + salary +
                ", yearOfEmployment=" + yearOfEmployment +
                '}';
    }
}
