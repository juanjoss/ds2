package ej2;

import interfaces.RemoteCurrencyConverter;
import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        RemoteCurrencyConverter service = (RemoteCurrencyConverter) Naming.lookup("rmi://localhost:9002/currencyConverter");
        JTextField amount = new JTextField(8);
        String[] options = {"ARS to USD", "USD to ARS"};
        JComboBox<String> jComboBox = new JComboBox<>(options);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("$:"));
        myPanel.add(amount);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Convertion:"));
        myPanel.add(jComboBox);

        int conv = JOptionPane.showConfirmDialog(null, myPanel,
                "Currency Converter", JOptionPane.OK_CANCEL_OPTION);

        if (conv == JOptionPane.OK_OPTION) {
            if (jComboBox.getSelectedIndex() == 0) {
                double result = service.arsToUsd(Double.parseDouble(amount.getText()));
                JOptionPane.showMessageDialog(null, "Resultado: " + result);
            } else {
                double result = service.usdToArs(Double.parseDouble(amount.getText()));
                JOptionPane.showMessageDialog(null, "Resultado: " + result);
            }
        }
    }
}
