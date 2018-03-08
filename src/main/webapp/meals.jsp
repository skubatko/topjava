<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
    <ul>
        <%
            List<MealWithExceed> meals = (List<MealWithExceed>) request.getAttribute( "meals" );

            if ( meals != null && !meals.isEmpty() ) {
                out.println("<ul>");
                for (MealWithExceed m : meals) {
                    if ( m.isExceed() ) {
                        out.println( "<li style=\"background-color:red;\">" + m + "</li>" );
                    } else {
                        out.println( "<li style=\"background-color:green;\">" + m + "</li>" );
                    }
                }
                out.println("</ul>");
            } else {
                System.out.println("<p> There is no meals yet!!</p>");
            }

        %>
    </ul>
</body>
</html>