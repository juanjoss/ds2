package ej4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCalculator extends Remote {

    public double sum(double x, double y) throws RemoteException;

    public double subtract(double x, double y) throws RemoteException;

    public double multiply(double x, double y) throws RemoteException;

    public double divide(double x, double y) throws RemoteException;
}
