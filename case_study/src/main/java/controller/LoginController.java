package controller;

import models.Account;
import service.ILoginService;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private ILoginService iLoginService = new LoginServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        login(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("login.jsp").forward(request, response);

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "logout":
                request.getSession().removeAttribute("usernameSession");
                request.getRequestDispatcher("/common/login.jsp").forward(request, response);
                break;
            case "login":
            default:
                goLogin(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        Account account = iLoginService.checkLogin(username, password);

        if (account != null) {
            try {
                if (rememberMe != null) {
                    if (rememberMe.equals("on")) {
                        Cookie cookieUsername = new Cookie("cookieUsername", username);
                        Cookie cookiePassword = new Cookie("cookiePassword", password);
                        response.addCookie(cookieUsername);
                        response.addCookie(cookiePassword);
                    }
                }
                request.getSession().setAttribute("usernameSession", account.getName());
                request.getSession().setAttribute("roleSession", account.getRole());
                response.sendRedirect("/common");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.setAttribute("message", "username or password wrong, try again");
                request.setAttribute("username", username);
                request.getRequestDispatcher("/common/login.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void goLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies !=null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cookieUsername")) {
                        request.setAttribute("username", cookie.getValue());
                    }
                    if (cookie.getName().equals("cookiePassword")) {
                        request.setAttribute("password", cookie.getValue());
                    }
                }
            }
            request.getRequestDispatcher("/common/login.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
