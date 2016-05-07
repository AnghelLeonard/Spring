<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login</title>
    </head>
    <body>
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <font color="red">
            Your login attempt was not successful due to <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
            </font>
        </c:if>
        <form name="loginForm" action="authenticateUser" method="post">
            User-name<input type="text" name="username" /><br /> Password <input
                type="password" name="password" /> <input type="hidden"
                name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
                type="submit" value="Submit">
        </form>
    </body>
</html>