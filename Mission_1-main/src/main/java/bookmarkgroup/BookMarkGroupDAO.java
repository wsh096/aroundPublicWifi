package bookmarkgroup;


import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMarkGroupDAO {

    public static String TABLE = " BOOKMARKGROUP\n";

    public ArrayList<String> getBookMarkGroupNames(){ //=> return bookmarkgroup names

        ArrayList<String> names=new ArrayList<>();
        String sql ="select BOOKMARK_GROUP_NM from " +TABLE;

        Connection con=null;
        ResultSet rs=null;
        try{
            con= DatabaseUtil.getConnection();
            rs=con.createStatement().executeQuery(sql);
            while(rs.next()){
                names.add(rs.getString("bookmark_group_nm"));
            }
            return names;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (con != null) try {con.close();con = null;} catch(SQLException ex){ex.printStackTrace();}
            if (rs!= null) try {rs.close(); rs = null;} catch(SQLException ex){ex.printStackTrace();}
        }
        return null; //=> error db
    }


    public int addBookmarkGroup(String BOOKMARK_GROUP_NM,String BOOKMARK_GROUP_SEQ) throws SQLException {
        String sql="insert into "+TABLE+"(BOOKMARK_GROUP_NM,BOOKMARK_GROUP_SEQ) VALUES(?,?)";
        Connection con=null;
        PreparedStatement pstmt=null;

        try {
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,BOOKMARK_GROUP_NM);
            pstmt.setString(2,BOOKMARK_GROUP_SEQ);
            pstmt.execute();

            return 1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){ex.printStackTrace();}
            if (con != null) try {con.close();con = null;} catch(SQLException ex){ex.printStackTrace();}
        }

        return -1;//error
    }
    public int deleteBookmarkGroup(String BOOKMARK_GROUP_ID){

        String sql="delete from" +TABLE+"where BOOKMARK_GROUP_ID=?";
        Connection con= null;
        PreparedStatement pstmt=null;
        try{
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);

            pstmt.setString(1,BOOKMARK_GROUP_ID);
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
    public int editBookmarkGroup(String BOOKMARK_GROUP_ID,String BOOKMARK_GROUP_NM,String BOOKMARK_GROUP_SEQ){

        String sql="Update " +TABLE+"set BOOKMARK_GROUP_NM=?,BOOKMARK_GROUP_SEQ=? where BOOKMARK_GROUP_ID=?";
        Connection con= null;
        PreparedStatement pstmt=null;
        try{
            con=DatabaseUtil.getConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,BOOKMARK_GROUP_NM);
            pstmt.setString(2,BOOKMARK_GROUP_SEQ);
            pstmt.setString(3,BOOKMARK_GROUP_ID);
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
    public ArrayList<BookMarkGroupDTO> getBookmarkGroupInfo(){
        ArrayList<BookMarkGroupDTO> list=new ArrayList<>();
        String sql="Select * from "+TABLE+" order by BOOKMARK_GROUP_SEQ";
        Connection con=null;
        ResultSet rs=null;
        try{
            con=DatabaseUtil.getConnection();
            rs=con.createStatement().executeQuery(sql);

            while(rs.next()){
                String BOOKMARK_GROUP_ID=rs.getString("BOOKMARK_GROUP_ID");
                String BOOKMARK_GROUP_NM=rs.getString("BOOKMARK_GROUP_NM");
                String BOOKMARK_GROUP_SEQ=rs.getString("BOOKMARK_GROUP_SEQ");
                String BOOKMARK_GROUP_CD=rs.getString("BOOKMARK_GROUP_CD");
                String BOOKMARK_GROUP_ED=rs.getString("BOOKMARK_GROUP_ED");
                list.add(new BookMarkGroupDTO(BOOKMARK_GROUP_ID,BOOKMARK_GROUP_NM,BOOKMARK_GROUP_SEQ,BOOKMARK_GROUP_CD,BOOKMARK_GROUP_ED));
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();

        }
        return null;// error
    }


    public static void main(String[] args){
        BookMarkGroupDAO dao=new BookMarkGroupDAO();
        for (String bookMarkGroupName : dao.getBookMarkGroupNames()) {
            System.out.println(bookMarkGroupName);
        }
    }
}
