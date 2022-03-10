package ej5;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        long sec = Long.parseLong(JOptionPane.showInputDialog("Ingrese una cantidad de segundos: "));
        RemoteSecondConverter service = (RemoteSecondConverter) Naming.lookup("rmi://localhost:9005/secondConverter");
        JOptionPane.showMessageDialog(null, "Resultado: " + service.convertSeconds(sec));
    }
}
