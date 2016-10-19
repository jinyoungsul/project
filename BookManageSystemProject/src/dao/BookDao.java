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
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 커넥션 연결
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

	public void closeConnection() {
		try {
			// 7. 커넥션 종료
			con.close();
			System.out.println("데이터베이스 연결이 종료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertBook(BookDTO book) {
		con = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		// 3. sql문 작성
		StringBuffer sb = new StringBuffer();
		sb.append(
				"insert into 도서(도서명,저자,등록일,분야,비고) ");
		sb.append("values (?,?,?,?,?)");

		// 4.pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublicationDate());
			pstmt.setString(4, book.getType());
			pstmt.setString(5, book.getComment());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Dao insert 에러");
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
		
		//sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("update 도서  ");
		sb.append("set 도서명 = ?,저자 = ?,등록일 = ?,분야 = ?,금액 = ?,비고 = ? ");
		sb.append("where 도서번호 = ?");
		
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
		
		//3. sql작성
		StringBuffer sb = new StringBuffer();
		sb.append("delete from 도서 ");
		sb.append("where 도서번호=?");
		
		//4.pstmt 생성
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
		// 3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from 도서");

		// 4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt("도서번호");
				String title = rs.getString("도서명");
				String lending = rs.getString("대여여부");
				int lendingNumber = rs.getInt("대여횟수");
				String author = rs.getString("저자");
				String publicationDate = rs.getString("등록일");
				String type = rs.getString("분야");
				int money = rs.getInt("금액");
				String comment = rs.getString("비고");

				BookDTO book = new BookDTO(bookNumber, title, lending, lendingNumber, author, publicationDate,
						type, money, comment);
				bookList.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Dao select 에러");
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
		// 3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from 도서 ");
		sb.append("where 도서명 like ?");

		// 4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+receiveTitle+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt("도서번호");
				String title = rs.getString("도서명");
				String lending = rs.getString("대여여부");
				int lendingNumber = rs.getInt("대여횟수");
				String author = rs.getString("저자");
				String publicationDate = rs.getString("등록일");
				String type = rs.getString("분야");
				int money = rs.getInt("금액");
				String comment = rs.getString("비고");

				BookDTO book = new BookDTO(bookNumber, title, lending, lendingNumber, author, publicationDate,
						type, money, comment);
				bookList.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Dao select 에러");
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
		// 3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from 도서 ");
		sb.append("where 저자 like ?");

		// 4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+receiveAuthor+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookNumber = rs.getInt("도서번호");
				String title = rs.getString("도서명");
				String lending = rs.getString("대여여부");
				int lendingNumber = rs.getInt("대여횟수");
				String author = rs.getString("저자");
				String publicationDate = rs.getString("등록일");
				String type = rs.getString("분야");
				int money = rs.getInt("금액");
				String comment = rs.getString("비고");

				BookDTO book = new BookDTO(bookNumber, title, lending, lendingNumber, author, publicationDate,
						type, money, comment);
				bookList.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Dao select 에러");
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
		//3. sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select MAX(도서번호) ");
		sb.append("from 도서");
		
		//4. pstmt 생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				bookNo = rs.getInt("MAX(도서번호)");
				
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
		return bookNo;
	}
	public BookDTO selectBook(int bookCode){
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookDTO book = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from 도서 ");
		sb.append("where 도서번호=?");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bookCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				book = new BookDTO(rs.getInt("도서번호"),rs.getInt("금액"));
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
		// sql 작성
		StringBuffer sb = new StringBuffer();
		sb.append("select 도서.도서명,도서.대여여부 ");
		sb.append("from 도서 ");
		sb.append("where 도서번호 = ?");
		
		//pstmt생성
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bookNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				book = new BookDTO(rs.getString("도서.도서명"),rs.getString("도서.대여여부"));
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
