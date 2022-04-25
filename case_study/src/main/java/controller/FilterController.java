package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FilterController", value = "/*")
public class FilterController implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login"; // http://localhost:8080/login
        String employeeURI = request.getContextPath() + "/employees";

        boolean loggedIn = session != null && session.getAttribute("usernameSession") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        boolean employeeRequest = request.getRequestURI().equals(employeeURI);
        boolean havePermission = session != null &&
                ("admin".equals(session.getAttribute("roleSession"))
                        || "manager".equals(session.getAttribute("roleSession")));

        if (loggedIn || loginRequest) {
            if (employeeRequest) {
                if (havePermission) {
                    chain.doFilter(request, response);
                } else {
                    response.sendRedirect("/common/access_denied.jsp");
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            response.sendRedirect(loginURI);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}