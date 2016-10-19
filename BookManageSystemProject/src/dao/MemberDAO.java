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
			//1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Ŀ�ؼ� ����
			con = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL ����");
			e.printStackTrace();
		}
		return con;
	}
	public int insertMember(MemberDTO member){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		//3. sql�� �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ȸ��(�̸�,�ּ�,��ȭ��ȣ,�������,���) ");
		sb.append("values (?,?,?,?,?)");
		
		//4.pstmt ����
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
		//3. sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from ȸ��");
		
		//4. pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				int memberNo = rs.getInt("ȸ����ȣ");
				String name = rs.getString("�̸�");
				String address = rs.getString("�ּ�");
				String phoneNumber = rs.getString("��ȭ��ȣ");
				String birthday = rs.getString("�������");
				String comment = rs.getString("���");
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
	//��� Ű���� �˻�(ȸ����ȣ,�̸�,��ȭ��ȣ)
	public ArrayList<MemberDTO> searchKeywordMember(int searchMemberNo) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		//3.sql�� �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from ȸ�� ");
		sb.append("where ȸ����ȣ = ? ");
		System.out.println(sb.toString());
		System.out.println(searchMemberNo);
		try {
			//4.pstmt ����
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, searchMemberNo);
			
			//5. sql����
			rs = pstmt.executeQuery();
			while(rs.next()){
				int memberNo = rs.getInt("ȸ����ȣ");
				String name = rs.getString("�̸�");
				String address = rs.getString("�ּ�");
				String phoneNumber = rs.getString("��ȭ��ȣ");
				String birthday = rs.getString("�������");
				String comment = rs.getString("���");
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
	//��� Ű���� �˻�(�̸�,��ȭ��ȣ)
		public ArrayList<MemberDTO> searchKeywordMember(String searchName,String searchPhoneNumber) {
			Connection con = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<MemberDTO> memberList = new ArrayList<>();
			//3.sql�� �ۼ�
			StringBuffer sb = new StringBuffer();
			sb.append("select * ");
			sb.append("from ȸ�� ");
			sb.append("where �̸� like ? and ��ȭ��ȣ like ? ");
			try {
				//4.pstmt ����
				pstmt = con.prepareStatement(sb.toString());
				pstmt.setString(1,"%"+searchName+"%");
				pstmt.setString(2, "%"+searchPhoneNumber+"%");
				
				//5. sql����
				rs = pstmt.executeQuery();
				while(rs.next()){
					int memberNo = rs.getInt("ȸ����ȣ");
					String name = rs.getString("�̸�");
					String address = rs.getString("�ּ�");
					String phoneNumber = rs.getString("��ȭ��ȣ");
					String birthday = rs.getString("�������");
					String comment = rs.getString("���");
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
		
		//3. sql�ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("update ȸ�� ");
		sb.append("set �̸�=?,�ּ�=?,��ȭ��ȣ=?,�������=?,���=? ");
		sb.append("where ȸ����ȣ=?");
		
		//4.pstmt ����
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
		//3. sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select MAX(ȸ����ȣ) ");
		sb.append("from ȸ��");
		
		//4. pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				memberNo = rs.getInt("MAX(ȸ����ȣ)");
				
			}
		} catch (SQLException e) {
			System.out.println("SQL ����");
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
		
		//3. sql�ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("delete ");
		sb.append("from ȸ�� ");
		sb.append("where ȸ����ȣ=?");
		
		//4.pstmt ����
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
