package ej8;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteAccount extends Remote {

    public void deposit(double amount) throws RemoteException;

    public boolean withdraw(double amount) throws RemoteException;

    public double checkBalance() throws RemoteException;
}
