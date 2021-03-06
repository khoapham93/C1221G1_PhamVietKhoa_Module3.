package controller;

import model.Product;
import service.IProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products", "/"})
public class ProductServlet extends HttpServlet {

    private IProductService iProductService = new ProductServiceImpl();

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
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                goListProduct(request, response);
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
                goCreateProduct(request, response);
                break;
            case "edit":
                goEditProduct(request, response);
                break;
            case "search":
                goSearchProduct(request, response);
                break;
            case "view":
                goDetailProduct(request,response);
                break;
            default:
                goListProduct(request, response);
        }

    }

    private void goSearchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("searchKey").toLowerCase();
        if ("".equals(keyWord)) {
            response.sendRedirect("/");
        } else {
            List<Product> products = iProductService.search(keyWord);
            request.setAttribute("products", products);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }

    }

    private void goEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Product product = iProductService.findByid(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void goListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = iProductService.getList();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void goCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/create.jsp").forward(request, response);

    }
    private void goDetailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Product product = iProductService.findByid(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        Double price = null;
        try {
            price = Double.valueOf(request.getParameter("price"));

        } catch (Exception e){
            e.printStackTrace();
        }

        String description = request.getParameter("description");
        String manufacturer = request.getParameter("manufacturer");
        Integer id = (int) (Math.random() * 10000);

        Product product = new Product(id, name, price, description, manufacturer);

        Map<String, String> map = iProductService.save(product);
        if (map.isEmpty()) {
            request.setAttribute("message", "Product was create successfully!");
            request.getRequestDispatcher("create.jsp").forward(request, response);
        } else {
            request.setAttribute("error", map);
            request.getRequestDispatcher("create.jsp").forward(request, response);
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");

        Double price = null;
        try {
            price = Double.valueOf(request.getParameter("price"));

        } catch (Exception e){
            e.printStackTrace();
        }
        String description = request.getParameter("description");
        String manufacturer = request.getParameter("manufacturer");

        Product product = new Product(id, name, price, description, manufacturer);

        Map<String, String> map = iProductService.save(product);
        if (map.isEmpty()) {
            response.sendRedirect("/");
        } else {
            request.setAttribute("error", map);
            request.setAttribute("product", product);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        iProductService.remove(id);
        response.sendRedirect("/");
    }

}
