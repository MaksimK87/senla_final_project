<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>main page</title>
</head>
<body>
<h2>Private announcement platform</h2>

<c:if test="${isAuth=false}">

    <form:form action="/login">
        <input type="submit" value="log in">
    </form:form><br/>

    <form:form action="/registration">
        <input type="submit" value="registration">
    </form:form>

</c:if>


<jsp:include page="announcementList.jsp"/>



<br/>


</body>
</html>
