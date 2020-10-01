import com.example.daoimpl.UserDaoImpl;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "StoreWithDAOServlet")
public class StoreWithDAOServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDaoImpl userImpl = new UserDaoImpl();

        // Get all params from request and store in User Obj
        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));
        user.setGender(request.getParameter("gender"));
        user.setPassword(request.getParameter("password")); // TODO: Encrypt password before passing here (Good Practice ;)
        // To make user activate by default
        user.setStatus(1);
        // Store user in DB
        if( userImpl.insertUser(user)){
            response.getWriter().println("<html><body><b>"+
                "The new user with username "+user.getUsername()+" is successfully stored with user_id "+user.getUserId()
                +".</b></body></html>");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
