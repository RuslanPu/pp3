package servlet;

import model.User;
import services.UserJDBCService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        User user1 = new User("Ruslan","123sd","rusbrus94@mail.ru");
//        UserService.getInstance().addUser(user1);

        req.setAttribute("users", UserService.getInstance().getAllUsers());




        req.getRequestDispatcher("/page/index.jsp").forward(req, resp);

//        PrintWriter out = resp.getWriter();
//        out.print("<h1>Hello Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    }
}
