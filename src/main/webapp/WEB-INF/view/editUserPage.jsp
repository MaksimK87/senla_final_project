<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
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
    <title>edit profile</title>
</head>

<body>
<h2>Edit user's profile:</h2>
<br/>

<h2 class="message">${successMessage}</h2>
<h2 class="error">${errMessage}</h2>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<form:form action="/user/saveProfile" modelAttribute="user">

    <br/>

    Enter Password: <form:input field="${user.password}" path="password"/>
    <form:errors path="password" cssClass="error"/><br/>

    Enter name: <form:input field="${user.userName}" path="userName"/>
    <form:errors path="userName" cssClass="error"/><br/>

    Choose region: <form:select placeholder="${user.region}" path="region">
    <form:options items="${region}"/>
    <form:errors path="region"/></form:select><br/>

    Choose city: <form:select placeholder="${user.city}" path="city">
    <form:options items="${city}"/>
    <form:errors path="city"/></form:select><br/>

    Enter phone number: <form:input placeholder="${user.phoneNumber}" path="phoneNumber"/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="save">
</form:form>
</body>
</html>
