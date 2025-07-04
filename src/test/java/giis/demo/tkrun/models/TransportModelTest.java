package giis.demo.tkrun.models;

// Imports de JUnit 4
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// Imports de Mockito
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;

// Imports de Java y del proyecto
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import giis.demo.tkrun.dtos.DTOPackage;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

/**
 * Unit tests for the TransportModel class, compatible with JUnit 4.
 * These tests verify the business logic in isolation by mocking the database dependency.
 */
public class TransportModelTest {

    @Mock
    private Database db; // Mocking the Database class

    @InjectMocks
    private TransportModel model; // The instance of the class we are testing

    @Before
    public void setUp() {
        // Initializes mocks before each test method.
        // For JUnit 4, we use MockitoAnnotations.initMocks(this)
        MockitoAnnotations.initMocks(this);
    }

    //region Business Process 1: Delivery Date Calculation
    //------------------------------------------------------------------------------------

    /**
     * Test for: Delivery Date Calculation
     * Equivalence Class: Distance < 200 km
     */
    @Test
    public void testCalculateDeliveryDate_ShortDistance() throws Exception {
        Method method = TransportModel.class.getDeclaredMethod("calculateDeliveryDate", double.class);
        method.setAccessible(true);
        
        double distance = 150.0;
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        
        LocalDate actualDate = (LocalDate) method.invoke(model, distance);
        
        assertEquals("For distance < 200km, delivery should be next day.", expectedDate, actualDate);
    }

    /**
     * Test for: Delivery Date Calculation
     * Equivalence Class: Distance between 200.1 and 400 km
     */
    @Test
    public void testCalculateDeliveryDate_MediumDistance() throws Exception {
        Method method = TransportModel.class.getDeclaredMethod("calculateDeliveryDate", double.class);
        method.setAccessible(true);
        
        double distance = 350.0;
        LocalDate expectedDate = LocalDate.now().plusDays(2);
        
        LocalDate actualDate = (LocalDate) method.invoke(model, distance);
        
        assertEquals("For distance > 200km and <= 400km, delivery should be in 2 days.", expectedDate, actualDate);
    }
    
    /**
     * Test for: Delivery Date Calculation
     * Equivalence Class: Distance = 200 km (Boundary Case)
     */
    @Test
    public void testCalculateDeliveryDate_BoundaryDistance() throws Exception {
        Method method = TransportModel.class.getDeclaredMethod("calculateDeliveryDate", double.class);
        method.setAccessible(true);
        
        double distance = 200.0;
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        
        LocalDate actualDate = (LocalDate) method.invoke(model, distance);
        
        assertEquals("For distance = 200km, delivery should be next day.", expectedDate, actualDate);
    }

    //endregion

    //region Business Process 2: Eligible Package Filtering for a Route
    //------------------------------------------------------------------------------------
    
    /**
     * Test for: Eligible Package Filtering
     * Equivalence Class: No packages at origin
     */
    @Test
    public void testGetPackagesTransport_NoPackagesAtOrigin() {
        String origin = "Gijón";
        String destination = "Madrid";
        
        // Configure the mock to return an empty list when the query is executed.
        Mockito.when(db.executeQueryPojo(eq(DTOPackage.class), anyString(), eq(origin), eq(destination), eq(destination)))
            .thenReturn(Collections.<DTOPackage>emptyList());
        
        List<DTOPackage> packages = model.getPackagesTransport(origin, destination);
        
        assertNotNull("The list should not be null.", packages);
        assertTrue("The list of packages should be empty.", packages.isEmpty());
    }

    /**
     * Test for: Eligible Package Filtering
     * Equivalence Class: Package destination matches truck destination
     */
    @Test
    public void testGetPackagesTransport_DestinationMatches() {
        String origin = "Gijón";
        String destination = "Madrid";
        
        DTOPackage eligiblePackage = new DTOPackage();
        eligiblePackage.setPackageId("PKG_MADRID");
        
        // Configure the mock to return a list with our eligible package.
        Mockito.when(db.executeQueryPojo(eq(DTOPackage.class), anyString(), eq(origin), eq(destination), eq(destination)))
            .thenReturn(Arrays.asList(eligiblePackage));
            
        List<DTOPackage> packages = model.getPackagesTransport(origin, destination);
        
        assertFalse("The list should not be empty.", packages.isEmpty());
        assertEquals("The list should contain one package.", 1, packages.size());
        assertEquals("The correct package should be in the list.", "PKG_MADRID", packages.get(0).getPackageId());
    }

    //endregion
    
    //region Business Process 3: New Shipment Creation Process
    //------------------------------------------------------------------------------------
    
    /**
     * Test for: New Shipment Creation (Happy Path)
     * Equivalence Class: All parameters are valid
     */
    @Test
    public void testMarkPackageAsShipped_HappyPath() {
        String packageId = "PKG101";
        int routeId = 1;
        String vehicleIdStr = "10";

        // Setup Mocks:
        Mockito.when(db.executeQuerySingle(eq(String.class), contains("SELECT status FROM Packages"), eq(packageId)))
            .thenReturn("REGISTERED");
        
        Mockito.when(db.executeQuerySingle(eq(Integer.class), contains("SELECT distance FROM Routes"), eq(routeId)))
            .thenReturn(350);

        // Execute
        model.markPackageAsShipped(packageId, routeId, vehicleIdStr);

        // Verify database interactions
        Mockito.verify(db).executeUpdate(eq("UPDATE Packages SET status = 'SHIPPED' WHERE package_id = ?"), eq(packageId));
        Mockito.verify(db).executeUpdate(eq("INSERT INTO Shipments (package_id, route_id, vehicle_id, pickup_date, delivery_date) VALUES (?, ?, ?, ?, ?)"), 
                                 eq(packageId), eq(routeId), eq(Integer.parseInt(vehicleIdStr)), any(java.sql.Date.class), any(java.sql.Date.class));
    }

    /**
     * Test for: New Shipment Creation (Invalid Package)
     * Equivalence Class: Package status is not "REGISTERED"
     * This test expects an ApplicationException to be thrown.
     */
    @Test(expected = ApplicationException.class)
    public void testMarkPackageAsShipped_PackageAlreadyShipped() {
        String packageId = "PKG102";
        int routeId = 1;
        String vehicleIdStr = "10";
        
        // Setup Mock: Simulate that the package has the "SHIPPED" status.
        Mockito.when(db.executeQuerySingle(eq(String.class), contains("SELECT status FROM Packages"), eq(packageId)))
            .thenReturn("SHIPPED");
        
        // Execute the method. JUnit 4 will catch the expected exception.
        model.markPackageAsShipped(packageId, routeId, vehicleIdStr);
        
        // If no exception is thrown, the test will fail automatically.
    }
    
    /**
     * Test for: New Shipment Creation (Invalid Route)
     * Equivalence Class: Route ID does not exist
     * This test expects an ApplicationException.
     */
    @Test
    public void testMarkPackageAsShipped_InvalidRoute() {
        String packageId = "PKG101";
        int invalidRouteId = 999;
        String vehicleIdStr = "10";
        
        // Setup Mocks:
        Mockito.when(db.executeQuerySingle(eq(String.class), contains("SELECT status FROM Packages"), eq(packageId)))
            .thenReturn("REGISTERED");
        
        Mockito.when(db.executeQuerySingle(eq(Integer.class), contains("SELECT distance FROM Routes"), eq(invalidRouteId)))
            .thenReturn(null); // Simulate route not found
            
        // Execute and assert that the correct exception is thrown.
        try {
            model.markPackageAsShipped(packageId, invalidRouteId, vehicleIdStr);
            fail("Expected ApplicationException was not thrown"); // This line fails the test if no exception occurs
        } catch (ApplicationException e) {
            assertEquals("Invalid route", e.getMessage()); // Verify the exception message is correct
        }
    }

    //endregion
}