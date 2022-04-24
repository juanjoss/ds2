package tp2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;

@WebService(serviceName = "Test")
@Stateless()
public class Test {

    /**
     * This is a test web service operation.
     * @return 
     */
    @WebMethod(operationName = "test")
    public String test() {
        return "Test service working.";
    }
}
