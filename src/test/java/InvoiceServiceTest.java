import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvoiceServiceTest {
    InvoiceService invoiceGenerator = null;
    @Mock
    InvoiceService invoiceService;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceService();
    }

    @Test
    public void givingDistance() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time, "normal");
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givingLessDistance_ShouldReturnFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time, "normal");
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givingMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides, "normal");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givingLessDistance_forPremiumRide_ShouldReturnFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time, "premium");
        Assert.assertEquals(20, fare, 0.0);
    }

    @Test
    public void givingUserIdRides_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides, "normal");
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
        Assert.assertEquals(invoiceSummary, summary);
    }

    @Test
    public void givingDistance_forNormalRideUsingMockito_ShouldReturnFare() {
        InvoiceEnum invoiceEnum = InvoiceEnum.NORMAL;
        InvoiceService invoiceService = mock(InvoiceService.class);
        when(invoiceService.calculateFare(2, 5, "normal")).thenReturn(invoiceEnum.calculateFare(2.0, 5));
        SampleInvoiceService sampleInvoiceService = new SampleInvoiceService();
        sampleInvoiceService.setInvoice(invoiceService);
        double fare = invoiceEnum.calculateFare(2.0, 5);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givingDistance_forPremiumRideUsingMockito_ShouldReturnFare() {
        InvoiceEnum invoiceEnum = InvoiceEnum.PREMIUM;
        InvoiceService invoiceService = mock(InvoiceService.class);
        when(invoiceService.calculateFare(2, 5, "normal")).thenReturn(invoiceEnum.calculateFare(2.0, 5));
        SampleInvoiceService sampleInvoiceService = new SampleInvoiceService();
        sampleInvoiceService.setInvoice(invoiceService);
        double fare = invoiceEnum.calculateFare(2.0, 5);
        Assert.assertEquals(40, fare, 0.0);
    }

}