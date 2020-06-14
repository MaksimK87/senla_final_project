<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>main page</title>
</head>
<body>
<h2>Private announcement platform</h2>

<form:form action="/user/showLoginForm">
    <input type="submit" value="log in">
</form:form><br/>

<form:form action="/user/showRegistrationForm">
    <input type="submit" value="registration">
</form:form>

<jsp:include page="announcementList.jsp"/>



<br/>


</body>
</html>
