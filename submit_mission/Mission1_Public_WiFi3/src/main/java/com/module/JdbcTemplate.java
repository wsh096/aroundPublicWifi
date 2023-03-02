package com.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jetbrains.annotations.TestOnly;

public class JdbcTemplate { //접속용도
	
	private String direction = "f:Mission1.db";
	// f:Mission1.db
	// f:test2.db
	public JdbcTemplate() {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("드라이버 검색 성공!"); //내부 확인용
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
		
		public Connection getConnection() {
			String url = "jdbc:sqlite:"+direction;//db경로
			Connection conn = null;
			try {
			conn = DriverManager.getConnection(url);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		@TestOnly
		public static void main(String[] args) {
			JdbcTemplate jt = new JdbcTemplate();
			System.out.println(jt);
		}
}


