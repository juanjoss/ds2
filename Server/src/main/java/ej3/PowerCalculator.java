package ej3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PowerCalculator extends UnicastRemoteObject implements RemotePowerCalculator {

    protected PowerCalculator() throws RemoteException {
        super();
    }

    @Override
    public long square(int number) throws RemoteException {
        double square = Math.sqrt(number);
        long result = (long) square;
        return result;
    }

    @Override
    public long pow(int base, int exponent) throws RemoteException {
        double pow = Math.pow(base, exponent);
        long result = (long) pow;
        return result;
    }
}
