package servlet;

import model.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.valueOf(request.getParameter("id")).longValue();

            User user = UserService.getInstance().getUserById(id);

            if(user!=null) {
                request.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/page/edit.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("page/notfound.jsp").forward(request, response);
            }
        }
        catch(Exception ex) {
            request.getRequestDispatcher("page/notfound.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.valueOf(request.getParameter("id")).longValue();
            String name = request.getParameter("name");
            String pass = request.getParameter("password");
            String email = request.getParameter("email");
            UserService service = UserService.getInstance();
            User user = new User(service.getUserById(id).getId(),service.getUserById(id).getName(),service.getUserById(id).getPass());
            service.update(user,name, pass,email);
            response.sendRedirect(request.getContextPath() + "/index");
        }
        catch(Exception ex) {

            request.getRequestDispatcher("page/notfound.jsp").forward(request, response);
        }
    }
}
