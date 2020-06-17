<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <title>user page</title>
</head>

<body>
Welcom to profile page,${user.userName}!
<h2 class="message">${successMessage}</h2>
<h2 class="error">${errMessage}</h2>

<br/>

Edit current profile:
<br/>
<form:form action="/user/editUser">
    <input type="submit" value="edit profile"/>
</form:form>
<br/>

Add new announcement:
<br/>
<form:form action="/announcement/showNewAnnouncementForm">
    <input type="submit" value="new announcement"/>
</form:form>
<br/>


Show announcement history:
<form:form action="/announcement/showAnnouncementHistory">
    <input type="submit" value="show announcement history"/>
</form:form>
<br/>

Show active announcements:
<br/>
<form:form action="/announcement/showActiveUserAnnouncement">
    <input type="submit" value="show active announcements"/>
</form:form>
<br/>
<jsp:include page="announcementList.jsp"/>
</body>
</html>
