import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;

public class ReadBook {
    String title;
    int pagesCount;
    double price;
    int publishingYear;

    public ReadBook() {
        this.title = "";
        this.pagesCount = 0;
        this.price = 0.0;
        this.publishingYear = 0;
    }

    public ReadBook(String title, int pagesCount, double price, int publishingYear) {
        this.title = title;
        this.pagesCount = pagesCount;
        this.price = price;
        this.publishingYear = publishingYear;
    }

    public ReadBook(String title, String pagesCount, String price, String publishingYear) {
        this.title = title;
        this.pagesCount = Integer.parseInt(pagesCount);
        this.price = Double.parseDouble(price);
        this.publishingYear = Integer.parseInt(publishingYear);
    }

    public ReadBook(String title, long pagesCount, long price, long publishingYear) {
        this.title = title;
        this.pagesCount = (int) pagesCount;
        this.price = price;
        this.publishingYear = (int) publishingYear;
    }

    public double getAverageCostOfOnePage() {
        return price / pagesCount * 1.0;
    }

    public long daysPassedAfterBooksRelease() {
        return Math.abs(Duration.between(LocalDate.now(),
                LocalDate.ofYearDay(publishingYear, 0)).toDays());
    }

    @Override
    public String toString() {
        return "ReadBook{" +
                "title='" + title + '\'' +
                ", pagesCount=" + pagesCount +
                ", price=" + price +
                ", publishingYear=" + publishingYear +
                '}';
    }
}
