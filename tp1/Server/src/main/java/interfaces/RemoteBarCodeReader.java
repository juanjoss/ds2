package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBarCodeReader extends Remote {

    public String checkPrice(int code) throws RemoteException;
}
