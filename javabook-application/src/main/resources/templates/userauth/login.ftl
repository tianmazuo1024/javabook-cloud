<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>用户登录</title>
</head>
<body>
<form action="/login">
    <table>
        <tr>
            <td>用户名/手机号/邮箱</td>
            <td><input type="text" name="identifier" placeholder="用户名/手机号/邮箱" autofocus></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="credential"></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">登录</button></td>
        </tr>
    </table>
</form>
<hr>
<!-- 链接地址 -->
<a href="https://github.com/login/oauth/authorize?client_id=14aff5ecbaaa4eca2ec4
&redirect=http://localhost:9527/oauth/redirect">GitHub登录</a>
<br>
<!-- 二维码图片地址 -->
<img src="https://qr.api.cli.im/newqr/create?data=https%253A%252F%252Fgithub.com
%252Flogin%252Foauth%252Fauthorize%253Fclient_id%253D14aff5ecbaaa4eca2ec4%2526redirect
%253Dhttp%253A%252F%252Flocalhost%253A9527%252Foauth%252Fredirect&level=H&transparent=false&
bgcolor=%23FFFFFF&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&logoshape=no&
size=260&kid=cliim&key=4d7a65b452977d2dc76e0b7929974f4a" />
</body>
</html>
