package ej5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static int SERVER_PORT = 9000;
    public static String SERVICE_NAME = "monetaryCalculator";

    public static void main(String[] args) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(ej4.Server.SERVER_PORT);
        r.rebind(SERVICE_NAME, new MonetaryCalculator());

        System.out.println("server ready for connections...");
    }
}
