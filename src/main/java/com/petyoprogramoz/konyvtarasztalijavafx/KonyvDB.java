package com.petyoprogramoz.konyvtarasztalijavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KonyvDB {

    Connection conn;

    public static String DB_DRIVER = "mysql";
    public static String DB_HOST = "localhost";
    public static String DB_PORT = "3306";
    public static String DB_DBNAME = "java";
    public static String DB_USER = "root";
    public static String DB_PASS = "";

    public KonyvDB() throws SQLException {
        String url = String.format("jdbc:%s://%s:%s/%s", DB_DRIVER, DB_HOST, DB_PORT, DB_DBNAME);
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);
    }

    public List<Konyv> readBooks() throws SQLException{
        List<Konyv> konyvek = new ArrayList<>();
        String sql = "SELECT * FROM books";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()){
            String title = result.getString("title");
            String author = result.getString("author");
            int publish_year = result.getInt("publish_year");
            int page_count = result.getInt("page_count");
            Konyv konyv = new Konyv(title , author , publish_year , page_count);
            konyvek.add(konyv);
        }
        return konyvek;
    }


}
