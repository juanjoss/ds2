package ej1;

import interfaces.CalendarioRemoto;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        CalendarioRemoto service = (CalendarioRemoto) Naming.lookup("rmi://localhost:9001/calendar");

        JOptionPane.showMessageDialog(null, "Fecha y Hora Actuales: " + service.consultarFechayHora());
    }
}
