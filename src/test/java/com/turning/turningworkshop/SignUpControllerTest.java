/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.turning.turningworkshop;

import java.io.IOException;
import java.sql.SQLException;
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
public class SignUpControllerTest {
    
    SignUpController signUpClass = new SignUpController();
    
    public SignUpControllerTest() {
    } 
    
    @BeforeClass
    public static void setUpClass() {
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
    
    @Test
    public void checkUserDataTest() throws IOException, 
            ClassNotFoundException, SQLException {
        boolean userHasBeenRegistered = signUpClass
                                         .checkUserData("Admin", "111", "111");
        assertEquals(false, userHasBeenRegistered);
    }
    
}
