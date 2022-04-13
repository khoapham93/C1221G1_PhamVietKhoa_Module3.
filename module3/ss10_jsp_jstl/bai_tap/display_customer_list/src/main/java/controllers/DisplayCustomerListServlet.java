package controllers;

import models.Customer;
import models.CustomerType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DisplayCustomerListServlet", urlPatterns = "/customer-servlet")
public class DisplayCustomerListServlet extends HttpServlet {
    private List<Customer> customerList;
    private List<CustomerType> customerTypeList;

    @Override
    public void init() throws ServletException {
        customerList = new ArrayList<>();
        customerList.add(new Customer("Khoa", "01/01/1993", 1, 8, "Quảng Nam", "https://picsum.photos/536/354", 1));
        customerList.add(new Customer("Tuan","01/01/1995", 1, 8.5, "Đà Nẵng","https://picsum.photos/536/354", 2));
        customerList.add(new Customer("Trinh","01/01/1999", 0, 7, "Quang Ngai","https://picsum.photos/536/354", 3));
        customerList.add(new Customer("Luat","01/01/2002", 1, 5, "Quang Nam","https://picsum.photos/536/354", 4));
        customerList.add(new Customer("Trung","01/01/2000", 1, 4, "Hue","https://picsum.photos/536/354", 1));
        customerList.add(new Customer("Hong","01/01/2000", 0, 3, "Quang Nam","https://picsum.photos/536/354", 2));
        customerList.add(new Customer("Da","01/01/1999", 1, 5, "Quang Nam","https://picsum.photos/536/354", 3));

        customerTypeList = new ArrayList<>();
        customerTypeList.add(new CustomerType(1,"Diamond"));
        customerTypeList.add(new CustomerType(2,"Platinum"));
        customerTypeList.add(new CustomerType(3,"Gold"));
        customerTypeList.add(new CustomerType(4,"Silver"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customerList",customerList);
        request.setAttribute("customerTypeList",customerTypeList);
        request.getRequestDispatcher("display_customer.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customerList",customerList);
        request.setAttribute("customerTypeList",customerTypeList);
        request.getRequestDispatcher("display_customer.jsp").forward(request,response);
    }
}
