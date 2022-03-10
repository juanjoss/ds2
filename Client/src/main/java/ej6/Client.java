package ej6;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RemoteMonetaryCalculator service = (RemoteMonetaryCalculator) Naming.lookup("rmi://localhost:9006/monetaryCalculator");
        boolean exit = false;

        while (!exit) {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto: "));
            int term = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el plazo: "));

            JOptionPane.showMessageDialog(null, "Monto Final: " + service.yield(amount, term));
        }
    }
}
