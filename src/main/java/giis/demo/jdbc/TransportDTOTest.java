/*package giis.demo.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import giis.demo.util.UnexpectedException;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import giis.demo.tkrun.DTOs.TransportDTO;
import giis.demo.tkrun.model.TransportModel;
import giis.demo.util.ApplicationException;

public class TransportDTOTest {

    //informacion de conexion a la base de datos utilizada
    public static final String DRIVER="org.sqlite.JDBC";
    public static final String URL="jdbc:sqlite:DemoDB.db";
    private TransportDTO transportDTO;

    @Before
    public void setUp() {
        // Initialize the DTO and set up a test database before each test.
        transportDTO = new TransportDTO();
        createTestDatabase();
    }

    @After
    public void tearDown() {
        // Clean up the test database after each test to avoid conflicts.
        dropTestDatabase();
    }

    /**
     * Sets up a test database with necessary tables and initial data.
     *
    private void createTestDatabase() {
        try (Connection cn = DriverManager.getConnection(URL);
             Statement stmt = cn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Packages (package_id VARCHAR(10) PRIMARY KEY, status VARCHAR(20))");
            stmt.executeUpdate("INSERT INTO Packages (package_id, status) VALUES ('PKG001', 'REGISTERED')");
            stmt.executeUpdate("INSERT INTO Packages (package_id, status) VALUES ('PKG002', 'SHIPPED')");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Routes (route_id INT PRIMARY KEY, distance INT)");
            stmt.executeUpdate("INSERT INTO Routes (route_id, distance) VALUES (1, 400)");
        } catch (SQLException e) {
            throw new ApplicationException("Error setting up the test database", e);
        }
    }

    /**
     * Cleans up the test database by dropping the created tables.
     *
    private void dropTestDatabase() {
        try (Connection cn = DriverManager.getConnection(URL);
             Statement stmt = cn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS Packages");
            stmt.executeUpdate("DROP TABLE IF EXISTS Routes");
        } catch (SQLException e) {
            throw new ApplicationException("Error tearing down the test database", e);
        }
    }

    /**
     * Test to validate a package in the 'REGISTERED' state.
     * Ensures that no exception is thrown when the package is eligible for shipping.
     *
    @Test
    public void testValidatePackage_Registered() {
        try {
            transportDTO.validatePackage("PKG001");
        } catch (ApplicationException e) {
            throw new AssertionError("Expected no exception but got: " + e.getMessage());
        }
    }

    /**
     * Test to verify that an error is thrown when attempting to process a package in the 'SHIPPED' state.
     *
    @Test
    public void testValidatePackage_AlreadyShipped() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            transportDTO.validatePackage("PKG002");
        });
        assertEquals("Invalid package", exception.getMessage());
    }

    /**
     * Test to verify that the distance of a valid route is retrieved correctly.
     *
    @Test
    public void testValidateRouteAndGetDistance_ValidRoute() {
        int distance = transportDTO.validateRouteAndGetDistance(1);
        assertEquals(400, distance);
    }

    /**
     * Test to ensure that an error is thrown when the route does not exist in the database.
     *
    @Test
    public void testValidateRouteAndGetDistance_InvalidRoute() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            transportDTO.validateRouteAndGetDistance(999);
        });
        assertEquals("Invalid route", exception.getMessage());
    }

    /**
     * Test to create a new shipment and verify that it is correctly stored in the database.
     * The test ensures that all the shipment details are recorded accurately.
     *
    @Test
    public void testCreateShipment() {
        TransportModel shipment = new TransportModel("PKG001", 1, 2, LocalDate.now(), LocalDate.now().plusDays(2));
        transportDTO.createShipment(shipment);

        // Verify that the shipment has been inserted into the database.
        try (Connection cn = DriverManager.getConnection(URL);
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Shipments WHERE package_id = 'PKG001'")) {

            if (rs.next()) {
                assertEquals("PKG001", rs.getString("package_id"));
                assertEquals(1, rs.getInt("route_id"));
                assertEquals(2, rs.getInt("vehicle_id"));
            } else {
                throw new AssertionError("Shipment not found in the database.");
            }
        } catch (SQLException e) {
            throw new ApplicationException("Error querying the test database", e);
        }
    }
}
*/