package ej6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MonetaryCalculator extends UnicastRemoteObject implements RemoteMonetaryCalculator {

    private final double inflation = 0.548;

    public MonetaryCalculator() throws RemoteException {
        super();
    }

    @Override
    public double yield(double amount, int term) {
        return amount - amount * term * (this.inflation / 12);
    }
}
