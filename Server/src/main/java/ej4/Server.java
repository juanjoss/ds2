package ej4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static int SERVER_PORT = 9000;
    public static String SERVICE_NAME = "calculator";

    public static void main(String[] args) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(Server.SERVER_PORT);
        r.rebind(SERVICE_NAME, new Calculator());

        System.out.println("server ready for connections...");
    }
}
