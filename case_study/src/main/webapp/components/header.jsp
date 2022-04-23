<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class=" px-3 navbar navbar-light bg-light ">
    <a class="navbar-brand" href="#">
        <img src="https://furamavietnam.com/wp-content/uploads/2018/10/logo.png" height="65" width="50" alt="">
    </a>
    <div class="orm-inline my-2 my-lg-0">
        <c:if test="${empty sessionScope.usernameSession}">
            <form action="/login" method="post">
                <input type="hidden" name="action" value="login">
                <button type="submit" class="btn btn-outline-success">Login</button>
            </form>

        </c:if>
        <c:if test="${not empty sessionScope.usernameSession}">
            <form class="form-inline" action="/login">
                <div class="form-group mb-2">
                    <a class="navbar-brand" href=""> ${sessionScope.usernameSession}
                        <span class="sr-only">(current)</span>
                    </a>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-outline-info">Logout</button>
                    <input type="hidden" name="action" value="logout">
                </div>
            </form>
        </c:if>
    </div>
</header>