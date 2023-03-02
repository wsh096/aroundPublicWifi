/**
 * DBtest
 */

import org.jetbrains.annotations.TestOnly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBtest {
    private static Connection con=null;
    private static String dbLoc="C:\\DB\\mission1.sqlite";
    static{
        try{
            Class.forName("org.sqlite.JDBC");
            con= DriverManager.getConnection("jdbc:sqlite:" + dbLoc);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void testQuery(Connection con){
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from \"wifi_info\" ");

            while(rs.next()){
                String id= rs.getString("ID");
                String name=rs.getString("name");
                System.out.println(id+"           "+ name );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @TestOnly
    public static void main(String[] args){
        // test Query
        testQuery(con);
    }


}
