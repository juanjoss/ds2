package ej4;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static String SERVER_IP = "localhost";
    public static int SERVER_PORT = 9000;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RemoteCalculator service = (RemoteCalculator) Naming.lookup("rmi://" + Client.SERVER_IP + ":" + Client.SERVER_PORT + "/" + "calculator");

        String op = args[1];
        long x = Long.parseLong(args[0]), y = Long.parseLong(args[2]);

        switch (op) {
            case "+" ->
                System.out.println("result for " + x + op + y + ": " + service.sum(x, y));
            case "-" ->
                System.out.println("result for " + x + op + y + ": " + service.subtract(x, y));
            case "x" ->
                System.out.println("result for " + x + op + y + ": " + service.multiply(x, y));
            case "/" ->
                System.out.println("result for " + x + op + y + ": " + service.divide(x, y));
            default ->
                System.out.println("invalid operation, format: x [+ | - | * | /] y");
        }
    }
}
