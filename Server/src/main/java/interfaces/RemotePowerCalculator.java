package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePowerCalculator extends Remote {

    public double square(double number) throws RemoteException;

    public double pow(double base, double exponent) throws RemoteException;
}
