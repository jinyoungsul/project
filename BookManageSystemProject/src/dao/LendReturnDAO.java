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
		//3.sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("insert into �뿩(ȸ����ȣ,������ȣ,�뿩����,�ݳ�����,�뿩�ݾ�) ");
		sb.append("values (?,?,str_to_date(?,'%Y-%m-%d'),str_to_date(?,'%Y-%m-%d'),?) ");
		//4. pstmt ����
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
		//3. sql�� �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select �뿩��ȣ,����.������,����.����,�뿩����,�ݳ�����,��ü����,�뿩�ݾ� ");
		sb.append("from �뿩 join ȸ�� join ���� ");
		sb.append("where �뿩.ȸ����ȣ = ? and ����.������ȣ = �뿩.������ȣ and �뿩.ȸ����ȣ = ȸ��.ȸ����ȣ and �ݳ�����='�̹ݳ�'");
		
		//4.sql�� ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Object[] raw = {rs.getString("�뿩��ȣ"),rs.getString("����.������"),
						rs.getString("����.����"),rs.getString("�뿩����"),
						rs.getString("�ݳ�����"),rs.getString("��ü����"),rs.getString("�뿩�ݾ�")
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
		// sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("update �뿩 set �ݳ�����='�ݳ�' ");
		sb.append("where �뿩��ȣ = ?");
		
		//pstmt����
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
