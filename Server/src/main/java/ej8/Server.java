package ej8;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static int SERVER_PORT = 9000;
    public static String SERVICE_NAME = "account";

    public static void main(String[] args) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(SERVER_PORT);
        r.rebind(SERVICE_NAME, new Account());

        System.out.println("server (ej8) ready for connections...");
    }
}
