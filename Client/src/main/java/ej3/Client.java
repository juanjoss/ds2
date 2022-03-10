package ej3;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        RemotePowerCalculator service = (RemotePowerCalculator) Naming.lookup("rmi://localhost:9002/powerCalculations");

        boolean exit = false;

        while (!exit) {
            int option = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                                                            Seleccione una opci\u00f3n: 
                                                                             1. Calcular ra\u00edz
                                                                             2. Calcular potencia 
                                                                             0.Salir"""));
            switch (option) {
                case 1 -> {
                    int sqrt = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un número: "));
                    JOptionPane.showMessageDialog(null, "La raíz es: " + service.square(sqrt));
                }
                case 2 -> {
                    int base = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la base: "));
                    int exponent = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el exponente: "));
                    JOptionPane.showMessageDialog(null, "La potencia es: " + service.pow(base, exponent));
                }
                case 0 ->
                    exit = true;
                default ->
                    JOptionPane.showMessageDialog(null, "Solo números entre 0 y 2");
            }
        }

    }
}
