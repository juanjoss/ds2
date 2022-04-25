package tp2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "CurrencyConverter")
@Stateless()
public class CurrencyConverter {

    private double convRate = 118.02;

    /**
     * Web service operation
     *
     * @param amount
     * @return arsToUsd
     */
    @WebMethod(operationName = "arsToUsd")
    public String arsToUsd(@WebParam(name = "amount") String amount) {
        try {
            double dAmount = Double.parseDouble(amount);
            if (dAmount >= 0) {
                return Double.toString(dAmount / this.convRate);
            } else {
                return "Invalid amount = " + amount;
            }
        } catch (NumberFormatException e) {
            return "Invalid amount = " + amount;
        }
    }

    /**
     * Web service operation
     *
     * @param amount
     * @return usdToArs
     */
    @WebMethod(operationName = "usdToArs")
    public String usdToArs(@WebParam(name = "amount") String amount) {
        try {
            double dAmount = Double.parseDouble(amount);
            
            if (dAmount >= 0) {
                return Double.toString(dAmount * this.convRate);
            } else {
                return "Invalid amount = " + amount;
            }
        } catch (NumberFormatException e) {
            return "Invalid amount = " + amount;
        }
    }
}