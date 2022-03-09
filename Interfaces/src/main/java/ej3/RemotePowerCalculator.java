package ej3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePowerCalculator extends Remote {

    public long square(int number) throws RemoteException;

    public long pow(int base, int exponent) throws RemoteException;
}
