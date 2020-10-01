import java.sql.*;

public class JDBCExample {

    public static void main(String[] args){

        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/";
        String DB_NAME = "concordia";
        // Database credentials
        String DB_USER = "root";
        String DB_PASSWORD = "";

        Connection conn = null;
        Statement stmt = null;

        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT user_id, first_name, last_name, gender FROM user_details";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){

                // Retrieve by column name
                int id              = rs.getInt("user_id");
                String username     = rs.getString("first");
                String firstName    = rs.getString("first");
                String lastName     = rs.getString("last");
                String gender       = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Username: " + username);
                System.out.print(", First name: " + firstName);
                System.out.println(", Last name: " + lastName);
                System.out.println(", Gender: " + gender);

            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{

            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}