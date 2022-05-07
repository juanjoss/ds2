<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <title>Ticket search</title>
    </head>
    <body class="text-center container">
        <div class="p-2" style="display: table"><a class="btn btn-primary" href="/client">Home</a></div>
        <h1>Ticket search</h1>

        <div class="row justify-content-center">
            <div class="card bg-light col-6 m-4">
                <form action="/client/ticket_search.jsp">
                    <div class="form-group row m-3">
                        <label for="startCity" class="col-sm-3 col-form-label">From</label>
                        <div class="col-sm-9">
                            <input required type="text" class="form-control" id="from_city" name="from_city" placeholder="City name">
                        </div>
                    </div>
                    <div class="form-group row m-3">
                        <label for="finalCity" class="col-sm-3 col-form-label">To</label>
                        <div class="col-sm-9">
                            <input required type="text" class="form-control" id="to_city" name="to_city" placeholder="City name">
                        </div>
                    </div>
                    <div class="form-group row m-3">
                        <label for="departureDate" class="col-sm-3 col-form-label">Departure Date</label>
                        <div class="col-sm-9">
                            <input required type="date" class="form-control" id="departure_date" name="departure_date">
                        </div>
                    </div>
                    <div class="form-group row m-3">
                        <label for="returnDate" class="col-sm-3 col-form-label">Return Date (Optional)</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="return_date" name="return_date">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary m-3">Search</button>
                </form>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="card bg-light col-6 m-4">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Nombre de Empresa</th>
                            <th scope="col">Asientos Disponibles</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            try {
                                tp2.TicketSearch ts = new tp2.TicketSearch();
                                List<String[]> tickets = new ArrayList();
                                String fromCity = request.getParameter("from_city");
                                String toCity = request.getParameter("to_city");
                                String departureDate = request.getParameter("departure_date");
                                String returnDate = request.getParameter("return_date");
                                tickets = ts.ticketSearch(fromCity, toCity, departureDate, returnDate);
                                request.setAttribute("tickets", tickets);

                            } catch (Exception ex) {
                            }
                        %>

                        <c:forEach var="ticket" items="${tickets}">
                            <tr>
                                <td><c:out value="${ticket[0]}"/></td>
                                <td><c:out value="${ticket[1]}"/></td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
