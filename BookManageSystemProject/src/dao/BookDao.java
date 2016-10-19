package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BookDTO;
import dto.MemberDTO;

public class BookDao {
	private Connection con = null;

	private Connection getConnection() {
		String url = "jdbc:mysql://70.12.109.108:3306/project";
		String user = "root";
		String password = "sds902";
//		String password = "root";
		Connection con = null;
		try {
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Ŀ�ؼ� ����
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

	public void closeConnection() {
		try {
			// 7. Ŀ�ؼ� ����
			con.close();
			System.out.println("�����ͺ��̽� ������ ����Ǿ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertBook(BookDTO book) {
		con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		// 3. sql�� �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append(
				"insert into ����(������,����,�����,�о�,���) ");
		sb.append("values (?,?,?,?,?)");

		// 4.pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublicationDate());
			pstmt.setString(4, book.getType());
			pstmt.setString(5, book.getComment());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Dao insert ����");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public int updateBook(BookDTO book){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		//sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("update ����  ");
		sb.append("set ������ = ?,���� = ?,����� = ?,�о� = ?,�ݾ� = ?,��� = ? ");
		sb.append("where ������ȣ = ?");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublicationDate());
			pstmt.setString(4, book.getType());
			pstmt.setInt(5, book.getMoney());
			pstmt.setString(6, book.getComment());
			pstmt.setInt(7, book.getBookNumber());
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
	public int deleteBook(int bookNumer) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		//3. sql�ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ���� ");
		sb.append("where ������ȣ=?");
		
		//4.pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bookNumer);
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
	public ArrayList<BookDTO> searchBook() {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
		// 3. sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from ����");

		// 4. pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt("������ȣ");
				String title = rs.getString("������");
				String lending = rs.getString("�뿩����");
				int lendingNumber = rs.getInt("�뿩Ƚ��");
				String author = rs.getString("����");
				String publicationDate = rs.getString("�����");
				String type = rs.getString("�о�");
				int money = rs.getInt("�ݾ�");
				String comment = rs.getString("���");

				BookDTO book = new BookDTO(bookNumber, title, lending, lendingNumber, author, publicationDate,
						type, money, comment);
				bookList.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Dao select ����");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bookList;
	}
	public ArrayList<BookDTO> searchKeywordBook(String receiveTitle) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
		// 3. sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from ���� ");
		sb.append("where ������ like ?");

		// 4. pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+receiveTitle+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt("������ȣ");
				String title = rs.getString("������");
				String lending = rs.getString("�뿩����");
				int lendingNumber = rs.getInt("�뿩Ƚ��");
				String author = rs.getString("����");
				String publicationDate = rs.getString("�����");
				String type = rs.getString("�о�");
				int money = rs.getInt("�ݾ�");
				String comment = rs.getString("���");

				BookDTO book = new BookDTO(bookNumber, title, lending, lendingNumber, author, publicationDate,
						type, money, comment);
				bookList.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Dao select ����");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bookList;
	}
	public ArrayList<BookDTO> searchBook(String receiveAuthor) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
		// 3. sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from ���� ");
		sb.append("where ���� like ?");

		// 4. pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+receiveAuthor+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt("������ȣ");
				String title = rs.getString("������");
				String lending = rs.getString("�뿩����");
				int lendingNumber = rs.getInt("�뿩Ƚ��");
				String author = rs.getString("����");
				String publicationDate = rs.getString("�����");
				String type = rs.getString("�о�");
				int money = rs.getInt("�ݾ�");
				String comment = rs.getString("���");

				BookDTO book = new BookDTO(bookNumber, title, lending, lendingNumber, author, publicationDate,
						type, money, comment);
				bookList.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Dao select ����");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bookList;
	}
	public int selectBookNo(){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int bookNo = 0;
		//3. sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select MAX(������ȣ) ");
		sb.append("from ����");
		
		//4. pstmt ����
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				bookNo = rs.getInt("MAX(������ȣ)");
				
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
		return bookNo;
	}
	public BookDTO selectBook(int bookCode){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookDTO book = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from ���� ");
		sb.append("where ������ȣ=?");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bookCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				book = new BookDTO(rs.getInt("������ȣ"),rs.getInt("�ݾ�"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	public ResultSet selectcustomer(int book){
		Connection con = getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		StringBuffer s = new StringBuffer();
		s.append("select *");
		s.append("from customer ");
		s.append("where cus_id=?");
		
		try {
			prst = con.prepareStatement(s.toString());
			prst.setInt(1, book);
			rs = prst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	public BookDTO selectIsLend(int bookNo) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookDTO book = null;
		// sql �ۼ�
		StringBuffer sb = new StringBuffer();
		sb.append("select ����.������,����.�뿩���� ");
		sb.append("from ���� ");
		sb.append("where ������ȣ = ?");
		
		//pstmt����
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bookNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				book = new BookDTO(rs.getString("����.������"),rs.getString("����.�뿩����"));
			}
			
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
		return book;
	}
}
