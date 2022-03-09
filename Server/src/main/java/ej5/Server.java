/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej5;

import ej5.SecondConverter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Luciano
 */
public class Server {
    public static void main(String[] args) throws RemoteException {
        Registry r = LocateRegistry.createRegistry(9000);
        r.rebind("secondConverter", new SecondConverter());

        System.out.println("server 5 ready for connections...");
    }
}

