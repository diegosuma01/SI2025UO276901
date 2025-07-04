/*import giis.demo.tkrun.controller.TransportController;
import giis.demo.tkrun.DTOs.TransportDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.model.TransportModel;
import giis.demo.tkrun.view.TransportView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransportControllerTest {

    private TransportController transportController;
    private TransportDTO transportDTO;
    private TransportModel transportModel;
    private TransportView transportView;

    @BeforeEach
    public void setUp() {
        // Inicializamos las dependencias
        transportDTO = new TransportDTO();
        transportModel = new TransportModel();
        transportView = new TransportView();

        // Asumiendo que `TransportController` requiere estas instancias
        transportController = new TransportController(transportDTO, transportModel, transportView);
    }

    // Test de carga de paquete
    @Test
    public void testLoadPackageSuccessful() {
        int vehicleId = 1;     // Asumimos que este ID existe y tiene capacidad
        int packageId = 101;   // Asumimos que este paquete existe y está listo para ser cargado
        
        boolean isLoaded = transportController.loadPackage(vehicleId, packageId);
        assertTrue(isLoaded, "Package should be successfully loaded onto the vehicle.");
    }

    // Test de límite de capacidad del vehículo
    @Test
    public void testLoadPackageFailDueToCapacity() {
        int vehicleId = 2;     // Asumimos que este vehículo está lleno
        int packageId = 102;   // Paquete con peso que sobrepasa el espacio disponible
        
        boolean isLoaded = transportController.loadPackage(vehicleId, packageId);
        assertFalse(isLoaded, "Package should not be loaded as the vehicle is at full capacity.");
    }

    // Test de descarga exitosa de paquete
    @Test
    public void testUnloadPackageSuccessful() {
        int vehicleId = 1;     // Este vehículo tiene paquetes cargados
        int packageId = 101;   // Paquete previamente cargado
        
        boolean isUnloaded = transportController.unloadPackage(vehicleId, packageId);
        assertTrue(isUnloaded, "Package should be successfully unloaded from the vehicle.");
    }

    // Test para obtener todos los paquetes cargados en un vehículo
    @Test
    public void testGetLoadedPackages() {
        int vehicleId = 1;     // Vehículo con paquetes cargados
        
        List<PackageModel> packages = transportController.getLoadedPackages(vehicleId);
        assertNotNull(packages, "The list of loaded packages should not be null.");
        assertTrue(packages.size() >= 0, "The list should contain zero or more packages.");
    }

    // Test de capacidad del vehículo
    @Test
    public void testGetVehicleCapacity() {
        int vehicleId = 1;     // Vehículo existente
        
        int capacity = transportController.getVehicleCapacity(vehicleId);
        assertTrue(capacity > 0, "Vehicle capacity should be a positive integer.");
    }

    // Test de carga en un vehículo no existente
    @Test
    public void testLoadPackageNonExistentVehicle() {
        int invalidVehicleId = 999; // ID no existente
        int packageId = 101;        // Paquete existente
        
        boolean isLoaded = transportController.loadPackage(invalidVehicleId, packageId);
        assertFalse(isLoaded, "Loading should fail as the vehicle does not exist.");
    }

}
*/