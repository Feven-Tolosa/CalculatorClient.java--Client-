import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    // Basic operations
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
    
    // Bonus operations
    double power(double base, double exponent) throws RemoteException;
    double sqrt(double a) throws RemoteException;
    
    // Bonus history feature
    String getHistory() throws RemoteException;
}