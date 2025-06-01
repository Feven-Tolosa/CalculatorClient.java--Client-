import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    private List<String> history = new ArrayList<>();
    
    public CalculatorImpl() throws RemoteException {
        super();
    }
    
    public double add(double a, double b) throws RemoteException {
        double result = a + b;
        history.add(String.format("%.2f + %.2f = %.2f", a, b, result));
        return result;
    }
    
    public double subtract(double a, double b) throws RemoteException {
        double result = a - b;
        history.add(String.format("%.2f - %.2f = %.2f", a, b, result));
        return result;
    }
    
    public double multiply(double a, double b) throws RemoteException {
        double result = a * b;
        history.add(String.format("%.2f * %.2f = %.2f", a, b, result));
        return result;
    }
    
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) throw new RemoteException("Cannot divide by zero!");
        double result = a / b;
        history.add(String.format("%.2f / %.2f = %.2f", a, b, result));
        return result;
    }
    
    // Bonus methods
    public double power(double base, double exponent) throws RemoteException {
        double result = Math.pow(base, exponent);
        history.add(String.format("%.2f ^ %.2f = %.2f", base, exponent, result));
        return result;
    }
    
    public double sqrt(double a) throws RemoteException {
        if (a < 0) throw new RemoteException("Cannot square root negative number!");
        double result = Math.sqrt(a);
        history.add(String.format("√%.2f = %.2f", a, result));
        return result;
    }
    
    public String getHistory() throws RemoteException {
        return history.isEmpty() ? "No history yet" : String.join("\n", history);
    }
}