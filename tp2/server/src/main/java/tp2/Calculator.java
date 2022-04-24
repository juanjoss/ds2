package tp2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import org.mariuszgromada.math.mxparser.*;

@WebService(serviceName = "Calculator")
@Stateless()
public class Calculator {

    /**
     * Web service operation
     * @param expression
     * @return result for expression
     */
    @WebMethod(operationName = "solveExpression")
    public String solveExpression(@WebParam(name = "expression") String expression) {
        Expression e = new Expression(expression);
        double result = e.calculate();
        
        if(Double.isNaN(result)) {
            return "invalid";
        }
        
        return Double.toString(result);
    }
}
