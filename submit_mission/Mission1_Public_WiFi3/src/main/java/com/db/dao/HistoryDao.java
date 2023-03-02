package com.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.TestOnly;

import com.db.dto.HistoryDto;
import com.module.JdbcTemplate;

public class HistoryDao {
	
	private static String Table = "mission1_history";
	
	private static String Columns = " ID, "
								  + " X_POS, "
								  + " Y_POS, "
								  + " TIME ";	
	
private JdbcTemplate jt; //연결자(Connect)
	
	public HistoryDao(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	//select //table 보여주는 용도.
	public List<HistoryDto> select(){
		//위에 생성자 때문에 없어도 됨jdbcTemplate jt = new jdbcTemplate();//connec
		String sql = "SELECT "
					+ Columns +
					" FROM "
					+ Table+";";
		
		Connection conn = jt.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HistoryDto> result = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HistoryDto history = new HistoryDto(
						rs.getString("ID"),
						rs.getString("X_POS"),
						rs.getString("Y_POS"),
						rs.getString("TIME"));
				result.add(history);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}if(pstmt != null){
				try {
				pstmt.close();
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
	return result;
		}
	}
	
	//insert //데이터
	public int insertHistory(String X_Pos, String Y_Pos) {
		String sql = "INSERT INTO " + Table +" (X_POS, Y_POS) " + "VALUES(?, ?);";
		//나머지 값은 DB상에서 올라가게 만들어 두었습니다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = jt.getConnection();
		int result = 0;
		try {
			
			pstmt = conn.prepareStatement(sql);//여기서 오류 발생 중
			pstmt.setString(1, X_Pos);
			pstmt.setString(2, Y_Pos);
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			if(pstmt != null) {
				try {
					System.out.println(pstmt);
					pstmt.close();
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}if(conn != null){
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
			
				}
			}
		}
		return result;
	}
	
	//delete //데이터
	public int deleteHistory(String ID){
        String sql=" DELETE FROM " +Table
        			+" WHERE ID = ?;";
        
        Connection conn= jt.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,ID);
            pstmt.execute();
            
            return 1;//정상 실행

        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}if(conn != null){
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@TestOnly
	private static void show() {
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
				System.out.println("ID: "+ rs.getString("ID"));
				System.out.println("X_POS: " + rs.getString("X_POS"));
				System.out.println("Y_POS: " + rs.getString("Y_POS"));
				System.out.println("TIME: " + rs.getString("TIME"));
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
		JdbcTemplate jt = new JdbcTemplate();
		HistoryDao dao = new HistoryDao(jt);
		System.out.println(dao.insertHistory("124", "24"));
		show();
	}
}
