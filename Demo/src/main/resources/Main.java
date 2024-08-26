package add;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class AddNumbers extends HttpServlet {

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        int num1=Integer.parseInt(req.getParameter(num1));
        int num2=Integer.parseInt(req.getParameter(num2));
        System.out.println(num1+num2);
    }
}
