package com.example.dao;

import com.example.model.User;

import java.sql.SQLException;
import java.util.Set;

// com.example.dao.DAO (Data Access Object) can do CRUD operations, it can Create, Retreive, Updata, Delete from our table.
public interface DAO {

    User getUser(int id) throws SQLException;

    Set<User> getAllUsers();

    User getUserByUserNameAndPassword();

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);
}