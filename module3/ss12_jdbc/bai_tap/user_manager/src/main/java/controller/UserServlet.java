package controller;

import model.User;
import service.IUserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = {"/users", "/"})
public class UserServlet extends HttpServlet {

    private IUserService iUserService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                goListUser(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                goCreateUser(request, response);
                break;
            case "edit":
                goEditUser(request, response);
                break;
            case "search":
                goSearchUser(request, response);
                break;
            case "view":
                goDetailUser(request,response);
                break;
            default:
                goListUser(request, response);
        }
    }

    private void goSearchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("searchKey").toLowerCase();
        if ("".equals(keyWord)) {
            response.sendRedirect("/");
        } else {
            List<User> users = iUserService.search(keyWord);
            request.setAttribute("users", users);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }

    }

    private void goEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = iUserService.findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void goListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = iUserService.getList();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void goCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/create.jsp").forward(request, response);

    }
    private void goDetailUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = iUserService.findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }
    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(name, email, country);

        Map<String, String> map = iUserService.save(user);

        if (map.isEmpty()) {
            request.setAttribute("message", "Product was create successfully!");
            request.getRequestDispatcher("create.jsp").forward(request, response);
        } else {
            request.setAttribute("error", map);
            request.getRequestDispatcher("create.jsp").forward(request, response);
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id, name, email, country);

        Map<String, String> map = iUserService.update(user);

        if (map.isEmpty()) {
            response.sendRedirect("/");
        } else {
            request.setAttribute("error", map);
            request.setAttribute("user", user);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean checkDelete = iUserService.remove(id);
        if (!checkDelete){
            try {
                request.setAttribute("message", "Something's wrong, can't delete!");
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else {
            try {
                request.setAttribute("message", "delete successfully!");
                goListUser(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

}
