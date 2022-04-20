package controller;

import dto.CustomerDTO;
import dto.EmployeeDTO;
import service.*;
import service.impl.AcademicLevelServiceImpl;
import service.impl.EmployeeServiceImpl;
import service.impl.PositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeController", urlPatterns = "/employees")
public class EmployeeController extends HttpServlet {
    private IEmployeeService iEmployeeService = new EmployeeServiceImpl();
    private IAcademicLevelService iAcademicLevelService = new AcademicLevelServiceImpl();
    private IPositionService iPositionService = new PositionServiceImpl();
    private IDepartmentService iDepartmentService = new DepartmentServiceImpl();

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
//                createCustomer(request, response);
                break;
            case "edit":
//                editCustomer(request, response);
                break;
            case "delete":
//                deleteCustomer(request, response);
            default:
//                goListCustomer(request, response);
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
//                goCreateCustomer(request, response);
                break;
            case "edit":
//                goEditCustomer(request, response);
                break;
            case "search":
//                goSearchCustomer(request, response);
                break;
            case "view":
//                goDetailCustomer(request, response);
                break;
            default:
                goListEmployee(request, response);
        }
    }

    private void goListEmployee(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<EmployeeDTO> employeeDTOList = iEmployeeService.getList();
            request.setAttribute("urlPath", "employee");
            request.setAttribute("employeeList", employeeDTOList);
            request.getRequestDispatcher("/view/employees/list.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
