import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
InvoiceGenerator invoiceGenerator = null;
    @Before
    public void setUp(){
        invoiceGenerator = new InvoiceGenerator();
    }
    @Test
    public void givingDistance(){
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance ,time,"normal");
        Assert.assertEquals(25,fare,0.0);
   }
    @Test
    public void givingLessDistance_ShouldReturnFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance ,time,"normal");
        Assert.assertEquals(5,fare,0.0);
    }
    @Test
    public void givingMultipleRides_ShouldReturnInvoiceSummary(){
        Ride[] rides = {new Ride(2.0,5),
                        new Ride(0.1,1)};
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides,"normal");
       InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
    @Test
    public void givingLessDistance_forPremiumRide_ShouldReturnFare(){
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance ,time,"premium");
        Assert.assertEquals(20,fare,0.0);
    }
}

