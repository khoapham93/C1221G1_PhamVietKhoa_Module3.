<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class=" px-5 navbar navbar-expand-lg navbar-dark bg-dark fs-5">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item h5">
                <a class="nav-link ${urlPath eq 'home' or empty urlPath  ? 'active' : ''}" href="/index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item h5">
                <a class="nav-link ${urlPath eq 'employee' ? 'active' : ''}" href="/employees">Employee</a>
            </li>
            <li class="nav-item h5">
                <a class="nav-link ${urlPath eq 'customer' ? 'active' : ''}" href="/customers">Customer</a>
            </li>
            <li class="nav-item h5">
                <a class="nav-link ${urlPath eq 'service' ? 'active' : ''}" href="/service">Service</a>
            </li>
            <li class="nav-item h5">
                <a class="nav-link ${urlPath eq 'contract' ? 'active' : ''}" href="/contract">Contract</a>
            </li>
        </ul>
    </div>
</nav>