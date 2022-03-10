package ej5;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSecondConverter extends Remote {

    public String convertSeconds(long sec) throws RemoteException;
}
