<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>用户注册</title>
</head>
<body>
<form action="/register">
    <table>
        <tr>
            <td>用户名</td><td><input type="text" name="username" autofocus></td>
        </tr>
        <tr>
            <td>密码</td><td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>手机号</td><td><input type="text" name="mobile"></td>
        </tr>
        <tr>
            <td>邮箱</td><td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>姓名</td><td><input type="text" name="realname"></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">注册</button></td>
        </tr>
    </table>
</form>
</body>
</html>
