<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <style>
        .error {
            color: red
        }
    </style>
    <style>
        .message {
            color: green
        }
    </style>
</head>
<body>
<h2 class="message">${successMessage}</h2>
<h2 class="error">${errMessage}</h2>

<form:form action="authenticateUser" method="post">

    <c:if test="${param.error != null}">

        <i class="error">Sorry! You entered invalid username/password.</i> <br/>


    </c:if>
<br/>
    Email: <input placeholder="email" name="username" type="text"/>
    <br/>
    Password: <input placeholder="password" name="password" type="password"/>

    <br/>
    <input type="submit" value="log in">

</form:form>
<br/>

<form:form action="/registration">
    <input type="submit" value="registration"/>
</form:form>


</body>
</html>