package ej5;

/**
 *
 * @author Luciano
 */


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SecondConverter extends UnicastRemoteObject implements RemoteSecondConverter{
    protected SecondConverter() throws RemoteException {
        super();
    }
    
    public String convertSeconds(long sec){
        long rem = sec%3600;
        sec = sec/3600;
        String hours = "";
        if(sec < 10){
            hours += "0";
        }
        hours += Long.toString(sec);
        
        String minutes = "";
        if((rem/60) < 10){
            minutes += "0";
        }
        minutes += Long.toString(rem/60);
        
        String seconds = "";
        if((rem%60) < 10){
            seconds += "0";
        }
        seconds += Long.toString(rem%60);
        
        return hours + ":" + minutes + ":" + seconds;
    }
}
