<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user registration</title>
    <style>
        .error {
            color: red
        }
    </style>
</head>
<body>

<h2> Welcome to registration page! </h2>

<form:form action="/user/processRegistration" modelAttribute="user">

    <br/>
    Enter email: <form:input placeholder="email" path="email"/>
    <form:errors path="email" cssClass="error"/> <span style="color: firebrick">${errMessage}</span>
    <br/>

    Enter Password: <form:password placeholder="password" path="password"/>
    <form:errors path="password" cssClass="error"/><br/>

    Enter name: <form:input placeholder="name" path="userName"/>
    <form:errors path="userName" cssClass="error"/><br/>

    Choose region: <form:select placeholder="region" path="region">
    <form:options items="${region}"/>
    <form:errors path="region"/></form:select><br/>

    Choose city: <form:select placeholder="city" path="city">
    <form:options items="${city}"/>
    <form:errors path="city"/></form:select><br/>

    Enter phone number: <form:input placeholder="phone number" path="phoneNumber"/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="sign up">
</form:form>
</body>
</html>