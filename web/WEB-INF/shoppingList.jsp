<%-- 
    Document   : shoppingList
    Created on : Feb 13, 2023, 10:45:17 AM
    Author     : Majid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello,${user.username} 

        
        <br>
        <h2>List</h2>
        <br>

        <form method="post">
            <label>Add item:</label>
            <input type="text" name="itemNew" value="${itemNew}" >
            <input type="submit"  value="add" >
            <input type="hidden" name="action" value="add">
        </form>   



        <form method="post">
            <c:if test="${itemsList.size()>0}">
                <ul>
                    <c:forEach items="${itemsList}" var="item">
                        <li><input type="radio" name="itemDelete" value=${item}>${item}</li>
                    </c:forEach>
                </ul>
                <input type="submit" value="Delete" >
                <input type="hidden" name="action" value="delete">
            </c:if>
        </form>            
            <br>
            <br>
            <br>
            
            <form method="get">
            <input type="submit" value="Logout" >
            <input type="hidden" name="action" value="logout">           
        </form>
    </body>
</html>