<!DOCTYPE HTML>
<html>
<style type="text/css">
    .login {
        height: 180px;
        width: 300px;
        margin: 0;
        padding: 10px;
        border: 1px #CCC solid;
    }

    .login input {
        padding: 5px;
        margin: 5px auto;
    }

</style>
<body>
<form method="get" action="/login">
    <div class="login">
        <h2>Login</h2>
        <input type="text" name="userName" size="30" placeholder="username"/>
        <input type="password" name="passWord" size="30" placeholder="password"/>
        <input type="submit" value="Sign in"/>
    </div>
</form>
</body>
</html>