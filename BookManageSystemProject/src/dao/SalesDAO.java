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
			//1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://70.12.109.108:3306/project";
//			String url = "jdbc:mysql://localhost:3306/project";
			String user = "root";
			String password = "sds902";
//			String password = "root";
			
			//2. Ŀ�ؼ� ����
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
		//3. sql�ۼ�
		sb.append("select �뿩����,ȸ��.�̸�,ȸ��.ȸ����ȣ,����.������,�뿩�ݾ� ");
		sb.append("from ȸ�� join ���� join �뿩 ");
		sb.append("where �뿩����>= ? and �뿩����<=? and ȸ��.ȸ����ȣ=�뿩.ȸ����ȣ and ����.������ȣ=�뿩.������ȣ order by �뿩���� ");
		
		// pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SalesDTO salesDTO = new SalesDTO();
				salesDTO.setSalesDate(rs.getString("�뿩����"));
				salesDTO.setName(rs.getString("�̸�"));
				salesDTO.setMemberNo(rs.getString("ȸ����ȣ"));
				salesDTO.setBookName(rs.getString("������"));
				salesDTO.setMoney(rs.getInt("�뿩�ݾ�"));
				
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
		//3. sql�ۼ�
		sb.append("select COUNT(distinct ȸ��.ȸ����ȣ) ");
		sb.append("from ȸ�� join ���� join �뿩 ");
		sb.append("where �뿩����>= ? and �뿩����<=? and ȸ��.ȸ����ȣ=�뿩.ȸ����ȣ and ����.������ȣ=�뿩.������ȣ ");
		
		// pstmt ����
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
		//3. sql�� �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select �뿩����,sum(�뿩�ݾ�) as �ϸ��� ");
		sb.append("from �뿩 ");
		sb.append("where �뿩����>=? and �뿩����<=? ");
		sb.append("group by �뿩���� ");
		sb.append("having sum(�뿩�ݾ�)>0");
		
		//4.sql�� ����
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
