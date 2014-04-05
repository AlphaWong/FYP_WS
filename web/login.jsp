<%-- 
    Document   : login
    Created on : Jan 12, 2014, 3:25:47 PM
    Author     : Alpha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/loginPage.css">
    </head>
    <body>
                    
<table id="login">
    <tr><td><label for="userID">user-ID : </label></td><td><input type="text" id="userID" name="userID" form="loginForm" required/></td></tr>
            <tr><td><label for="userPassword">Password : </label></td><td><input type="password" id="userPassword" name="userPassword" form="loginForm" required/></td></tr>
            <tr><td></td>
                <td><input type="submit" value="Login" form="loginForm" style="width: 100%;" id="loginBtn"/></td>
            </tr>
</table>
        <form method="POST" id="loginForm" action="DoLoginServlet"> 
            <input type="hidden" name="action" value="login"/>
        </form>
    </body>
</html>
