import java.rmi.registry.LocateRegistry;

public class RMITest {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry started on port 1099");
        } catch (Exception e) {
            System.err.println("Registry error: " + e.getMessage());
        }
    }
}