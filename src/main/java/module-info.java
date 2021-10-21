module com.turning.turningworkshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.turning.turningworkshop to javafx.fxml;
    exports com.turning.turningworkshop;
}
