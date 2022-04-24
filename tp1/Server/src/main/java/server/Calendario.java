package server;

import interfaces.CalendarioRemoto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.format.DateTimeFormatter;

public class Calendario extends UnicastRemoteObject implements CalendarioRemoto {

    protected Calendario() throws RemoteException {
        super();
    }

    @Override
    public String consultarFechayHora() {
        return java.time.LocalDate.now().toString()
                + " "
                + java.time.LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
