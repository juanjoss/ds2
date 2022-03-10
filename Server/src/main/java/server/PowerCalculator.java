package server;

import ej3.RemotePowerCalculator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PowerCalculator extends UnicastRemoteObject implements RemotePowerCalculator {

    protected PowerCalculator() throws RemoteException {
        super();
    }

    @Override
    public double square(double number) throws RemoteException {
        double result = Math.sqrt(number);
        return result;
    }

    @Override
    public double pow(double base, double exponent) throws RemoteException {
        double result = Math.pow(base, exponent);
        return result;
    }
}
