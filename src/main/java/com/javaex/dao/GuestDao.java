package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.MemberVo;

@Repository
public class GuestDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phone_db";
	private String id = "phone";
	private String pw = "phone";

	// 생성자
	// 메소드-gs

	// 메소드-일반

	// 연결
	public void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
	}

	// 종료
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public int memberInsert(MemberVo memberVo) {
		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guest_db";
			conn = DriverManager.getConnection(url, "guest", "guest");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " INSERT INTO member ";
			query += " VALUES (null,?, ?, ?,now()) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getPassword());
			pstmt.setString(3, memberVo.getContent());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

	public List<MemberVo> memberSelect() {

		List<MemberVo> memberList = new ArrayList<MemberVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guest_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select no, ";
			query += "		  name, ";
			query += "		  password, ";
			query += "		  content, ";
			query += "		  reg_date ";
			query += " from member ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				MemberVo memberVo = new MemberVo(no, name, password, content, reg_date);
				memberList.add(memberVo);

				/* System.out.println(personVo); */

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return memberList;
	}

	// 삭제
	public int memberDelete(int no, String password) {
		int count = -1;

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guest_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from member ";
			query += " where no = ? and password = ? ";
			// 4.결과처리
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);

			// 실행
			count = pstmt.executeUpdate();

			System.out.println(no + "건이 삭제되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

	public MemberVo memberSelectOne(int no) {

		this.getConnection();

		MemberVo memberVo = null;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select no, ";
			query += "		  name, ";
			query += "		  password, ";
			query += "		  content, ";
			query += "		  reg_date ";
			query += " from member ";
			query += " where no = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {// 반복
				int personId = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				// db에서 가져온 데이터 vo로 묶기
				memberVo = new MemberVo(no, name, password, content, reg_date);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return memberVo;
	}

}
