<%-- 
    Document   : index
    Created on : Jul 22, 2016, 12:16:18 PM
    Author     : Anghel Leonard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update records ...</title>
    </head>
    <body>        

        <h1>New product ...</h1><hr/>       
        <form:form method="post" action="${pageContext.request.contextPath}/saveorupdateproduct">
            <form:label path="name">Name</form:label>
            <form:input path="name" type="text" />       

            <form:label path="name">Code</form:label>
            <form:input path="code" type="text" />    

            <input type="submit" value="Submit"/>
        </form:form> 
        <hr/>
    </body>
</html>
