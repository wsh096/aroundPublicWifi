package util;


import org.jetbrains.annotations.TestOnly;


import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.sql.*;

public class DatabaseUtil {
    private  static Connection con= null;
    private static String dbLoc="C:/DB/mission1_test.sqlite";

    public static Connection getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            con= DriverManager.getConnection("jdbc:sqlite:" + dbLoc);
            return con;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void init(String path) throws SQLException, IOException {
        con = getConnection();
        String sql = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        con.createStatement().executeUpdate(sql);
    }

    @TestOnly
    public static void testQuery(Connection con){
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from \"TEST\" ");

            while(rs.next()){
                String id= rs.getString("ID");
                String name=rs.getString("TEST_CODE");
                System.out.println(id+"           "+ name );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @TestOnly
    public static void main(String[] args){
        // test Query
        testQuery(getConnection());
    }


}
