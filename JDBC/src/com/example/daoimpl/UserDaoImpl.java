package com.example.daoimpl;

import com.example.dao.UserDAO;
import com.example.db.DBConnection;
import com.example.model.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl implements UserDAO {

    @Override
    public User getUser(int id) {
        // DB connection
        Connection connection = DBConnection.getConnection();

        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_details WHERE user_id=" + id);

            if(rs.next())
            {
                //com.example.model.User user = new com.example.model.User();
                //user.setUserId( rs.getInt("id") );
                //user.setUsername( rs.getString("username") );
                //user.setFirstName( rs.getString("first_name") );
                //user.setLastName( rs.getString("Last_name") );
                //user.setPassword( rs.getString("pass") );
                //user.setGender( rs.getString("age") );
                //user.getStatus( rs.getInt("status") );
                //return user;

                // It's more convenient to make a separate method to extract user data from result set as we'd use it in many methods.
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Set<User> getAllUsers() {

        Connection connection = DBConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_details");

            Set users = new HashSet();

            while(rs.next())
            {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }

            return users;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User getUserByUserNameAndPassword() {
        // TODO: Try it yourself
        // Hint : Use 'AND' in your query
        return null;
    }

    @Override
    public boolean insertUser(User user) {

        Connection connection = DBConnection.getConnection();

        try {
            String query = "INSERT INTO user_details (username,first_name,last_name,gender,password,status) VALUES (?, ?, ?, ?, ?, ?)";
            // Passing Statement.RETURN_GENERATED_KEYS to make getGeneratedKeys() work
            PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getPassword());
            ps.setInt(6,user.getStatus());

            int i = ps.executeUpdate();

            if(i == 1) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // generatedKeys() checkout this method it's doing interesting job
                        System.out.println(generatedKeys.getInt(1));
                        user.setUserId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user_details SET username=?, first_name=?, last_name=?, gender=?, password=?, status=? WHERE user_id=?");

            ps.setString(1,user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getPassword());
            ps.setInt(6,user.getStatus());
            ps.setInt(7,user.getUserId());

            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        Connection connection = DBConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM user_details WHERE user_id=" + id);

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {

        User user = new User();

        user.setUserId( rs.getInt("user_id") );
        user.setUsername( rs.getString("username") );
        user.setFirstName( rs.getString("first_name") );
        user.setLastName( rs.getString("last_name") );
        user.setPassword( rs.getString("password") );
        user.setGender( rs.getString("gender") );
        user.setStatus( rs.getInt("status") );

        return user;
    }
}
