<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>Currency Converter</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body class="text-center">
        <h1>Currency Converter</h1>
        <form action="/client/currency_converter.jsp">
            <div class="col-2 offset-5 col-sm-2 offset-sm-5 col-lg-2 offset-lg-5 col-xl-2 offset-xl-5">
                <input class="form-control" type="number" name="amount" placeholder="amount" required />
            </div>
            
            <button class="btn btn-primary mt-3">Convert</button>
        </form>
        <hr>
        
        <div class="row">
            <div class="col">
                <h1>ARS to USD</h1>
                <%
                    try { 
                        tp2.CurrencyConverter service = new tp2.CurrencyConverter();
                        String amount = request.getParameter("amount");
                        String result = service.arsToUsd(amount);
                        out.println("Result: USD " + result);
                    } catch (Exception ex) {
                    }
                %>
            </div>
            <div class="col">
                <h1>USD to ARS</h1>
                <%
                    try {
                        tp2.CurrencyConverter service = new tp2.CurrencyConverter();
                        String amount = request.getParameter("amount");
                        String result = service.usdToArs(amount);
                        out.println("Result: ARS " + result);
                    } catch (Exception ex) {
                    }
                %>
            </div>
        </div>
    </body>
</html>
