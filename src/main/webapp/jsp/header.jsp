<%@ page import="org.example.model.User" %><%--
  Created by IntelliJ IDEA.
  User: sharlan_a
  Date: 16.01.2023
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<div class="topnav">
    <a class="active" href="#home">Home</a>
    <a href="#news">News</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
    %>
    <a href="login">Login</a> <%} else { %>
    <h2><%=user.getEmail()%></h2>
    <a href="logout">Logout</a>
    <% }%>

</div>

<div style="padding-left:16px">
    <h2>Top Navigation Example</h2>
    <p>Some content..</p>
</div>

</body>

