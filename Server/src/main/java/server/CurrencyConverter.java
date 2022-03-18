package server;

import interfaces.RemoteCurrencyConverter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CurrencyConverter extends UnicastRemoteObject implements RemoteCurrencyConverter {

    private double convRate = 108.13;

    protected CurrencyConverter() throws RemoteException {
        super();
    }

    @Override
    public double arsToUsd(double amount) {
        return amount / this.convRate;
    }

    @Override
    public double usdToArs(double amount) {
        return amount * this.convRate;
    }
}
