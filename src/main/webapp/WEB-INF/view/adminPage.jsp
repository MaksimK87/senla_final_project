<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>admin page</title>
    </head>

<body>



<h2>Welcome to administrator's account, ${user.userName}! </h2>
<br/>

<h2 class="message">${successMessage}</h2>
<h2 class="error">${errMessage}</h2>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin page</title>
Available operations:
<br/>
Add new administrator:
<br/>
<form:form action="/user/showAdminRegistrationForm">
    <input type="submit" value="add new administrator"/>
</form:form>
<br/>
Edit current profile:
<br/>
<form:form action="/user/editUser">
    <input type="submit" value="edit profile"/>
</form:form>
<br/>
Show all users:
<form:form action="/user/showAllUsers">
    <input type="submit" value="show all users"/>
</form:form>
<br/>


Delete admin profile:
<br/>
<form:form action="/user/deleteAdmin">
    <input type="submit" value="delete admin profile"/>
</form:form>
<br/>

</body>
</html>
