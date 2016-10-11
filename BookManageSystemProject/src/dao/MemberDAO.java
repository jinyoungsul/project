package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemberDTO;

public class MemberDAO {
	private Connection getConnection(){
		String url = "jdbc:mysql://127.0.0.1:3306/project";
		String user = "root";
		String password = "sds902";
		Connection con=null;
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. 커넥션 연결
			con = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 에러");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL 에러");
			e.printStackTrace();
		}
		return con;
	}
	public int insertMember(MemberDTO member){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		//3. sql문 작성
		StringBuffer sb = new StringBuffer();
		sb.append("insert into member(name,address,phoneNumber,birthday,comment,memberNo) ");
		sb.append("values (?,?,?,?,?,?)");
		
		//4.pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getPhoneNumber());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getComment());
			pstmt.setString(6, member.getMemberNo());
			
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
	public ArrayList<MemberDTO> searchMember(){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> memberList = null;
		//3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from member");
		
		//4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				String memberNo = rs.getString("memberNo");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String birthday = rs.getString("birthday");
				String comment = rs.getString("comment");
				MemberDTO member = new MemberDTO(name, address, phoneNumber, birthday, comment);
				member.setMemberNo(memberNo);
				
				memberList.add(member);
			}
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
		return memberList;
		
	}
}
