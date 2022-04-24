package tp2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "PowerCalculator")
@Stateless()
public class PowerCalculator {

    /**
     * Web service operation
     * @param n
     * @return n^2
     */
    @WebMethod(operationName = "square")
    public double square(@WebParam(name = "n") double n) throws NumberFormatException {
        return Math.pow(n, 2);
    }

    /**
     * Web service operation
     * @param n
     * @param p
     * @return n^p
     */
    @WebMethod(operationName = "power")
    public double power(@WebParam(name = "n") double n, @WebParam(name = "p") double p) throws NumberFormatException {
        return Math.pow(n, p);
    }
}
