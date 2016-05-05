<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Spring MVC Form Handling</title>
    </head>
    <body>

        <h2>Submitted Student Information</h2>
        <table>
            <tr>
                <td>Name</td>
                <td>${name}</td>
            </tr>
            <tr>
                <td>Age</td>
                <td>${age}</td>
            </tr>
            <tr>
                <td>Birthplace</td>
                <td>${birthplace}</td>
            </tr>
             <tr>
                <td>Residence</td>
                <td>${residence}</td>
            </tr>
            <tr>
                <td>Height</td>
                <td>${height}</td>
            </tr>
            <tr>
                <td>Weight</td>
                <td>${weight}</td>
            </tr>
        </table>  
    </body>
</html>
