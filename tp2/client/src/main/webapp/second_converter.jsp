<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Second Converter</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="p-2" style="display: table"><a class="btn btn-primary" href="/client">Home</a></div>
        <h1 class="text-center m-5">Second Converter</h1>
        <form action="/client/second_converter.jsp">
            <div class="col-2 offset-5 col-sm-2 offset-sm-5 col-lg-2 offset-lg-5 col-xl-2 offset-xl-5">
                <input class="form-control" type="text" name="seconds" placeholder="seconds" required />
                <button class="btn btn-primary mt-3">Convert</button>
            </div>
            <hr>
            <div class="container justify-content-center">               
                <div class="row">
                    <div class="text-center mt-5">
                        <%
                            try {
                                tp2.SecondConverter service = new tp2.SecondConverter();
                                String seconds = request.getParameter("seconds");
                                Long secondsLong = Long.parseLong(seconds);
                                String result = service.convertSeconds(secondsLong);
                                out.println(result);
                            } catch (Exception ex) {
                            }
                        %>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>