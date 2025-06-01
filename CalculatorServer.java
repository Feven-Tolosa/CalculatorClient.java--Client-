import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Create and bind calculator service
            CalculatorImpl calculator = new CalculatorImpl();
            registry.rebind("CalculatorService", calculator);
            
            System.out.println("Calculator server running on port 1099...");
        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}