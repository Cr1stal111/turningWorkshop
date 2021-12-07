/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.turning.turningworkshop;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class SignUpControllerTest {
    
    SignUpController signUpClass = new SignUpController();
    
    @Test
    public void checkUserSignUpAdminTest() throws IOException, 
            ClassNotFoundException, SQLException {
        boolean userHasBeenRegistered = signUpClass
                .checkUserData("Admin", "root", "root");
        assertFalse(userHasBeenRegistered);
    }
    @Test
    public void checkUserSignUpTest() throws IOException, 
            ClassNotFoundException,
            SQLException {
        boolean userHasBeenRegistered = signUpClass
                .checkUserData("user10", ")(*&^%$#@!", ")(*&^%$#@!");
        assertEquals(false, userHasBeenRegistered);
    }
    @Test
    public void createUserStartWithNumberTest() throws IOException, 
            ClassNotFoundException,
            SQLException {
        boolean userHasBeenRegistered = signUpClass
                .checkUserData("111", "pass", "pass");
        assertFalse(userHasBeenRegistered);
    }
}
