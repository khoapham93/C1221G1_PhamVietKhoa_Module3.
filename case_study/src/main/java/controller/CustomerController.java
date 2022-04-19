package controller;

import dto.CustomerDTO;
import service.ICustomerService;
import service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controller.CustomerController",urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    private ICustomerService iCustomerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
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
//                goCreateUser(request, response);
                break;
            case "edit":
//                goEditUser(request, response);
                break;
            case "search":
//                goSearchUser(request, response);
                break;
            case "view":
//                goDetailUser(request,response);
                break;
            default:
                goListUser(request, response);
        }
    }

    private void goListUser(HttpServletRequest request, HttpServletResponse response) {
        List<CustomerDTO> customerDTOList = iCustomerService.getList();
        request.setAttribute("customerList",customerDTOList);
        request.setAttribute("/customers/list.jsp",customerDTOList);
    }
}
