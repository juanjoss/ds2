package ej5;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMonetaryCalculator extends Remote {

    public double yield(double amount, int term) throws RemoteException;
}
