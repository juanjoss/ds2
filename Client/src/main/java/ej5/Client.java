/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej5;
import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Luciano
 */
public class Client{
    
    private static int SERVER_PORT = 9000;
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        long sec = Long.parseLong(JOptionPane.showInputDialog("Ingrese una cantidad de segundos."));
        RemoteSecondConverter service = (RemoteSecondConverter) Naming.lookup("rmi://localhost:" + SERVER_PORT + "/secondConverter");
        JOptionPane.showMessageDialog(null,"Resultado: "+service.convertSeconds(sec));
    }
}
