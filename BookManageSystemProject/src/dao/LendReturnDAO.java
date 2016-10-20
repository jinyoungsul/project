package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BookDTO;
import dto.LendReturnDTO;

public class LendReturnDAO {
	public Connection getConnection(){
		Connection con = null;
		String url = "jdbc:mysql://70.12.109.108:3306/project";
//		String url = "jdbc:mysql://localhost:3306/project";
		String user = "root";
		String password = "sds902";
//		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public int insertLendReturn(LendReturnDTO lendReturn){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		//3.sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("insert into 대여(회원번호,도서번호,대여일자,반납일자,대여금액) ");
		sb.append("values (?,?,str_to_date(?,'%Y-%m-%d'),str_to_date(?,'%Y-%m-%d'),?) ");
		//4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			System.out.println(sb.toString());
			pstmt.setInt(1, lendReturn.getMemberNo());
			pstmt.setInt(2, lendReturn.getBookNo());
			pstmt.setString(3, lendReturn.getLendDate());
			pstmt.setString(4, lendReturn.getReturnDate());
			pstmt.setInt(5, lendReturn.getMoney());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public ArrayList<Object[]> selectLendReturnList(int memberNo){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ArrayList<Object[]> lendReturnList = new ArrayList<>(); 
		ResultSet rs = null;
		//3. sql문 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select 대여번호,도서.도서명,도서.저자,대여일자,반납일자,연체여부,대여금액 ");
		sb.append("from 대여 join 회원 join 도서 ");
		sb.append("where 대여.회원번호 = ? and 도서.도서번호 = 대여.도서번호 and 대여.회원번호 = 회원.회원번호 and 반납여부='미반납'");
		
		//4.sql문 실행
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Object[] raw = {rs.getString("대여번호"),rs.getString("도서.도서명"),
						rs.getString("도서.저자"),rs.getString("대여일자"),
						rs.getString("반납일자"),rs.getString("연체여부"),rs.getString("대여금액")
				};
				lendReturnList.add(raw);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lendReturnList;
	}
	public int updateLendReturn(int lendReturnNo) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		// sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("update 대여 set 반납여부='반납' ");
		sb.append("where 대여번호 = ?");
		
		//pstmt생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, lendReturnNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
}
