public class InvoiceService {

    private static  int cost_per_time ;
    private static   double minimum_cost_per_km ;
    private static double minimum_fare ;
    private  RideRepository rideRepository;

    public InvoiceService(){
        this.rideRepository = new RideRepository();
    }
    public double calculateFare(double distance, int time, String type) {
      if(type == "premium"){
           cost_per_time = 2;
           minimum_cost_per_km = 15;
           minimum_fare = 20;
          double totalFare = distance * minimum_cost_per_km + time * cost_per_time;
          return Math.max(totalFare,minimum_fare);
      }
        cost_per_time = 1;
        minimum_cost_per_km = 10;
        minimum_fare = 5;
        double totalFare = distance * minimum_cost_per_km + time * cost_per_time;
            return Math.max(totalFare,minimum_fare);
    }

    public InvoiceSummary calculateFare(Ride[] rides,String type) {
        double totalFare = 0;
        if(type == "premium"){
        for (Ride ride :rides) {
           totalFare += this.calculateFare(ride.distance,ride.time,"premium");
        }
        return new InvoiceSummary(rides.length,totalFare);
        }
        for (Ride ride :rides) {
            totalFare += this.calculateFare(ride.distance,ride.time,"normal");
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
   return this.calculateFare(rideRepository.getRides(userId),"normal");
    }


}
