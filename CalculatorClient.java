import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Connect to RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nOptions: add, sub, mul, div, pow, sqrt, history, exit");
                System.out.print("Enter command: ");
                String command = scanner.next().toLowerCase();
                
                if (command.equals("exit")) {
                    System.out.println("Exiting calculator...");
                    break;
                }
                
                try {
                    switch (command) {
                        case "add": case "sub": case "mul": case "div": case "pow":
                            System.out.print("Enter two numbers (space-separated): ");
                            double x = readDouble(scanner);
                            double y = readDouble(scanner);
                            try {
                                double result = calculate(calculator, command, x, y);
                                System.out.println("Result: " + result);
                            } catch (RemoteException e) {
                                System.err.println("Calculation error: " + e.getMessage());
                            }
                            break;
                            
                        case "sqrt":
                            System.out.print("Enter number: ");
                            double num = readDouble(scanner);
                            try {
                                System.out.println("Result: " + calculator.sqrt(num));
                            } catch (RemoteException e) {
                                System.err.println("Calculation error: " + e.getMessage());
                            }
                            break;
                            
                        case "history":
                            try {
                                System.out.println("\nCalculation History:\n" + calculator.getHistory());
                            } catch (RemoteException e) {
                                System.err.println("Error retrieving history: " + e.getMessage());
                            }
                            break;
                            
                        default:
                            System.out.println("Invalid command! Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input! Please enter numbers only.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
        } catch (Exception e) {
            System.err.println("Client connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static double calculate(Calculator calc, String op, double x, double y) 
        throws RemoteException {
    switch (op) {
        case "add":
            return calc.add(x, y);
        case "sub":
            return calc.subtract(x, y);
        case "mul":
            return calc.multiply(x, y);
        case "div":
            return calc.divide(x, y);
        case "pow":
            return calc.power(x, y);
        default:
            throw new RemoteException("Invalid operation");
    }
}
     private static double readDouble(Scanner scanner) throws InputMismatchException {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.err.print("Invalid number! Please re-enter: ");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}