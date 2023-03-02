package com.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.TestOnly;

import com.db.dto.WifiDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.module.JdbcTemplate;

public class WifiDao {

	private static String Table = "mission1";

	private static String Columns = " X_SWIFI_MGR_NO, " + "X_SWIFI_WRDOFC, " + "X_SWIFI_MAIN_NM, " + "X_SWIFI_ADRES1, "
			+ "X_SWIFI_ADRES2," + "X_SWIFI_INSTL_FLOOR, " + "X_SWIFI_INSTL_TY, " + "X_SWIFI_INSTL_MBY, "
			+ "X_SWIFI_SVC_SE, " + "X_SWIFI_CMCWR, " + "X_SWIFI_CNSTC_YEAR, " + "X_SWIFI_INOUT_DOOR, "
			+ "X_SWIFI_REMARS3, " + "LNT, " + "LAT, " + "WORK_DTTM";

	private JdbcTemplate jt; // 연결자(Connect)

	public WifiDao(JdbcTemplate jt) {
		this.jt = jt;
	}

	// select(여기서 distance구하고 limit으로 제한)

	public List<WifiDto> select(String Lat, String Lnt) {// result 반환 == 20개의 정렬된 함수.
		// 위에 생성자 때문에 없어도 됨jdbcTemplate jt = new jdbcTemplate();//connec
		String sql = " SELECT " + // 쿼리문 준비
				" ( " + " 6371 * acos( " + " cos( radians(" + Lat + ") ) " + " * cos( radians( LAT ) ) "
				+ " * cos( radians( LNT ) - radians(" + Lnt + ") ) " + " + sin( radians(" + Lat
				+ ") ) * sin( radians( LAT ) ) ) " + " ) AS DISTANCE" + " " + Columns + " " + " FROM " + Table
				+ " ORDER BY DISTANCE " //
				+ " LIMIT 20; ";
		Connection conn = jt.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WifiDto> result = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String distance = rs.getString("DISTANCE").substring(0, 8);
				String merNo = rs.getString("X_SWIFI_MGR_NO");
				String wifiName = rs.getString("X_SWIFI_MAIN_NM");
				String gu = rs.getString("X_SWIFI_WRDOFC");
				String arr1 = rs.getString("X_SWIFI_ADRES1");
				String arr2 = rs.getString("X_SWIFI_ADRES2");
				String floor = rs.getString("X_SWIFI_INSTL_FLOOR");
				String installAgency = rs.getString("X_SWIFI_INSTL_MBY");
				String installType = rs.getString("X_SWIFI_INSTL_TY");
				String service = rs.getString("X_SWIFI_SVC_SE");
				String mangType = rs.getString("X_SWIFI_CMCWR");
				String installYear = rs.getString("X_SWIFI_CNSTC_YEAR");
				String where = rs.getString("X_SWIFI_INOUT_DOOR");
				String wifiState = rs.getString("X_SWIFI_REMARS3");
				String lnt = rs.getString("LNT");
				String lat = rs.getString("LAT");
				String installDate = rs.getString("WORK_DTTM");

				WifiDto dtos = new WifiDto();
				dtos.setDistance(distance);
				dtos.setMerNo(merNo);
				dtos.setGu(gu);
				dtos.setWifiName(wifiName);
				dtos.setArr1(arr1);
				dtos.setArr2(arr2);
				dtos.setFloor(floor);
				dtos.setInstallType(installType);
				dtos.setInstallAgency(installAgency);
				dtos.setService(service);
				dtos.setMangType(mangType);
				dtos.setInstallYear(installYear);
				dtos.setWhere(where);
				dtos.setWifiState(wifiState);
				dtos.setLat(lat);
				dtos.setLnt(lnt);
				dtos.setInstallDate(installDate);

				result.add(dtos);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return result;// 가져 와야 하는 테이블
		}
	}

	// insert//기본 데이터 삽입

	public int insertAll(List<WifiDto> results) {//All cnt를 해주며 버튼 누르면 나오는 화면의 숫자를 출력해줄 함수 방법.
		
		
		String sql = "INSERT INTO "
				+ Table
				+ " ( " + Columns + " ) "
        		+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ); ";
		for(WifiDto i : results) {
			
			int idx = 0;
			if(idx==0) {
			System.out.println("관리번호:" + i.getMerNo());
			System.out.println(i.getGu());
			System.out.println(i.getWifiName());
			System.out.println(i.getArr1());
			System.out.println(i.getArr2());
			System.out.println(i.getFloor());
			System.out.println(i.getInstallType());
			System.out.println(i.getInstallAgency());
			System.out.println(i.getService());
			System.out.println(i.getMangType());
			System.out.println(i.getInstallYear());
			System.out.println(i.getWhere());
			System.out.println(i.getWifiState());
			System.out.println(i.getLnt());
			System.out.println(i.getLat());
			System.out.println(i.getInstallDate());
			}
			idx++;
		}
		System.out.println();
		
		Connection conn =jt.getConnection();
		PreparedStatement pstmt = null;
	
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < results.size(); i++) {
	                pstmt.setString(1, results.get(i).getMerNo());
	                pstmt.setString(2, results.get(i).getGu());
	                pstmt.setString(3, results.get(i).getWifiName());
	                pstmt.setString(4, results.get(i).getArr1());
	                pstmt.setString(5, results.get(i).getArr2());
	                pstmt.setString(6, results.get(i).getFloor());
	                pstmt.setString(7, results.get(i).getInstallType());
	                pstmt.setString(8, results.get(i).getInstallAgency());
	                pstmt.setString(9, results.get(i).getService());
	                pstmt.setString(10, results.get(i).getMangType());
	                pstmt.setString(11, results.get(i).getInstallYear());
	                pstmt.setString(12, results.get(i).getWhere());
	                pstmt.setString(13, results.get(i).getWifiState());
	                pstmt.setString(14, results.get(i).getLnt());
	                pstmt.setString(15, results.get(i).getLat());
	                pstmt.setString(16, results.get(i).getInstallDate());
	                result = pstmt.executeUpdate();
	                return result;
	            }
	            pstmt.executeBatch();
	            pstmt.clearBatch();
	            return 1;
		}catch(

	SQLException e)
	{
			e.printStackTrace();
		}finally
	{
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}return result;
	}

	@TestOnly
	public static void show() {
		String sql = "SELECT * FROM "+ Table+";";
		
		JdbcTemplate jt = new JdbcTemplate();
		Connection conn = jt.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(rs.next());
			System.out.println();
			while(rs.next()) {
				System.out.println("X_SWIFI_MGR_NO: "+ rs.getString("X_SWIFI_MGR_NO"));
				System.out.println("LNT: " + rs.getString("LNT"));
				System.out.println("X_SWIFI_SVC_SE: " + rs.getString("X_SWIFI_SVC_SE"));
				System.out.println("WORK_DTTM: " + rs.getString("WORK_DTTM"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}if(rs != null){
				try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
		}
	}

	@TestOnly
	public static void main(String[] args) {
		show();

	}

}