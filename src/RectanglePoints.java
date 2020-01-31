public class RectanglePoints {
    double x1;
    double y1;
    double x2;
    double y2;

    public RectanglePoints() {
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
    }

    public RectanglePoints(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public RectanglePoints(long x1, long y1, long x2, long y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public RectanglePoints(String x1, String y1, String x2, String y2) {
        this.x1 = Integer.parseInt(x1);
        this.y1 = Integer.parseInt(y1);
        this.x2 = Integer.parseInt(x2);
        this.y2 = Integer.parseInt(y2);
    }

    public double getArea() {
        return Math.abs(x2 - x1) * Math.abs(y2 - y1);
    }

    public boolean isSquare() {
        return Math.abs(x2 - x1) == Math.abs(y2 - y1);
    }

    @Override
    public String toString() {
        return "RectanglePoints{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}
