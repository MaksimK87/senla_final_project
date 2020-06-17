<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Announcements</title>
</head>
<body>
<br/>

<table>
    <tr>
        <th>Head</th>
        <th>Price</th>
        <th>Creation date</th>
        <th>City</th>
        <th>Details</th>
    </tr>
    <tr><td><h2>Announcements in top list:</h2></td></tr>
    <c:forEach var="announcementTop" items="${top}">

        <c:url var="open" value="/announcement/openDetails">
            <c:param name="idAnnouncement" value="${announcementTop.idAnnouncement}"/>
        </c:url>


        <tr>
            <td>${announcementTop.header}</td>
            <td>${announcementTop.itemPrice}</td>
            <td>${announcementTop.creationDate.time} </td>
            <td>${user.city}</td>

            <td>

                <a href="${open}">Open details</a>
                |

            </td>

        </tr>

    </c:forEach>

    <tr><td><h2>Other announcements:</h2></td></tr>
    <br/>
    <c:forEach var="announcement" items="${nonTop}">


        <c:url var="open" value="/announcement/openDetails">
            <c:param name="idAnnouncement" value="${announcement.idAnnouncement}"/>
        </c:url>


        <tr>
            <td>${announcement.header}</td>
            <td>${announcement.itemPrice}</td>
            <td>${announcement.creationDate.time}</td>
            <td>${user.city}</td>

            <td>

                <a href="${open}">Open details</a>
                |

            </td>

        </tr>

    </c:forEach>

</table>
<br/>
</body>
</html>
