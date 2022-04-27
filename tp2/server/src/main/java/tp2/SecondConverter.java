package tp2;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "SecondConverter")
public class SecondConverter {

    /**
     * Web service operation
     * @param sec
     * return hours, minutes and seconds
     */
    @WebMethod(operationName = "convertSeconds")
    public String convertSeconds(@WebParam(name = "sec") long sec) {
        long rem = sec % 3600;
        sec = sec / 3600;
        String hours = "";
        if (sec < 10) {
            hours += "0";
        }
        hours += Long.toString(sec);

        String minutes = "";
        if ((rem / 60) < 10) {
            minutes += "0";
        }
        minutes += Long.toString(rem / 60);

        String seconds = "";
        if ((rem % 60) < 10) {
            seconds += "0";
        }
        seconds += Long.toString(rem % 60);

        return hours + ":" + minutes + ":" + seconds;
    }
}
