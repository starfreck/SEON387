import com.example.daoimpl.UserDaoImpl;
import com.example.model.User;

import java.util.Set;

public class JDBCWithDAOExample {

    public static void main(String[] args) {

            // Create com.example.daoimpl.UserDaoImpl Obj to perform actions on user_details table
            UserDaoImpl userImpl = new UserDaoImpl();


            // 1. Create a new com.example.model.User and Store in DB
            // Creating a new user
            User u1 = new User();
            u1.setUsername("JohnDoe56");
            u1.setFirstName("John");
            u1.setLastName("Doe");
            u1.setGender("Male");
            u1.setPassword("$password$");  // TODO: Encrypt your password before passing here {Good practice ;)}
            u1.setStatus(1); // Keep com.example.model.User active by default

            // Store user in DB
            if(userImpl.insertUser(u1)){
                System.out.println("The new user with username "+u1.getUsername()+" is successfully stored.");
            }

            // 2. Update newly Created com.example.model.User
            // Update Username of John
            u1.setUsername("jo_doe");
            if(userImpl.updateUser(u1)){
                System.out.println("The user with id "+u1.getUserId()+" is successfully updated.");
            }

            //3. Find user by id
            User u2 = userImpl.getUser(1);
            System.out.println("The username with user_id 1 is "+u2.getUsername());

            // Delete com.example.model.User
            if(userImpl.deleteUser(2)){
                System.out.println("The user with user_id 2 is successfully deleted.");
            }

            // Get All Users
            Set<User> users= userImpl.getAllUsers();
            System.out.println();
            for (User u:users) {
                System.out.println(u.getUsername());
            }
    }
}
