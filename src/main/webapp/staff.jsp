<%-- 
    Document   : staff.jsp
    Created on : 01-Feb-2023, 6:27:24 am
    Author     : vinaychowdarykatta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Staff Entry Page</title>
        <link rel="stylesheet" href="css/staff_entry.css">
    </head>
    <body>
        <c:if test="${not empty requestScope.violations}">
            <p>There were problems validating user input</p>
            <ul>
                <c:forEach items="${requestScope.violations}" var="error">
                    <li>
                        There is an issue with ${error.propertyPath}: ${error.message}
                    </li>
                
                </c:forEach>
            </ul>  
        </c:if>        
        
        
        <form method="post" action="/itmd4515-s23-fp-vkatta316/staff">
            <h1>New Staff Entry</h1>
            <div class ="form-signin">
                  
                <div>
                    <label>Staff ID:</label>
                    <input class="form-control" type ="text" id="staffId" name="staffId" value="${requestScope.staff.staffId}" />
                </div>
                <div>
                    <label>First Name:</label>
                    <input class="form-control" type ="text" id="firstName" name="firstName" value="${requestScope.staff.firstName}" />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input class="form-control" type ="text" id="lastName" name="lastName" value="${requestScope.staff.lastName}"/>
                </div>
                <div>
                    <label>Email:</label>
                    <input class="form-control" type ="text" id="email" name="email" value="${requestScope.staff.email}"/>
                </div>
                <div>
                    <label>Address Id:</label>
                    <input class="form-control" type ="text" id="addressId" name="addressId" value="${requestScope.staff.addressId}"/>
                </div>
                 <div>
                    <label>Username:</label>
                    <input class="form-control" type ="text" id="userName" name="userName" value="${requestScope.staff.username}"/>
                </div>
                <div>
                    <label>Store Id:</label>
                    <select class="form-control"  id="storeId" name="storeId">
                        <option value = "1" ${requestScope.staff.storeId eq 1 ? 'selected': ''}>One</option>
                        <option value = "2" ${requestScope.staff.storeId == 2 ? 'selected': ''}>Two</option>
                    </select>
                </div>
                <div>
                    <label>Date:</label>
                    <input class="form-control" type ="date" id="exDate" name="exDate" value="${requestScope.staff.lastUpdate}"/>
                </div>
                <div>
                    <button class="btn btn-primary" type="submit">Create</button>
                </div>
                
            </div>
        </form>
    </body>
</html>
