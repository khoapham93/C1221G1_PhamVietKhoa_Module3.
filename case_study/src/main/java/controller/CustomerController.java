package controller;

import dto.CustomerDTO;
import models.Customer;
import models.CustomerType;
import service.ICustomerService;
import service.ICustomerTypeService;
import service.impl.CustomerServiceImpl;
import service.impl.CustomerTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@WebServlet(name = "controller.CustomerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    private ICustomerService iCustomerService = new CustomerServiceImpl();
    private ICustomerTypeService iCustomerTypeService = new CustomerTypeServiceImpl();

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
                createCustomer(request, response);
                break;
            case "edit":
                editCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                goListCustomer(request, response);
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
                goCreateCustomer(request, response);
                break;
            case "edit":
                goEditCustomer(request, response);
                break;
            case "search":
                goSearchCustomer(request, response);
                break;
            case "view":
                goDetailCustomer(request, response);
                break;
            default:
                goListCustomer(request, response);
        }
    }

    private void goDetailCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("urlPath", "customer");

            request.getRequestDispatcher("/view/customers/detail.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void goListCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CustomerDTO> customerDTOList = iCustomerService.getList();
            request.setAttribute("urlPath", "customer");
            request.setAttribute("customerList", customerDTOList);
            request.getRequestDispatcher("/view/customers/list.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void goCreateCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
//            String value = null;
//            double b = value == null || value.isEmpty() ? Double.NaN : Double.parseDouble(value);
//            System.out.println(b);
//            System.out.println(Double.isNaN(b));

            //get list customer type
            List<CustomerType> customerTypes = iCustomerTypeService.getList();
            request.setAttribute("urlPath", "customer");
            request.setAttribute("types", customerTypes);
            request.getRequestDispatcher("/view/customers/create.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String customerCode = request.getParameter("customerCode");

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

        Integer customerType = null;
        try {
            customerType = Integer.valueOf(request.getParameter("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer gender = null;
        try {
            gender = Integer.valueOf(request.getParameter("gender"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer customer =
                new Customer(
                        name,
                        birthday,
                        idCard,
                        phone,
                        email,
                        address,
                        customerCode,
                        customerType,
                        gender);
        Map<String, String> map = iCustomerService.save(customer);
        if (map.isEmpty()) {
            try {
                request.setAttribute("message", "Customer was create successfully!");
                request.setAttribute("urlPath", "customer");
                request.getRequestDispatcher("/view/customers/create.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {

            request.setAttribute("error", map);
            request.setAttribute("customer", customer);

            goCreateCustomer(request, response);

        }
    }

    private void goEditCustomer(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Customer customer = iCustomerService.findById(id);
        List<CustomerType> customerTypes = iCustomerTypeService.getList();

        if (customer != null) {
            try {
                request.setAttribute("types", customerTypes);
                request.setAttribute("customer", customer);
                request.setAttribute("urlPath", "customer");
                request.getRequestDispatcher("/view/customers/edit.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.setAttribute("message", "can't not find customer");
                request.getRequestDispatcher("/customers").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String customerCode = request.getParameter("customerCode");

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

        Integer customerType = null;
        try {
            customerType = Integer.valueOf(request.getParameter("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer gender = null;
        try {
            gender = Integer.valueOf(request.getParameter("gender"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer customer =
                new Customer(
                        id,
                        name,
                        birthday,
                        idCard,
                        phone,
                        email,
                        address,
                        customerCode,
                        customerType,
                        gender);
        Map<String, String> map = iCustomerService.update(customer);
        if (map.isEmpty()) {
            request.setAttribute("message", "Update successfully!");
            goListCustomer(request, response);
        } else {
            request.setAttribute("error", map);
            goEditCustomer(request, response);
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {

        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean checkDelete = iCustomerService.remove(id);
        if (!checkDelete) {
            request.setAttribute("message", "Something's wrong, can't delete!");
            goListCustomer(request, response);
        } else {
            request.setAttribute("message", "delete successfully!");
            goListCustomer(request, response);
        }
    }

    private void goSearchCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fieldSearch1 = request.getParameter("fieldSearch1");
            String searchKey1 = request.getParameter("searchKey1");
            String fieldSearch2 = request.getParameter("fieldSearch2");
            String searchKey2 = request.getParameter("searchKey2");
            String fieldSearch3 = request.getParameter("fieldSearch3");
            String searchKey3 = request.getParameter("searchKey3");
            List<CustomerDTO> customerDTOList = iCustomerService.search(fieldSearch1, fieldSearch2, fieldSearch3, searchKey1, searchKey2, searchKey3);
            request.setAttribute("customerList", customerDTOList);
            request.setAttribute("urlPath", "customer");
            request.getRequestDispatcher("/view/customers/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
















