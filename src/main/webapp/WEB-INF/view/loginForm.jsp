<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<form:form action="/user/processLoginForm" method="get">

    Email: <input placeholder="email" name="email" type="text"/>
    <br/>
    Password: <input placeholder="password" name="password" type="password"/>

    <br/>
    <input type="submit" value="log in">

</form:form>
<br/>

<form:form action="/user/showRegistrationForm">
    <input type="submit" value="registration"/>
</form:form>


</body>
</html>