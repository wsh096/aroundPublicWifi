package wifiinfo;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import locationhistory.LocationHistoryDAO;
import org.jetbrains.annotations.TestOnly;
import util.DatabaseUtil;
import util.OpenApiUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WifiInfoDAO {
    private static String TABLE= "wifi_info";

    public static int TABLECOUNT=getTableCount();
    public static String COLUMNS="X_SWIFI_MGR_NO,X_SWIFI_WRDOFC,X_SWIFI_MAIN_NM,X_SWIFI_ADRES1,X_SWIFI_ADRES2,X_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_MBY,X_SWIFI_INSTL_TY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,X_SWIFI_REMARS3,LNT,LAT,WORK_DTTM";

    private static int getTableCount(){
        String sql = "select count(*) from "+TABLE; // 현재 테이블 데이터 검사
        Connection con= null;
        ResultSet rs=null;
        try {
            con=DatabaseUtil.getConnection();
            rs = con.createStatement().executeQuery(sql);
            String amount = rs.getString(1);
            return Integer.parseInt(amount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(con!=null){
                try {
                    con.close();

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
    }
    public void getDataFromApi(){
        //Todo api util 에서 1000개씩 데이터 가져와서 db에 반영하기
        int start=TABLECOUNT+1;
        int limit = OpenApiUtil.TOTALAMOUNT;
        OpenApiUtil oau= new OpenApiUtil();
        while(start<limit){
            insert(oau.getRows(start));
            start=Math.min(start+999,limit);
            System.out.println("테이블 데이터수 :"+getTableCount());
        }
        TABLECOUNT=getTableCount();
    }
    public WifiInfoDTO getDetail(String mgrNo,String distance){
        String sql="select * from "+TABLE+
                "\n" +
                "where X_SWIFI_MGR_NO= ?"+
                "\n group by X_SWIFI_MAIN_NM\n";
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs= null;
        try{
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,mgrNo);
            rs= pstmt.executeQuery();
            while(rs.next()){
                String X_SWIFI_MGR_NO =rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_MAIN_NM=rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_WRDOFC=rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_ADRES1=rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2=rs.getString("X_SWIFI_ADRES2");
                String x_SWIFI_INSTL_FLOOR=rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_MBY=rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_INSTL_TY=rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_SVC_SE=rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR=rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR=rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR=rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3=rs.getString("X_SWIFI_REMARS3");
                String LNT=rs.getString("LNT");
                String LAT=rs.getString("LAT");
                String WORK_DTTM =rs.getString("WORK_DTTM");
                return new WifiInfoDTO(distance,X_SWIFI_MGR_NO,X_SWIFI_MAIN_NM,X_SWIFI_WRDOFC,X_SWIFI_ADRES1,X_SWIFI_ADRES2,x_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_MBY,
                        X_SWIFI_INSTL_TY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,X_SWIFI_REMARS3 ,LNT,LAT,WORK_DTTM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){ex.printStackTrace();}
            if (con != null) try {con.close();con = null;} catch(SQLException ex){ex.printStackTrace();}
            if (rs!= null) try {rs.close(); rs = null;} catch(SQLException ex){ex.printStackTrace();}
        }

        return null; //error
    }
    public ArrayList<WifiInfoDTO> getWifiInfo(String lat,String lnt){
        return getWifiInfo(lat,lnt,20);
    }
    public ArrayList<WifiInfoDTO> getWifiInfo(String lat, String lnt ,int limit){
        /*
        * return query data with distance(km)
        * */

        ArrayList<WifiInfoDTO> list=new ArrayList<>();
        String distance="6371 * 2 * ASIN(POWER(POWER(SIN((?- lat) * pi()/180 / 2), 2) + COS(? * pi()/180) * COS(lat * pi()/180) * POWER(SIN((? - lnt) * pi()/180 / 2), 2), 0.5)) AS distance";
        String sql="select "+distance+", "+COLUMNS+" from "+TABLE+
                "\n" +
                "group by X_SWIFI_MAIN_NM\n" +
                "order by distance\n" +
                "limit 20";
        Connection con= null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);

            pstmt.setString(1,lat);
            pstmt.setString(2,lat);
            pstmt.setString(3,lnt);

            rs=pstmt.executeQuery();

            while(rs.next()){
                String X_distance= rs.getString("distance");
                String X_SWIFI_MGR_NO =rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_MAIN_NM=rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_WRDOFC=rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_ADRES1=rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2=rs.getString("X_SWIFI_ADRES2");
                String x_SWIFI_INSTL_FLOOR=rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_MBY=rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_INSTL_TY=rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_SVC_SE=rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR=rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR=rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR=rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3=rs.getString("X_SWIFI_REMARS3");
                String LNT=rs.getString("LNT");
                String LAT=rs.getString("LAT");
                String WORK_DTTM =rs.getString("WORK_DTTM");
                list.add(new WifiInfoDTO(X_distance,X_SWIFI_MGR_NO,X_SWIFI_MAIN_NM,X_SWIFI_WRDOFC,X_SWIFI_ADRES1,X_SWIFI_ADRES2,x_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_MBY,
                        X_SWIFI_INSTL_TY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,X_SWIFI_REMARS3 ,LNT,LAT,WORK_DTTM));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            new LocationHistoryDAO().recordHistory(lat,lnt);
            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){ex.printStackTrace();}
            if (con != null) try {con.close();con = null;} catch(SQLException ex){ex.printStackTrace();}
            if (rs!= null) try {rs.close(); rs = null;} catch(SQLException ex){ex.printStackTrace();}
        }

        return null; // error db
    }



    public int insert(JsonArray ja){ //new data from api
        String sql="INSERT INTO "+TABLE +"("+COLUMNS  +") "+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection con=null;
        PreparedStatement pstmt=null;
        Gson gson = new Gson();
        try {
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            for (JsonElement e : ja) {
                int index=1;
                Map<String,String> map = new LinkedHashMap<>();
                map = (Map<String, String>) gson.fromJson(e,map.getClass()); // gson to hashmap

                for(Map.Entry<String,String> entrySet:map.entrySet()){
                    pstmt.setString(index++,entrySet.getValue());
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            pstmt.clearBatch();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){ex.printStackTrace();}
            if (con != null) try {con.close();con = null;} catch(SQLException ex){ex.printStackTrace();}
        }
        return -1;
    }


    @TestOnly
    public static ArrayList<WifiInfoDTO> returnAllData(){
        ArrayList<WifiInfoDTO> list=new ArrayList<>();
        String sql="select * from "+TABLE;
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                 String X_SWIFI_MGR_NO =rs.getString("X_SWIFI_MGR_NO");
                 String X_SWIFI_MAIN_NM=rs.getString("X_SWIFI_MAIN_NM");
                 String X_SWIFI_WRDOFC=rs.getString("X_SWIFI_WRDOFC");
                 String X_SWIFI_ADRES1=rs.getString("X_SWIFI_ADRES1");
                 String X_SWIFI_ADRES2=rs.getString("X_SWIFI_ADRES2");
                 String x_SWIFI_INSTL_FLOOR=rs.getString("X_SWIFI_INSTL_FLOOR");
                 String X_SWIFI_INSTL_MBY=rs.getString("X_SWIFI_INSTL_MBY");
                 String X_SWIFI_INSTL_TY=rs.getString("X_SWIFI_INSTL_TY");
                 String X_SWIFI_SVC_SE=rs.getString("X_SWIFI_SVC_SE");
                 String X_SWIFI_CMCWR=rs.getString("X_SWIFI_CMCWR");
                 String X_SWIFI_CNSTC_YEAR=rs.getString("X_SWIFI_CNSTC_YEAR");
                 String X_SWIFI_INOUT_DOOR=rs.getString("X_SWIFI_INOUT_DOOR");
                 String X_SWIFI_REMARS3=rs.getString("X_SWIFI_REMARS3");
                 String LNT=rs.getString("LNT");
                 String LAT=rs.getString("LAT");
                 String WORK_DTTM =rs.getString("WORK_DTTM");
                 list.add(new WifiInfoDTO( X_SWIFI_MGR_NO,  X_SWIFI_MAIN_NM, X_SWIFI_WRDOFC,  X_SWIFI_ADRES1,  X_SWIFI_ADRES2,x_SWIFI_INSTL_FLOOR,  X_SWIFI_INSTL_MBY,  X_SWIFI_INSTL_TY,  X_SWIFI_SVC_SE,  X_SWIFI_CMCWR,  X_SWIFI_CNSTC_YEAR,  X_SWIFI_INOUT_DOOR,  X_SWIFI_REMARS3, LNT, LAT, WORK_DTTM));

            }
            return list;

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
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null; // error
    }

    @TestOnly
    public static void testPrint(ArrayList<WifiInfoDTO> list){
        for (WifiInfoDTO dto : list) {
            System.out.print(dto.getX_SWIFI_MGR_NO()+" ");
            System.out.println(dto.getX_SWIFI_MAIN_NM()+" ");
            System.out.println(dto.getX_SWIFI_WRDOFC()+" ");
            System.out.println(dto.getX_SWIFI_ADRES1()+" ");
            System.out.println(dto.getX_SWIFI_ADRES2()+" ");
            System.out.println(dto.getX_SWIFI_REMARS3()+" ");
        }
    }

    @TestOnly
    public static void main(String[] args){
        WifiInfoDAO dao = new WifiInfoDAO();
//        ArrayList<WifiInfoDTO> list=dao.getWifiInfo("34.8061696","126.48448");
//        for (WifiInfoDTO dto: list) {
//            System.out.print(dto.getX_SWIFI_MAIN_NM()+"  ");
//            System.out.println(dto.getDistance());
//        }

    }

}
