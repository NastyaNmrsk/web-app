<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: st
  Date: 11.01.2023
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- #1 Declaration -->
<%Date serverDate = new Date();%>
<!-- #2 Scriptlet -->
<%
    // start Java logic

    System.out.println("Test Scriptlet " + serverDate);
    for (int i = 0; i < 5; i++) {

%><h4><%= i%>
</h4><%}%>
<!-- #3 Expression -->
<h2><%= serverDate %>
</h2>
</body>
</html>
