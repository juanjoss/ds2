package ej7;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import javax.swing.JOptionPane;

public class Client {

    private static int SERVER_PORT = 9000;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        RemoteBarCodeReader service = (RemoteBarCodeReader) Naming.lookup("rmi://localhost:" + SERVER_PORT + "/barCodeReader");

        boolean exit = false;

        while (!exit) {
            int barCode = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese un codigo de barras: "));
            JOptionPane.showMessageDialog(null, service.checkPrice(barCode));

            if (barCode == JOptionPane.NO_OPTION) {
                exit = true;
            }

        }
        
    }
}
