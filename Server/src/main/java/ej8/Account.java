package ej8;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Account extends UnicastRemoteObject implements RemoteAccount {

    private double balance;

    public Account() throws RemoteException {
        super();
        this.balance = 0;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }

        return false;
    }

    @Override
    public double checkBalance() {
        return this.balance;
    }
}
