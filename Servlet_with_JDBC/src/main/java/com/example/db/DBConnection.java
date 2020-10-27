package com.example.db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

    // NOTE: It'd be better to get them from a properties or XML configuration file
    // Read more here : http://wiki.metawerx.net/wiki/Context.xml

    // JDBC driver name and database URL
    // static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Note : class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
    // static String DB_URL = "jdbc:mysql://localhost:3306/";
    // static String DB_NAME = "concordia";
    // Database credentials
    // static String DB_USER = "root";
    // static String DB_PASSWORD = "";

    static Connection conn = null;

    public static Connection getConnection() {

        try{
            // Get DataSource
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
            // Get Connection and return
            return ds.getConnection();

            //Register JDBC driver
            //Class.forName(JDBC_DRIVER);
            //Open a connection
            //System.out.println("Connecting to database...");
            //conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);
            //return conn;

        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database",e);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection() throws SQLException{
        //Close connection
        if(conn!=null) conn.close();
    }
}