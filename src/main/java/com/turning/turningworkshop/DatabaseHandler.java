package com.turning.turningworkshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler extends Config {
    Connection dbConnection;
    public Connection getdbConnection() throws ClassNotFoundException, SQLException {
        String connectionLine = "jdbc:mysql://" + dbhost + ":" + dbport
                + "/" + dbname;
        System.out.println("connection" + connectionLine);
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionLine, dbuser, dbpass);
        
        return dbConnection;
    }
    public void signUpUser(String login, String password) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + 
                Const.USER_LOGIN + ", " + Const.USER_PASS + ") values (?, ?)";
        try {
            PreparedStatement prSt = getdbConnection().prepareStatement(insert);
            
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
