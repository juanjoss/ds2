package ej2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CurrencyConverter extends UnicastRemoteObject implements RemoteCurrencyConverter {

    private static final double CONV_RATE = 108.13;

    protected CurrencyConverter() throws RemoteException {
        super();
    }

    @Override
    public double arsToUsd(double amount) {
        return amount / CONV_RATE;
    }

    @Override
    public double usdToArs(double amount) {
        return amount * CONV_RATE;
    }
}
