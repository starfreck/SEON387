import com.example.db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "StoreServlet")
public class StoreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get all params from request
        String userName     = request.getParameter("username");
        String firstName    = request.getParameter("first_name");
        String lastName     = request.getParameter("last_name");
        String gender       = request.getParameter("gender");
        String password     = request.getParameter("password");
        // To make user activate by default
        String status       = "1";


        try {

            // Initialize the database
            Connection con = DBConnection.getConnection();

            // SQL query
            String query = "insert into user_details (username,first_name,last_name,gender,password,status) values(?, ?, ?, ?, ?, ?)";

            // Create a SQL query to insert data into user_details table
            PreparedStatement st = con.prepareStatement(query);

            // For the first parameter,
            // sets the data to st pointer
            st.setString(1,userName);
            // Same for second parameter
            st.setString(2, firstName);
            // Same for rest of the parameters
            st.setString(3, lastName);
            st.setString(4, gender);
            st.setString(5, password);
            st.setInt(6,1);

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer
            // to display the successful result
            response.getWriter().println("<html><body><b>Successfully Inserted"+"</b></body></html>");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
