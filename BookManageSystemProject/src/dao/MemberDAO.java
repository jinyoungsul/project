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
		String url = "jdbc:mysql://70.12.109.108:3306/project";
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
		sb.append("insert into 회원(이름,주소,전화번호,생년월일,비고) ");
		sb.append("values (?,?,?,?,?)");
		
		//4.pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getPhoneNumber());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getComment());
			
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
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		//3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from 회원");
		
		//4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				int memberNo = rs.getInt("회원번호");
				String name = rs.getString("이름");
				String address = rs.getString("주소");
				String phoneNumber = rs.getString("전화번호");
				String birthday = rs.getString("생년월일");
				String comment = rs.getString("비고");
				MemberDTO member = new MemberDTO(memberNo,name, address, phoneNumber, birthday, comment);
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
	//멤버 키워드 검색(회원번호,이름,전화번호)
	public ArrayList<MemberDTO> searchKeywordMember(int searchMemberNo) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		//3.sql문 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from 회원 ");
		sb.append("where 회원번호 = ? ");
		System.out.println(sb.toString());
		System.out.println(searchMemberNo);
		try {
			//4.pstmt 생성
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, searchMemberNo);
			
			//5. sql실행
			rs = pstmt.executeQuery();
			while(rs.next()){
				int memberNo = rs.getInt("회원번호");
				String name = rs.getString("이름");
				String address = rs.getString("주소");
				String phoneNumber = rs.getString("전화번호");
				String birthday = rs.getString("생년월일");
				String comment = rs.getString("비고");
				MemberDTO member = new MemberDTO(memberNo,name, address, phoneNumber, birthday, comment);
				memberList.add(member);
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
		return memberList;
	}
	//멤버 키워드 검색(이름,전화번호)
		public ArrayList<MemberDTO> searchKeywordMember(String searchName,String searchPhoneNumber) {
			Connection con = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MemberDTO> memberList = new ArrayList<>();
			//3.sql문 작성
			StringBuffer sb = new StringBuffer();
			sb.append("select * ");
			sb.append("from 회원 ");
			sb.append("where 이름 like ? and 전화번호 like ? ");
			try {
				//4.pstmt 생성
				pstmt = con.prepareStatement(sb.toString());
				pstmt.setString(1,"%"+searchName+"%");
				pstmt.setString(2, "%"+searchPhoneNumber+"%");
				
				//5. sql실행
				rs = pstmt.executeQuery();
				while(rs.next()){
					int memberNo = rs.getInt("회원번호");
					String name = rs.getString("이름");
					String address = rs.getString("주소");
					String phoneNumber = rs.getString("전화번호");
					String birthday = rs.getString("생년월일");
					String comment = rs.getString("비고");
					MemberDTO member = new MemberDTO(memberNo,name, address, phoneNumber, birthday, comment);
					memberList.add(member);
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
			return memberList;
		}
	public int updateMember(MemberDTO member){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		//3. sql작성
		StringBuffer sb = new StringBuffer();
		sb.append("update 회원 ");
		sb.append("set 이름=?,주소=?,전화번호=?,생년월일=?,비고=? ");
		sb.append("where 회원번호=?");
		
		//4.pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getPhoneNumber());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getComment());
			pstmt.setInt(6,member.getMemberNo() );
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
	public int selectMemberNo(){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int memberNo = 0;
		//3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select MAX(회원번호) ");
		sb.append("from 회원");
		
		//4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				memberNo = rs.getInt("MAX(회원번호)");
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류");
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
		return memberNo;
	}
	public int deleteMember(int memberNo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		//3. sql작성
		StringBuffer sb = new StringBuffer();
		sb.append("delete ");
		sb.append("from 회원 ");
		sb.append("where 회원번호=?");
		
		//4.pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, memberNo);
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
}
