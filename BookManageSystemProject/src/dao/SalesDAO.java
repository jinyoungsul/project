package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.SalesDTO;

public class SalesDAO {
	private Connection getConnection(){
		Connection con = null;
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://70.12.109.108:3306/project";
//			String url = "jdbc:mysql://localhost:3306/project";
			String user = "root";
			String password = "sds902";
//			String password = "root";
			
			//2. 커넥션 연결
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
	public ArrayList<SalesDTO> selectSalesTable(String startDate,String endDate){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SalesDTO> salesList = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		//3. sql작성
		sb.append("select 대여일자,회원.이름,회원.회원번호,도서.도서명,대여금액 ");
		sb.append("from 회원 join 도서 join 대여 ");
		sb.append("where 대여일자>= ? and 대여일자<=? and 회원.회원번호=대여.회원번호 and 도서.도서번호=대여.도서번호 order by 대여일자 ");
		
		// pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SalesDTO salesDTO = new SalesDTO();
				salesDTO.setSalesDate(rs.getString("대여일자"));
				salesDTO.setName(rs.getString("이름"));
				salesDTO.setMemberNo(rs.getString("회원번호"));
				salesDTO.setBookName(rs.getString("도서명"));
				salesDTO.setMoney(rs.getInt("대여금액"));
				
				salesList.add(salesDTO);
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
		}
		return salesList;
	}
	public int selectMemberCount(String startDate,String endDate){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		StringBuffer sb = new StringBuffer();
		//3. sql작성
		sb.append("select COUNT(distinct 회원.회원번호) ");
		sb.append("from 회원 join 도서 join 대여 ");
		sb.append("where 대여일자>= ? and 대여일자<=? and 회원.회원번호=대여.회원번호 and 도서.도서번호=대여.도서번호 ");
		
		// pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
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
		}
		return count;
	}
	public ArrayList<Object[]> selectSumMoney(String startDate,String endDate){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ArrayList<Object[]> dailySumMoneyList = new ArrayList<>(); 
		ResultSet rs = null;
		//3. sql문 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select 대여일자,sum(대여금액) as 일매출 ");
		sb.append("from 대여 ");
		sb.append("where 대여일자>=? and 대여일자<=? ");
		sb.append("group by 대여일자 ");
		sb.append("having sum(대여금액)>0");
		
		//4.sql문 실행
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Object[] date = {rs.getString(1),rs.getInt(2)};
				dailySumMoneyList.add(date);
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
		return dailySumMoneyList;
	}
	
}
