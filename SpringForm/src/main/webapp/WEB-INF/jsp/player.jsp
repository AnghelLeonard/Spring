<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Spring MVC Form Handling</title>
    </head>
    <body>

        <h2>Player Information</h2>
        <form:form method="POST" action="/SpringForm/addPlayer">
            <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><form:label path="age">Age</form:label></td>
                    <td><form:input path="age" /></td>
                </tr>
                <tr>
                    <td><form:label path="birthplace">Birthplace</form:label></td>
                    <td><form:input path="birthplace" /></td>
                </tr>
                <tr>
                    <td><form:label path="residence">Residence</form:label></td>
                    <td><form:input path="residence" /></td>
                </tr>
                <tr>
                    <td><form:label path="height">Height</form:label></td>
                    <td><form:input path="height" /></td>
                </tr>
                <tr>
                    <td><form:label path="weight">Weight</form:label></td>
                    <td><form:input path="weight" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>  
        </form:form>
    </body>
</html>
