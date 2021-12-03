package com.turning.turningworkshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler extends Config {
    Connection dbConnection;
    public Connection getdbConnection() throws ClassNotFoundException, 
            SQLException {
        String connectionLine = "jdbc:mysql://" + dbhost + ":" + dbport
                + "/" + dbname;
        System.out.println("connection" + connectionLine);
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionLine, dbuser, 
                dbpass);
        
        return dbConnection;
    }
    
    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + 
            Const.USER_LOGIN + ", " + Const.USER_PASS + ", " + Const.USER_SALARY
                + ", " + Const.USER_ROLE + ") values (?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(insert);
            
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setInt(3, (int) Math.round(25000 + Math.random() * 5000));
            prSt.setInt(4, 3);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    public ResultSet getAllUsers() throws ClassNotFoundException, SQLException {
        ResultSet result = null;
        String select = "Select " + Const.USER_ID + ", " + Const.USER_LOGIN + 
                ", " + Const.USER_SALARY + ", " + Const.USER_ROLE + " from " 
                + Const.USER_TABLE + " where " + Const.USER_ID + " > 2";
        
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            result = prSt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public ResultSet getRole(int role_user) {
        ResultSet result = null;
        String select = "Select name_role from roles where id_role in (select " 
            + Const.USER_ROLE + " from users where " + Const.USER_ROLE + " =?)";
        
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setInt(1, role_user);
            result = prSt.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ResultSet getRoleId(String role) {
        ResultSet result = null;
        String select = "Select id_role from roles where name_role=?";
        
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, role);
            result = prSt.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public ResultSet getUser(User user) throws SQLException, 
            ClassNotFoundException {
        ResultSet result = null;
        
        String select = "Select login_user, password_user from "
                + Const.USER_TABLE + " Where " + 
                Const.USER_LOGIN + "=? and " + Const.USER_PASS + "=?";
        
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            result = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public ResultSet getLoginUser(String login_user) throws 
            ClassNotFoundException {
        ResultSet result = null;
        
        String select = "Select " + Const.USER_LOGIN + " from " 
                + Const.USER_TABLE + " Where " + Const.USER_LOGIN + " =?";
        
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, login_user);
            result = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public void promotionUser(String login_user, int id_role) 
            throws SQLException, ClassNotFoundException {
        String select = "Update " + Const.USER_TABLE + " set " + Const.USER_ROLE
                + " =? where " + Const.USER_LOGIN + " =?";
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setInt(1, id_role);
            prSt.setString(2, login_user);
            prSt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void dismissUser(String login_user) {
        String select = "Delete from " + Const.USER_TABLE + " where " 
                + Const.USER_LOGIN + " =?";
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, login_user);
            prSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet changeUserSalary(String login_user, int salary) throws 
            SQLException, ClassNotFoundException {
        ResultSet result = null;
        String select = "Update " + Const.USER_TABLE + " set " 
                + Const.USER_SALARY + " =? where " + Const.USER_LOGIN + " =?";
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setInt(1, salary);
            prSt.setString(2, login_user);
            prSt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        select = "Select " + Const.USER_SALARY + " from " + Const.USER_TABLE 
                + " where " + Const.USER_LOGIN + " =?";
        
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(select);
            prSt.setString(1, login_user);
            result = prSt.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
