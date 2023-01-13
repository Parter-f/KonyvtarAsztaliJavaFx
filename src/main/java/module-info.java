module com.petyoprogramoz.konyvtarasztalijavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.petyoprogramoz.konyvtarasztalijavafx to javafx.fxml;
    exports com.petyoprogramoz.konyvtarasztalijavafx;
}