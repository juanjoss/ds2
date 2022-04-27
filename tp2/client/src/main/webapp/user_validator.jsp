<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title>User Validation</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <%
            try {
                tp2.UserValidator service = new tp2.UserValidator();
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if(username != null && password != null) {
                    Boolean result = service.validate(username, password);

                    if(result) {
                        %>
                            <div class="p-2"><a class="btn btn-primary" href="/client/user_validator.jsp">Home</a></div>
                            <h1 class="text-center text-success">User "<% out.print(username); %>" validated successfully.</h1>
                        <%
                    } else {
                        %>
                            <div class="p-2"><a class="btn btn-primary" href="/client/user_validator.jsp">Home</a></div>
                            <h1 class="text-center text-danger">Validation error</h1>
                            <h1 class="text-center text-danger">Incorrect credentials for user "<% out.print(username); %>"</h1>
                        <%
                    }
                } else {
                    %>
                    <div class="p-2" style="display: table"><a class="btn btn-primary" href="/client">Home</a></div>    
                    <h1 class="text-center mt-5">Login</h1>

                        <div class="card bg-dark text-light col-6 col-offset-3 col-sm-6 offset-sm-3 col-lg-6 offset-lg-3 col-xl-6 offset-xl-3">
                            <form class="text-center" method="POST" action="/client/user_validator.jsp">
                                <div class="row d-flex justify-content-center p-3">
                                    <div class="col-6 col-sm-6 col-lg-6 col-xl-6">
                                        <label>Username:</label>
                                        <input class="form-control" type="text" name="username" value="admin" required />
                                    </div>
                                </div>
                                <div class="row d-flex justify-content-center p-3">
                                    <div class="col-6 col-sm-6 col-lg-6 col-xl-6">
                                        <label>Password:</label>
                                        <input class="form-control" type="password" name="password" value="admin" required />
                                    </div>
                                </div>

                                <div class="p-2"><button class="btn btn-primary p-3">Login</button></div>
                            </form>
                        </div>
                    <%
                }
            } catch (Exception ex) {}
        %>
    </body>
</html>
