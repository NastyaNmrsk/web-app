<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.model.User" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crazy Users List</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
            border: solid;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<h2>${msg == null?'':msg}</h2>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>PASSWORD</th>
        <th>OFFICE</th>
        <th>ROLES</th>
        <th>ACTIVE</th>
        <th>LAST UPDATED</th>
        <th>CREATED</th>
        <th></th><!-- Stab -->
        <th></th><!-- Stab -->
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}
            </td>
            <td>${user.name}
            </td>
            <td>${user.email}
            </td>
            <td>${user.password()}
            </td>
            <td><a href="viewOfficeId=${user.office.id}">View Details</a></td>
            <td><a href="viewRolesList?userId=${user.id}">View Details</a></td>
            <td>${user.isActive}
            </td>
            <td>${user.getUpdatedTs}
            </td>
            <td>${user.getCreatedTs}
            </td>
            <td><a href='users?action=U&userId${user.id}'>UPDATE</a>
            </td>
            <td><a href='users?action=D&userId${user.id}'>DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href='users?action=U'>CREATE NEW USER</a>
<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
