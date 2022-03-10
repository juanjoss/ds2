package ej4;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RemoteCalculator service = (RemoteCalculator) Naming.lookup("rmi://localhost:9004/calculator");
        boolean exit = false;

        while (!exit) {
            double op1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese operando 1: "));
            String op = JOptionPane.showInputDialog("Ingrese operación (+ | - | x | /): ");
            double op2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese operando 2: "));
            double result = 0;

            switch (op) {
                case "+" ->
                    result = service.sum(op1, op2);
                case "-" ->
                    result = service.subtract(op1, op2);
                case "x" ->
                    result = service.multiply(op1, op2);
                case "/" ->
                    result = service.divide(op1, op2);
                default -> {
                    JOptionPane.showMessageDialog(null, "Operación invalida.");
                    continue;
                }
            }

            JOptionPane.showMessageDialog(null, "Resultado: " + result);
        }
    }
}
