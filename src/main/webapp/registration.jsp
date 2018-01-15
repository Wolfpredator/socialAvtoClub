<%--
  Created by IntelliJ IDEA.
  User: motoc
  Date: 31.10.2017
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1> Привет ${username}</h1>
<form action="registration" method="post">
    <label>Nickname</label>
    <input type="text" name="nickname" value="${nickname}"><a>${null_nickname}</a>
    <br>
    <label>Name</label>
    <input type="text" name="name"><a>${null_name}</a>
    <br>
    <label>Surname</label>
    <input type="text" name="surname"><a>${null_surname}</a>
    <br>
    <label>Password</label>
    <input type="password" name="password"><a>${null_password}</a>
    <br>
    <input type="submit">
</form>

</body>
</html>
