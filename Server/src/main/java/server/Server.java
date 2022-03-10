package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    private String ip = "localhost";
    private int port = 9000;

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    public static void main(String[] args) throws RemoteException {
        new Server("localhost", 9000)
                .run("calendar", new Calendario())
                .run("currencyConverter", new CurrencyConverter())
                .run("powerCalculations", new PowerCalculator())
                .run("calculator", new Calculator())
                .run("monetaryCalculator", new MonetaryCalculator())
                .run("account", new Account());
    }

    private Server run(String serviceName, UnicastRemoteObject remoteObject) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(this.port);
        r.rebind(serviceName, remoteObject);

        System.out.println("service binded on " + this.getAddress() + serviceName);
        this.port++;

        return this;
    }

    public String getAddress() {
        return this.ip + ":" + this.port + "/";
    }
}
