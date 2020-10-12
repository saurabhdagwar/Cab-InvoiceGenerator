public class InvoiceSummary {

    private final int numberRides;
    private double totalFare;
    private double averageFare;

    public InvoiceSummary(int numberRides, double totalFare) {
    this.numberRides = numberRides;
    this.totalFare = totalFare;
    this.averageFare = this.totalFare/this.numberRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numberRides == that.numberRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }
}
