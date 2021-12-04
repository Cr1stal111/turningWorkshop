/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.turning.turningworkshop;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author winston
 */
public class UserControllerTest {
    
    static String login_user = "";
    
    UserController controller = new UserController();
    
    public UserControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        login_user = "user1";
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of logoutUserFromDashboard method, of class UserController.
     * @param login_user
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void checkUserFreeTest() throws SQLException, 
            ClassNotFoundException {
        boolean userHasOrder = controller.checkUserFree(login_user);
        
        assertEquals(true, userHasOrder);
    }
    
}
