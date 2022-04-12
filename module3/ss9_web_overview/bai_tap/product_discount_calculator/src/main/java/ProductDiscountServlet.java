import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductDiscountServlet", urlPatterns = "/product_discount")
public class ProductDiscountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        double discountAmount = price * discount * 0.01;
        double priceAfterDiscount = price - discountAmount;

        request.setAttribute("nameProduct",product);
        request.setAttribute("price",price);
        request.setAttribute("discountAmount",discountAmount);
        request.setAttribute("priceAfterDiscount",priceAfterDiscount);

        request.getRequestDispatcher("result.jsp").forward(request,response);
    }
}
