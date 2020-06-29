<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>new announcement</title>
    <style>
        .error {
            color: red
        }
    </style>
</head>
<body>

<h2>Create new announcement:</h2>
<form:form action = "/announcement/processNewAnnouncement" modelAttribute="announcement">
    <table>
        <tr>
            <td><form:label path = "header">Enter header:</form:label></td>
            <td><form:input path = "header" /></td>
        </tr>
        <tr>
            <td><form:label path = "description">Enter description:</form:label></td>
            <td><form:textarea path = "description" rows = "5" cols = "30"/></td>
        </tr>
        <tr>
            <td><form:label path = "announcementCategory.idCategory">Choose category:</form:label></td>
            <td>
                <form:select path = "announcementCategory.idCategory">
                   <%-- <form:option value = "NONE" label = "Select"/>--%>
                    <form:options items = "${categories}" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path = "itemPrice">Enter price (BYN):</form:label></td>
            <td><form:input path = "itemPrice" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "save"/>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
