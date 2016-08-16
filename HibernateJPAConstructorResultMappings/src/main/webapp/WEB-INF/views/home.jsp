<%-- 
    Document   : index
    Created on : Jul 22, 2016, 12:16:18 PM
    Author     : Anghel Leonard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get DTO</title>
    </head>
    <body>        
        <h1>Get it ...</h1>
        <form method="get" action="${pageContext.request.contextPath}/dto">
            <input type="submit" value="DTO"/>
        </form>

        <c:forEach var="i" items="${dto}">
            Item: <c:out value="${i}"/><p>
        </c:forEach>            
    </body>
</html>
