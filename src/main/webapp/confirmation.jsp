<%-- 
    Document   : confirmation
    Created on : 01-Feb-2023, 4:09:14 pm
    Author     : vinaychowdarykatta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Confirmation Page</title>
    </head>
    <body>
        <h1>You have successfully created Staff member record!</h1>
        <ul>
            <li>${requestScope.staff.staffId}</li>
            <li>${requestScope.staff.firstName}</li>
            <li>${requestScope.staff.lastName}</li>
            <li>${requestScope.staff.email}</li>
            <li>${requestScope.staff.storeId}</li>
            <li>${requestScope.staff.addressId}</li>
            <li>${requestScope.staff.username}</li>
            <li>${requestScope.staff.lastUpdate}</li>
        </ul>
    </body>
</html>
