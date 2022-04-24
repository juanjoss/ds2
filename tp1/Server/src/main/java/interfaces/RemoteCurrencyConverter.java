package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCurrencyConverter extends Remote {

    public double arsToUsd(double amount) throws RemoteException;

    public double usdToArs(double amount) throws RemoteException;
}
