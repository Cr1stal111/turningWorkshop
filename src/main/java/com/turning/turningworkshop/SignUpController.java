package com.turning.turningworkshop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_confirm_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    void initialize() {
        
    }
    
    @FXML
    public void authLoginController() {
        login_field.setText("");
        login_field.setPromptText("Wrong username!");
    }
    
    @FXML
    public void authPassController() {
        pass_field.setText("");
        pass_field.setPromptText("Wrong pass!");
    }
    
    @FXML
    public void authPassConfirmController() {
        pass_confirm_field.setText("");
        pass_confirm_field.setPromptText("Passwords don't match!");
    }
    
    @FXML
    private void authSignUpUser() throws IOException {
        String username = login_field.getText();
        String password = pass_field.getText();
        String passConf = pass_confirm_field.getText();
        if (checkUserData(username, password, passConf)) {
            authRegisterUser(username, password);
        } else
            System.out.println("Error");
    }

    @FXML
    private void switchToLoginPage() throws IOException {
        App.setRoot("signin");
    }

    private void authRegisterUser(String username, String password) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        System.out.println("User " + username + " has been logined, password - "
                + password);
        dbHandler.signUpUser(username, password);
    }
    
    public boolean checkUserData(String username, String password, String passConf)
            throws IOException {
        if ((!username.equals("") && !username.contains(" ")) 
                && (!password.equals("") && !password.contains(" "))
                    && passConf.equals(password)) {
            return true;
        } else {
            if (username.equals("") || username.contains(" ")) {
                authLoginController();
            } else if (password.equals("") || password.contains(" ")) {
                authPassController();
            } else if (!passConf.equals(password) || passConf.contains(" ")) {
                authPassConfirmController();
            }
        }
        return false;
    }
}
