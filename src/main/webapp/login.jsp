<%--
  Created by IntelliJ IDEA.
  User: motoc
  Date: 19.11.2017
  Time: 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Singin</title>
</head>
<body>
<a>Login form</a>
<form action="login" method="post">
    <label>Nickname</label>
    <input type="text" name="nickname"><a>${nullNickname}</a>
    <br>
    <label>Password</label>
    <input type="password" name="password"><a>${nullPassword}</a>
    <br>
    <input type="submit" value="отправить">
    <br>
    ${registration}
</form>
</body>
</html>
