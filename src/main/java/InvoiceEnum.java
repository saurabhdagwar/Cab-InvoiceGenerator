public enum InvoiceEnum {
NORMAL(10.0,1.0,5.0), PREMIUM(15.0,2.0,20.0);

    private final double costPerKm;
    private final double costPerMinutes;
    private final double minimumFare;

    InvoiceEnum(double costPerKm,double costPerMinutes,double minimumFare){
        this.costPerKm = costPerKm;
        this.costPerMinutes = costPerMinutes;
        this.minimumFare =minimumFare;
    }
    public double calculateFare(double distance, int time){
        double totalFare = distance * costPerKm + time * costPerMinutes;
        return Math.max(totalFare,minimumFare);
    }
}
