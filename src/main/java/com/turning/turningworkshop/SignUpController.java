package com.turning.turningworkshop;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    
    @FXML
    private Label successfullLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_confirm_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    void initialize() {}
    
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
    private void authSignUpUser() throws IOException, SQLException, 
            ClassNotFoundException {
        String username = login_field.getText();
        String password = pass_field.getText();
        String passConf = pass_confirm_field.getText();
        if (checkUserData(username, password, passConf)) {
            authRegisterUser(username, password);
        } else {
            errorLabel.setStyle("-fx-opacity: 1");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    errorLabel.setStyle("-fx-opacity: 0");
                }
            }, Const.TIME_ANIMATION);
        }
    }

    @FXML
    private void switchToLoginPage() throws IOException {
        App.setRoot("signin");
    }

    private void authRegisterUser(String username, String password) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            User user = new User(username, password);
            dbHandler.signUpUser(user);
            System.out.println("User " + username 
                    + " has been registered, password - " + password);
            successfullLabel.setStyle("-fx-opacity: 1");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    successfullLabel.setStyle("-fx-opacity: 0");
                }
            }, Const.TIME_ANIMATION);
    }
    
    public boolean checkUserData(String username, String password, 
            String passConf) throws IOException, 
            ClassNotFoundException, SQLException {
        String symbols[] = {")", "(", "*", "&", "^", "%", "$", "#", "@", "!"};
        String numbers[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getLoginUser(username);
        for (int i = 0; i < numbers.length; i++) {
            if (username.startsWith(numbers[i]) || username.contains(symbols[i])
                    || password.contains(symbols[i])) 
                return false;
        }
        if ((!username.equals("") && !username.contains(" ") && !username
               .contains("Admin")) && (!password.equals("") && !password
               .contains(" ")) && passConf.equals(password) && !result.next()) {
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
