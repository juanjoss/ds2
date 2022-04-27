<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <title>Material Design for Bootstrap</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="calculator/css/style.css">
    </head>
    <body>
        <div class="p-3" style="display: table"><a class="btn btn-primary" href="/client">Home</a></div>
        <div class="container my-4">  
            <div class="calculator card">
                <%
                    try {
                        String expression = null;
                        tp2.Calculator service = new tp2.Calculator();
                        expression = request.getParameter("expression");
                        
                        %>
                        <%
                            if(expression != null) {
                                expression = service.solveExpression(expression);
                                %>
                                    <input type="text" class="calculator-screen z-depth-1" value="<% out.print(expression); %>" disabled />
                                <%
                            } else {
                                %>
                                    <input type="text" class="calculator-screen z-depth-1" value="" disabled />
                                <%
                            }
                        %>

                        <div class="calculator-keys">
                            <button type="button" class="btn btn-info" value="+">+</button>
                            <button type="button" class="btn btn-info" value="-">-</button>
                            <button type="button" class="btn btn-info" value="*">&times;</button>
                            <button type="button" class="btn btn-info" value="/">&divide;</button>

                            <button type="button" value="7" class="btn btn-light waves-effect">7</button>
                            <button type="button" value="8" class="btn btn-light waves-effect">8</button>
                            <button type="button" value="9" class="btn btn-light waves-effect">9</button>
                            <button type="button" value="(" class="btn btn-light waves-effect">(</button>


                            <button type="button" value="4" class="btn btn-light waves-effect">4</button>
                            <button type="button" value="5" class="btn btn-light waves-effect">5</button>
                            <button type="button" value="6" class="btn btn-light waves-effect">6</button>
                            <button type="button" value=")" class="btn btn-light waves-effect">)</button>


                            <button type="button" value="1" class="btn btn-light waves-effect">1</button>
                            <button type="button" value="2" class="btn btn-light waves-effect">2</button>
                            <button type="button" value="3" class="btn btn-light waves-effect">3</button>
                            <button type="button" class="btn btn-danger btn-sm" value="AC">AC</button>

                            <button type="button" value="0" class="btn btn-light waves-effect">0</button>
                            <button type="button" class="btn btn-secondary" value=".">.</button>
                            <a 
                                href="/client/calculator.jsp?expression=<% if(expression != null) {out.print(expression);} %>" 
                                id="urlExpression" 
                                class="equal-sign btn btn-dark" 
                                value="="
                            >=</a>
                        </div>
                        <%
                    } catch (Exception ex) {
                        out.print("nop");
                    }
                %>
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script type="text/javascript" src="calculator/js/script.js"></script>
    </body>
</html>

