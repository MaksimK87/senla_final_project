<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<br/>

<table>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone number</th>
        <th>Role</th>
        <th>Rating</th>
        <th>Region</th>
        <th>City</th>
    </tr>

    <c:forEach var="user" items="${users}">


        <c:url var="deleteUser" value="/admin/deleteUser">
            <c:param name="userId" value="${user.idUser}" />
        </c:url>


        <tr>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.role}</td>
            <td>${user.rating}</td>
            <td>${user.region}</td>
            <td>${user.city}</td>

            <td>

                <a href="${deleteUser}">Delete user</a>
                |

            </td>

        </tr>

    </c:forEach>

</table>
<a href="/user/showMainPage"> Go to admin main page</a>   <%--home page!!!!--%>
<br/>
</body>
</html>
