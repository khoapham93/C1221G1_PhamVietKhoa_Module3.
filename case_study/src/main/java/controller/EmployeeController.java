package controller;

import dto.EmployeeDTO;
import models.*;
import service.*;
import service.impl.AcademicLevelServiceImpl;
import service.impl.DepartmentServiceImpl;
import service.impl.EmployeeServiceImpl;
import service.impl.PositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
                createEmployee(request, response);
                break;
            case "edit":
                editEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                goListEmployee(request, response);
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
                goCreateEmployee(request, response);
                break;
            case "edit":
                goEditEmployee(request, response);
                break;
            case "search":
                goSearchEmployee(request, response);
                break;
            case "view":
//                goDetailCustomer(request, response);
                break;
            default:
                goListEmployee(request, response);
        }
    }

    private void goSearchEmployee(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fieldSearch1 = request.getParameter("fieldSearch1");
            String searchKey1 = request.getParameter("searchKey1");
            String fieldSearch2 = request.getParameter("fieldSearch2");
            String searchKey2 = request.getParameter("searchKey2");
            String fieldSearch3 = request.getParameter("fieldSearch3");
            String searchKey3 = request.getParameter("searchKey3");
            List<EmployeeDTO> employeeDTOList = iEmployeeService.search(fieldSearch1, fieldSearch2, fieldSearch3, searchKey1, searchKey2, searchKey3);
            request.setAttribute("urlPath", "employee");
            request.setAttribute("employeeList", employeeDTOList);
            request.getRequestDispatcher("/view/employees/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void goEditEmployee(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Employee employee = iEmployeeService.findById(id);
        List<AcademicLevel> academicLevels = iAcademicLevelService.getList();
        List<Position> positions = iPositionService.getList();
        List<Department> departments = iDepartmentService.getList();
        if (employee != null) {
            try {
                request.setAttribute("employee", employee);
                request.setAttribute("urlPath", "employee");
                request.setAttribute("academicLevels", academicLevels);
                request.setAttribute("positions", positions);
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/view/employees/edit.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.setAttribute("message", "can't not find Employee");
                request.getRequestDispatcher("/employees").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
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

    private void goCreateEmployee(HttpServletRequest request, HttpServletResponse response) {
        List<AcademicLevel> academicLevels = iAcademicLevelService.getList();
        List<Position> positions = iPositionService.getList();
        List<Department> departments = iDepartmentService.getList();
        try {

            request.setAttribute("urlPath", "employee");
            request.setAttribute("academicLevels", academicLevels);
            request.setAttribute("positions", positions);
            request.setAttribute("departments", departments);
            request.getRequestDispatcher("/view/employees/create.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        LocalDate birthday = null;
        try {
            birthday = LocalDate.parse(request.getParameter("birthday"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Double salary = null;
        try {
            salary = Double.valueOf(request.getParameter("salary"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer positionId = null;
        try {
            positionId = Integer.valueOf(request.getParameter("position"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer academicId = null;
        try {
            academicId = Integer.valueOf(request.getParameter("academicLevel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer departmentId = null;
        try {
            departmentId = Integer.valueOf(request.getParameter("department"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee employee = new Employee(name, birthday, idCard, phone, email, address, salary, positionId, academicId, departmentId);

        Map<String, String> map = iEmployeeService.save(employee);

        if (map.isEmpty()) {
            try {
                request.setAttribute("message", "Customer was create successfully!");
                request.setAttribute("urlPath", "employee");
                request.getRequestDispatcher("/view/employees/create.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error", map);
            request.setAttribute("employee", employee);
            goCreateEmployee(request, response);

        }
    }

    private void editEmployee(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        LocalDate birthday = null;
        try {
            birthday = LocalDate.parse(request.getParameter("birthday"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Double salary = null;
        try {
            salary = Double.valueOf(request.getParameter("salary"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer positionId = null;
        try {
            positionId = Integer.valueOf(request.getParameter("position"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer academicId = null;
        try {
            academicId = Integer.valueOf(request.getParameter("academicLevel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer departmentId = null;
        try {
            departmentId = Integer.valueOf(request.getParameter("department"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee employee = new Employee(id, name, birthday, idCard, phone, email, address, salary, positionId, academicId, departmentId);

        Map<String, String> map = iEmployeeService.update(employee);

        if (map.isEmpty()) {
            request.setAttribute("message", "Update successfully!");
            goListEmployee(request, response);
        } else {
            request.setAttribute("error", map);
            goEditEmployee(request, response);
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        Integer id = null;
        try {
            id = Integer.valueOf(request.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (id != null) {
            boolean checkDelete = iEmployeeService.remove(id);
            if (!checkDelete) {
                try {
                    response.sendRedirect("/employees");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("message", "delete successfully!");
                goListEmployee(request, response);
            }
        } else {
            try {
                response.sendRedirect("/employees");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
