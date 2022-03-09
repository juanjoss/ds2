package ej1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        CalendarioRemoto service = (CalendarioRemoto) Naming.lookup("rmi://localhost:" + SERVER_PORT + "/calendar");
        System.out.println("fecha y hora actuales: " + service.consultarFechayHora());
    }
}
