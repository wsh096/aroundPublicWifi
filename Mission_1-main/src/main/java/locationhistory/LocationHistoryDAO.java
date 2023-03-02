package locationhistory;

import org.jetbrains.annotations.TestOnly;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationHistoryDAO {
    // id, x ,y , date
    private static String TABLE= "LOCHISTORY";


    public int recordHistory(String lat, String lnt){
        String OPTION ="(\"LAT\",\"LNT\")";
        String sql= "insert into "+TABLE +OPTION +"values( ?,? )";
        Connection con=null;
        PreparedStatement pstmt=null;

        try{
            con= DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,lat);
            pstmt.setString(2,lnt);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(con!=null){
                try {
                    con.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return -1; //error
    }

    public ArrayList<LocationHistoryDTO> getHistory(){

        ArrayList list=new ArrayList<LocationHistoryDTO>();

        Connection con =null;
        PreparedStatement pstmt=null;
        ResultSet rs =null;

        try{
            con=DatabaseUtil.getConnection();
            String sql="select * from "+TABLE;
            pstmt = con.prepareStatement(sql);
            rs= pstmt.executeQuery();

            while(rs.next()){
                String id= rs.getString("ID");
                String lat= rs.getString("LAT");
                String lnt= rs.getString("LNT");
                String time_log = rs.getString("TIME_LOG");

                list.add(new LocationHistoryDTO(id,lat,lnt,time_log));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(con!=null){
                try {
                    con.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;

    }

    public int deleteHistory(String ID){
        String sql="delete from "+TABLE+" WHERE ID=?";
        Connection con=null;
        PreparedStatement pstmt=null;
        try{
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,ID);
            pstmt.execute();
            return 1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(con!=null){
                try {
                    con.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
    @TestOnly
    public static void testSelect(){
        ArrayList<LocationHistoryDTO> tmp=new LocationHistoryDAO().getHistory();
        for (LocationHistoryDTO  dto: tmp) {
            System.out.print(" id :"+dto.getId());
            System.out.print(" lat : "+dto.getLat());
            System.out.print(" lnt :"+dto.getLnt());
            System.out.print(" time : "+dto.getTime_log());
            System.out.println();
        }
    }

    @TestOnly
    public static void main(String[] args){
        testSelect();
    }
}
