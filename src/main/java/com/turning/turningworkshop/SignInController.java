package com.turning.turningworkshop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

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
    private PasswordField pass_field;

    @FXML
    void initialize() throws IOException {
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
    private void authCheckUser() throws IOException {
        try {
            String username = login_field.getText();
            String password = pass_field.getText();
            if (checkUserData(username, password)) {
                authLogin(username, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
            authLoginController();
            authPassController();
        }
    }
    
    @FXML
    private void switchToRegisterPage() throws IOException {
        App.setRoot("signup");
    }

    private void authLogin(String loginUser, String loginPass) {
        System.out.println("User " + loginUser + " has been logined, password - " + loginPass);
    }
    
    public boolean checkUserData(String username, String password) throws IOException {
        if ((!username.equals("") && !username.contains(" ")) 
                && (!password.equals("") && !password.contains(" "))) {
            return true;
        } else {
            if (username.equals("") || username.contains(" ")) {
                authLoginController();
            } else if (password.equals("") || password.contains(" ")) {
                authPassController();
            }
            return false;
        }
    }
}
