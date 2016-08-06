<%-- 
    Document   : index
    Created on : Jul 22, 2016, 12:16:18 PM
    Author     : Anghel Leonard
--%>

<%@ page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update records ...</title>
    </head>
    <body>        

        <button onclick="location.href = '${pageContext.request.contextPath}/newproduct'">New product</button>
        <hr/>

        Existing products:
        <c:if test="${not empty products}">
            <table>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>
                            ${product.id}
                        </td>
                        <td>
                            ${product.name}
                        </td>
                        <td>
                            ${product.code}
                        </td>
                        <td>
                            <button onclick="location.href = '${pageContext.request.contextPath}/product/${product.id}/update'">Update product</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>                                                
        </c:if>
    </body>
</html>
