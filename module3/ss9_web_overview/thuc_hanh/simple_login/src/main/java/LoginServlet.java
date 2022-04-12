import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        PrintWriter writer =    response.getWriter();
        writer.println("<html>");
        if ("admin".equals(userName) && "admin".equals(passWord)){
            writer.println("<h1> Welcome "+userName+" to website</h1>" );
        }else {
            writer.println("<h1> Login error! </h1>");
        }
        writer.println("</html>");
    }
}
