package com.turning.turningworkshop;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class AdminController {
    
    public ArrayList<User> listUsers = new ArrayList<>();
    
    public ObservableList<String> listUsersLogin = FXCollections
        .observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelUserId;

    @FXML
    private Label labelUserName;

    @FXML
    private Label labelUserRole;
    
    @FXML
    private Label labelUserSalary;

    @FXML
    private Button logoutUserButton;

    @FXML
    private Pane userBlockInfo;

    @FXML
    private Label userLoginnedLabel;
    
    @FXML
    private ChoiceBox<String> listUsersDropBlock;

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        userLoginnedLabel.setText("Welcome, " + SignInController.userLogin);
        logoutUserButton.setOnMouseMoved(e -> {
            logoutUserButton.setStyle("-fx-background-color: #39d2f0");
        });
        getUsersFromDB();
    }

    @FXML
    private void checkUserLoginnedLabel() {
        
    }
    
    @FXML
    void logoutUserFromDashboard(ActionEvent event) throws IOException {
        App.setRoot("signin");
    }

    void getUsersFromDB() throws ClassNotFoundException, 
            ClassNotFoundException, SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultUsers = dbHandler.getAllUsers();
        
        for (; resultUsers.next();) {
            int id_user = resultUsers.getInt("id_user");
            String login_user = resultUsers.getString("login_user");
            int role_user = resultUsers.getInt("role_user");
            int salary_role = resultUsers.getInt("salary_user");
            String name_role = null;
            ResultSet resultRole = dbHandler.getRole(role_user);
            if (resultRole.next()) {
                name_role = resultRole.getString("name_role");
            }
            
            listUsersLogin.add(login_user);
            User user = new User(id_user, login_user, name_role, salary_role);
            listUsers.add(user);
        }
        listUsersDropBlock.setValue(listUsersLogin.get(0));
        listUsersDropBlock.setItems(listUsersLogin);
        listUsersDropBlock.setOnAction(e -> {
            labelUserId.setText(Integer.toString(listUsersDropBlock
                    .getSelectionModel().getSelectedIndex() + 1));
            labelUserName.setText(listUsersDropBlock.getValue());
            labelUserRole.setText(listUsers.get(listUsersDropBlock
                    .getSelectionModel().getSelectedIndex()).getRole());
            labelUserSalary.setText(Integer.toString(listUsers
                    .get(listUsersDropBlock.getSelectionModel()
                            .getSelectedIndex()).getSalary()) + " руб");
        });
    }
}
