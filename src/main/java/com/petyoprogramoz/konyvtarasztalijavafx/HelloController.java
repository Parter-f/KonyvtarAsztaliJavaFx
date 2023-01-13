package com.petyoprogramoz.konyvtarasztalijavafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class HelloController {

    @FXML
    private TableView<Konyv> bookTable;
    @FXML
    private TableColumn<Konyv, String> cimCol;
    @FXML
    private TableColumn<Konyv, String> szerzoCol;
    @FXML
    private TableColumn<Konyv, Integer> kiadasEveCol;
    @FXML
    private TableColumn<Konyv, Integer> oldalSzamCol;
    @FXML
    private Button deleteButton;
    @FXML
    private KonyvDB db;
    private Alert.AlertType alertType;
    private String headerText;
    private String contentText;

    @FXML
    private void initialize(){
        cimCol.setCellFactory(new PropertyValueFactory<>("title"));
        szerzoCol.setCellFactory(new PropertyValueFactory<>("author"));
        kiadasEveCol.setCellFactory(new PropertyValueFactory<>("publish_year"));
        oldalSzamCol.setCellFactory(new PropertyValueFactory<>("page_count"));
        try{
            db = new KonyvDB();
            readBooks();
        } catch (SQLException e){
            Platform.runLater(() ->{
                sqlAlert(e);
                Platform.exit();
            });
        }
    }

    private void sqlAlert(SQLException e) {
        alert(Alert.AlertType.ERROR,
                "Hiba történt az adatbázis kapcsolat kialakításakor",
                e.getMessage());
    }

    private Optional<ButtonType> alert(Alert.AlertType error, String s, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert.showAndWait();
    }

    private void readBooks() throws SQLException{
        List<Konyv> konyvek = db.readBooks();
        bookTable.getItems().clear();
        bookTable.getItems().addAll(konyvek);
    }

    public void deleteClick(ActionEvent actionEvent) {
    }
}