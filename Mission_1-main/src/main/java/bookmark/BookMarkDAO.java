package bookmark;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMarkDAO {

    public ArrayList<BookMarkDTO> getAllData() { //모든 북마크

        ArrayList<BookMarkDTO> list = new ArrayList<>();

        String sql = "SELECT BM.BOOKMARK_ID,BG.BOOKMARK_GROUP_ID,BG.BOOKMARK_GROUP_NM,WI.X_SWIFI_MGR_NO as X_SWIFI_MGR_NO,WI.X_SWIFI_MAIN_NM,BM.BOOKMARK_CD \n" +
                "FROM BOOKMARK BM, BOOKMARKGROUP BG, WIFI_INFO WI\n" +
                "WHERE BM.BOOKMARK_GROUP_ID==BG.BOOKMARK_GROUP_ID AND BM.WIFI_INFO_ID==WI.ID\n ";
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtil.getConnection();
            rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                String BOOKMARK_ID = rs.getString("BOOKMARK_ID");
                String BOOKMARK_GROUP_ID = rs.getString("BOOKMARK_GROUP_ID");
                String BOOKMARK_GROUP_NM = rs.getString("BOOKMARK_GROUP_NM");
                String BOOKMARK_WIFI_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_MGR_NO=rs.getString("X_SWIFI_MGR_NO");
                String BOOKMARK_CD = rs.getString("BOOKMARK_CD");


                list.add(new BookMarkDTO(BOOKMARK_ID, BOOKMARK_GROUP_ID, BOOKMARK_GROUP_NM, BOOKMARK_WIFI_NM,X_SWIFI_MGR_NO, BOOKMARK_CD));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public int insertBookmark(String mgrNo, String BOOKMARK_GROUP_NM) {
        /*
         * Todo
         *  mgrno: 와이파이 기기명 으로 wifi_id
         *  bookmark group_nm 으로 bookmark_group_id
         * 가져온 이후 insert bookmarkslelect
         * */
        String sql = "insert into bookmark(bookmark_group_id, wifi_info_id) values(\n" +
                "(select bookmark_group_id \n" +
                "from bookmarkgroup \n" +
                "where bookmark_group_nm=?),\n" +
                "(select ID \n" +
                "from wifi_info\n" +
                "    where x_swifi_mgr_no=?\n" +
                "     group by x_swifi_mgr_no))";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {

            con = DatabaseUtil.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, BOOKMARK_GROUP_NM);
            pstmt.setString(2, mgrNo);

            pstmt.execute();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int deleteBookmark(String BOOKMARK_ID) {

        String sql = "delete from BOOKMARK WHERE BOOKMARK_ID=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DatabaseUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, BOOKMARK_ID);
            pstmt.execute();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return -1;
    }
    public static void main (String[]args){

    }
}
