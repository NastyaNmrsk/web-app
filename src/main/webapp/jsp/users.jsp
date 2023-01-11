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
    </tr>
    <% Set<User> users = (Set<User>) request.getAttribute("users");
    <% for (User user : users) {%>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td>TODO</td>
        <td>TODO</td>
        <td><%=user.isActive() ? "Yes" : "No"%>
        </td>
        <td><%=user.getUpdatedTs()%>
        </td>
        <td><%=user.getCreatedTs()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
