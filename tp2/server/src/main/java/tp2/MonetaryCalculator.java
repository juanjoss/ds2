package tp2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "MonetaryCalculator")
public class MonetaryCalculator {
    
    private final double inflation = 0.548;

    /**
     * Web service operation
     * @param amount
     * @param term
     */
    @WebMethod(operationName = "yield")
    public double yield(@WebParam(name = "amount") double amount, @WebParam(name = "term") int term) {
        return amount - amount * term * (this.inflation / 12);
    }
}
