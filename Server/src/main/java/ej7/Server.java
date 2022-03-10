package ej7;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws RemoteException {

        Registry r = LocateRegistry.createRegistry(9000);
        r.rebind("barCodeReader", new BarCodeReader());

        System.out.println("server ready for connections...");
    }
}
