package ej8;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RemoteAccount service = (RemoteAccount) Naming.lookup("rmi://localhost:9008/account");
        boolean exit = false;

        while (!exit) {
            int op = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                                                            Seleccione una opci\u00f3n: 
                                                                             1. Depositar
                                                                             2. Retirar
                                                                             3. Consultar
                                                                             Salir (esc)"""));

            switch (op) {
                case 1 ->
                    service.deposit(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese monto a depositar: ")));
                case 2 -> {
                    boolean success = service.withdraw(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese monto a retirar: ")));
                    if (!success) {
                        JOptionPane.showMessageDialog(null, "Saldo Insuficiente.");
                    }
                }
                case 3 ->
                    JOptionPane.showMessageDialog(null, "Saldo Disponible: " + service.checkBalance());
                default ->
                    throw new AssertionError();
            }
        }
    }
}
